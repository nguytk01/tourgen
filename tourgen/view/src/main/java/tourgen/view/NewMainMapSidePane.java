package tourgen.view;

public class NewMainMapSidePane extends javax.swing.JTabbedPane {
  public NewMainMapSidePane(DetailsPanel detailsPanel, 
      HostChooserPanel hostChooserPanel,
      AvailableMeetsPanel availableMeetPanel) {
    this.addTab("Details", detailsPanel );
    this.addTab("hosts", hostChooserPanel);
    this.addTab("Meets", availableMeetPanel);
  }
}
