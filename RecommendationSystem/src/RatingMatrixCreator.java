import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class RatingMatrixCreator {

//	private Movie[][] matrix;
	private int rows;
	private int cols;
	private Scanner in;
	private Random generator;
	private ArrayList<Movie> allMovies;
	private Movie current, movies[][];
	private Queue<Movie> q;
	
	
	/**
	 * The constructor for the class
	 * @param rows
	 * @param columns
	 */
	public RatingMatrixCreator(int rows, int columns){
		this.rows = rows;
		cols = columns;
		generator = new Random();
		current = new Movie();
		allMovies = new ArrayList<Movie>();

		// Create the matrix.     
		movies = new Movie[rows][cols];
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				movies[i][j] = new Movie();
			}
		}
	}
	
	
	
	
	/**
	 *  Accessor method that returns the number of rows in the matrix.
	 *  @return The number of rows in the matrix.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 *  Accessor that returns the number of columns in the matrix.
	 *  @return The number of columns in the matrix.
	 */
	public int getCols() {
		return cols;
	}

	/**
	 *  Returns the cell in the maze at the given coordinates.
	 *  @param row  The row in the maze of the cell.
	 *  @param col  The column in the maze of the cell.
	 *  @return  The cell at (<code>row</code>, <code>col</code>)
	 */
	public Movie getMovie(int row, int col) {
		Movie target = new Movie();
		try{
			target = movies[row][col];
		} catch (IndexOutOfBoundsException ioobe){
			System.out.println("Indices provided are outside of matrix!");
		}
		return target;
	}

	
	
}
