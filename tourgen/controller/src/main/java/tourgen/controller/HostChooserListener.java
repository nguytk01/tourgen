package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tourgen.util.IHostChooserPanel;

public class HostChooserListener implements ActionListener{
  private tourgen.controller.NewMainViewController newMainViewController;
  private tourgen.util.IHostChooserPanel hostChooserPanel;
  public HostChooserListener(tourgen.controller.NewMainViewController newMainViewControllerArg, IHostChooserPanel hostChooserPanelArg) {
    newMainViewController = newMainViewControllerArg;
    hostChooserPanel = hostChooserPanelArg;
  }
  @Override
  public void actionPerformed(ActionEvent arg0) {
    newMainViewController.changeHostForMeet(hostChooserPanel.getSelectedMeet() ,hostChooserPanel.getNewHost());
  }

}
