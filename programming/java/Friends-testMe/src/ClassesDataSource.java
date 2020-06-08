
import java.util.List;

public interface ClassesDataSource {

	/*
	 * Returns a List of the names of the classes 
	 * that are being taken by the specified student.
	 */
	public List<String> getClasses(String studentName) ;

}
