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
				//				System.out.println(line);
				String[] split = line.split("::");
				String userID = split[0];
				String movieID = split[1];
				String userRating = split[2];
				int counter = 0;

				//step 1. check if movie object already exists or not
				if(!movieList.containsKey(movieID)) {
					current = new Movie(); //if doesn't exist, create new movie object
					current.id = movieID;
					movieList.put(current.id, current); //store new movie object in list of all movies
				} else if (movieList.containsKey(movieID)){
					current = movieList.get(movieID); //get existant Movie object
				}

				//step 2. check to see if user object already created
				if (!userList.containsKey(userID)) {
					user = new User();
					user.id = userID;
					user.indexLocation = counter;
					userList.put(userID, user);
				} else if (userList.containsKey(userID)){
					user = userList.get(userID);
				}

				//step 3. add current movie/rating to user's list of rated movies
				double parsedDouble = Double.parseDouble(userRating);
				user.ratedMovies.put(current.id, parsedDouble);

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
				counter++;
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



	//****************TESTING********************
	public static void main(String[] args) throws IOException{
		fileReader fr = new fileReader("rating_condensed.txt");
		fr.countLines();
		//		fr.readFile();
	}
}
