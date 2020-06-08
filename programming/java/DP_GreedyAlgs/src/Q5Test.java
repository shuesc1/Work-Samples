
public class Q5Test {

	// Prints a maximum set of classes that can be scheduled in a 24 hour period
	
	//sort by end time?
	
	//  n   -->  Total number of activities
	//  s[] -->  An array that contains start time of all activities
	//  f[] -->  An array that contains finish time of all activities
	public static void greedyClassSelection(Date startTime[], Date finishTime[])
	{
		int i, j;
		System.out.print("Following classes can be scheduled: \n");
		for (j = 1; j < n; j++){
			// if start time same or after end time
			if (s[j] >= f[i])
			{
				System.out.print(j+" ");
				i = j;
			}
		}
	}

	// driver program to test above function
	public static void main(String[] args){
		int s[] =  {1, 3, 0, 5, 8, 5};
		int f[] =  {2, 4, 6, 7, 9, 9};
		int n = s.length;

		greedyClassSelection(s, f);
	}
	
}
