/**
 * The class for determining how to move.
 * @author swapneel
 *
 */
public class Move {

	int type;
	int childpos;
	int key;

	public Move(int type) {
		this.type = type;
	}

	/**
	 * The constructor for the class
	 * @param type an int
	 * @param childpos
	 * @param key
	 */
	public Move(int type, int childpos, int key) {
		this.type = type;
		this.childpos = childpos;
		this.key = key;
	}

	public int type() {
		return type;
	}

	public void setType(int t) {
		type = t;
	}

	public int childpos() {
		return childpos;
	}

	public int key() {
		return key;
	}

	public String toString() {
		try {
			switch (type()) {
			case Constants.STAYPUT:
				return new String("Stay Put");
			case Constants.WEST:
				return new String("Moving West");
			case Constants.EAST:
				return new String("Moving East");
			case Constants.NORTH:
				return new String("Moving North");
			case Constants.SOUTH:
				return new String("Moving South");
			case Constants.REPRODUCE:
				return new String("Reproducing");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "Error";
		
	}
}
