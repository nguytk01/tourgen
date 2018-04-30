package tourgen.view;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.event.MouseInputListener;

public class GMapPinButton extends JButton {
  private static String pinPath = "pin.png";
  private static String pinBrightPath = "pin-bright.png";
  private static String pinOldPath = "resources/pin-old.png";
  private static String pinOldHoverPath = "resources/pin-old-hover.png";
  private static ImageIcon okPin = getImageIcon(pinPath);
  private static ImageIcon okPinHover = getImageIcon(pinBrightPath);
  private ImageIcon okPinPressed;
  private static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
  private static Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
  private JButton currentButton;

  private JToolTip toolTip;
  
  public GMapPinButton(String text) {
    // super(text);
    mapPinButtonCommonConstructor();
  }

  public GMapPinButton() {
    super();
    mapPinButtonCommonConstructor();
  }
  
  public GMapPinButton(JToolTip toolTipArg) {
	    super();
	    toolTip = toolTipArg;
	    mapPinButtonCommonConstructor();
	    this.setToolTipText("");
  }

  @Override
  public JToolTip createToolTip() {
	if (toolTip != null) {
		return toolTip;
	} else {
		return super.createToolTip();
	}
  }
  
  /**
   * Common routine used by the constructors of GMapPinButton.
   */
  public void mapPinButtonCommonConstructor() {
    currentButton = this;
    this.setIcon(okPin);
    this.setRolloverIcon(okPinHover);
    // this.setPressedIcon(okPinPressed);
    this.setOpaque(false);
    this.setContentAreaFilled(false);
    this.setBorderPainted(true);
    this.setBorder(null);
    PinButtonMouseListener mouseListener = new PinButtonMouseListener();
    this.addMouseMotionListener(mouseListener);
    this.addMouseListener(mouseListener);
    // this.revalidate();
    // this.repaint();
    //this.addFocusListener(new PinButtonFocusListener());
  }

  /*private static ImageIcon makeIcon(String img, int i, int j) {
    // The process of scaling an image!
    /*ImageIcon ico = new ImageIcon(img);
    Image image = ico.getImage(); // transform it
    Image newimg = image.getScaledInstance(i, j, Image.SCALE_SMOOTH); // scale it the smooth way
    return new ImageIcon(newimg); // transform it back*/
	  //return null;
  //}

  private static ImageIcon getImageIcon(String iconName) {
    BufferedImage bufferedImage = null;
    try {
      bufferedImage = ImageIO.read(
          GMapPinButton.class.getClassLoader().getResourceAsStream(iconName));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // System.out.println(bufferedImage.getHeight());
    // System.out.println(bufferedImage.getWidth());
    return new ImageIcon(bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
  }

  private class PinButtonMouseListener implements MouseInputListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //currentButton.setCursor(handCursor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
      requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
      // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
      // TODO Auto-generated method stub
    }
  }
  

}
