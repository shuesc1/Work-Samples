import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Scanner;

public class fileReader {

	private String file;
	private BufferedReader br;
	private RatingMatrixCreator matrix;
	private Movie movie, current;
	private User user;
	HashMap <String, Movie> movieList;
	HashMap <String, User> userList;

	/**
	 * The constructor for the class
	 * @param filename
	 */
	public fileReader(String filename){
		file = filename;
		br = null;
		movie = new Movie();
		user = new User();
		movieList = new HashMap<String, Movie>();
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
				//				System.out.println(line); //TESTING
				String[] split = line.split("::");
				String userID = split[0];
				String movieID = split[1];
				String userRating = split[2];
				int counter = 0;

				//Step 1. check if movie object already exists or not
				if(!movieList.containsKey(movieID)) { //if not in list of all movies
					current = new Movie(); //if doesn't exist, create new movie object
					current.id = movieID; //change ID
					movieList.put(current.id, current); //store new movie object in list of all movies
				} else if (movieList.containsKey(movieID)){ //if movie object is in list of all movies
					current = movieList.get(movieID); //get existent Movie object/set to current
				}

				//Step 2. check to see if user object already created
				if (!userList.containsKey(userID)) { //if user obj not in list of all users
					user = new User(); //create new user obj
					user.id = userID; // change id to current id
					user.ratedMovies = new HashMap<>();
					user.indexLocation = counter; //add index value (for possible future implementation of matrix)
					userList.put(userID, user); //add user to list of all users
					counter++; //increase index counter
				} else if (userList.containsKey(userID)){
					user = userList.get(userID); //set current user to existent user
				}

				//Step 3. add current movie/rating to user's list of rated movies
				double parsedDouble = Double.parseDouble(userRating); //parse String rating of this movie for this user
				if(current.id != null && parsedDouble != 0){
					user.ratedMovies.put(current.id, parsedDouble); //add movie id and rating in user's HM of rated movies
				}



				/* <<<<<<TESTING>>>>>>>>>		
				for(String element : split){
					String header = "";
					if(counter == 0){
						header = "User id: ";
					} else if (counter == 1){
						header = "Movie id: ";
					} else if (counter == 2){
						header = "User rating: ";
					} else if (counter == 3) {
						header = "Timestamp: ";
					}
					System.out.println(header + "" + element);
				 */
			}

			//			}
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
	public HashMap<String, Movie> getMovieList() {
		return movieList;
	}

	/**
	 * A setter method for the list of all movies
	 * @param movieList a HM with String keys and Movie object values
	 */
	public void setMovieList(HashMap<String, Movie> movieList) {
		this.movieList = movieList;
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
		fileReader fr = new fileReader("rating_condensed.txt");
		fr.countLines();
		//		fr.readFile();
	}
}
