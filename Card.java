/*
 * card is a simple object that stores a few attributes cards
 * must have, obtained from a string in constructor, for now
 * not worried about a default constructor since the only 
 * call to Card class is from Deck class and Deck will only
 * use the String constructor
 */
public class Card {

	int cardCopies;
	String name;
	String set;
	int num;
	
	public Card(String cardString)
	{
		//find number of copies of card
		int space = cardString.indexOf(' ');
		cardCopies = Integer.parseInt(cardString.substring(0,space));
		cardString = cardString.substring(space + 1);
		
		//find name of card
		space = cardString.indexOf('(');
		name = cardString.substring(0, space);
		cardString = cardString.substring(space + 1);
			
		//find set of card
		space = cardString.indexOf(' ');
		set = cardString.substring(0,space - 1);
		cardString = cardString.substring(space + 1);
		
		//find card number
		num = Integer.parseInt(cardString);
	}
	
	/*
	 * print card info in format
	 * @return #ofCards NameOfCard (SET) CardIDNum
	 */
	public String toString()
	{
		return (cardCopies + " " + name +" (" + set + ") " + num);
	}
	
	
}
