

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;


public class FindClassmatesTest {

	private static final String NULL = null;

	//========================================================================================
	//<<<<<<<<<<<<< #1 - if initial Student object passed is null then throw exception >>>>>>>
	//========================================================================================
	@Test
	public void testFindClassmatesNullArg1() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				List listMyClasses = new List();
				listMyClasses.add("CIS 573");
				listMyClasses.add("STAT 571");
				listMyClasses.add("CIT 593") ;
				//						return (java.util.List<String>) new List();
				return (java.util.List<String>) listMyClasses;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				List listMyClassMates = new List();
				listMyClassMates.add("Alejandro");
				listMyClassMates.add(NULL); //***** HERE ******
				listMyClassMates.add("Xi") ;
				return (java.util.List<Student>) listMyClassMates;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ; // new class object with not arguments
		Student joey = null ; // ****** HERE *******
		try {
			ff.findClassmates(joey) ;
			fail("input is null, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}

	//========================================================================================
	//<<<<<<<<<<<<< #2 - if Student object's name is null then return null >>>>>>>
	//========================================================================================
	@Test
	public void testStudentNameNull2() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				List listMyClasses = new List();
				listMyClasses.add("CIS 573");
				listMyClasses.add("STAT 571");
				listMyClasses.add("CIT 593") ;
				//						return (java.util.List<String>) new List();
				return (java.util.List<String>) listMyClasses;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				List listMyClassMates = new List();
				listMyClassMates.add("Alejandro");
				listMyClassMates.add(NULL); //***** HERE ******
				listMyClassMates.add("Xi") ;
				return (java.util.List<Student>) listMyClassMates;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ; // new class object with not arguments
		Student joey = new Student(NULL, "001") ; //***** HERE ******
		assertEquals("if user name is null, method should return null", ff.findClassmates(joey), null) ;
	}

	//========================================================================================
	//<<<<<<<<<<<<< #3 - if classesDataSource.getClasses(name) is null then return null >>>>>>>
	//========================================================================================
	@Test
	public void testGetClassesRetNull3() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				return null ; //***** HERE ******
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				List listMyClassMates = new List();
				//						Student ale = new Student("Alejandro", "002") ;
				//						listMyClassMates.add(ale);
				listMyClassMates.add("Alejandro");
				//						Student kav = new Student("Kavinda", "003") ;
				//						listMyClassMates.add(kav);
				listMyClassMates.add("Kavinda");
				//						Student xi = new Student("Xi", "004") ;
				//						listMyClassMates.add(xi) ;
				listMyClassMates.add("Xi") ;
				return (java.util.List<Student>) listMyClassMates;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ; 
		Student joey = new Student("joey", "001") ;
		assertEquals("if my class list is null, method should return null", ff.findClassmates(joey), null) ;
	}

	//========================================================================================
	//<<<<<<<<<<<<< #4 - if studentsDataSource.getStudents(myClass) is null then return null>>>>>>>
	//========================================================================================
	@Test
	public void testGetStudentsRetNull4() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				ArrayList<String> listMyClasses = new ArrayList<String>();
				listMyClasses.add("CIS 573");
				listMyClasses.add("STAT 571");
				listMyClasses.add("CIT 593") ;
				return listMyClasses;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				List listMyClassMates = null; //***** HERE ******
				return (java.util.List<Student>) listMyClassMates;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ; 
		Student joey = new Student("Joey", "001") ;
		assertEquals("if my list of classmates is null, method should return null", ff.findClassmates(joey), null) ;
	}

	//========================================================================================
	//<<<<<<<<<<<<< #5 - if current Student obj in list of students is null go to next student>>>>>>>
	//========================================================================================
	@Test
	public void testNullClassmateObjInList5() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				ArrayList<String> listMyClasses = new ArrayList<String>();
				listMyClasses.add("CIS 573");
				listMyClasses.add("STAT 571"); 
				listMyClasses.add("CIT 593") ;
				//						return (java.util.List<String>) new List();
				return (java.util.List<String>) listMyClasses;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				ArrayList<Student> listMyClassMates = new ArrayList<Student>();
				Student ale = new Student("Alejandro", "002") ;
				listMyClassMates.add(ale);
				Student kav = null ;
				listMyClassMates.add(kav); //***** HERE ******
				Student xi = new Student("Xi", "004") ;
				listMyClassMates.add(xi) ;
				return (java.util.List<Student>) listMyClassMates;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ; // new class object with not arguments
		Student joey = new Student("Joey", "001") ;
		//expected output should be a list of Student objs Alejandro and Xi assuming they all have same classes
		ArrayList<Student> expectedOutput = new ArrayList<Student>() ;
		Student ale = new Student("Alejandro", "002") ;
		expectedOutput.add(ale);
		Student xi = new Student("Xi", "004") ;
		expectedOutput.add(xi) ;
		
		assertEquals("if one classmate's list of classes is null, method should skip over it and continue with other classmates", ff.findClassmates(joey), expectedOutput) ;
	}

	//========================================================================================
	//<<<<<<<<<<<<< #6 - if List of 'theirClasses' is null go to next student >>>>>>>
	//========================================================================================
	@Test
	public void testNullTheirClassesList6() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				ArrayList<String> listMyClasses = new ArrayList<String>();
				if(studentName.equalsIgnoreCase("joey")){ //*** HERE ****
					listMyClasses.add("CIS 573");      	 //
					listMyClasses.add("STAT 571");		//
					listMyClasses.add("CIT 593") ;		//
				} else {									//
					listMyClasses = null ;				//
				}										// *** THROUGH HERE ***
				return (java.util.List<String>) listMyClasses;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				ArrayList<Student> listMyClassMates = new ArrayList<Student>();
				Student ale = new Student("Alejandro", "002") ;
				listMyClassMates.add(ale);
				Student kav = new Student("Kavinda", "003") ;
				listMyClassMates.add(kav);
				Student xi = new Student("Xi", "004") ;
				listMyClassMates.add(xi) ;
				return (java.util.List<Student>) listMyClassMates;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ; // new class object with not arguments
		Student joey = new Student("Joey", "001") ;
		assertEquals("if classmate's list of classes is null, method should go on to next classmate", ff.findClassmates(joey), null) ;
	}

	//========================================================================================
	//<<<<<<<<<<<<< #7 - if 'class name' string is null go on to next class >>>>>>>
	//========================================================================================
	@Test
	public void testNullClassNameString7() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				ArrayList<String> listMyClasses = new ArrayList<String>();
				listMyClasses.add("CIS 573");
				listMyClasses.add(NULL); //***** HERE ******
				listMyClasses.add("CIT 593") ;
				//						return (java.util.List<String>) new List();
				return (java.util.List<String>) listMyClasses;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				ArrayList<Student> listMyClassMates = new ArrayList<Student>();
				Student ale = new Student("Alejandro", "002") ;
				listMyClassMates.add(ale);
				Student kav = new Student("Kavinda", "003") ;
				listMyClassMates.add(kav);
				Student xi = new Student("Xi", "004") ;
				listMyClassMates.add(xi) ;
				return (java.util.List<Student>) listMyClassMates;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ; // new class object with not arguments
		Student joey = new Student("Joey", "001") ;
		assertEquals("if class name string is null, method should go on to next class", ff.findClassmates(joey), null) ;
	}


	//========================================================================================
	//<<<<<<<<<<<<< #8 - if HashSet of classmates not initialized then throw illegal state >>>>>>>
	//========================================================================================
	@Test
	public void testNonInitializedHashsetOfClassmates8() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				ArrayList<String> listMyClasses = new ArrayList<String>();
				listMyClasses.add("CIS 573");
				listMyClasses.add("STAT 571");
				listMyClasses.add("CIT 593") ;
				//						return (java.util.List<String>) new List();
				return (java.util.List<String>) listMyClasses;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				ArrayList<Student> listMyClassMates = new ArrayList<Student>();
				Student ale = new Student("Alejandro", "002") ;
				listMyClassMates.add(ale);
				Student kav = new Student("Kavinda", "003") ;
				listMyClassMates.add(kav);
				Student xi = new Student("Xi", "004") ;
				listMyClassMates.add(xi) ;
				return (java.util.List<Student>) listMyClassMates;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ; // new class object with not arguments
		Student joey = new Student("Joey", "001") ;
		try {
			ff.findClassmates(joey) ;
			fail("Hashset of classmates not initialized, should throw IllegalStateException") ;
		} catch(IllegalStateException ise) {
		}
	}


}
