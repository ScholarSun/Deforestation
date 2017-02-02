import java.awt.Color;
import java.awt.image.BufferedImage;
import java.lang.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//import com.sun.prism.paint.Color;

public class Color_Array {

	public static int sizex, sizey;
	public int[][] redcol;
	public int[][] greencol;
	public int[][] bluecol;

	public Color_Array() throws Exception {
		BufferedImage red = ImageIO.read(new File("pics/Red0.jpg"));
		BufferedImage green = ImageIO.read(new File("pics/Green0.jpg"));
		BufferedImage blue = ImageIO.read(new File("pics/Blue0.jpg"));

		sizex = imgsize('x', red);
		sizey = imgsize('y', red);

		redcol = new int[sizey][sizex];
		greencol = new int[sizey][sizex];
		bluecol = new int[sizey][sizex];

		redcol = CreateArray(red);
		greencol = CreateArray(green);
		bluecol = CreateArray(blue);

		for (int i = 0; i < redcol.length; i = i + 100) {
			System.out.println("");
			for (int j = 0; j < redcol[0].length; j = j + 100) {
				System.out.print(redcol[i][j]);
			}
		}
		Analysis a = new Analysis(redcol, greencol, bluecol);
		
	}

	public static int imgsize(char dir, BufferedImage pic) {
		if (dir == 'x')
			return pic.getWidth();
		else if (dir == 'y')
			return pic.getHeight();
		else
			return -1;
	}

	public static int[][] CreateArray(BufferedImage pic) {
		int[][] temp = new int[sizey][sizex];
		for (int i = 0; i < temp.length - 1; i += 100) {
			for (int j = 0; j < temp[0].length - 1; j += 100) {
				Color mycolor = new Color(pic.getRGB(j, i));
				int red = mycolor.getRed();
				temp[i][j] = red;
			}
		}
		return temp;
	}
}
