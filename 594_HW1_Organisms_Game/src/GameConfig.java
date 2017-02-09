/**
 * This is the game configuration that is publicly known.
 * @author swapneel
 *
 */
public interface GameConfig {

	/**
	 * The energy consumed in staying put
	 * @return should always return 1 (other parameters scale)
	 */
	public int s();

	/**
	 * The energy consumed in moving or reproducing
	 * @return the value of v
	 */
	public int v();

	/**
	 * The energy per unit of food
	 * @return the value of v
	 */
	public int u();

	/**
	 * The maximum energy per organisms
	 * @return the value of M
	 */
	public int M();

	/**
	 * The maximum food units per cell
	 * @return the value of K
	 */
	public int K();

}