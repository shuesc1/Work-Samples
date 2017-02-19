import java.io.FileNotFoundException;

public class HTMLValidatorTestRunner {

	public static void main(String[] args) throws FileNotFoundException {

		HTMLValidator val = new HTMLValidator("html1.html");
		val.isValid();
	}

}
