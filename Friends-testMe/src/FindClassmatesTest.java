

import static org.junit.Assert.*;

import java.awt.List;

import org.junit.Test;


public class FindClassmatesTest {

	private static final String NULL = null;

	@Test
	public void testFindClassmatesNullArg1() {
		FriendFinder ff = new FriendFinder() {
			public ClassesDataSource createClassesDataSource() {
				return new ClassesDataSource(){
					public java.util.List<String> getClasses(String studentName){
						List listMyClasses = new List();
						listMyClasses.add("CIS 573");
						listMyClasses.add("STAT 571");
						listMyClasses.add("CIT 593") ;
						//						return (java.util.List<String>) new List();
						return (java.util.List<String>) listMyClasses;
					}
				} ;
			}
			public StudentsDataSource createStudentsDataSource() {
				return new StudentsDataSource(){
					public java.util.List<Student> getStudents(String myClass){
						return (java.util.List<Student>) new List();
					}
				} ;
			}
		} ;
		//		Student joey = new Student("Joey", "001") ;
		Student joey = null ;
		//		ff.addClassesDataSource(cds);
		//		ff.addStudentsDataSource(sds);
		try {
			ff.findClassmates(joey) ;
			fail("input is null, should throw IllegalArgmuentException") ;
		} catch(IllegalArgumentException iae) {
		}
	}

	@Test
	public void testStudentNameNull2() {
		FriendFinder ff = new FriendFinder() {
			public ClassesDataSource createClassesDataSource() {
				return new ClassesDataSource(){
					public java.util.List<String> getClasses(String studentName){
						List listMyClasses = new List();
						listMyClasses.add("CIS 573");
						listMyClasses.add("STAT 571");
						listMyClasses.add("CIT 593") ;
						//						return (java.util.List<String>) new List();
						return (java.util.List<String>) listMyClasses;
					}
				} ;
			}
			public StudentsDataSource createStudentsDataSource() {
				return new StudentsDataSource(){
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
//						return (java.util.List<Student>) new List();
					}
				} ;
			}
		} ;
		Student joey = new Student(NULL, "001") ;
		//		Student joey = null ;
		//		ff.addClassesDataSource(cds);
		//		ff.addStudentsDataSource(sds);
//		ff.findClassmates(joey) ;
//		assertEquals("student name missing", joey.getName()) ;
		assertEquals("if user name is null, method should return null", ff.findClassmates(joey), null) ;
	}

	@Test
	public void testGetClassesRetNull3() {
		FriendFinder ff = new FriendFinder() {
			public ClassesDataSource createClassesDataSource() {
				return new ClassesDataSource(){
					public java.util.List<String> getClasses(String studentName){
						return null;
					}
				} ;
			}
			public StudentsDataSource createStudentsDataSource() {
				return new StudentsDataSource(){
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
//						return (java.util.List<Student>) new List();
					}
				} ;
			}
		} ;
		Student joey = new Student(NULL, "001") ;
		assertEquals("if my class list is null, method should return null", ff.findClassmates(joey), null) ;
	}
	
	@Test
	public void testGetStudentsRetNull4() {
		FriendFinder ff = new FriendFinder() {
			public ClassesDataSource createClassesDataSource() {
				return new ClassesDataSource(){
					public java.util.List<String> getClasses(String studentName){
						List listMyClasses = new List();
						listMyClasses.add("CIS 573");
						listMyClasses.add("STAT 571");
						listMyClasses.add("CIT 593") ;
						//						return (java.util.List<String>) new List();
						return (java.util.List<String>) listMyClasses;
					}
				} ;
			}
//			public StudentsDataSource createStudentsDataSource() {
//				return new StudentsDataSource(){
//					public java.util.List<Student> getStudents(String myClass){
//						return null;
//					}
//				} ;
//			}
		} ;
		Student joey = new Student("Joey", "001") ;
		assertEquals("if my list of classmates is null, method should return null", ff.findClassmates(joey), null) ;
	}
	
	@Test
	public void testNullClassmateObjInList5() {
		FriendFinder ff = new FriendFinder() {
			public ClassesDataSource createClassesDataSource() {
				return new ClassesDataSource(){
					public java.util.List<String> getClasses(String studentName){
						List listMyClasses = new List();
						listMyClasses.add("CIS 573");
						listMyClasses.add("STAT 571");
						listMyClasses.add("CIT 593") ;
						//						return (java.util.List<String>) new List();
						return (java.util.List<String>) listMyClasses;
					}
				} ;
			}
			public StudentsDataSource createStudentsDataSource() {
				return new StudentsDataSource(){
					public java.util.List<Student> getStudents(String myClass){
						List listMyClassMates = new List();
//						Student ale = new Student("Alejandro", "002") ;
//						listMyClassMates.add(ale);
						listMyClassMates.add("Alejandro");
//						Student kav = new Student("Kavinda", "003") ;
//						listMyClassMates.add(kav);
						listMyClassMates.add(NULL);
//						Student xi = new Student("Xi", "004") ;
//						listMyClassMates.add(xi) ;
						listMyClassMates.add("Xi") ;
						return (java.util.List<Student>) listMyClassMates;
//						return (java.util.List<Student>) new List();
					}
				} ;
			}
		} ;
		Student joey = new Student("Joey", "001") ;
		assertEquals("if my list of classmates is null, method should return null", ff.findClassmates(joey), null) ;
	}


}
