//package maze;

import java.util.ArrayList;
import java.util.Random;

/**
 *  The <code>MazeCell</code> class stores information about each individual cell
 *  in the maze.  The boolean values <code>north</code>, <code>east</code>,
 *  <code>west</code>, and <code>south</code> store which walls are up; e.g., if
 *  <code>north</code> is <code>true</code>, then the north wall is up.
 *  
 *  Each cell also has pointer to its four <code>MazeCell</code> neighbors,
 *  <code>neighborN</code>, <code>neighborE</code>, <code>neighborS</code>, and
 *  <code>neighborW</code>.  If any of these values are null, it means this cell
 *  is on the border of the maze.  
 *
 *
 */
public class MazeCell {

	private boolean north, east, south, west;
	private boolean visited, examined, wallUp;
	public MazeCell neighborN, neighborE, neighborS, neighborW, rep, parent, current;
	private Random generator;
	public String color;
	public MazeCell predecessor;
	public int start, finish, distance, rank;
	private ArrayList<MazeCell> neighborList;

	/** 
	 *  Sets all the walls to <code>true</code> and initializes
	 *  the random number generator.
	 */
	public MazeCell() {
		north = true; //MazeCell trait
		east  = true; //MazeCell trait
		south = true; //MazeCell trait
		west  = true; //MazeCell trait
		generator = new Random();
		visited = false; //used for visualization
		examined = false; //used for visualization
		rep = null; //only used for LL implementation of disjoint set
		parent = null; //for path compression/disjoint sets
		rank = 0; //for path compression/disjoint sets
		color = "white"; //for BFS & DFS
		distance = 0; //BFS
		predecessor = null;
		start = 0;//DFS (time)
		finish = 0;//DFS (time)

	}

	/** Visited means first reached/colored white for our purposes
	 *  Sets the visited flag to <code>true</code>.
	 */
	public void visit() {
		visited = true;
	}

	/**
	 *  Returns whether or not this cell has been visited.
	 *  @return <code>true</code> if the cell has been visited.
	 */
	public boolean visited() {
		return visited;
	}

	/**Examined means blacked out/no longer reacheable for our purposes
	 *  Sets the examined flag to <code>true</code>.
	 */
	public void examine() {
		examined = true;
	}

	/**
	 *  Returns whether or not this cell has been examined.
	 *  @return <code>true</code> if the cell has been examined.
	 */
	public boolean examined() {
		return examined;
	}

	/**
	 *  Sets the pointers to the neighbors of this cell.  If a pointer 
	 *  is null, that means this cell is along the border of the maze.
	 *  @param n  The north neighbor of this cell.
	 *  @param e  The east neighbor of this cell.
	 *  @param s  The south neighbor of this cell.
	 *  @param w  The west neighbor of this cell.
	 */
	public void setNeighbors(MazeCell n, MazeCell e, MazeCell s, MazeCell w) {
		neighborN = n;
		neighborE = e;
		neighborS = s;
		neighborW = w;
	}

	public void setRep(MazeCell representative){
		rep = representative;
	}

	/**
	 *  Sets whether or not there are walls up to the north, east, south, and 
	 *  west of this cell.
	 *  @param north <code>true</code> if there's a wall on the north side of the cell.
	 *  @param east <code>true</code> if there's a wall on the east side of the cell.
	 *  @param south <code>true</code> if there's a wall on the south side of the cell.     
	 *  @param west <code>true</code> if there's a wall on the west side of the cell.
	 */
	public void setWalls(boolean north, boolean east, boolean south, boolean west) {
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}

	/** 
	 *  Returns whether or not this cell has all its walls up.
	 *  @return <code>true</code> if all walls are up.
	 */
	public boolean hasAllWalls(MazeCell m) {
		boolean AllWallsUp = false;
		if(m.north == true && m.east == true && m.south == true && m.west == true){
			AllWallsUp = true;
		}
		return AllWallsUp;
	}

	/**
	 * A method that checks if adjacent cells share a wall or not
	 * @param x a mazecell with a neighbor y
	 * @param y a mazecell with a neighbor x
	 * @return
	 */
	public boolean sharesWallWith(MazeCell x, MazeCell y){
		boolean wall = false;
		if (x.neighborE==y && x.east() || x.neighborE == y && y.west()){
			wall = true;
		} else if (x.neighborN == y && x.north() || x.neighborN == y && y.south()){
			wall = true;
		} else if (x.neighborS == y && x.south() || x.neighborS == y && y.north()){
			wall = true;
		}else if (x.neighborW == y && x.west() || x.neighborW == y && y.east()){
			wall = true;
		}
		return wall;
	}

	/**
	 *  Returns whether or not this cell has its north wall up.
	 *  @return <code>true</code> if the cell's north wall is up.
	 */
	public boolean north() {
		if(north == true){
			wallUp = true;
		} else if (north == false){
			wallUp = false;
		}
		return wallUp;
	}

	/**
	 *  Returns whether or not this cell has its south wall up.
	 *  @return <code>true</code> if the cell's south wall is up.
	 */
	public boolean south() {
		if(south == true){
			wallUp = true;
		} else if (south == false) {
			wallUp = false;
		}
		return wallUp;
	}

	/**
	 *  Returns whether or not this cell has its east wall up.
	 *  @return <code>true</code> if the cell's east wall is up.
	 */
	public boolean east() {
		if(east == true){
			wallUp = true;
		} else if (east == false) {
			wallUp = false;
		}
		return wallUp;
	}

	/**
	 *  Returns whether or not this cell has its west wall up.
	 *  @return <code>true</code> if the cell's west wall is up.
	 */
	public boolean west() {
		if(west == true){
			wallUp = true;
		} else if (west == false) {
			wallUp = false;
		}
		return wallUp;
	}

	/**
	 *  Gets the accessible neighbors of this cell.
	 *  Returns an array of those neighbors.  The length of the array
	 *  is the number of neighbors this cell has.
	 *  @return  An array with the neighbors contained within it.
	 */
	public ArrayList<MazeCell> getNeighbors(MazeCell b) {
		current = b;
		neighborList = new ArrayList<MazeCell>();
		if(current.neighborE != null){
			neighborList.add(current.neighborE);
		} if(current.neighborN != null){
			neighborList.add(current.neighborN);
		}if(current.neighborS != null){
			neighborList.add(current.neighborS);
		}if(current.neighborW != null){
			neighborList.add(current.neighborW);
		}
		return neighborList;
	}

	/**
	 *  Knocks down the wall between this cell and the neighbor cell.
	 *  The neighbor cell must actually be a neighbor of this cell.
	 *  This method is used in conjunction with <code>neighborWithWalls</code>.
	 *  @param neighbor  The neighboring cell; must be one of the neighbors
	 *                   set in <code>setNeighbors</code>.
	 */
	public void knockDownWall(MazeCell current, MazeCell neighbor) {
		if(current.neighborE == neighbor) {
			current.east = false;
			neighbor.west = false;
		} else if(current.neighborN == neighbor) {
			current.north = false;
			neighbor.south = false;
		} else if(current.neighborS == neighbor) {
			current.south = false;
			neighbor.north = false;
		} else if(current.neighborW == neighbor) {
			current.west = false;
			neighbor.east = false;
		}
	}

	/**
	 * Use this function whenever you want to randomly pick one of the neighbours
	 * @return - random choice of one of the neighbours.
	 */
	public MazeCell getRandomNeighbor(MazeCell c) {
		ArrayList<MazeCell> neighbors = getNeighbors(c);
		int num = generator.nextInt(neighbors.size());
		MazeCell mc = neighbors.get(num);
		return mc;
	}

	/**
	 *  Returns a neighbor that has all its walls intact.
	 *  @return Neighbor with all its walls intact.
	 */
	public MazeCell neighborWithWalls(MazeCell m) {
		MazeCell walled = null;
		ArrayList<MazeCell> neighbors = getNeighbors(m);
		for(MazeCell n : neighbors){
			if(hasAllWalls(n) == true){
				walled = n;
				break;
			}
		}
		return walled;
	}

	/**
	 * A setter method for the current cell
	 * @param currentCell
	 */
	public void setCurrent(MazeCell currentCell){
		current = currentCell;
	}

	/**
	 * a getter method for the current cell
	 * @return current the current cell
	 */
	public MazeCell getCurrent(){
		return current;
	}


}
