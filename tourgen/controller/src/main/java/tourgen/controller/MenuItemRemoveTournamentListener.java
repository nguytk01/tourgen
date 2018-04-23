package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuItemRemoveTournamentListener implements ActionListener {

	private NewMainViewController newMainViewController;

	@Override
	public void actionPerformed(ActionEvent e) {
		newMainViewController.removeTournamentClicked();
	}
	
	public void setNewMainViewController(NewMainViewController newMainViewControllerArg) {
	    newMainViewController = newMainViewControllerArg;
	  }

}
