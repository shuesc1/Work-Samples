
import java.util.List;


public interface StudentsDataSource {

	/*
	 * Returns a List of students who are taking the specified class.
	 */
	public List<Student> getStudents(String className) ;
	

}
