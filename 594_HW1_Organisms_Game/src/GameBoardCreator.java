import java.util.Random;

public class GameBoardCreator {

	private int[][] board;
	//	private int marksMade;
	private Random rand = new Random();
//	private int currentSpace = board[0][0];

	public GameBoardCreator(int row, int col){
		board = new int[row][col];
		for(int i=0; i < row; i++){
			for(int j=0; j < col; j++){
				board[i][j] = 0;
			}
		}
	}

	public int getStartPosition(){
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		System.out.println("Starting at position " + x + "," + y);
		return board[x][y];
	}
	
	
//	public void moveSpace(int move, int x, int y){
//
//		if(checkOccupiedSpace() == false){
//			if(move == 1){
//				board[x][y-1];
//			} else if (move == 2){
//
//			} else if (move == 3){
//
//			} else if (move == 4){
//
//			}
//		} else {
//			System.out.println("Space is already occupied");
//		}
//
//
//	}



//	public boolean checkOccupiedSpace(){
//		if(currentSpace != 0){
//			return true;
//		} else {
//			return false;
//		}
//	}

}
