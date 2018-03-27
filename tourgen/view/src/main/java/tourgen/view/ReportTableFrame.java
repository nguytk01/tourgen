package tourgen.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ListSelectionListener;

import tourgen.util.IReportTableFrame;
import tourgen.util.IReportTableView;
import tourgen.util.IRepositoryView;



public class ReportTableFrame extends JFrame implements IReportTableFrame{
	private JMenu schoolsMenu;
	private RepositoryView repoView;
	private ReportTableView reportView;
	public ReportTableFrame(ActionListener reportViewManageSchoolsMenuListener, 
			ListSelectionListener tournamentSelectionListener) {
		setTitle("Cross Country Report Table");
		setSize(500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		repoView = new RepositoryView(tournamentSelectionListener);
		reportView = new ReportTableView();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(repoView, BorderLayout.EAST);
		this.getContentPane().add(reportView,BorderLayout.CENTER);
		
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
	public IReportTableView returnReportTableView() {
		return reportView;
	}
	
	public IRepositoryView returnRepositoryView() {
		return repoView;
	}
}
