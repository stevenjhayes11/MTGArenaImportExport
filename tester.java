import java.awt.datatransfer.*;
import java.awt.*;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
    
	public class tester {

		public static void main(String[] args) throws InterruptedException{
			// TODO Auto-generated method stub
		
			JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnValue = chooser.showOpenDialog(null);
			String path = "";
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				path = chooser.getSelectedFile().getAbsolutePath();
			}
			System.out.println(path);
	}

}