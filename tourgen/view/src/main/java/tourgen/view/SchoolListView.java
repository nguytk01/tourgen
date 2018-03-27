package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tourgen.controller.IController;
import tourgen.model.School;
import tourgen.model.SchoolManager;
import tourgen.util.ISchoolListView;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JList;

public class SchoolListView extends JFrame implements ISchoolListView{
	
	private Vector<School> schoolVector;
	private JList jList;
	private SchoolManager manager;
	private JScrollPane scrollPane;
	
	public SchoolListView(ActionListener listAddListener, 
			ActionListener listEditListener, 
			ActionListener listRemoveListener, SchoolManager managerArg) {
		manager = managerArg;
		setTitle("School List");
		this.setSize(483,463);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSchool = new JMenu("School");
		menuBar.add(mnSchool);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mnSchool.add(mntmAdd);
		mntmAdd.addActionListener(listAddListener);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mnSchool.add(mntmEdit);
		mntmEdit.addActionListener(listEditListener);
		
		JMenuItem mntmRemove = new JMenuItem("Remove");
		mnSchool.add(mntmRemove);
		mntmRemove.addActionListener(listRemoveListener);
		
		getContentPane().setLayout(null);
		
		schoolVector = new Vector<School>();
		jList = new JList(schoolVector);
		jList.setBounds(61, 35, 379, 330);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(jList);
		getContentPane().setLayout(new BorderLayout());
		jList.setVisibleRowCount(10);
		getContentPane().add(scrollPane);
		
	}
	
	@Override
	public void addSchoolToList(Object a) {
		School school = (School) a;
		schoolVector.add(school);
	}

	@Override
	public void removeSchoolFromList(Object a) {
		School school = (School)a;
		schoolVector.remove(school);
	}

	@Override
	public void showView() {
		this.setVisible(true);
	}
	
	public void populate(List<School> list) {
		schoolVector.addAll(list);
	}

	@Override
	public void populate() {
		List<School> list = manager.getSchoolList();
		schoolVector.clear();
		schoolVector.addAll(list);
	}

	@Override
	public Object getSelectedSchool() {
		if (jList.getSelectedIndex()!= -1) {
			return schoolVector.get(jList.getSelectedIndex());
		}
		else return null;

	}
}
	