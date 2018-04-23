package tourgen.view;

public class NewMainMapAndDetailsPanel2 extends javax.swing.JSplitPane {
  private tourgen.controller.MapController mapController;
  private NewMainMapSidePane mapSidePane;
  public NewMainMapAndDetailsPanel2(tourgen.controller.MapController mapControllerArg, javax.swing.JPanel mapPanel, NewMainMapSidePane mapSidePaneArg) {
    super(javax.swing.JSplitPane.HORIZONTAL_SPLIT, mapPanel, mapSidePaneArg);
    java.awt.Dimension minimumSize = new java.awt.Dimension(900, 500);
    mapPanel.setMinimumSize(minimumSize);
    mapController = mapControllerArg;
    mapSidePane = mapSidePaneArg;
  }

  public void setActiveTournament(Object tournament) {
    mapSidePane.resetPanel();
    mapController.emptyMap();
  }

  public void initUserInterface() {
    
  }
}
