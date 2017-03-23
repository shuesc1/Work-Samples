
public class Q5part2 {

	//DP problem -- greedy approach won't necessarily produce best answer
	//
	
	
	
	
	
	
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		Q5part2 q = new Q5part2();
		System.out.print("Following classes can be scheduled: \n");
		for(String string : getFinalSchedule()){
			System.out.print(string + ",");
		}

		long endTime   = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println("Running time: " + runningTime);



	}
	
}
