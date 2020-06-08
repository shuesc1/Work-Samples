import java.util.Scanner;

public class RegexTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String string = "Hi, how are you's all doin's this day? And then I's not so happy about's Kevin's guitar";
		
		
		Scanner in = new Scanner(string);
		in.useDelimiter(" ");
		
		while (in.hasNext()){
		String chunk = in.next();
		String s = chunk.replace("'s", "");
		String newChunk = chunk.replace("'s", "").replaceAll("[^A-Za-z]", "").trim().toLowerCase();
		System.out.println(newChunk);
		}
		
		
	}

}
