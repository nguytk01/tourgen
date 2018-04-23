package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXCollapsiblePane;

public class NewMainMapAndDetailsPanel extends javax.swing.JLayeredPane {
  private tourgen.controller.MapController mapController;
  private NewMainMapSidePane mapSidePane;
  private JPanel glassPane = new JPanel();
  private JPanel mapPanel;
  
  public NewMainMapAndDetailsPanel(tourgen.controller.MapController mapControllerArg, javax.swing.JPanel mapPanelArg, NewMainMapSidePane mapSidePaneArg) {
    mapPanel = mapPanelArg;
    glassPane.setOpaque(false);
    glassPane.setVisible(false);
    
    JXCollapsiblePane collapsiblePane = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);
    glassPane.setLayout(new BorderLayout());
    glassPane.add(collapsiblePane, BorderLayout.EAST);
    collapsiblePane.setLayout(new BorderLayout());
    collapsiblePane.getContentPane().add(mapSidePaneArg, BorderLayout.CENTER);
    java.awt.Dimension minimumSize = new java.awt.Dimension(900, 500);
    mapPanel.setMinimumSize(minimumSize);
    mapController = mapControllerArg;
    mapSidePane = mapSidePaneArg;
    
    mapSidePane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if  (evt.getPropertyName().equals(HostChooserPanel.ALTERNATIVE_HOST_MOUSE_ENTER)) {
          mapController.mapReplace(evt.getOldValue(), evt.getNewValue());
        }
      }
    });
    
    //this.setLayout(new BorderLayout());
    this.add(mapPanel, JLayeredPane.DEFAULT_LAYER);
    this.add(glassPane, JLayeredPane.PALETTE_LAYER);
    //mapPanel.setSize(this.getSize());
    System.out.println("width " + this.getWidth());
    
    
    //glassPane.setPreferredSize(collapsiblePane.getPreferredSize());
    //glassPane.setPreferredSize(new Dimension(500,600));
    //glassPane.setSize(collapsiblePane.getPreferredSize());
    glassPane.setSize(new Dimension(500,800));
    collapsiblePane.setCollapsed(true);

    collapsiblePane.setOpaque(false);
    
    mapSidePane.setParentPanel(collapsiblePane);
    mapSidePane.setGlassPane(glassPane);
    mapSidePane.setGrandParentPane(this);
    this.addComponentListener(new Listener());
  }

  public void setActiveTournament(Object tournament) {
    mapSidePane.resetPanel();
    mapController.emptyMap();
  }

  public void initUserInterface() {
    
  }
  
  public void showGlassPane() {
    this.add(glassPane, JLayeredPane.PALETTE_LAYER);
  }
  
  private class Listener implements ComponentListener{

    @Override
    public void componentHidden(ComponentEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void componentResized(ComponentEvent arg0) {
      System.out.println("component width : " + getWidth());
      mapPanel.setBounds(0,0,getWidth(), getHeight());
      glassPane.setBounds(getWidth() - 600, 0, 600, getHeight());
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
      // TODO Auto-generated method stub
      
    }
    
  }
}
