import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class processDeck extends Thread{

	private boolean keepRunning;
	Deck newDeck;
	boolean multiFile;
	String destination;
	public processDeck(boolean multiFileChoice, String fileDestination)
	{
		multiFile = multiFileChoice;
		destination = fileDestination;
		keepRunning = true;
	}
	
	public void stopProcessing()
	{
		keepRunning = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//get the system clipboard and set it to a blank string
		//so there is no remnant on the clipboard from before 
		//program started
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection blankCB = new StringSelection("");
		cb.setContents(blankCB, null);
		boolean NASexception = false;
		
		String oldString = "";
		int counter = 1;
		ArrayList<Deck> decksList = new ArrayList<>();
		
		//until keepRunning is set to false by driver, keep listening
		//for new decks to put in the list
		while(keepRunning)
		{
			//sleep so the while loop doesn't consume anything the system
			//allows it to have
			try {
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				System.out.println("Sleep Interrupt");
			}
			
			//check cb to see if there is a new deck
			try
			{	
				String newString = cb.getData(DataFlavor.stringFlavor).toString();
				Deck newDeck;
				
				//if there is a new deck then either add to deckList or
				//create a new text file with exportToTextIndividual
				if(!(oldString.equals(newString)) && !NASexception)
				{
					newDeck = new Deck(newString);
					oldString = newString;
					if(multiFile)
						exportToTextIndividual(newDeck, destination, counter++);
					else
						decksList.add(newDeck);
				}
			}
			
			
			//catch NAS exceptions
			//This might be redundant with the UnsupportedFlavorException?
			catch(IOException e)
			{
				if(!NASexception)
				{
					System.out.println("not a valid String");
					NASexception = true;
				}
			}
			
			//catch unsupported flavor exceptions, dont do anything with them for now
			catch(UnsupportedFlavorException e)
			{
				
			}
		}
		//after all decks have been added to deckList, call 
		//single text file export method
		if(!multiFile)
		{
			exportToText(decksList, destination);
		}
	}
	
	/*
	 * Exports a single deck to a uniquely named file
	 * @param Deck to be exported
	 * @param location for text file to be made
	 * @param number of decks already made so a unique file name can be made
	 */
	private static void exportToTextIndividual(Deck deck, String location, int counter)
	{
		
		File exportedDeck = new File(location + "\\ArenaDecks" + counter + ".txt");
		try
		{
			exportedDeck.createNewFile();
			PrintWriter writer = new PrintWriter(exportedDeck);
			writer.print(deck);
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("write error");
		}
		
		
		
	}
	
	/*
	 * Exports a list of decks to a single text file
	 * @param Array List of decks
	 * @param location to create text file
	 */
	private static void exportToText(ArrayList deck, String location)
	{
		File exportedDeck = new File(location + "\\ArenaDecks.txt");
		try
		{
			exportedDeck.createNewFile();
			PrintWriter writer = new PrintWriter(exportedDeck);
			for(int i = 0; i < deck.size(); i++)
			{
				writer.print(deck.get(i));
				writer.print("\n\n");
			}
				
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("write error");
		}
		
		
		
	}
	
	
	
	
}
