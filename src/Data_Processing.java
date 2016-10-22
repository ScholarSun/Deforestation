import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

public class Data_Processing {

	public static void main(String[] args) throws Exception {

		Data_Processing http = new Data_Processing();

		System.out.println("Testing 1 - Send Http GET request");
		String resp = http.sendGet();
		ArrayList<String> hey = extract(resp);
		downloadSat(hey);

	}

	public static void downloadSat(ArrayList<String> bob) throws Exception {
		int red = 0;
		int blue = 0;
		int green = 0;
		for (int x = 0; x < bob.size(); x++) {
			if (bob.get(x).contains("Red")) {
				URL website = new URL(bob.get(x - 1));
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream("pics/Red" + red + ".TIF");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				red = red + 1;
			}
			if (bob.get(x).contains("Blue")) {
				URL website = new URL(bob.get(x - 1));
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream("pics/Blue" + blue + ".TIF");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				blue = blue + 1;
			}
			if (bob.get(x).contains("Green")) {
				URL website = new URL(bob.get(x - 1));
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream("pics/Green" + green + ".TIF");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				green = green + 1;
			}
		}
	}

	public static ArrayList<String> extract(String responsesat) {
		ArrayList<String> bandsndown = new ArrayList();
		// ArrayList<String> download = new ArrayList();
		responsesat.indexOf("bands");
		responsesat.indexOf("download_path");
		List<String> items = Arrays.asList(responsesat.split(","));

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).contains("band")) {
				String specband = items.get(i).substring(8, items.get(i).length());
				bandsndown.add(specband);
			}
			if (items.get(i).contains("download_path")) {
				String lol = items.get(i).substring(17, items.get(i).length());
				bandsndown.add(lol);
			}
		}

		for (int i = 0; i < bandsndown.size(); i++) {
			if (!(bandsndown.get(i).contains("https://"))) {
				if (!((bandsndown.get(i).contains("Blue") || bandsndown.get(i).contains("Green")
						|| bandsndown.get(i).contains("Red")))) {
					// System.out.println(bandsndown.get(i));
					bandsndown.remove(i);
					bandsndown.remove(i - 1);
					i = i - 2;
				}
			}
		}

		for (int x = 0; x<bandsndown.size();x++) {
			//System.out.println(bandsndown.get(x));
			if(bandsndown.get(x).contains("https")){
				String alter = bandsndown.get(x);
				alter = alter.trim();
				alter = alter.substring(1, alter.length()-1);
				bandsndown.set(x, alter);
			}
		}
		
		ArrayList<String>dupe = new ArrayList<String>();
		int y=0;
		for(int x =0;x<bandsndown.size();x++){
			if(!(dupe.contains(bandsndown.get(x)))){
			dupe.add(y,bandsndown.get(x));
			y++;
			}
		}
		bandsndown=dupe;
		for(String d:bandsndown){
			//System.out.println(d);
		}
		return bandsndown;
	}

	// HTTP GET request
	private String sendGet() throws Exception {

		String url = "https://api.skywatch.co/data/time/2016-08-25T13:00:00.0000+0000/location/-7.16245,-61.5836,28.83755,-33.5836/source/LANDSAT-8/3000/";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("x-api-key", "htp4p898jb3VJrqrgv0DY1PDbCOPpv66G8gNjUJg");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		// System.out.println(response.toString());

		String answer = response.toString();
		return answer;
	}
}