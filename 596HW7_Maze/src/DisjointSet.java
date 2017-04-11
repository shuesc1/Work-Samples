import java.util.ArrayList;

/**
 * A class that creates a disjoint-set forest for the modified Kruskal's algorithm,
 * and implements the union-by-rank and path compression heuristics outlined in CLRS
 * @author josephhaymaker
 *
 */
public class DisjointSet {
	private MazeCell setMember;
	private ArrayList<MazeCell> allSetMembers;


	/**
	 * Class constructor
	 */
	public DisjointSet(){
		setMember = new MazeCell();
		allSetMembers = new ArrayList<>();
	}

	/**
	 * make a set out of the cells in the maze
	 * @param maze
	 */
	public void makeSet(MazeCell[][] maze) {
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze.length; j++) {
				setMember = maze[i][j];
				setMember.parent = setMember;
				setMember.rank = 0;
				allSetMembers.add(setMember);
			}
		}
	}

	/**
	 * Create the union of the sets that contain cell1 and cell2.
	 * If the two cells are in the same set, nothing changes,
	 * else create the new set as a union. 
	 * Please see the union-find algorithm.
	 * @param <T>
	 * @param cell1
	 * @param cell2
	 */
	public void union(MazeCell cell1, MazeCell cell2){
		link(findSet(cell1), findSet(cell2));
	}

	/**
	 * A helper method for union(x,y)
	 * @param x
	 * @param y
	 */
	public void link(MazeCell x, MazeCell y){
		if (x.rank > y.rank){
			y.parent = x;
		} else if (x.rank < y.rank){
			x.parent = y;
		} else if (x.rank == y.rank){
			y.rank = y.rank + 1;
		}
	}

	/**
	 * a method to achieve path compression and speed up running time of union method
	 * @param x
	 * @return
	 */
	public MazeCell findSet(MazeCell x){
		if(x != x.parent){
			x.parent = findSet(x.parent);
		}
		return x.parent;
	}

	/**
	 * Find the set that the cell is a part of.
	 * While finding the set, do the path compression as well.
	 * 
	 * @param cell
	 * @return
	 */
	public MazeCell find(MazeCell cell){
		MazeCell representative = new MazeCell();
		representative = cell.rep;
		return representative;
	}

	public ArrayList<MazeCell> getAllSetMembers(){
		return allSetMembers;
	}
	
}
