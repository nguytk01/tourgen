package tourgen.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import tourgen.model.Repository;
import tourgen.util.IReportTableFrame;
import tourgen.util.IReportTableView;
import tourgen.util.IRepositoryView;

public class ReportTableFrame extends JFrame implements IReportTableFrame {
  private JMenu schoolsMenu;
  //private RepositoryView repoView;
  private ReportTableView reportView;

  /**
   * Build a frame for the report table.
   * @param reportViewManageSchoolsMenuListener ActionListener for the menu of the frame
   * @param tournamentSelectionListener a SelectionListener to listen to tournament selection
   * @param model a repository object.
   */
  public ReportTableFrame(ActionListener reportViewManageSchoolsMenuListener,
      ListSelectionListener tournamentSelectionListener, Repository model) {
    setTitle("Cross Country Report Table");
    setBounds(200, 50, 1400, 800);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //repoView = new RepositoryView(tournamentSelectionListener);
    reportView = new ReportTableView();
    JScrollPane scrollPane = new JScrollPane(reportView);
    // scrollPane.setPreferredSize(new Dimension(100,100));
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    // scrollPane.setViewportView(reportView);

    this.getContentPane().setLayout(new BorderLayout());
    //this.getContentPane().add(repoView, BorderLayout.WEST);
    this.getContentPane().add(scrollPane, BorderLayout.CENTER);

    JMenuBar menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);

    schoolsMenu = new JMenu("Manage Schools");
    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);

    JMenuItem manageSchoolMenuItem = new JMenuItem("Manage schools");
    fileMenu.add(manageSchoolMenuItem);
    manageSchoolMenuItem.addActionListener(reportViewManageSchoolsMenuListener);

  }

  
  @Override
  public void showView() {
    this.setVisible(true);

  }

  @Deprecated
  public IReportTableView returnReportTableView() {
    return reportView;
  }

  @Deprecated
  public IRepositoryView returnRepositoryView() {
    return null;
  }
  
  javax.swing.JPanel getRepositoryView() {
    return null;
  }
  
  javax.swing.JPanel getReportView() {
    return reportView;
  }
}
