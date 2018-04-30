package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeCompetitionSiteListener implements ActionListener {
  private NewMainViewController newMainViewController;
  private tourgen.util.IAvailableMeetsPanel availableMeetsPanel;
  public ChangeCompetitionSiteListener(tourgen.controller.NewMainViewController newMainViewControllerArg, 
      tourgen.util.IAvailableMeetsPanel availableMeetsPanelArg) {
    newMainViewController = newMainViewControllerArg;
    availableMeetsPanel = availableMeetsPanelArg;
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    newMainViewController.changeCompetitionSite(availableMeetsPanel.getSelectedSchool(), 
        availableMeetsPanel.getOldMeet(), availableMeetsPanel.getNewMeet());
    //System.out.println("changeCompetitionSiteCalled");
  }
  
  
  public void setNewMainViewController(NewMainViewController newMainViewControllerArg) {
    newMainViewController = newMainViewControllerArg;
  }
}
 