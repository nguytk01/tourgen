package tourgen.view;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JPanel;

import javax.swing.event.ListSelectionEvent;

import javax.swing.event.ListSelectionListener;

import tourgen.util.IAvailableMeetsPanel;
public class AvailableMeetsPanel extends javax.swing.JPanel 
implements IAvailableMeetsPanel {
  
  private Object selectedSchool;
  private Object newMeet;
  private Object oldMeet;
  
  private tourgen.controller.NewMainViewController newMainViewController;
  private  javax.swing.DefaultListModel availableMeetListModel;
  private javax.swing.JList<tourgen.model.Meet> availableMeetGraphicalList;
  private tourgen.controller.ChangeCompetitionSiteListener changeCompetitionSiteListener;
  private javax.swing.JButton changeCompetitionSiteButton;
  private JPanel headerPanel;
  private JLabel lblAvailableSectionalMeets;
  private JLabel lblSchoolName;
  private JPanel schoolNamePanel;
  private JPanel titlePanel;
  public AvailableMeetsPanel() {
    
    availableMeetListModel = new javax.swing.DefaultListModel();
    
    this.setLayout(new java.awt.BorderLayout());
    
    headerPanel = new JPanel();
    add(headerPanel, BorderLayout.NORTH);
    headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
    
    titlePanel = new JPanel();
    headerPanel.add(titlePanel);
    
    lblAvailableSectionalMeets = new JLabel("Available Sectional Meets for:");
    titlePanel.add(lblAvailableSectionalMeets);
    
    schoolNamePanel = new JPanel();
    headerPanel.add(schoolNamePanel);
    
    lblSchoolName = new JLabel("New label");
    schoolNamePanel.add(lblSchoolName);
    availableMeetGraphicalList = 
        new JList<tourgen.model.Meet>(availableMeetListModel);
    availableMeetGraphicalList.addListSelectionListener(new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			changeCompetitionSiteButton.setEnabled(true);
		}
	});
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(availableMeetGraphicalList);
    add(scrollPane, java.awt.BorderLayout.CENTER);
    
    javax.swing.JPanel panel = new javax.swing.JPanel();
    
    changeCompetitionSiteButton = new javax.swing.JButton("Change");
    panel.add(changeCompetitionSiteButton);
    changeCompetitionSiteButton.addActionListener(changeCompetitionSiteListener);
    
    
    add(panel, java.awt.BorderLayout.SOUTH);
  }
  void setNewMainViewController(
      tourgen.controller.NewMainViewController newMainViewControllerArg) {
    newMainViewController = newMainViewControllerArg;
  }
  
  public void showAvailableMeets(Object meet, Object school) {
   selectedSchool = school;
   oldMeet = meet;
   lblSchoolName.setText(((tourgen.model.School)school).getName());
   java.util.List<tourgen.model.Meet> sectionalMeetSuggestionList = 
       newMainViewController.getAvailableSectionalMeets(meet, school);
   availableMeetListModel.removeAllElements();
   for (tourgen.model.Meet sectionalMeet : sectionalMeetSuggestionList) {
     availableMeetListModel.addElement(sectionalMeet);
   }
   changeCompetitionSiteButton.setEnabled(false);
  }
  
  void createListeners() {
    changeCompetitionSiteButton.addActionListener(
        new tourgen.controller.ChangeCompetitionSiteListener(
            newMainViewController,this));
    
  }
  @Override
  public Object getSelectedSchool() {
    return selectedSchool;
  }
  
  @Override
  public Object getNewMeet() {
    return availableMeetGraphicalList.getSelectedValue();
  }
  
  @Override
  public Object getOldMeet() {
    return oldMeet;
  }
}
