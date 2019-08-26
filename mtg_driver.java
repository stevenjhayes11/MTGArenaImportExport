import java.awt.datatransfer.*;
import java.io.IOException;
import java.awt.*;
import java.util.Scanner;
    
public class mtg_driver {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub

		
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		boolean NASexception = false; //Not a String exception
		String oldString = "";

		
		while(true)
		{
			Thread.sleep(1000);
			try
			{	
				Deck newDeck;
				String newString = cb.getData(DataFlavor.stringFlavor).toString();
				if(!(oldString.equals(newString)) && !NASexception)
				{
					newDeck = new Deck(newString);
					oldString = newString;
					System.out.println("deck: " + newDeck.toString());
				}
			}
			catch(IOException e)
			{
				if(!NASexception)
				{
					System.out.println("not a valid String");
					NASexception = true;
				}
			}
			catch(UnsupportedFlavorException e)
			{
				System.out.println("UnsupportedFlavorException");
			}
		}
		
	}
	
	

	/*FlavorListener cbListener = new FlavorListener(){
			@Override
			public void flavorsChanged(FlavorEvent e)
			{
				System.out.println(e);
			}
		}; 
		
		cb.addFlavorListener(cbListener);*/
	
	
}
