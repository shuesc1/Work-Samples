
public class Q5part2 {

	//DP problem -- greedy approach won't necessarily produce best answer
	//rows- start time, column- finish time
	//Want to max priority value sum
	
	//Implementation not taking into account priority
	//	public static void DPScheduling(int classStart[], int classFinish[], int classes){
	//		int i, j;
	//		i = 0;
	//		System.out.print(i+" ");
	//		for (j = 1; j < classes; j++){
	//			if (classStart[j] >= classFinish[i])
	//			{
	//				System.out.print(j+" ");
	//				i = j;
	//			}
	//		}
	//	}

	public static int DPScheduling(int matrix[][]){
		matrix[0][0] = 2;
		matrix[1][0] = 5;
		matrix[2][0] = 1;
		matrix[3][0] = 3;
		matrix[4][0] = 2;

		matrix[0][0] = 2;
		matrix[0][1] = 1;
		matrix[0][2] = 5;
		matrix[0][3] = 3;
		matrix[0][4] = 2;

		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				//				matrix[i][j] = Math.min{matrix[i-1][j] + matrix[i][j], matrix[i][j-1] + matrix[i][j], matrix[i-1][j-1] + matrix[i][j]};
				int val1 = Math.min(matrix[i-1][j] + matrix[i][j], matrix[i][j-1] + matrix[i][j]);
				int val2 = Math.min(matrix[i][j-1] + matrix[i][j], matrix[i-1][j-1] + matrix[i][j]);
				int val3 = Math.min(matrix[i-1][j] + matrix[i][j], matrix[i-1][j-1] + matrix[i][j]);
				int valX = Math.min(val1, val2);
				matrix[i][j] = Math.min(val3, valX);
			}
		}
		return matrix[4][4];
	}

	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		Q5part2 q = new Q5part2();
		System.out.println("Classes: 0-CHEM 101, 1-ASR 211, 2-SOC 341, 3-CIS 110, 4-JOB 111");
		System.out.print("Following classes can be scheduled: \n");

		//		int start[] =  {490, 645, 660, 720, 1140};
		//		int finish[] =  {550, 765, 703, 780, 1284};
		//		int n = start.length;

		int[][] matrix = new int[5][5];
		int val = DPScheduling(matrix);
		System.out.println("Max priority value is: " + val);
		//		DPScheduling(start, finish, n);

		long endTime   = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println("Running time: " + runningTime);



	}

}
