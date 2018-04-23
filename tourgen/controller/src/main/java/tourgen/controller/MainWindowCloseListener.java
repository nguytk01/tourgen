package tourgen.controller;

import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class MainWindowCloseListener implements java.awt.event.WindowListener {

  private tourgen.model.SchoolManager schoolManager;
  
  public MainWindowCloseListener(tourgen.model.SchoolManager schoolManagerArg) {
    schoolManager = schoolManagerArg;
  }
  
  @Override
  public void windowOpened(WindowEvent e) {
    
  }

  @Override
  public void windowClosing(WindowEvent e) {
    try {
    	tourgen.model.IoManager.saveEverything(schoolManager);
    	e.getWindow().dispose();
    	System.exit(0);
    } catch (IllegalStateException event) {
    	JOptionPane.showMessageDialog(null, "Please save all of your changes before exit.", 
    			"Unsaved changes detected", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void windowClosed(WindowEvent e) {
    
  }

  @Override
  public void windowIconified(WindowEvent e) {
    
  }

  @Override
  public void windowDeiconified(WindowEvent e) {

  }

  @Override
  public void windowActivated(WindowEvent e) {

  }

  @Override
  public void windowDeactivated(WindowEvent e) {

  }

}
