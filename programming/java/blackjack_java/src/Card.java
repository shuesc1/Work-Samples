import java.util.Random;

public class Card {

	private String rank;
	private String suit;
	
	public Card(String r, String s) {
		rank = r;
		suit = s;
		String cardDetails = r + " of " + s; //added to account for how assignValue is set up
	}
	
	public String toString(){
		String cardDetails = rank + " of " + suit;
		return cardDetails;
	}
	
//	public int assignValues(String aCard){
	public int assignValues(){
		String aCard = this.toString();
		int value = 0;
		if (aCard.equals("2 of Hearts") || aCard.equals("2 of Clubs") || aCard.equals("2 of Diamonds") || aCard.equals("2 of Spades")){
			value =  2;
		}
		if (aCard.equals("3 of Hearts") || aCard.equals("3 of Clubs") || aCard.equals("3 of Diamonds") || aCard.equals("3 of Spades")){
			value = 3;
		}
		if (aCard.equals("4 of Hearts") || aCard.equals("4 of Clubs") || aCard.equals("4 of Diamonds") || aCard.equals("4 of Spades")){
			value = 4;
		}
		if (aCard.equals("5 of Hearts") || aCard.equals("5 of Clubs") || aCard.equals("5 of Diamonds") || aCard.equals("5 of Spades")){
			value = 5;
		}
		if (aCard.equals("6 of Hearts") || aCard.equals("6 of Clubs") || aCard.equals("6 of Diamonds") || aCard.equals("6 of Spades")){
			value = 6;
		}
		if (aCard.equals("7 of Hearts") || aCard.equals("7 of Clubs") || aCard.equals("7 of Diamonds") || aCard.equals("7 of Spades")){
			value = 7;
		}
		if (aCard.equals("8 of Hearts") || aCard.equals("8 of Clubs") || aCard.equals("8 of Diamonds") || aCard.equals("8 of Spades")){
			value = 8;
		}
		if (aCard.equals("9 of Hearts") || aCard.equals("9 of Clubs") || aCard.equals("9 of Diamonds") || aCard.equals("9 of Spades")){
			value = 9;
		}
		if (aCard.equals("10 of Hearts") || aCard.equals("10 of Clubs") || aCard.equals("10 of Diamonds") || aCard.equals("10 of Spades")){
			value = 10;
		}
		if (aCard.equals("Jack of Hearts") || aCard.equals("Jack of Clubs") || aCard.equals("Jack of Diamonds") || aCard.equals("Jack of Spades")){
			value =  10;
		}
		if (aCard.equals("Queen of Hearts") || aCard.equals("Queen of Clubs") || aCard.equals("Queen of Diamonds") || aCard.equals("Queen of Spades")){
			value =  10;
		}
		if (aCard.equals("King of Hearts") || aCard.equals("King of Clubs") || aCard.equals("King of Diamonds") || aCard.equals("King of Spades")){
			value =  10;
		}
		if (aCard.equals("Ace of Hearts") || aCard.equals("Ace of Clubs") || aCard.equals("Ace of Diamonds") || aCard.equals("Ace of Spades")){
			value =  11;
		}
		return value;
	}
	
//	String card = assignRank() + " of " +assignSuit(); //creating a card with a randomly generated rank and suit
//	assignValues(card);


//public String assignSuit(){
//	String cardsSuit = suits[rand.nextInt(4)];
//	return cardsSuit;
//}
//
//public String assignRank(){
//	String cardsRank = ranks[rand.nextInt(13)];
//	return cardsRank;
//}
	
}
