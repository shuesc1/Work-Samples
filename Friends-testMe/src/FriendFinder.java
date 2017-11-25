
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class FriendFinder {
	
	protected ClassesDataSource classesDataSource;
	protected StudentsDataSource studentsDataSource;
	
	public FriendFinder(ClassesDataSource cds, StudentsDataSource sds) {
		classesDataSource = cds;
		studentsDataSource = sds;
	}
	
	public FriendFinder() {
		// TODO Auto-generated constructor stub
	}

	public void addClassesDataSource(ClassesDataSource cds) {
		classesDataSource = cds ;
	}
	
	public void addStudentsDataSource(StudentsDataSource sds) {
		studentsDataSource = sds ;
	}
	
	/*
	 * This method takes a String representing the name of a student 
	 * and then returns a list containing the names of everyone else 
	 * who is taking the same classes as that student.
	 * The ordering of the elements in the list is non-deterministic. 
	 */
	public Set<String> findClassmates(Student theStudent) {
		//notify caller if there is a null value passed
		
		// #1 if initial Student object passed is null then throw exception
		if (theStudent == null) {
			throw new IllegalArgumentException("Student object is null");
		}
		String name = "";
		
		// #2
		if(theStudent.getName() == null) {
			return null ;
//			name = "student name missing"; //if it's missing just let caller know
//			System.out.println("Current student's 'name' value is missing") ;
		} else {
			name = theStudent.getName() ;
		}
		
		// find the classes that this student is taking
		// #3
		if(classesDataSource.getClasses(name) == null) { return null; }
		List<String> myClasses = classesDataSource.getClasses(name);
		
		// use the classes to find the names of the students
		Set<String> classmates = new HashSet<String>();
		
		for (String myClass : myClasses) {
			// list all the students in the class
			
			//# 4 if database returns a null List of student objects return null
			if(studentsDataSource.getStudents(myClass) == null) { return null;}
			List<Student> students = studentsDataSource.getStudents(myClass) ;
			for (Student student : students) {
				
				// #5 if current Student obj in list of students is null go to next student
				if(student == null) { continue; }
				// find the other classes that they're taking
				List<String> theirClasses = classesDataSource.getClasses(student.getName());
				
				// #6 if List of 'theirClasses' is null go to next student
				if(theirClasses == null) { continue ;}
				// see if all of the classes that they're taking are the same as the ones this student is taking
				boolean same = true;
				for (String c : myClasses) {
					
					// #7 if 'class name' string is null go on to next class
					if(c == null) { continue ; }
					if (theirClasses.contains(c) == false) {
						same = false;
						break;
					}
				}
				if (same) {
					
					// #8 if HashSet of classmates not initialized then throw illegal state -- should have been done above
					if(classmates == null) {throw new IllegalStateException("classmates HashSet not initialized") ; }
					if (student.getName().equals(name) == false && classmates.contains(student.getName()) == false) 
						classmates.add(student.getName());
				}
			}

		}
		
		// #9 just in case -if HashSet of classmates not initialized then throw illegal state -- should have been done above
		if(classmates == null) {throw new IllegalStateException("classmates HashSet not initialized") ; }
		if (classmates.isEmpty()) return null;
		else return classmates;
	}
	

}
