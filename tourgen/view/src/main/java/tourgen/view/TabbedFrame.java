package tourgen.view;

public class TabbedFrame extends javax.swing.JFrame {
  private javax.swing.JPanel repositoryView;
  private javax.swing.JPanel reportTableView;
  private javax.swing.JPanel checkBoxPanel;
  private javax.swing.JPanel mapPanel;
  private javax.swing.JPanel tabPanel1;
  private javax.swing.JPanel tabPanel2;
  
  private javax.swing.JTabbedPane tabbedPane;
  
  /**
   * Construct a tabbed frame (main frame of this application).
   * @param reportViewManageSchoolsMenuListener listener for the report table's collapsible pane
   * @param tournamentSelectionListener listener for tournament selection event
   * @param windowListener listener for window closing events
   * @param repositoryViewArg the view for the repository (the listView of the report table)
   * @param reportTableViewArg the table view of the report table
   * @param checkBoxPanelArg the check box tree of the map view.
   * @param mapPanelArg the map panel of the map view
   * @param addTournamentMenuItemListener the action object for the addTournamentMenuItem.
It also listens to button events.
   * @param tournamentMenuListener the listener for tournament menu.
   */
  
  public TabbedFrame(
      java.awt.event.ActionListener reportViewManageSchoolsMenuListener,
      javax.swing.event.ListSelectionListener tournamentSelectionListener,
      java.awt.event.WindowListener windowListener,
      javax.swing.JPanel repositoryViewArg,
      javax.swing.JPanel reportTableViewArg,
      javax.swing.JPanel checkBoxPanelArg,
      javax.swing.JPanel mapPanelArg,
      javax.swing.Action addTournamentMenuItemListener,
      javax.swing.Action tournamentMenuListener) {
    this.addWindowListener(windowListener);
    repositoryView = repositoryViewArg;
    reportTableView = reportTableViewArg;
    checkBoxPanel = checkBoxPanelArg;
    mapPanel = mapPanelArg;
    initGui(addTournamentMenuItemListener, tournamentMenuListener);
  }
  
  private void initGui(javax.swing.Action addTournamentMenuItemListener, 
      javax.swing.Action tournamentMenuListener) {
    javax.swing.JScrollPane reportTableViewScrollPane = 
        new javax.swing.JScrollPane(reportTableView);
    reportTableViewScrollPane.setHorizontalScrollBarPolicy(
        javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    reportTableViewScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    
    tabbedPane = new javax.swing.JTabbedPane();
    tabPanel1 = new javax.swing.JPanel();
    tabPanel2 = new javax.swing.JPanel();
    
    tabPanel1.setLayout(new java.awt.BorderLayout());
    tabPanel2.setLayout(new java.awt.BorderLayout());
    
    tabPanel1.add(repositoryView, java.awt.BorderLayout.WEST);
    tabPanel1.add(reportTableViewScrollPane, java.awt.BorderLayout.CENTER);
    
    tabPanel2.add(checkBoxPanel, java.awt.BorderLayout.WEST);
    tabPanel2.add(mapPanel, java.awt.BorderLayout.CENTER);
    
    tabbedPane.addTab("Report Table",tabPanel1);
    tabbedPane.addTab("Map",tabPanel2);
    
    javax.swing.JMenuBar  menuBar = new javax.swing.JMenuBar();
    javax.swing.JMenu tournamentMenu = new javax.swing.JMenu(tournamentMenuListener);
    tournamentMenu.addMenuListener((javax.swing.event.MenuListener)tournamentMenuListener);
    javax.swing.JMenuItem addTournamentMenuItem = 
        new javax.swing.JMenuItem(addTournamentMenuItemListener);
    
    this.setJMenuBar(menuBar);
    menuBar.add(tournamentMenu);
    tournamentMenu.add(addTournamentMenuItem);
    
    
    this.setBounds(50,50,1200,800);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().add(tabbedPane);
    this.setTitle("Tournament Generator");
  }
}
