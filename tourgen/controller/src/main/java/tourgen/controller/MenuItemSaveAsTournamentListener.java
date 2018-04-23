package tourgen.controller;

import java.awt.event.ActionEvent;

public class MenuItemSaveAsTournamentListener implements java.awt.event.ActionListener {

  private NewMainViewController newMainViewController;

  @Override
  public void actionPerformed(ActionEvent e) {
    newMainViewController.saveAsTournamentClicked();
  }

  public void setNewMainViewController(NewMainViewController newMainViewControllerArg) {
    newMainViewController = newMainViewControllerArg;
  }
}
