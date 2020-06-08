import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Q5 implements Comparable<MyObject> {

	private int counter;
	private static ArrayList<MyGenericNode<String>> classes;
	private Scanner in;
	private Date date;
	private MyGenericNode<String> oneClass;
	private String thisFile = "Q5-1.csv";
	private Date datetime;

	public Q5(){
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
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		in = new Scanner(file);
		//		in.useDelimiter(",");
		while(in.hasNextLine()){
			StringTokenizer st = new StringTokenizer(in.nextLine());
			oneClass = new MyGenericNode<String>(st.nextToken(","));
			java.util.Date start = null;
			try {
				start = parser.parse(st.nextToken());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			oneClass.startTime = start;
			//			oneClass.startTime = st.nextToken();
			java.util.Date finish = null;
			try {
				finish = parser.parse(st.nextToken());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			oneClass.endTime = finish;
			//			oneClass.endTime = st.nextToken();
			classes.add(oneClass);
		}
	}

	public static ArrayList<MyGenericNode<String>> getClasses() {
		return classes;
	}

	public Date getStartTime(MyGenericNode<String> generic) {
		datetime = (Date) generic.startTime;
		return datetime;
	}

	public int compareTo(MyGenericNode<String> o, MyGenericNode<String> l) {
		return getStartTime(l).compareTo(getStartTime(o));
	}


	public static void main(String[] args){

		long startTime = System.currentTimeMillis();

		Q5 q = new Q5();
//		//		ArrayList<MyGenericNode<String>> classNodes = getClasses();
//		for(MyGenericNode<String> node : getClasses()){
//			System.out.println("Class: " + node.value + " , start time: " + node.startTime + " , end time: " + node.endTime);
//		}


		/*
		 * program stuff
		 * 
		 */



		long endTime   = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println("Running time: " + runningTime);



	}





}
