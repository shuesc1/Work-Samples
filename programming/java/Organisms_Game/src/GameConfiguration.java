import java.util.Random;

/**
 * A class that provides methods for the game parameters
 * @author josephhaymaker
 *
 */
public class GameConfiguration implements GameConfig {

	private Random rand = new Random();
	
	/**
	 * The energy consumed in staying put
	 * @return should always return 1 (other parameters scale)
	 */
	@Override
	public int s() {
		return 1;
	}

	/**
	 * The energy consumed in moving or reproducing
	 * @return the value of v
	 */
	@Override
	public int v() {
		int energyConsumed = rand.nextInt(21);
		while(energyConsumed < 2 || energyConsumed > 20){		
			energyConsumed = rand.nextInt(21);
		}
		return energyConsumed;
	}

	/**
	 * The energy per unit of food
	 * @return the value of v
	 */
	@Override
	public int u() {
		int foodEnergy = rand.nextInt(501);
		while(foodEnergy < 10){
			foodEnergy = rand.nextInt(501);
		}
		return foodEnergy;
	}

	/**
	 * The maximum energy per organisms
	 * @return the value of M
	 */
	@Override
	public int M() {
		int maxEnergy = rand.nextInt(1001);
		while(maxEnergy < 100){
			maxEnergy = rand.nextInt(1001);
		}
		return maxEnergy;
	}

	
	/**
	 * The maximum food units per cell
	 * @return the value of K
	 */
	@Override
	public int K() {
		int maxFood = rand.nextInt(51);
		while(maxFood < 10){
			maxFood = rand.nextInt(51);
		}
		return maxFood;
	}

}
