package tourgen.view;

public class NewMainMapAndDetailsPanel extends javax.swing.JSplitPane {
  public NewMainMapAndDetailsPanel(javax.swing.JPanel mapPanel, NewMainMapSidePane mapSidePane) {
    super(javax.swing.JSplitPane.HORIZONTAL_SPLIT, mapPanel, mapSidePane);
    java.awt.Dimension minimumSize = new java.awt.Dimension(900, 500);
    mapPanel.setMinimumSize(minimumSize);
  }
}
