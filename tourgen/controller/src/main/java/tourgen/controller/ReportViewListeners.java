package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ReportViewListeners {
  private ReportViewUseCaseController controller;
  private ViewSchoolListUseCaseController viewSchoolListController;

  public ReportViewListeners() {

  }

  public void setCoordinator(ReportViewUseCaseController cont) {
    controller = cont;
  }

  public void setViewSchoolListUseCaseController(ViewSchoolListUseCaseController controller) {
    viewSchoolListController = controller;
  }

  public class ManageSchoolButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      viewSchoolListController.startProcess();
    }

  }

  public class TournamentSelectionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent arg0) {
      if (!((JList) arg0.getSource()).getValueIsAdjusting()) {
        controller.changeTournamentView();
      }
    }

  }
}
