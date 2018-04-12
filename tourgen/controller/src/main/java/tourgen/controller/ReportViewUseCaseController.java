package tourgen.controller;

import tourgen.model.Tournament;
import tourgen.util.IReportTableView;
import tourgen.util.IRepositoryView;

public class ReportViewUseCaseController {
  private IReportTableView reportTable;
  private IRepositoryView repoView;
  private AddTournamentMenuItemListener addTournamentMenuItemListener;
  
  /**
   * Construct a ReportViewUseCaseController.
   * @param reportTableArg a report table panel
   * @param repoArg a repository view panel
   * @param addTournamentMenuItemListenerArg a listener listening to AddTournamentMenuItem.
   */
  public ReportViewUseCaseController(IReportTableView reportTableArg,
      IRepositoryView repoArg, 
      AddTournamentMenuItemListener addTournamentMenuItemListenerArg) {
    reportTable = reportTableArg;
    repoView = repoArg;
    addTournamentMenuItemListener = addTournamentMenuItemListenerArg;
    addTournamentMenuItemListener.setReportViewUseCaseController(this);
  }

  public void changeTournamentView() {
    Object currentTournament = repoView.getSelectedTournament();
    reportTable.display(currentTournament);
  }
  
  public Object getSelectedTournament() {
    return repoView.getSelectedTournament();
  }
  
  void enableDisableAddTournamentMenuItem() {
    if (repoView.getSelectedTournament() != null) {
      addTournamentMenuItemListener.setEnabled(true);
    } else {
      addTournamentMenuItemListener.setEnabled(false);
    }
  }
}
