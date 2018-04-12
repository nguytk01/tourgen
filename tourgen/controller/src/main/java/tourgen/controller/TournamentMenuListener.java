package tourgen.controller;

public class TournamentMenuListener 
    extends javax.swing.AbstractAction 
    implements javax.swing.event.MenuListener {

  private ReportViewUseCaseController reportViewController;

  /**
   * Construct a listener listening to the Tournament menu.
   */
  public TournamentMenuListener() {
    super();
    putValue(javax.swing.Action.NAME, "Tournament");
    putValue(javax.swing.Action.MNEMONIC_KEY, java.awt.event.KeyEvent.VK_T);
  }

  public void actionPerformed(java.awt.event.ActionEvent event) {
  }

  public void setReportViewUseCaseController(ReportViewUseCaseController controllerArg) {
    reportViewController = controllerArg;
  }

  public void menuCanceled(javax.swing.event.MenuEvent e) {
  }

  public void menuSelected(javax.swing.event.MenuEvent e) {
    System.out.println("call reportViewUseCaseController.enableDisable..()");
    reportViewController.enableDisableAddTournamentMenuItem();
  }

  public void menuDeselected(javax.swing.event.MenuEvent e) {
  }

}