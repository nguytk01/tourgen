package tourgen.controller;

public class AddTournamentMenuItemListener extends javax.swing.AbstractAction {
  private ReportViewUseCaseController reportViewController;
  
  /**
   * Construct a listener listening to an AddTouranmentMenuItem instance.
   */
  public AddTournamentMenuItemListener() {
    super();
    putValue(javax.swing.Action.NAME, "Add selected tournament to Repsitory.");
    putValue(javax.swing.Action.MNEMONIC_KEY, java.awt.event.KeyEvent.VK_A);
  }
  
  /**
   * This method is called whenever the menu item is clicked.
   */
  public void actionPerformed(java.awt.event.ActionEvent event) {
    if (reportViewController.getSelectedTournament() != null) {
      tourgen.model.Repository.getInstance1().addTournament(
          (tourgen.model.Tournament)reportViewController.getSelectedTournament());
    }
  }

  void setReportViewUseCaseController(ReportViewUseCaseController controllerArg) {
    reportViewController = controllerArg;
  }
}