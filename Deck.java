
public class Deck {

	Card cards[];
	int deckLength;
	
	public Deck(String deckString)
	{
		int lastNewLine = deckString.lastIndexOf('\n');
		String tempDeck = new String("" + deckString);
		int j = tempDeck.indexOf('\n');
		deckLength = 0;
		
		//get the number of unique cards in the deck
		while(j != -1)
		{
			deckLength++;
			tempDeck = tempDeck.substring(j + 1);
			j = tempDeck.indexOf('\n');
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
