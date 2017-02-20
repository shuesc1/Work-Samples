import java.io.FileNotFoundException;

public class HTMLValidatorTestRunner {

	public static void main(String[] args) throws FileNotFoundException {

		HTMLValidator val = new HTMLValidator("html1.html");
		HTMLValidator val2 = new HTMLValidator("html2.html");
		HTMLValidator val3 = new HTMLValidator("html3.html");
		HTMLValidator val4 = new HTMLValidator("html4.html");
		HTMLValidator val5 = new HTMLValidator("html5_INVALID.html");
//		val.isValid();
		System.out.println("HTML 1 file valid?: " + val.isValid());
		System.out.println("HTML 2 file valid?: " + val.isValid());
		System.out.println("HTML 3 file valid?: " + val.isValid());
		System.out.println("HTML 4 file valid?: " + val.isValid());
		System.out.println("HTML 5 file valid?: " + val.isValid());
		
	}

}
