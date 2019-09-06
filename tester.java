import java.awt.datatransfer.*;
import java.io.File;
import java.io.PrintWriter;
import java.awt.*;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
    
	public class tester {

		public static void main(String[] args) throws InterruptedException{
			// TODO Auto-generated method stub
		
			String location = "C:\\Users\\steve\\Desktop\\MagicTest.txt";
			
			String deck = "4 Cavalcade of Calamity (RNA) 95\r\n" + 
					"22 Mountain (M20) 276\r\n" + 
					"4 Chandra's Spitfire (M20) 132\r\n" + 
					"4 Scorch Spitter (M20) 159\r\n" + 
					"4 Goblin Instigator (M19) 142\r\n" + 
					"4 Shock (M19) 156\r\n" + 
					"3 Chandra's Pyrohelix (WAR) 120\r\n" + 
					"3 Lightning Strike (XLN) 149\r\n" + 
					"4 Risk Factor (GRN) 113\r\n" + 
					"2 Tormenting Voice (M19) 164\r\n" + 
					"3 Skewer the Critics (RNA) 115\r\n" + 
					"1 Chandra, Acolyte of Flame (M20) 126\r\n" + 
					"1 Bag of Holding (M20) 222\r\n" + 
					"1 Chandra, Awakened Inferno (M20) 127\r\n" + 
					"\r\n";
			
			File exportedDeck = new File(location);
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