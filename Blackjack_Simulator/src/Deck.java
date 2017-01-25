import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * A class that creates a card deck
 * @author josephhaymaker
 *
 */
public class Deck {

	ArrayList<Card> deck;
	Random rand;
	
/**
 * The constructor for the class. Calls a method that returns an arraylist with all suits and ranks matched.
 */
	public Deck(){	
		deck  = new ArrayList<Card>();
		rand = new Random();
		createDeck();
	}
	
	public void createDeck(){
		String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
		String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen", "Ace"} ; 
		
		for (int i = 0; i < suits.length; i++){
			for (int j = 0; j < ranks.length; j++){
				Card newCard = new Card(ranks[j], suits[i]);
//				String cardDescription = ranks[j] + " of " + suits[i];
//				newCard.cardDescriptor();
				deck.add(newCard);
			}
		}
	}
	
	//TEST METHOD
	public int getTestCard(){
		//test card will be of value 5
		return 5;
	}
	
//	public int dealCard(){
//		int cardsLeft = deck.size(); //find out size
//		int randomIndex = rand.nextInt(cardsLeft); //get a random index #
//		Card chosenCard = deck.get(randomIndex); //get Card obj at that random index
//		deck.remove(randomIndex); //then take that card out of the deck so can't be reused
////		chosenCard.cardDescriptor();
////		int value = assignValues(chosenCard);
//		return value;
//	}
	
	public Card dealCard(){
		int cardsLeft = deck.size(); //find out size
		int randomIndex = rand.nextInt(cardsLeft); //get a random index #
		Card choosenCard = deck.get(randomIndex); //get Card obj at that random index
		deck.remove(randomIndex); //then take that card out of the deck so can't be reused
//		choosenCard.cardDescriptor();
//		assignValues(choosenCard);
		return choosenCard;
	}

}
	

	

	

