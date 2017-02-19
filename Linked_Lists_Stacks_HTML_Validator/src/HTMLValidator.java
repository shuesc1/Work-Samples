import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HTMLValidator {

	DoublyLinkedList<String> openingTags = new DoublyLinkedList<>();
	MyStack<String> closingTags = new MyStack<>();
	String filename;
	Scanner in;
	
	public HTMLValidator() throws FileNotFoundException{
		File file = new File(filename);
		in = new Scanner(file);
	}
	
	
	
}
