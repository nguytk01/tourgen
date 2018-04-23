package tourgen.controller;

import java.awt.event.ActionEvent;

public class MenuItemSaveTournamentListener implements java.awt.event.ActionListener{
  private NewMainViewController newMainViewController;
  @Override
  public void actionPerformed(ActionEvent arg0) {
    newMainViewController.saveCurrentTournamentClicked();
  }
  
  public void setNewMainViewController(NewMainViewController newMainViewControllerArg) {
    newMainViewController = newMainViewControllerArg;
  }

}
