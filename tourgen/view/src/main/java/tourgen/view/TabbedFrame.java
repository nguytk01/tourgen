package tourgen.view;

public class TabbedFrame extends javax.swing.JFrame {
  private javax.swing.JPanel repositoryView;
  private javax.swing.JPanel reportTableView;
  private javax.swing.JPanel checkBoxPanel;
  private javax.swing.JPanel mapPanel;
  private javax.swing.JPanel tabPanel1;
  private javax.swing.JPanel tabPanel2;
  
  private javax.swing.JTabbedPane tabbedPane;
  public TabbedFrame(
      java.awt.event.ActionListener reportViewManageSchoolsMenuListener,
      javax.swing.event.ListSelectionListener tournamentSelectionListener,
      java.awt.event.WindowListener windowListener,
      javax.swing.JPanel repositoryViewArg,
      javax.swing.JPanel reportTableViewArg,
      javax.swing.JPanel checkBoxPanelArg,
      javax.swing.JPanel mapPanelArg) {
    this.addWindowListener(windowListener);
    repositoryView = repositoryViewArg;
    reportTableView = reportTableViewArg;
    checkBoxPanel = checkBoxPanelArg;
    mapPanel = mapPanelArg;
    initGui();
  }
  
  private void initGui() {
    tabbedPane = new javax.swing.JTabbedPane();
    tabPanel1 = new javax.swing.JPanel();
    tabPanel2 = new javax.swing.JPanel();
    
    tabPanel1.setLayout(new java.awt.BorderLayout());
    tabPanel2.setLayout(new java.awt.BorderLayout());
    
    tabPanel1.add(repositoryView, java.awt.BorderLayout.WEST);
    tabPanel1.add(reportTableView, java.awt.BorderLayout.CENTER);
    
    tabPanel2.add(checkBoxPanel, java.awt.BorderLayout.WEST);
    tabPanel2.add(mapPanel, java.awt.BorderLayout.CENTER);
    
    tabbedPane.addTab("Tab1",tabPanel1);
    tabbedPane.addTab("Tab2",tabPanel2);
    
    this.setBounds(50,50,1200,800);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().add(tabbedPane);
  }
}
