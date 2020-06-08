import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;

public class fileReader2 {

	private String file;
	private BufferedReader br;
	private Book current;
	private User user;
	HashMap <String, Book> bookList;
	HashMap <String, User> userList;

	/**
	 * The constructor for the class
	 * @param filename
	 */
	public fileReader2(String filename){
		file = filename;
		br = null;
		new Movie();
		user = new User();
		bookList = new HashMap<String, Book>();
		userList = new HashMap<String, User>();
	}

	/**
	 * The primary method of the class that reads the file and parses data
	 * @throws FileNotFoundException
	 */
	public void readFile() throws FileNotFoundException{

		try{
			File f = new File(file);
			br = new BufferedReader(new FileReader(f));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("Full line: " + line); //TESTING
				String[] split = line.split(";");
				String userID = split[0];				
				String bookID = split[1].replaceAll("\"", "").trim();
				String userRating = split[2].replaceAll("\"","").trim();
				//				System.out.println("User id: " + userID + " , book id: " + bookID + " , rating: "+ userRating);
				int counter = 0;

				//Step 1. check if book object already exists or not
				if(!bookList.containsKey(bookID)) { //if not in list of all books
					current = new Book(); //if doesn't exist, create new book object
					current.id = bookID; //change ID
					bookList.put(current.id, current); //store new book object in list of all books
				} else if (bookList.containsKey(bookID)){ //if book object is in list of all books
					current = bookList.get(bookID); //get existent Book object/set to current
				}

				//Step 2. check to see if user object already created
				if (!userList.containsKey(userID)) { //if user obj not in list of all users
					user = new User(); //create new user obj
					user.id = userID; // change id to current id
					user.ratedItems = new HashMap<>();
					user.indexLocation = counter; //add index value (for possible future implementation of matrix)
					userList.put(userID, user); //add user to list of all users
					counter++; //increase index counter
				} else if (userList.containsKey(userID)){
					user = userList.get(userID); //set current user to existent user
				}

				//Step 3. add current book/rating to user's list of rated books
				double parsedDouble = Double.parseDouble(userRating); //parse String rating of this book for this user
				if(current.id != null && parsedDouble != 0){
					user.ratedItems.put(current.id, parsedDouble); //add book id and rating in user's HM of rated books
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * a method that counts how many lines a file has, and thus how many objects we will be creating
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public int countLines() throws IOException{
		LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(file)));
		lnr.skip(Long.MAX_VALUE);
		System.out.println(lnr.getLineNumber() + 1);
		lnr.close();
		return lnr.getLineNumber()+1;

	}

	/**
	 * A getter method for the list of all movie objects
	 * @return a HM with string keys and Movie obj values
	 */
	public HashMap<String, Book> getBookList() {
		return bookList;
	}

	/**
	 * A setter method for the list of all movies
	 * @param movieList a HM with String keys and Movie object values
	 */
	public void setMovieList(HashMap<String, Book> bookList) {
		this.bookList = bookList;
	}

	/**
	 * A getter method for the list of all users
	 * @return a HM with String keys, and User object values
	 */
	public HashMap<String, User> getUserList() {
		return userList;
	}

	/**
	 * A setter method for the list of all users
	 * @param userList
	 */
	public void setUserList(HashMap<String, User> userList) {
		this.userList = userList;
	}



	//****************TESTING********************
	public static void main(String[] args) throws IOException{
		fileReader2 fr = new fileReader2("book_ratings_sample.txt");
		//		fr.countLines();
		fr.readFile();
		//		System.out.println(fr.getUserList().get("2").id);
	}
}
