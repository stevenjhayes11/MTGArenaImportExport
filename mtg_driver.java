import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.*;
import java.awt.datatransfer.Clipboard;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
    
public class mtg_driver{
	
	

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub

		boolean multiFile;
		
		//Get user input whether they want one big file or a multiple files each with a single deck
		JFrame filePrompt = new JFrame("File Format");
		filePrompt.setDefaultCloseOperation(filePrompt.DISPOSE_ON_CLOSE);
		Object[] fileOptions = {"One File for All Decks", "One Deck Per File"};
		int multiFileUserChoice = JOptionPane.showOptionDialog(filePrompt, "Do you want one file for all Decks or one file for each Deck? (Location prompt follows)" , "File Formats", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, /*icon choice*/ fileOptions, fileOptions[0]);
		if(multiFileUserChoice == 0)
			multiFile = false;
		else
			multiFile = true;
		
		//get file export location from user
		String destination = LocationPrompt();
		
		//Start the Threads to wait for the player to indicate they are done and
		//to process new decks as they are added to the clipboard
		processDeck deckChecker = new processDeck(multiFile, destination);
		Thread playerDialogBox = new Thread(new WaitForPlayer());
		deckChecker.start();
		playerDialogBox.start();
		
		//if the dialog box that asks if the player is done is closed, then the
		//deck checker also shuts down
		boolean playerDialogAlive = true;
		while(playerDialogAlive)
		{
			Thread.sleep(100);
			playerDialogAlive = playerDialogBox.isAlive();
		}
		if(!playerDialogAlive)
			deckChecker.stopProcessing();
		
		//gives deckChecker thread time to finish any leftover tasks before closing
		while(deckChecker.isAlive())
			Thread.sleep(50);
		System.out.println("ending");
		
		
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
	/*private static void exportToTextIndividual(Deck deck, String location, int counter)
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
				writer.print('\n');
			}
				
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("write error");
		}
		
		
		
	}*/
	
	
}
