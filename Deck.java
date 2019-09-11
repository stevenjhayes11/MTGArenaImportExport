/*
 * Deck takes a string list of multiple cards,
 * seperates them into individual card objects,
 * and adds the cards to an array
 */


public class Deck {

	Card cards[];
	int deckLength;
	
	/*
	 * takes in the list of cards, processes
	 * the string into individual cards
	 * stores in array
	 */
	public Deck(String deckString)
	{
		int lastNewLine = deckString.lastIndexOf('\n');
		String tempDeck = new String("" + deckString);
		int currPos = tempDeck.indexOf('\n');
		deckLength = 0;
		
		//get the number of unique cards in the deck
		//takes advantage of string indexOf function that,
		//when it cannot find another of the char 
		//specified, returns a -1  
		while(currPos != -1)
		{
			deckLength++;
			tempDeck = tempDeck.substring(currPos + 1);
			currPos = tempDeck.indexOf('\n');
		}
		deckLength--;
		
		
		//seperates out individual cards into card objects
		cards = new Card[deckLength];
		for(int i = 0; i < deckLength; i++)
		{
			int nextNewLine = deckString.indexOf('\n');
			String newCard = deckString.substring(0,nextNewLine);
			cards[i] = new Card(newCard);
			deckString = deckString.substring(nextNewLine + 1);
		}
	}
	
	/*
	 * returns a formated string representation of
	 * cards in a deck, same default formatting by Arena
	 * client
	 */
	public String toString()
	{
		String result = "";
		for(int i = 0; i < deckLength; i++)
		{
			if(i != 0)
				result = result + '\n' + cards[i].toString();
			else
				result = cards[i].toString();
		}
		return result;
	}
}
