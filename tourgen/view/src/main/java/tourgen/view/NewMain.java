package tourgen.view;

public class NewMain extends javax.swing.JFrame {
  public NewMain(tourgen.controller.MapController mapController, javax.swing.JPanel mapPanel) {

    setTitle("Tournament Generator");
    setBounds(50, 50, 1500, 800);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
    setJMenuBar(menuBar);

    /**
     * File menu
     */
    
    javax.swing.JMenu mnFile = new javax.swing.JMenu("File");
    menuBar.add(mnFile);
    
    javax.swing.JMenuItem mntmExit = new javax.swing.JMenuItem("Exit");
    mnFile.add(mntmExit);

    /**
     * Tournament menu
     */
    javax.swing.JMenu mnTournament = new javax.swing.JMenu("Tournament");
    menuBar.add(mnTournament);

    javax.swing.JMenuItem mntmOpenTournament = new javax.swing.JMenuItem("Open a tournament...");
    mnTournament.add(mntmOpenTournament);
    
    javax.swing.JMenuItem mntmSaveTournament = new javax.swing.JMenuItem("Save tournament");
    mnTournament.add(mntmSaveTournament);
    
    javax.swing.JMenuItem mntmSaveAsTournament = new javax.swing.JMenuItem("Save tournament as...");
    mnTournament.add(mntmSaveAsTournament);
    
    javax.swing.JMenuItem mntmRemoveTournament = new javax.swing.JMenuItem("Remove a tournament");
    mnTournament.add(mntmRemoveTournament);
    
    javax.swing.JMenu mnHost = new javax.swing.JMenu("Host");
    menuBar.add(mnHost);

    javax.swing.JMenuItem mntmHostManager = new javax.swing.JMenuItem("Host Manager...");
    mnHost.add(mntmHostManager);

    ReportTableView reportTableView = new ReportTableView();
    DetailsPanel detailsPanel = new DetailsPanel();
    HostChooserPanel hostChooserPanel = new HostChooserPanel();
    AvailableMeetsPanel availableMeetsPanel = new AvailableMeetsPanel();
    NewMainMapSidePane mapSidePane = new NewMainMapSidePane(detailsPanel, hostChooserPanel, availableMeetsPanel);
    NewMainMapAndDetailsPanel mapAndDetailsPanel = new NewMainMapAndDetailsPanel(mapPanel, mapSidePane);
    
    NewMainLeftTournamentPane newMainLeftTournamentPane = new NewMainLeftTournamentPane(mapController);
    NewMainRightTournamentPane newMainRightTournamentPane = 
        new NewMainRightTournamentPane(reportTableView, mapAndDetailsPanel);
    javax.swing.JSplitPane splitPane = new javax.swing.JSplitPane(javax.swing.JSplitPane.HORIZONTAL_SPLIT,
        newMainLeftTournamentPane, newMainRightTournamentPane);
    getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);
  }

}
