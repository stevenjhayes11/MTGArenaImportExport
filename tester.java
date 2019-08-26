import java.awt.datatransfer.*;
import java.awt.*;
import java.util.Scanner;
    
	public class tester {

		public static void main(String[] args) throws InterruptedException{
			// TODO Auto-generated method stub
		
			String testStringDeck = "1 Zahid, Djinn of the Lamp (DAR) 76\n3 Gearsmith Prodigy (M19) 57\n25 Island (M19) 265\n4 Field Creeper (M19) 234\n1 Diamond Mare (M19) 231\n3 Aviation Pioneer (M19) 46\n1 Sai, Master Thopterist (M19) 69\n2 Aven Wind Mage (M19) 45\n2 Scholar of Stars (M19) 71\n2 Gilded Sentinel (XLN) 239\n2 Gearsmith Guardian (M19) 237\n3 Waterknot (RIX) 61\n1 Arcane Encyclopedia (M19) 227\n1 Divination (DAR) 52\n2 Disperse (M19) 50\n1 Befuddle (M19) 309\n1 Riddlemaster Sphinx (M19) 287\n1 Tempest Djinn (DAR) 68\n1 Meteor Golem (M19) 241\n3 Air Elemental (M19) 308";
			Deck newDeck = new Deck(testStringDeck);
			newDeck.toString();
		
	}

}