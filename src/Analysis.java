import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Analysis {

	public int[][] tree;

	public Analysis(int[][] sr, int[][] sg, int[][] sb) throws Exception {
		tree = new int[sr.length][sr[0].length];
		int[][] resized = new int[((sr.length)/100)+1][((sr[0].length)/100)+1];

		// Sifts Through
		for (int i = 0; i < sr.length; i += 100) {
			for (int j = 0; j < sr[0].length; j += 100) {
				if (sr[i][j] == 0 && sg[i][j] == 0 && sb[i][j] == 0)
					tree[i][j] = 2;
				else if (sg[i][j] > 100)
					tree[i][j] = 1;
				else
					tree[i][j] = 0;
			}
		}

		// Displays
		for (int i = 0; i < tree.length; i += 100) {
			System.out.println("");
			for (int j = 0; j < tree[0].length; j += 100) {
				System.out.print(tree[i][j]);
				resized[(i/100)][(j/100)] = tree[i][j];
			}
		}
		produceimg(resized);
	}

	protected void produceimg(int[][] bitmap) {
		BufferedImage imageOut = new BufferedImage(bitmap[0].length, bitmap.length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bitmap.length; i ++) {
			for (int j = 0; j < bitmap[0].length; j ++) {
				int r = 0;
				int g = 0;
				int b = 0;
				int col = (r << 16) | (g << 8) | b;
				imageOut.setRGB(j, i, col);
			}
		}
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(imageOut)));
		frame.pack();
		frame.setVisible(true);
	}
}
