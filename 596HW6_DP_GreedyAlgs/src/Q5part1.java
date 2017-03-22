import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Q5part1 {

	private int counter;
	private static ArrayList<MyGenericNode<String>> classes;
	private Scanner in;
	private Date date;
	private MyGenericNode<String> oneClass;
	private String thisFile = "Q5-1.csv";

	public Q5part1(){
		classes = new ArrayList<MyGenericNode<String>>();
		try {
			readFile("Q5-1.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A method to read in a CSV file with course start and finish times
	 * @param filename
	 * @throws FileNotFoundException 
	 */
	public void readFile(String filename) throws FileNotFoundException{
		File file = new File(filename);
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		in = new Scanner(file);
		in.useDelimiter(",");
		
//		counter = 1;

		while(in.hasNextLine()){
			StringTokenizer st = new StringTokenizer(in.nextLine());
			//			date = new Date(st.nextToken());
			//			sdf.format(date);
			oneClass = new MyGenericNode<String>(st.nextToken(","));
//			oneClass.value = st.nextToken();
			oneClass.startTime = st.nextToken();
			oneClass.endTime = st.nextToken();
			classes.add(oneClass);
		}
	}
	
	public static ArrayList<MyGenericNode<String>> getClasses() {
		return classes;
	}


	public static void main(String[] args){

		long startTime = System.currentTimeMillis();

		Q5part1 q = new Q5part1();
//		ArrayList<MyGenericNode<String>> classNodes = getClasses();
		for(MyGenericNode<String> node : getClasses()){
			System.out.println("Class: " + node.value + " , start time: " + node.startTime + " , end time: " + node.endTime);
		}
		

		/*
		 * program stuff
		 * 
		 */



		long endTime   = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println("Running time: " + runningTime);



	}



}
