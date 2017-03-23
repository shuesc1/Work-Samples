import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Q5part1 {
	//	private static List<MyGenericNode<String>> classes;
	private static ArrayList<MyGenericNode<String>> classes;
	private Scanner in;
	private MyGenericNode<String> oneClass;
	private String thisFile = "Q5-1.csv";
	private static ArrayList<Date> greedyTimes;
	private static ArrayList<String> finalSchedule = new ArrayList<String>();
	private ArrayList<Date> startTimes = new ArrayList<Date>();

	public Q5part1(){
		classes = new ArrayList<MyGenericNode<String>>();
		try {
			readFile(thisFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		greedyClassSelection(getStartTimeArray(), getEndTimeArray());
		finalClassSchedule();
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
		while(in.hasNextLine()){
			StringTokenizer st = new StringTokenizer(in.nextLine());
			oneClass = new MyGenericNode<String>(st.nextToken(","));
			Date start = null;
			try {
				start = new Date(parser.parse(st.nextToken()).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			oneClass.startTime = start;
			startTimes.add(oneClass.startTime);
			Date finish = null;
			try {
				finish = new Date(parser.parse(st.nextToken()).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			oneClass.endTime = finish;
			classes.add(oneClass);
		}
	}

	public static Date getStartTime(MyGenericNode<String> generic) {
		Date datetime = (Date) generic.startTime;
		return datetime;
	}

	public static ArrayList<MyGenericNode<String>> getClasses() {
		return classes;
	}

	public static void greedyClassSelection(ArrayList<Date> startTime, ArrayList<Date> finishTime){
		int i = 0, j;
		greedyTimes = new ArrayList<Date>();
		for (j = 1; j < startTime.size(); j++){
			if (startTime.get(j).after(finishTime.get(i)) == true){
				//				System.out.print(j+" ");
				greedyTimes.add(startTime.get(j));
				i = j;
			}
		}
	}

	public void finalClassSchedule(){
		for(Date date : greedyTimes){
			for(MyGenericNode<String> node : classes){
				if(node.startTime.equals(date)){
					finalSchedule.add(node.value);
				}
			}
		}
		//		return finalSchedule;
	}

	public ArrayList<Date> getStartTimeArray(){
		ArrayList<Date> startTimes = new ArrayList<Date>();
		for(MyGenericNode<String> node : classes){
			startTimes.add(node.startTime);
		}
		return startTimes;
	}

	public ArrayList<Date> getEndTimeArray(){
		ArrayList<Date> endTimes = new ArrayList<Date>();
		for(MyGenericNode<String> node : classes){
			endTimes.add((Date) node.endTime);
		}
		return endTimes;
	}

	public static ArrayList<Date> getGreedyTimes() {
		return greedyTimes;
	}

	public static ArrayList<String> getFinalSchedule() {
		return finalSchedule;
	}

	
	//Greed works here--series of locally optimal choices for scheduling will produce globally optimal solution
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		Q5part1 q = new Q5part1();
		//		ArrayList<MyGenericNode<String>> list = q.getClasses();
		System.out.print("Following classes can be scheduled: \n");
		for(String string : getFinalSchedule()){
			System.out.print(string + ",");
		}

		long endTime   = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println("Running time: " + runningTime);



	}





}
