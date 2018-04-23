package tourgen.view;

import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXCollapsiblePane;

import tourgen.controller.TournamentChooserComboBoxListener;

public class NewMainMapSidePane extends javax.swing.JTabbedPane implements tourgen.util.IMapSidePane {
  private HostChooserPanel hostChooserPanel;
  private AvailableMeetsPanel availableMeetsPanel;
  private DetailsPanel detailsPanel;
  private JXCollapsiblePane collapsiblePanel;
  private JPanel glassPane;
  private NewMainMapAndDetailsPanel grandParentPane;
  public NewMainMapSidePane(DetailsPanel detailsPanelArg,
      HostChooserPanel hostChooserPanelArg,
      AvailableMeetsPanel availableMeetsPanelArg) {
    detailsPanel = detailsPanelArg;
    hostChooserPanel = hostChooserPanelArg;
    availableMeetsPanel = availableMeetsPanelArg;
    this.addTab("Details", detailsPanel);
    this.addTab("hosts", hostChooserPanel);
    this.addTab("Meets", availableMeetsPanel);
    
    hostChooserPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent arg0) {
        firePropertyChange(arg0.getPropertyName(), arg0.getOldValue(), arg0.getNewValue());
      }
    });
    
  }

  @Override
  public void displayPinInformation(Object meetArg, Object schoolArg) {
    tourgen.model.Meet meet = (tourgen.model.Meet) meetArg;
    tourgen.model.School school = (tourgen.model.School) schoolArg;
    if (collapsiblePanel != null) {
      collapsiblePanel.setCollapsed(false);
    }
    System.out.println("hey");
    
  }

  public void resetPanel() {
    this.setEnabledAt(1, false);
    this.setEnabledAt(2, false);
  }

  @Override
  public void displayPinRegularInformation(Object meet, Object school) {
    this.setEnabledAt(1, false);
    this.setEnabledAt(2, true);
    if (collapsiblePanel != null) {
      glassPane.setVisible(true);
      collapsiblePanel.setCollapsed(false);
    }
    System.out.println("displayPinRegularInformation");

    availableMeetsPanel.showAvailableMeets(meet, school);
    this.setSelectedIndex(2);
  }

  @Override
  public void displayPinHostInformation(Object meet, Object school) {
    this.setEnabledAt(1, true);
    this.setEnabledAt(2, false);
    if (collapsiblePanel != null) {
      glassPane.setVisible(true);
      collapsiblePanel.setCollapsed(false);
    }
    hostChooserPanel.showHostList(meet, school);
    this.setSelectedIndex(1);
  }
  
  public void hide(){
    if (collapsiblePanel != null) {
      glassPane.setVisible(false);
      collapsiblePanel.setCollapsed(true);
    }
  }
  
  AvailableMeetsPanel getAvailableMeetsPanel() {
    return availableMeetsPanel;
  }

  public HostChooserPanel getHostChooserPanel() {
    return hostChooserPanel;
  }
  
  void setParentPanel(JXCollapsiblePane panel) {
    collapsiblePanel = panel;
  }

  void setGlassPane(JPanel glassPaneArg) {
    glassPane = glassPaneArg;
    
  }

  void setGrandParentPane(NewMainMapAndDetailsPanel newMainMapAndDetailsPanel) {
    grandParentPane = newMainMapAndDetailsPanel;
  }
}
