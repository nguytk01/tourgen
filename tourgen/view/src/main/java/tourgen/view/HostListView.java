package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.matchers.AbstractMatcherEditor;
import ca.odell.glazedlists.matchers.CompositeMatcherEditor;
import ca.odell.glazedlists.matchers.Matcher;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.matchers.Matchers;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import tourgen.model.School;
import tourgen.model.SchoolManager;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import java.awt.Dimension;

public class HostListView extends JFrame{
	private JRadioButton chkHost;
	private JRadioButton chkNotWillingToHost;
	private SchoolManager schoolManager;
	private JList list;
	private FilterList<School> filterList;
	private JTextField textField;
	private JRadioButton chckbxAll;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblDoubleClickOn;
	private JPanel panel_5;
	
	/*public static void main(String[] args){
		SchoolManager schoolManager = new SchoolManager();
		schoolManager.initSchools();
		new HostListView(schoolManager).setVisible(true);
	}*/
	
	public HostListView(SchoolManager schoolManager){
		this.schoolManager = schoolManager;
		ButtonGroup radioButtonGroup = new ButtonGroup();

		EventList<School> eventList = GlazedLists.eventList(schoolManager.getSchoolList());
		ObservableElementList<School> observableSchoolList = new ObservableElementList<>(eventList, GlazedLists.beanConnector(School.class));
		SortedList<School> sortedList = new SortedList<School>(observableSchoolList, new SchoolComparator());
		SchoolComplexMatcherEditor schoolMatcherEditor = new SchoolComplexMatcherEditor();
		
		

		
		
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.CENTER);
		
		textField = new JTextField(20);
		textField.setPreferredSize(new Dimension(150, 22));
		panel_5.add(textField);
		
		MatcherEditor<School> textMatcherEditor = new TextComponentMatcherEditor<>(textField, new SchoolFilterator());
		EventList<MatcherEditor<School>> matcherEditorEventList = new BasicEventList<MatcherEditor<School>>();
		matcherEditorEventList.add(textMatcherEditor);
		matcherEditorEventList.add(schoolMatcherEditor);

		CompositeMatcherEditor<School> compositeMatcher = new CompositeMatcherEditor<School>( matcherEditorEventList );
		compositeMatcher.setMode(CompositeMatcherEditor.AND);
		
		filterList = new FilterList<>(sortedList, compositeMatcher);

		list = new JList(GlazedListsSwing.eventListModelWithThreadProxyList(filterList));
		JScrollPane scrollPane = new JScrollPane(list);
		
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		
		chkHost = new JRadioButton("Host");
		panel_3.add(chkHost);
		chkHost.setActionCommand("HostCheck");
		radioButtonGroup.add(chkHost);
		
		chkNotWillingToHost = new JRadioButton("Not willing to host");
		panel_3.add(chkNotWillingToHost);
		chkNotWillingToHost.setActionCommand("NoHostCheck");
		radioButtonGroup.add(chkNotWillingToHost);
		
		chckbxAll = new JRadioButton("All");
		panel_3.add(chckbxAll);
		chckbxAll.addActionListener(schoolMatcherEditor);
		radioButtonGroup.add(chckbxAll);
		
		panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.SOUTH);
		
		lblDoubleClickOn = new JLabel("Double click on a school to change host eligibility");
		panel_4.add(lblDoubleClickOn);
		
		chckbxAll.doClick();
		chkNotWillingToHost.addActionListener(schoolMatcherEditor);
		
		chkHost.addActionListener(schoolMatcherEditor);
		
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.setBounds(50,50,500,500);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Host Manager");
		list.addMouseListener(new HostListMouseClick());
		list.setCellRenderer(new HostEligibilityListCellRenderer());
	}
	
	private class SchoolComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			School a1 = (School) o1;
			School a2 = (School) o2;
			return a1.getName().compareTo(a2.getName());
		}
	}
	
	private class SchoolFilterator implements TextFilterator<School> {

		@Override
		public void getFilterStrings(List<String> arg0, School arg1) {
			arg0.add(arg1.getName());
		}
		
	}
	
	private class SchoolComplexMatcherEditor 
	extends AbstractMatcherEditor<School> 
	implements java.awt.event.ActionListener{
		private Matcher<School> hostMatcher;
		private Matcher<School> nonHostMatcher;
		public SchoolComplexMatcherEditor() {
			
			hostMatcher = new HostSchoolMatcher();
			nonHostMatcher = new NonHostSchoolMatcher();
			fireMatchAll();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == chkHost) {
				fireChanged( hostMatcher);
			} else if (e.getSource() == chkNotWillingToHost) {
				fireChanged(nonHostMatcher);
			} else if (e.getSource() == chckbxAll) {
				fireMatchAll();
			}
		}

			
	}
	
	private class HostSchoolMatcher implements Matcher<School> {

		@Override
		public boolean matches(School arg0) {
			return arg0.isEligibleToHost();
		}
	}
	
	private class NonHostSchoolMatcher implements Matcher<School> {

		@Override
		public boolean matches(School arg0) {
			return ! arg0.isEligibleToHost();
		}
	}
	
	private class AllSchoolMatcher implements Matcher<School> {
		@Override
		public boolean matches(School arg0) {
			return true;
		}
	}
	
	private class HostListMouseClick extends java.awt.event.MouseAdapter{

		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (e.getClickCount() == 2) {
				if (((School) filterList.get(list.locationToIndex(e.getPoint())) != null)) {
					schoolManager.toggleSchoolHostEligibility(((School) filterList.get(list.locationToIndex(e.getPoint()))));
				}
			}
		}
	}
	
	private class HostEligibilityListCellRenderer extends JLabel implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			if (value == null) return this;
			School school = (School) value;
			this.setText(school.getName());
			this.setOpaque(true);
			if ( !school.isEligibleToHost() ) {
				this.setBackground(new Color(211, 160, 176));
			} else if ( index % 2 == 0) {
				this.setBackground(new Color(198, 220, 255));
			} else {
				this.setBackground(Color.WHITE);
			}
			
			if (isSelected) {
				this.setBackground(list.getSelectionBackground());
			}
			
			return this;
		}
		
	}
}
