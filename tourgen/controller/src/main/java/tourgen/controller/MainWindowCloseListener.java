package tourgen.controller;

import java.awt.event.WindowEvent;

public class MainWindowCloseListener implements java.awt.event.WindowListener{

  private tourgen.model.SchoolManager schoolManager;
  public MainWindowCloseListener(tourgen.model.SchoolManager schoolManagerArg) {
    schoolManager = schoolManagerArg;
  }
  @Override
  public void windowOpened(WindowEvent e) {
    
  }

  @Override
  public void windowClosing(WindowEvent e) {
    tourgen.model.IoManager.saveEverything(schoolManager);
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
