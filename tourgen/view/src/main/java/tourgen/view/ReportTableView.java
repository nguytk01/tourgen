package tourgen.view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.VerticalLayout;

import tourgen.model.Tournament;
import tourgen.util.IReportTableView;
import javax.swing.border.LineBorder;
import java.awt.Color;

/*import org.jdesktop.swingx.JXCollapsiblePane;*/

public class ReportTableView extends JPanel implements IReportTableView{
	List<CollapsibleStagePanel> stageList;
	Listener listener;
	public ReportTableView() {
		setSize(400,400);
		this.setBorder(new LineBorder(new Color(0, 0, 0)));

		this.setLayout(new VerticalLayout());
		stageList = new ArrayList<CollapsibleStagePanel>();
		
		Listener listener = new Listener();
	}

	@Override
	public void showReport() {
		this.setVisible(true);
	}

	public class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean collapsedState = false;
			for (int i = 0; i <= stageList.size(); i++)
				if (arg0.getSource() != stageList.get(i))
					stageList.get(i).setCollapsed(true);
				else stageList.get(i).setCollapsed(!stageList.get(i).isCollapsed());
			repaint();
		}
		
	}

	@Override
	public void display(Object arg) {
		Tournament tournament = (Tournament) arg;
		if (stageList.isEmpty() == false) stageList.clear();
		List<List<String>> stageDescriptionsList = tournament.getReport();
		for (int i = 0 ; i < stageDescriptionsList.size(); i++) {
			CollapsibleStagePanel panel = new CollapsibleStagePanel(stageDescriptionsList.get(i).get(0), listener);
			stageList.add(panel);
			this.add(panel);
		}
		
	}
}

	