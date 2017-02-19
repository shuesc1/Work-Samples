import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;
/**
 * A class that reads in a .html file and assesses if it is formatted correctly or not
 * @author josephhaymaker
 *
 */
public class HTMLValidator {

	DoublyLinkedList<String> openingTags = new DoublyLinkedList<>();
	MyStack<String> closingTags = new MyStack<>();
	Scanner in;
	boolean outcome;

	/**
	 * The constructor for the class
	 * takes in a filename and initializes scanner
	 * @param filename a string designating the .html to analyze
	 * @throws FileNotFoundException
	 */
	public HTMLValidator(String filename) throws FileNotFoundException{
		File file = new File(filename);
		in = new Scanner(file);
	}

	/**
	 * A method that tests whether a .html file is valid or not
	 * @return a boolean if it is a valid html file
	 */
	public boolean isValid(){

		while(in.hasNext()){
			String tags = in.nextLine();
			Pattern p = Pattern.compile("<(.+)>");		

			java.util.regex.Matcher m = p.matcher(tags);
			while(m.find()) {
				if(!m.group(1).contains("<") || !m.group(1).contains(">")){ //if string is html tag
					if(m.group(1).contains("/")){
						String closingTag = m.group(1);
						closingTags.push(closingTag);
					} else {
						String openingTag = m.group(1);
						openingTags.add(openingTag);
					}
					//				System.out.println("found: " + m.group(1));
				}
			}
		}

		DoublyLinkedNode<String> head = openingTags.getHead();

		if(openingTags.size() == closingTags.size()){
			outcome = true;
		} else {
			outcome = false;
		}
//		while(head.next != null){ 
//			if(head.value == closingTags.pop()){
//				head = head.next;
//				outcome = true;
//			} else {
//				outcome = false;
//			}
//		}
		return outcome;
	}
}
