package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.VerticalLayout;

import tourgen.model.Tournament;
import tourgen.util.IReportTableView;


/*import org.jdesktop.swingx.JXCollapsiblePane;*/

public class ReportTableView extends JPanel implements IReportTableView {
  List<CollapsibleStagePanel> stageList;
  Listener listener;
  JPanel northPanel;
  ReportContentRenderer reportContentRenderer;
  private Tournament currentTournament;
  private boolean currentTournamentAlreadyRendered = false;
  /**
   * Build a report table view.
   */
  public ReportTableView() {
    reportContentRenderer = new ReportContentRenderer();
    // setSize(400,400);
    this.setBorder(new LineBorder(new Color(0, 0, 0)));
    northPanel = new JPanel();

    this.setLayout(new BorderLayout());
    stageList = new ArrayList<CollapsibleStagePanel>();

    listener = new Listener();
    this.add(northPanel, BorderLayout.NORTH);
    northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
  }

  @Override
  public void showReport() {
    this.setVisible(true);
  }

  public class Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      boolean collapsedState = false;
      Object sourcePanel = null;
      for (int i = 0; i < stageList.size(); i++) {
        sourcePanel = ((JCustomizedButton) arg0.getSource()).getSwingParent();
        if (sourcePanel != stageList.get(i)) {
          stageList.get(i).setCollapsed(true);
        } else {
          stageList.get(i).setCollapsed(!stageList.get(i).isCollapsed());
        }
      }
      repaint();
    }
  }

  public void display() {
    display(currentTournament);
    showReport();
  }
  
  @Override
  public void display(Object arg) {
    Tournament tournament = (Tournament) arg;
    if (currentTournament == tournament && currentTournamentAlreadyRendered == true) {
      System.out.println("skip rendering.");
      return;
    } else if (currentTournament == tournament && currentTournamentAlreadyRendered == false){
      currentTournamentAlreadyRendered = true;
    } else if (currentTournament != tournament) {
      currentTournament = tournament;
      currentTournamentAlreadyRendered = true;
    }
    
    
    if (stageList.isEmpty() == false) {
      stageList.clear();
      northPanel.removeAll();
      
    }
    HashMap<String, String> stageDescriptionsMap = 
        reportContentRenderer.getTournamentReport(tournament);
    for (String stageTitle : stageDescriptionsMap.keySet()) {
      CollapsibleStagePanel panel = 
          new CollapsibleStagePanel(stageTitle, stageDescriptionsMap.get(stageTitle),
          listener);
      stageList.add(panel);
      panel.setCollapsed(true);
      northPanel.add(panel);
    }
    repaint();
    revalidate();

  }

  public void setActiveTournament(Object tournamentArg) {
    
    if (currentTournament != tournamentArg) {
      System.out.println("set active tournament");
      currentTournament = (Tournament) tournamentArg;
      currentTournamentAlreadyRendered = false;
    }
  }
  

}

  /*@Override
  public void display(Object tournament) {
    // TODO Auto-generated method stub
    
  }*/