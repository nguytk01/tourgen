package tourgen.view;

public class NewMainRightTournamentPane extends javax.swing.JTabbedPane {
  public NewMainRightTournamentPane(ReportTableView reportTableView, NewMainMapAndDetailsPanel mapDetailsPanel) {
    this.addTab("Map", mapDetailsPanel);
    this.addTab("Report", reportTableView);
  }
}
