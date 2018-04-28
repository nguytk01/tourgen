package tourgen.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tourgen.controller.MenuItemRemoveTournamentListener;
import tourgen.controller.NewMainViewController;
import tourgen.controller.TournamentChooserComboBoxListener;
import tourgen.model.SchoolManager;

public class NewMain extends javax.swing.JFrame implements tourgen.util.INewMain {
  
  private NewMainLeftTournamentPane newMainLeftTournamentPane;
  private NewMainRightTournamentPane newMainRightTournamentPane;
  private javax.swing.JFrame thisFrame;
  
  private HostListView hostListView; 
  
  public NewMain(tourgen.controller.MapController mapController,
      javax.swing.JPanel mapPanel, 
      ReportTableView reportTableView,
      NewMainMapSidePane mapSidePane,
      SchoolManager schoolManager) {

	thisFrame = this;
    setTitle("Tournament Generator");
    setBounds(50, 50, 1500, 800);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
    setJMenuBar(menuBar);

    /**
     * File menu
     */
    
    javax.swing.JMenu mnFile = new javax.swing.JMenu("File");
    menuBar.add(mnFile);
    
    javax.swing.JMenuItem mntmExit = new javax.swing.JMenuItem("Exit");
    mntmExit.setMnemonic(java.awt.event.KeyEvent.VK_E);
    mntmExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke('Q', java.awt.Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));

    mnFile.add(mntmExit);
    mntmExit.addActionListener(new java.awt.event.ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			thisFrame.dispatchEvent(new java.awt.event.WindowEvent(thisFrame, java.awt.event.WindowEvent.WINDOW_CLOSING));
		}
    });
    /**
     * Tournament menu
     */
    javax.swing.JMenu mnTournament = new javax.swing.JMenu("Tournament");
    menuBar.add(mnTournament);

    tourgen.controller.TournamentMenuListener tournamentMenuListener = new tourgen.controller.TournamentMenuListener();
    mnTournament.addMenuListener(tournamentMenuListener);
    
    //javax.swing.JMenuItem mntmOpenTournament = new javax.swing.JMenuItem("Open a tournament...");
    //mnTournament.add(mntmOpenTournament);
    
    javax.swing.JMenuItem mntmSaveTournament = new javax.swing.JMenuItem("Save tournament");
    mnTournament.add(mntmSaveTournament);
    mntmExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke('Q', java.awt.Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
    
    tourgen.controller.MenuItemSaveTournamentListener saveTournamentListener = 
        new tourgen.controller.MenuItemSaveTournamentListener();
    mntmSaveTournament.addActionListener(saveTournamentListener);
    
    javax.swing.JMenuItem mntmSaveAsTournament = new javax.swing.JMenuItem("Save tournament as...");
    mnTournament.add(mntmSaveAsTournament);
    
    tourgen.controller.MenuItemSaveAsTournamentListener saveAsTournamentListener = 
        new tourgen.controller.MenuItemSaveAsTournamentListener();
    
    mntmSaveAsTournament.addActionListener(saveAsTournamentListener);
    
    javax.swing.JMenuItem mntmRemoveTournament = new javax.swing.JMenuItem("Remove a tournament");
    mnTournament.add(mntmRemoveTournament);
    
    tournamentMenuListener.setSaveTournamentMenuItem(mntmSaveTournament);
    tournamentMenuListener.setSaveAsTournamentMenuItem(mntmSaveAsTournament);
    tournamentMenuListener.setRemoveTournamentMenuItem(mntmRemoveTournament);
    
    MenuItemRemoveTournamentListener mntmRemoveTournamentListener = new MenuItemRemoveTournamentListener(); 
    mntmRemoveTournament.addActionListener(mntmRemoveTournamentListener);
    
    javax.swing.JMenu mnHost = new javax.swing.JMenu("Host");
    menuBar.add(mnHost);

    javax.swing.JMenuItem mntmHostManager = new javax.swing.JMenuItem("Host Manager...");
    mnHost.add(mntmHostManager);

    NewMainMapAndDetailsPanel mapAndDetailsPanel = new NewMainMapAndDetailsPanel(mapController, mapPanel, mapSidePane);
    
    /* create a tournament combobox for the left pane of the window */
    TournamentChooserComboBoxListener tournamentChooserComboBoxListener = new TournamentChooserComboBoxListener();
    
    /* create 2 big panes: left and right panes for the window. */
    newMainLeftTournamentPane = new NewMainLeftTournamentPane(mapController, tournamentChooserComboBoxListener);
    newMainRightTournamentPane = new NewMainRightTournamentPane(reportTableView, mapAndDetailsPanel);
    javax.swing.JSplitPane splitPane = new javax.swing.JSplitPane(javax.swing.JSplitPane.HORIZONTAL_SPLIT,
        newMainLeftTournamentPane, newMainRightTournamentPane);
    
    /* create NewMainViewController */
    NewMainViewController newMainViewController = new NewMainViewController(
        newMainLeftTournamentPane, 
        newMainRightTournamentPane,
        schoolManager);
    /* set TournamentChooserComboBoxListener's controller to NewMainViewController */
    tournamentChooserComboBoxListener.setNewMainViewController(newMainViewController);
    
    /*set AvailableMeetsPanel's controller to NewMainViewController. It needs it for getting sectional meet list */
    mapSidePane.getAvailableMeetsPanel().setNewMainViewController(newMainViewController);
    mapSidePane.getHostChooserPanel().setNewMainViewController(newMainViewController);
    
    /* create listeners for availableMeetsPanel and HostChooserPanel */
    /* listeners need to know the panel and the controller. That is why they are created
     * after the controller is plugged in two panels.
     */
    mapSidePane.getAvailableMeetsPanel().createListeners();
    mapSidePane.getHostChooserPanel().createListeners();
    
    /*set TournamentMenuListener's controller to NewMainViewController. It needs it to coordinate saving, saving as feature */
    tournamentMenuListener.setNewMainViewController(newMainViewController);
    
    /*set saveTournamentListener's controller to NewMainViewController */
    saveTournamentListener.setNewMainViewController(newMainViewController);
    
    /*set saveAsTournamentListener's controller to NewMainViewController*/
    saveAsTournamentListener.setNewMainViewController(newMainViewController);
    
    /* set removeTournamentListener's controller to NewMainViewController*/
    mntmRemoveTournamentListener.setNewMainViewController(newMainViewController);
    
    /* set NewMainViewController's NewMain window to this instance */
    newMainViewController.setMainView(this);
    
    getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);
    
    javax.swing.ToolTipManager.sharedInstance().setInitialDelay(10);
    
    hostListView = new HostListView(schoolManager);
    
    mntmHostManager.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			hostListView.setVisible(true);
		}
	});
    
  }
  
  public void initUserInterface() {
    newMainLeftTournamentPane.initUserInterface();
    newMainRightTournamentPane.initUserInterface();
  }

  @Override
  public boolean showOverwriteTournamentConfirmationDialog() {
    int respond = javax.swing.JOptionPane.showConfirmDialog(this, "Do you want to overwrite the existing tournament ?");
    if (respond == javax.swing.JOptionPane.OK_OPTION) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String showSaveAsTournamentConfirmationDialog() {
    String newName = javax.swing.JOptionPane.showInputDialog("Please enter new name");
    return newName;
  }

}
