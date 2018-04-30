package tourgen.controller;

import static org.junit.Assert.*;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.security.Permission;

import javax.swing.JFrame;

import org.junit.Test;

public class MainWindowCloseListenerTest extends BaseTestUtils{

	@Test
	public void test() {
		
		MainWindowCloseListener mainWindowCloseListener = new MainWindowCloseListener(schoolManager);
		
		mainWindowCloseListener.windowOpened(null);
		
		mainWindowCloseListener.windowClosed(null);
		mainWindowCloseListener.windowIconified(null);
		mainWindowCloseListener.windowDeactivated(null);
		mainWindowCloseListener.windowDeiconified(null);
		mainWindowCloseListener.windowActivated(null);
		
		/*SecurityManager sm = new SecurityManagerMock(System.getSecurityManager());
	    System.setSecurityManager(sm);
		
		JFrame frame = new JFrame();
		WindowEvent e = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
		
		try{
			mainWindowCloseListener.windowClosing(e);
		} catch (SecurityException ex) {
			
		}*/
		
		
		 
	}

}
