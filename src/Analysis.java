
public class Analysis{
	
public int[][] tree;

	public Analysis(int[][] sr, int[][] sg, int[][] sb) throws Exception
	{
		tree = new int[sr.length][sr[0].length];
		for (int i = 0; i<sr.length; i+=100)
		{
			for(int j = 0; j<sr[0].length;j+=100 )
			{
				if (sr[i][j]==0&&sg[i][j]==0&&sb[i][j]==0)
					tree[i][j] = 2;
				else if(sg[i][j]>100)
					tree[i][j] = 1;
				else 
					tree[i][j] = 0;
			}
		}
		
		for (int i = 0; i < tree.length; i+=100) {
			   System.out.println("");
				for (int j = 0; j < tree[0].length; j+=100) {
			        System.out.print(tree[i][j]);
			    }
			}
	}
	
}
