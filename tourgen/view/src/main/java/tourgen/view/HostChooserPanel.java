package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXList;

import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class HostChooserPanel extends javax.swing.JPanel implements tourgen.util.IHostChooserPanel {
  
  private javax.swing.DefaultListModel<tourgen.model.School> defaultListModel;
  private tourgen.controller.NewMainViewController newMainViewController;
  private JButton hostChooserButton;
  private JXList list;
  
  private Object selectedMeet;
  private Object newHost;
  
  private int customListSelectedIndex = -1;
  
  private Object hostUnderMouse = null;
  
  static final String ALTERNATIVE_HOST_MOUSE_ENTER = "mouse entered the host school.";
  static final String ALTERNATIVE_HOST_MOUSE_EXIT = "mouse exit the host school.";
  private JPanel headerPanel;
  private JPanel titlePanel;
  private JPanel meetPanel;
  private JLabel lblAlternativeHost;
  private JLabel lblMeetName;
  
  public HostChooserPanel() {
    
    defaultListModel = new  javax.swing.DefaultListModel<tourgen.model.School>();
    list = new JXList(defaultListModel);
    list.setComparator(new HostChooserComparator());
    list.setAutoCreateRowSorter(true);
    list.toggleSortOrder();
    list.addMouseListener(new HostChooserListMouseListener());
    list.addMouseMotionListener(new HostChooserListMouseListener());
    //list.addMouseWheelListener(new HostChooserListMouseListener());
    //list.setCellRenderer(new HostChooserCellRenderer());
    list.setCellRenderer(new HostChooserPanelPrivateListCellRenderer());
    setLayout(new BorderLayout(0, 0));
    
    headerPanel = new JPanel();
    add(headerPanel, BorderLayout.NORTH);
    headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
    
    titlePanel = new JPanel();
    headerPanel.add(titlePanel);
    
    lblAlternativeHost = new JLabel("Alternative Hosts for Meet:");
    titlePanel.add(lblAlternativeHost);
    
    meetPanel = new JPanel();
    headerPanel.add(meetPanel);
    
    lblMeetName = new JLabel("New label");
    meetPanel.add(lblMeetName);
    
    JScrollPane scrollPane = new JScrollPane(list);
    scrollPane.setViewportView(list);
    scrollPane.setPreferredSize(new java.awt.Dimension(300,300));
    //scrollPane.getVerticalScrollBar().addAdjustmentListener(new HostChooserListMouseListener());
    
    scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    add(scrollPane, java.awt.BorderLayout.CENTER);
    
    JPanel panel = new JPanel();
    hostChooserButton = new JButton("Choose");
    panel.add(hostChooserButton);
    add(panel,  java.awt.BorderLayout.SOUTH);
    
  }
  
  void setNewMainViewController(tourgen.controller.NewMainViewController newMainViewControllerArg) {
    newMainViewController = newMainViewControllerArg;
  }
  
  public void showHostList(Object meetArg, Object schoolArg) {
	  customListSelectedIndex = -1;
	  hostUnderMouse = null;
	  list.clearSelection();
    selectedMeet = meetArg;
    
    defaultListModel.removeAllElements();
    tourgen.model.Tournament tournament = newMainViewController.getCurrentlyDisplayTournament();
    tourgen.model.SchoolManager schoolManager = newMainViewController.getSchoolManager();
   
    java.util.List<tourgen.model.School> meetSchoolList = ((tourgen.model.Meet)meetArg).getSectionalSchoolsOfMeet();
    java.util.List<tourgen.model.School> listOfSchoolsNotWillingToHost = tournament.getListOfSchoolsNotWillingToHost();
    
    for (tourgen.model.School school : meetSchoolList){
      if (!listOfSchoolsNotWillingToHost.contains(school) && school != (((tourgen.model.Meet)meetArg).getHostSchool())){
        defaultListModel.addElement(school);
      }
    }
    lblMeetName.setText(((tourgen.model.Meet)meetArg).toString());
    hostChooserButton.setEnabled(false);
    /*for (tourgen.model.School school: schoolManager.getSchoolList()) {
      if (!listOfSchoolsNotWillingToHost.contains(school) && !meetSchoolList.contains(school)) {
        defaultListModel.addElement(school);
      }
    }*/
  }
  
  void createListeners() {
    tourgen.controller.HostChooserListener listener = new tourgen.controller.HostChooserListener(newMainViewController, this);
    hostChooserButton.addActionListener(listener);
  }

  public void parentPanelAboutToHide() {
    firePropertyChange(ALTERNATIVE_HOST_MOUSE_ENTER, hostUnderMouse, null);
    hostUnderMouse = null;
  }
  @Override
  public Object getSelectedMeet() {
    return selectedMeet;
  }

  @Override
  public Object getNewHost() {
    return list.getSelectedValue();
  }
  
  private class HostChooserComparator implements java.util.Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		return (((tourgen.model.School)o1).getName().compareTo(((tourgen.model.School)o2).getName()));
	}
	  
  }
  
  private class HostChooserListMouseListener implements java.awt.event.MouseListener,
  java.awt.event.MouseMotionListener, java.awt.event.MouseWheelListener, 
  java.awt.event.AdjustmentListener{
	   //private tourgen.model.School persistentSchool = null;
    @Override
    public void mouseClicked(MouseEvent e) {
      System.out.println("mouse entered host chooser panel.");
      hostChooserButton.setEnabled(true);
      tourgen.model.School school = (tourgen.model.School)list.getSelectedValue();//defaultListModel.getElementAt(list.locationToIndex(e.getPoint()));
      customListSelectedIndex = list.locationToIndex(e.getPoint());
      System.out.println("default List model just clicked on " + school);
      System.out.println("JList just clicked on " + list.getSelectedValue());

      list.setSelectedIndex(customListSelectedIndex);
      list.repaint();
    }

    
    @Override
    public void mouseEntered(MouseEvent e) {
      System.out.println("mouse entered host chooser panel.");
      tourgen.model.School school = (tourgen.model.School)list.getSelectedValue();//defaultListModel.getElementAt(list.locationToIndex(e.getPoint()));
      System.out.println("school is " + school);

      if (school != null) {
        firePropertyChange(ALTERNATIVE_HOST_MOUSE_ENTER, hostUnderMouse, school);
        hostUnderMouse = school;
      }
    }

    @Override
    public void mouseExited(MouseEvent e) {
      //if (list.getSelectedIndex() >= 0) {
      if (customListSelectedIndex >= 0) {
        firePropertyChange(ALTERNATIVE_HOST_MOUSE_ENTER, hostUnderMouse, list.getElementAt(customListSelectedIndex));
        list.setSelectedIndex(customListSelectedIndex);
        list.repaint();
      } else {
        firePropertyChange(ALTERNATIVE_HOST_MOUSE_ENTER, hostUnderMouse, null);
        hostUnderMouse = null;
        list.clearSelection();
      }
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	  int activeItemIndex = list.locationToIndex(e.getPoint());  
	  list.setSelectedIndex(activeItemIndex);
	  tourgen.model.School school = (tourgen.model.School)list.getSelectedValue();//defaultListModel.getElementAt(activeItemIndex);  
	    /*System.out.println(list.getCellRenderer().getListCellRendererComponent(list, null, defaultListModel.getSize() -1, false, false).getPreferredSize().getHeight());
	    Point mousePoint = (Point) e.getPoint().clone();
		SwingUtilities.convertPointFromScreen(mousePoint, list);//e.getPoint().getY());
		System.out.println(mousePoint.getY());
		System.out.println("default size " +defaultListModel.getSize() );
		System.out.println(list.getCellBounds(defaultListModel.getSize() - 2, defaultListModel.getSize() - 1));
		System.out.println(list.indexToLocation(defaultListModel.getSize() - 1));
		System.out.println("activeItemIndex " + activeItemIndex);
		//Rectangle lastCellRectangle = list.getCellBounds(defaultListModel.getSize() - 1, defaultListModel.getSize() - 1);
		//tourgen.model.School school = defaultListModel.getElementAt(list.locationToIndex(e.getPoint()));
	    /*if (mousePoint.getY() > lastCellRectangle.getMaxY() ) {
	      mouseExited(e);
	      return;
	    }*/
		if (school != null && school != hostUnderMouse) {
	    	firePropertyChange(ALTERNATIVE_HOST_MOUSE_ENTER, hostUnderMouse, school);
	    	hostUnderMouse = school;
	        
	    }
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int activeItemIndex = list.locationToIndex(e.getPoint());
		System.out.println("activeItemIndex " + activeItemIndex);
		tourgen.model.School school = (tourgen.model.School)list.getElementAt(activeItemIndex);//defaultListModel.getElementAt(activeItemIndex);
	    if (school != null && school != hostUnderMouse) {
	    	firePropertyChange(ALTERNATIVE_HOST_MOUSE_ENTER, hostUnderMouse, school);
	    	hostUnderMouse = school;
	        
	    }
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		//System.out.println("scrolle.d");
		int activeItemIndex = list.locationToIndex(MouseInfo.getPointerInfo().getLocation());
		System.out.println("activeItemIndex " + activeItemIndex);
		if (activeItemIndex >= 0){
			tourgen.model.School school = (tourgen.model.School)list.getElementAt(activeItemIndex);//defaultListModel.getElementAt(activeItemIndex);
			if (school != null && school != hostUnderMouse) {
		    	firePropertyChange(ALTERNATIVE_HOST_MOUSE_ENTER, hostUnderMouse, school);
		    	hostUnderMouse = school;
		    }
		}
	}

	
  }
  private class HostChooserPanelPrivateListCellRenderer extends JLabel implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			this.setOpaque(true);
			if (value == null) return this;
			this.setText(value.toString());
			System.out.println("customeSelectedIndex is " + customListSelectedIndex);
			System.out.println("index to be drawn is " + index);
			if ( isSelected && index != customListSelectedIndex ){
				this.setBackground(new Color(207, 224, 252));
			} else if ( index == customListSelectedIndex) {
				this.setBackground(list.getSelectionBackground());
			} else if ( !isSelected ) {
				this.setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
}
