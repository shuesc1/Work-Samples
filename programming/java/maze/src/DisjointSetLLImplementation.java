import java.util.HashMap;
import java.util.LinkedList;

public class DisjointSetLLImplementation {
	private HashMap<MazeCell,LinkedList<MazeCell>> sets;
	private LinkedList<MazeCell> disjointSets;
	private MazeCell setMember, helper1, helper2;


	/**
	 * Class constructor
	 */
	public DisjointSetLLImplementation(){
		sets = new HashMap<MazeCell,LinkedList<MazeCell>>();
		disjointSets = new LinkedList<MazeCell>();
		setMember = new MazeCell();
		helper1 = new MazeCell();
		helper2 = new MazeCell();
	}

	/**
	 * make a set out of the cells in the maze
	 * @param maze
	 */
	public void makeSet(MazeCell[][] maze) {
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze.length; j++) {
				setMember = maze[i][j];
				setMember.setRep(setMember); //sets this cell as the representative --LL representation of disjoint sets
				disjointSets.add(setMember);//adds MazeCell to LL
				sets.put(setMember, disjointSets);//adds MazeCell key, LL value to hashmap
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
				helper1 = find(cell1); //rep for cell1
				helper2 = find(cell2); //rep for cell2

				if(helper1 != helper2){ //if they are in different sets (have a different rep)
					if(sets.get(helper1).size() > sets.get(helper2).size()){ //if set1 larger then append smaller set2 to it (less work)
						for(MazeCell mc : sets.get(helper2)){
							mc.rep = helper1;
						}
						sets.get(helper1).addAll(sets.get(helper2));
						sets.remove(helper2); //remove set since it's already appended to other set
					} else {
						for(MazeCell mc : sets.get(helper1)){
							mc.rep = helper2;
						}
						sets.get(helper2).addAll(sets.get(helper1));
						sets.remove(helper1); //remove set since it's already appended to other set
					}
				}
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


}
