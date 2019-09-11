import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.*;
import java.awt.datatransfer.Clipboard;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
    
public class mtg_driver {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub

		Scanner kb = new Scanner(System.in);
		
		boolean multiFile;
		
		//Get user input whether they want one big file or a few single file decks
		System.out.println("Use one file for all decks? y/n");
		String choice = kb.next();
		if(choice.equalsIgnoreCase("y"))
		{
			multiFile = false;
		}
		else
		{
			multiFile = true;
		}
		
		//get file export location from user
		System.out.println("Select Export Location");
		String destination = LocationPrompt();
		System.out.println(destination);
		
		//set up clipboard object
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		boolean NASexception = false; //Not a String exception
		String oldString = "";

		
		//checks the clipboard for a string every .25 sec, if the object is new
		//and a string, adds a deck object TODO add checking to see if string is
		//a deck in some way
		int counter = 1;
		ArrayList<Deck> decksList = new ArrayList<>();
		while(true)
		{
			Thread.sleep(250);
			try
			{	
				Deck newDeck;
				String newString = cb.getData(DataFlavor.stringFlavor).toString();
				if(!(oldString.equals(newString)) && !NASexception)
				{
					newDeck = new Deck(newString);
					oldString = newString;
					System.out.println("deck: " + newDeck.toString());
					
					if(multiFile)
						exportToTextIndividual(newDeck, destination, counter);
					else
						decksList.add(newDeck);
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
	
	
	/*
	 * Method puts out a file select box for the user to select
	 * deck list download location
	 * @return string representation of file path
	 */
	private static String LocationPrompt()
	{
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = chooser.showOpenDialog(null);
		String path = "";
		if(returnValue == JFileChooser.APPROVE_OPTION)
		{
			path = chooser.getSelectedFile().getAbsolutePath();
		}
		
		return path;
	}
	
	
	/*
	 * exports an individual deck to a single text doc, handles changing
	 * file name slightly to not delete previous decks exported during
	 * this session, e.g. ArenaDecks1, ArenaDecks2, etc.
	 * @param deck to be exported
	 * @param export location
	 * @param what number deck is being exported with this method
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
	
	//TODO change this param deck to a list of decks
	private static void exportToText(Deck deck, String location)
	{
		File exportedDeck = new File(location + "\\ArenaDecks.txt");
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
	
	
}
