package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tourgen.controller.IController;
import tourgen.model.School;
import tourgen.util.ISchoolListView;

import javax.swing.JCheckBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class SchoolListView extends JFrame implements ISchoolListView{
	
	private IController controller;
	private DefaultListModel<School> listModel;
	private JList jList;
	public void addSchool(School obj)
	{
		listModel.addElement(obj);
		
	}
	
	public void removeSchool(School obj)
	{
		listModel.removeElement(obj);
		
	}
	
	public SchoolListView(IController controllerArg) {
		setTitle("School List");
		this.setSize(483,463);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		controller = controllerArg;
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSchool = new JMenu("School");
		menuBar.add(mnSchool);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mnSchool.add(mntmAdd);
		ListAddListener listAddListener = new ListAddListener();
		mntmAdd.addActionListener(listAddListener);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mnSchool.add(mntmEdit);
		ListEditListener listEditListener = new ListEditListener();
		mntmEdit.addActionListener(listEditListener);
		
		JMenuItem mntmRemove = new JMenuItem("Remove");
		mnSchool.add(mntmRemove);
		ListRemoveListener listRemoveListener = new ListRemoveListener();
		mntmRemove.addActionListener(listRemoveListener);
		
		getContentPane().setLayout(null);
		
		listModel = new DefaultListModel();
		jList = new JList(listModel);
		jList.setBounds(61, 35, 379, 330);
		getContentPane().add(jList);
	}
	
	public class ListAddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			controller.listAddEvent(arg0);
		}
		
	}
	
	public class ListEditListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (jList.isSelectionEmpty()) return;
			controller.listEditEvent(e,listModel.get(jList.getSelectedIndex()));
		}
		
	}
	
	public class ListRemoveListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.listRemoveEvent(e,listModel.get(jList.getSelectedIndex()));
		}
		
	}

	@Override
	public void addSchoolToList(Object a) {
		School school = (School) a;
		listModel.addElement(school);
	}

	@Override
	public void removeSchoolFromList(Object a) {
		School school = (School)a;
		listModel.removeElementAt(listModel.indexOf(school));
	}

	@Override
	public void showView() {
		this.setVisible(true);
	}
}
	