import java.awt.Toolkit;
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
		
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		boolean NASexception = false;
		
		String oldString = "";
		int counter = 1;
		ArrayList<Deck> decksList = new ArrayList<>();
		while(keepRunning)
		{
			try {
				Thread.sleep(250);
			}
			catch(InterruptedException e)
			{
				System.out.println("Sleep Interrupt");
			}
			
			try
			{	
				Deck newDeck;
				String newString = cb.getData(DataFlavor.stringFlavor).toString();
				if(!(oldString.equals(newString)) && !NASexception)
				{
					newDeck = new Deck(newString);
					oldString = newString;
					System.out.println(newDeck.toString());
					if(multiFile)
						exportToTextIndividual(newDeck, destination, counter);
					else //TODO add functionality to export decksList
					{
						System.out.println("decks: \n");
						decksList.add(newDeck);
						for(int i = 0; i < decksList.size(); i++)
						{
							System.out.println("current deck list");
							System.out.println(decksList.get(i).toString());
						}
					}
						
				}
			}
			//catch NAS exceptions
			
			catch(IOException e)
			{
				if(!NASexception)
				{
					System.out.println("not a valid String");
					NASexception = true;
				}
			}
			
			//catch unsupported flavor excetions
			catch(UnsupportedFlavorException e)
			{
				System.out.println("UnsupportedFlavorException");
			}
		}
	}
	
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
	
	//TODO change this param deck to a list of decks
	private static void exportToText(ArrayList deck, String location)
	{
		System.out.println("deck:");
		System.out.println(deck.toString());
		File exportedDeck = new File(location + "\\ArenaDecks.txt");
		try
		{
			exportedDeck.createNewFile();
			PrintWriter writer = new PrintWriter(exportedDeck);
			for(int i = 0; i < deck.size(); i++)
			{
				writer.print(deck.get(i));
				writer.print('\n');
			}
				
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("write error");
		}
		
		
		
	}
	
	
	
	
}
