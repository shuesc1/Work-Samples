import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "Q5-1.csv";
		File file = new File(filename);
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Scanner in = new Scanner(file);
		in.useDelimiter(",");
//		counter = 1;
		MyGenericNode<String> oneClass;
		
		while(in.hasNextLine()){
			StringTokenizer st = new StringTokenizer(in.nextLine());
			//			date = new Date(st.nextToken());
			//			sdf.format(date);
			oneClass.value = st.nextToken();
			oneClass.startTime = st.nextToken();
			oneClass.endTime = st.nextToken();
			classes.add(oneClass);
		
	}

}
