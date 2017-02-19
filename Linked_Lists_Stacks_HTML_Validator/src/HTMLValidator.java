import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;

public class HTMLValidator {

	DoublyLinkedList<String> openingTags = new DoublyLinkedList<>();
	MyStack<String> closingTags = new MyStack<>();
	Scanner in;

	public HTMLValidator(String filename) throws FileNotFoundException{
		File file = new File(filename);
		in = new Scanner(file);
	}

	public boolean isValid(){
		
		
		while(in.hasNext()){
			String tags = in.nextLine();
			Pattern p = Pattern.compile("<(.+)>");		
//			Pattern p = Pattern.compile("<...>");
//			Pattern p = Pattern.compile(">([\w\s]*)<");
//			Pattern p = Pattern.compile("<[a-zA-Z]>");
//			Pattern p = Pattern.compile("</?\w+\s+[\^>]*>");
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
//
//		DoublyLinkedNode<String> head = openingTags.getHead();
//
//		while(head.next != null){
//			if(head.value == closingTags.pop()){
//				head = head.next;
//			}
//		}
		return true;
	}

}
