
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
	
	public String toString()
	{
		return (cardCopies + name + set + num);
	}
	
	
}
