import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Color_Array {

	public static int sizex, sizey;
	public int[][] redcol;
	public int[][] greencol;
	public int[][] bluecol;
	

	public Color_Array() throws IOException
	{
		BufferedImage red = ImageIO.read(new File ("pic/red.tif"));
		BufferedImage green = ImageIO.read(new File ("pic/green.tif"));
		BufferedImage blue = ImageIO.read(new File ("pic/blue.tif"));
		
		sizex = imgsize('x',red);
		sizey = imgsize('y',red);
		
		redcol = new int[sizey][sizex];
		greencol = new int[sizey][sizex];
		bluecol = new int[sizey][sizex];
		
		redcol = CreateArray(red);
		greencol = CreateArray(green);
		bluecol = CreateArray(blue);
		
		
	}

	public static int imgsize (char dir, BufferedImage pic)
	{
		if (dir == 'x')
			return pic.getWidth();
		else if (dir == 'y')	
			return pic.getHeight();
		else
			return -1;	
	}
	
	public static int[][] CreateArray (BufferedImage pic)
	{
		int[][] temp = new int [sizey][sizex];
		
		for(int i = 0; i<temp.length; i++)
		{
			for (int j = 0; j<temp[0].length; j++)
			{
				int temp2 = pic.getRGB(i, j);
				temp[i][j] = temp2 & 0x000000ff;
			}
		}
		return temp;
	}

	
}

