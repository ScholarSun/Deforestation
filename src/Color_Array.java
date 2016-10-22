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
	

	public Color_Array() throws IOException
	{
		BufferedImage red = ImageIO.read(new File ("pics/Red0.jpg"));
		BufferedImage green = ImageIO.read(new File ("pics/Green0.jpg"));
		BufferedImage blue = ImageIO.read(new File ("pics/Blue0.jpg"));
		
		sizex = imgsize('x',red);
		sizey = imgsize('y',red);
		
		System.out.println(sizex);

		System.out.println(sizey);
		/*JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.getContentPane().add(new JLabel(new ImageIcon(img2)));
		frame.getContentPane().add(new JLabel(new ImageIcon(img3)));
		frame.pack();
		frame.setVisible(true);*/
		
		
		redcol = new int[sizey][sizex];
		greencol = new int[sizey][sizex];
		bluecol = new int[sizey][sizex];
		
		redcol = CreateArray(red);
		greencol = CreateArray(green);
		bluecol = CreateArray(blue);
		
		for (int i = 0; i < redcol.length; i++) {
		   System.out.println("");
			for (int j = 0; j < redcol[0].length; j++) {
		        System.out.print(redcol[i][j]);
		    }
		}
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
		
		for(int i = 0; i<temp.length-1; i++)
		{
			
			for (int j = 0; j<temp[0].length-1; j++)
			{
				/*int temp2 = pic.getRGB(i, j);
				temp[i][j] = temp2 & 0x000000ff;*/
				
				//System.out.println(j);
				
				Color mycolor = new Color(pic.getRGB(j, i));
				int red = mycolor.getRed();
				temp[i][j]=red;
				
				
				
				//Color c = new Color(pic.getRGB(j, i));
				
			}
		}
		return temp;
	}
}

