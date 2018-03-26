package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class SchoolListView extends JFrame{

	public SchoolListView() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSchool = new JMenu("School");
		menuBar.add(mnSchool);

		JMenuItem mntmAdd = new JMenuItem("Add");
		mnSchool.add(mntmAdd);

		JMenuItem mntmEdit = new JMenuItem("Edit");
		mnSchool.add(mntmEdit);

		JMenuItem mntmRemove = new JMenuItem("Remove");
		mnSchool.add(mntmRemove);
		getContentPane().setLayout(null);

		JList list = new JList();
		list.setBounds(61, 35, 379, 330);
		getContentPane().add(list);
	}

}
