package tourgen.controller;

import java.awt.event.ActionEvent;

import tourgen.model.Repository;
import tourgen.util.ITournamentChooserComboBox;
// since comboboxes do not work well with actionlistener, itemlistener is added here
public class TournamentChooserComboBoxListener implements java.awt.event.ActionListener, 
java.awt.event.ItemListener{

  private NewMainViewController newMainViewController;
  public TournamentChooserComboBoxListener() {
  }
  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (((javax.swing.JComboBox) (arg0.getSource())).getSelectedIndex() >=0) {
      System.out.println("actionEvent");
      ITournamentChooserComboBox tournamentChooserComboBox = (ITournamentChooserComboBox) arg0.getSource();
      newMainViewController.setActiveTournament(Repository.getInstance1().getGirlList().get(tournamentChooserComboBox.getSelectedTournamentIndex()));
    }
  }
  
  @Override
  public void itemStateChanged(java.awt.event.ItemEvent event){
    System.out.println("change state combobox");
    if ( event.getStateChange() == java.awt.event.ItemEvent.SELECTED ) {
      ITournamentChooserComboBox tournamentChooserComboBox = (ITournamentChooserComboBox) event.getItemSelectable();
      newMainViewController.setActiveTournament(Repository.getInstance1().getGirlList().get(tournamentChooserComboBox.getSelectedTournamentIndex()));
    }
  }
  public void setNewMainViewController(NewMainViewController newMainViewControllerArg) {
    newMainViewController = newMainViewControllerArg;
  }
}
