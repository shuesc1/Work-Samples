import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GeneralTesting {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub


		String child = null;
		String parent = null;
		//		File file = new File("Bold_Tree.txt");
		File file = new File("family_tree.txt");
		Scanner in = new Scanner(file);
		in.useDelimiter(",");
		int count = 0;


		//		StringTokenizer st = new StringTokenizer(in.nextLine());
		while(in.hasNextLine()){
			StringTokenizer st = new StringTokenizer(in.nextLine());
			
			while(st.hasMoreTokens()){
				parent = st.nextToken(",");
				System.out.println("Parent:" + parent);
//				if(st.nextToken(",") != null){
					child = st.nextToken();
					System.out.println("Child:" + child);
//				}
			}
			//				parent = in.next().trim();
			//				child = in.next().trim();
			//			System.out.println(parent + "," + child);
		}
		//		
		//		while(in.hasNext()){
		//				if(count % 2 == 0){
		//					parent = in.next().trim();
		//				} else if(count % 2 != 0){
		//					child = in.next().trim();
		//				}
		//				count++;
		//				System.out.println("Parent: " + parent + ", child: " + child);
		//		}
	}

}
