/**
 * This is a Constants Interface.
 * It will be used to provide game-wide constants.
 * @author swapneel
 *
 */
public interface Constants {

    public static final int STAYPUT    = 0;
    public static final int WEST       = 1;
    public static final int EAST       = 2;
    public static final int NORTH      = 3;
    public static final int SOUTH      = 4;

    public static final int REPRODUCE  = 5;

    public static final int[] DIRECTIONS = { STAYPUT, WEST, EAST, NORTH, SOUTH };
    public static final int[] CXTrans     = { 0, -1, 1, 0, 0};
    public static final int[] CYTrans     = { 0, 0, 0, -1, 1};
}
