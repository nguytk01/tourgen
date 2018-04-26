package tourgen.view;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NewMainRightTournamentPane extends javax.swing.JTabbedPane implements tourgen.util.INewMainViewPane{
  private ReportTableView reportTableView;
  private NewMainMapAndDetailsPanel mapAndDetailsPane;
  
  public NewMainRightTournamentPane(ReportTableView reportTableViewArg, NewMainMapAndDetailsPanel mapDetailsPanelArg) {
    this.addTab("Map", mapDetailsPanelArg);
    javax.swing.JScrollPane reportScrollPane = new javax.swing.JScrollPane(reportTableViewArg);
    reportScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    this.addTab("Report", reportScrollPane);
    reportTableView = reportTableViewArg;
    mapAndDetailsPane = mapDetailsPanelArg;
    this.addChangeListener(new MapAndReportTabChangeListener());
  }

  @Override
  public void setActiveTournament(Object tournament) {
    mapAndDetailsPane.setActiveTournament(tournament);
    reportTableView.setActiveTournament(tournament);
  }

  public void initUserInterface() {
    mapAndDetailsPane.initUserInterface();
  }
  
  private class MapAndReportTabChangeListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent arg0) {
      if (getSelectedIndex() == 1) {
    	
        reportTableView.display();
       revalidate();
        repaint();
      }
      
    }
  
  }
}
