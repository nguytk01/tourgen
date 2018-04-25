package tourgen.controller;

import java.sql.Savepoint;

public class TournamentMenuListener 
    extends javax.swing.AbstractAction 
    implements javax.swing.event.MenuListener {

  @Deprecated
  private ReportViewUseCaseController reportViewController;
  
  private NewMainViewController newMainViewController;
  private javax.swing.JMenuItem saveTournamentMenuItem;
  private javax.swing.JMenuItem saveAsTournamentMenuItem;
  private javax.swing.JMenuItem removeTournamentMenuItem;
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

  @Deprecated
  public void setReportViewUseCaseController(ReportViewUseCaseController controllerArg) {
    reportViewController = controllerArg;
  }

  public void menuCanceled(javax.swing.event.MenuEvent e) {
  }

  public void menuSelected(javax.swing.event.MenuEvent e) {
    //System.out.println("call reportViewUseCaseController.enableDisable..()");
    //reportViewController.enableDisableAddTournamentMenuItem();
    if (!newMainViewController.getCurrentlyDisplayTournament().isSavingNeeded()) {
      saveAsTournamentMenuItem.setEnabled(true);
      saveTournamentMenuItem.setEnabled(false);
    } else {
      saveAsTournamentMenuItem.setEnabled(true);
      saveTournamentMenuItem.setEnabled(true);
    }
    
    if (! newMainViewController.getCurrentlyDisplayTournament().isRemovable() ) {
    	removeTournamentMenuItem.setEnabled(false);
    } else {
    	removeTournamentMenuItem.setEnabled(true);
    }
  }

  public void setNewMainViewController(NewMainViewController newMainViewControllerArg) {
    newMainViewController = newMainViewControllerArg;
  }
  
  public void menuDeselected(javax.swing.event.MenuEvent e) {
  }

  public void setSaveTournamentMenuItem(javax.swing.JMenuItem saveTournamentMenuItemArg) {
    saveTournamentMenuItem = saveTournamentMenuItemArg;
  }
  
  public void setSaveAsTournamentMenuItem(javax.swing.JMenuItem saveAsTournamentMenuItemArg) {
    saveAsTournamentMenuItem = saveAsTournamentMenuItemArg;
  }
  public void setRemoveTournamentMenuItem (javax.swing.JMenuItem removeTournamentMenuItemArg) {
	  removeTournamentMenuItem = removeTournamentMenuItemArg;
  }
}