import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class testeragain implements ClipboardOwner {
  Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

  public testeragain() {
    // Implement Copy operation
    StringSelection contents = new StringSelection("data");
    clipboard.setContents(contents, this);
    FlavorListener lis = new FlavorListener(){
      @Override
      public void flavorsChanged(FlavorEvent e) {
       System.out.println(e);
      }
    };
    clipboard.addFlavorListener(lis);
    clipboard.removeFlavorListener(lis);
    // Implement Paste operation
    Transferable content = clipboard.getContents(this);
    String dstData;
    try {
      dstData = (String) content.getTransferData(DataFlavor.stringFlavor);
      System.out.println(dstData);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void lostOwnership(Clipboard clipboard, Transferable contents) {
    System.out.println("Clipboard contents replaced");
  }

  public static void main(String[] args) {
    testeragain test = new testeragain();
  }
}