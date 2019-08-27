import java.awt.datatransfer.*;
import java.io.*;
import java.awt.*;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import java.io.PrintWriter;
    
public class mtg_driver {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub

		System.out.println("Select Export Location");
		String destination = LocationPrompt();
		System.out.println(destination);
		
		//set up clipboard object
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		boolean NASexception = false; //Not a String exception
		String oldString = "";

		//checks the clipboard for a string every .25 sec, if the object is new
		//and a string, adds a deck object
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
					
					exportToText(newDeck, destination);
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
	
	private static void exportToText(Deck deck, String location)
	{
		File exportedDeck = new File(location + ".txt");
		try
		{
			exportedDeck.createNewFile();
			//PrintWriter writer = new PrintWriter(exportedDeck);
			//writer.print(deck.toString());
			//writer.close();
		}
		catch(Exception e)
		{
			System.out.println("write error");
		}
		
		
		
	}
	
	
}
