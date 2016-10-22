import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Data_Processing {

	public static void main(String[] args) throws Exception {

		Data_Processing http = new Data_Processing();

		System.out.println("Testing 1 - Send Http GET request");
		String resp = http.sendGet();
		String [] blo = extract(resp);
		
	}
	
	public void downloadSat(String saturl) throws Exception{
		URL website = new URL("https://s3-us-west-2.amazonaws.com/landsat-pds/L8/233/087/LC82330872016239LGN00/LC82330872016239LGN00_B11.TIF");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("information.TIF");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	}
	
	public static String[] extract(String responsesat){
		ArrayList<Integer> indexes = new ArrayList();
		while(1<3){
		int miniind = responsesat.indexOf("band");
		if(miniind == -1){
			break;
		}
		
		responsesat = responsesat.substring(miniind,responsesat.length());
		
		}
	}
	
	// HTTP GET request
	private String sendGet() throws Exception {

		String url = "https://api.skywatch.co/data/time/2016-08-25T13:00:00.0000+0000/location/-71.1043443253471,-42.3150676015829,71.1043443253471,42.3150676015829/source/LANDSAT-8/3000/blue";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("x-api-key", "htp4p898jb3VJrqrgv0DY1PDbCOPpv66G8gNjUJg");
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		String answer = response.toString();
		return answer;
	}
}