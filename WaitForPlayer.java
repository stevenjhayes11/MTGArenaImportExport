import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WaitForPlayer implements Runnable {

	
	public WaitForPlayer()
	{
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		JFrame exitBox = new JFrame();
		exitBox.setDefaultCloseOperation(exitBox.DISPOSE_ON_CLOSE);
		JOptionPane.showMessageDialog(exitBox, "Click done when finished copying decks");
		
	}

}
