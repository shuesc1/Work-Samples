import java.util.Random;
import java.util.Scanner;

public class BlackJack {

	Random rand;
	int playerScore;
	int computerScore;
	Deck thisDeck;
	
	/**
	 * The constructor for the class. Sets both players scores to 0 and declares a random variable.
	 */
	public BlackJack(){
		thisDeck = new Deck();
		rand = new Random();
		playerScore = 0;
		computerScore = 0;
	}
	
	public void playBlackJack(){
	//computer's 1st two turns
		while (playerScore <= 21 && computerScore <= 21){ //play if scores less than 21
			int compThisGameScore = computerPlay(); //has to return a value
			System.out.println("The computer got a " + compThisGameScore + " their first turn");//where would cardValue be coming from?
			computerScore += compThisGameScore;
			compThisGameScore += computerPlay(); //computer plays a second time
			computerScore += compThisGameScore; // redundant
			
		//human player starts to play and plays till they decide to stand//
			if (playerScore <=21){
				int playerThisGameScore = humanPlay(); //humanPlay just being something like drawing a card (redundant??) 
				playerScore += playerThisGameScore;
				//humanPlay();
			} if (playerScore > 21){
				System.out.println("You busted!");
				break;
			}
			
		//after human stands computer plays till it hits 17
		while (computerScore < 17){
			System.out.println("The computer got a " + compThisGameScore + " on their second turn.");
			compThisGameScore += computerPlay(); //computer plays a second time
			computerScore += compThisGameScore;
		}
		
		
		//outcome printed
			if (playerScore > computerScore && playerScore <= 21){
				System.out.println("Player wins this round! Enhorabuena!!");
			} else if (playerScore < computerScore){
				System.out.print("You lose this round-- the computer is the winner with a score of: " + computerScore);
			}
			}
		
	}		
			
	public int humanPlay(){
		String response = "Y";
		Scanner in = new Scanner(System.in);
		
		System.out.println("Your current score is: " + playerScore);
		int roundScore = 0;
		
		System.out.println("Do you wish to hit? (Y/N)");
		response = in.next();
			while (response.equalsIgnoreCase("Y")){
				Card roundCard = thisDeck.dealCard();
//				int value = thisDeck.assignValues(roundCard);
//				int value = dealCard();
//				int value = (int) (Math.random()* 11); //TEST CASE
				System.out.println("You were dealt a: " + roundCard);
				int value = roundCard.assignValues();
				
				if (value != 11){
					roundScore += value;
					System.out.println("Your score is now: " + roundScore);
					if(roundScore > 21) {
						System.out.println("You busted! You automatically lose!");
						break;
					}
						System.out.println("Hit again? (Y/N):");
						response = in.next();
							if (response.equalsIgnoreCase("N")){
								System.out.println("You have choosen to stand. Your score is: " + roundScore);
								playerScore += roundScore;
						}
				} else if (value == 11 && value + roundScore > 21){
					value = 1;
				} else if (value == 11 && value + roundScore < 21){
						value = 11;
				}
				playerScore += roundScore;
			}
//			if (response.equalsIgnoreCase("N")){
//				System.out.println("You have choosen to stand. Your score is: " + playerScore);
//			}
			return playerScore;
	}
			
	public int computerPlay(){
		int roundScore = 0;
//		int value = dealCard();
//		int value = getTestCard(); //still not working..
		Card roundCard = thisDeck.dealCard();
		int value = roundCard.assignValues();
//		int value = (int) (Math.random()* 11);//TEST CASE
		
		if (value != 11){
			roundScore += value;
		} else if (value == 11 && value + roundScore > 21){
				value = 1;
		} else if (value == 11 && value + roundScore < 21){
				value = 11;
		}
		computerScore += roundScore;
			if (computerScore > 21){
				System.out.println("Computer busted! You automatically win!");
			}
		return computerScore;
	}
}
