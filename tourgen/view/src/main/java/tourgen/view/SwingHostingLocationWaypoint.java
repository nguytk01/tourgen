package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.School;


public class SwingHostingLocationWaypoint extends SwingWaypoint {
  private  JButton button;
  private String text;
  private Meet meet;
  private String distanceStr;
  private School school;
  private tourgen.controller.MapAssistantController mapAssistantController;
  /**
   * Build the SwingWaypoint.
   * @param text the text for the point. 
   * @param coord the coordinates of the point
   * @param meet the meet associated to the point.
   * @param mapAssistantController
   */
  public SwingHostingLocationWaypoint(String text, GeoPosition coord, School schoolArg, Meet meet, boolean hostFlag, 
      tourgen.controller.MapAssistantController mapAssistantControllerArg) {
    super(coord);
    this.text = text;
    school = schoolArg;
    mapAssistantController = mapAssistantControllerArg;
    // button = new GMapPinButton(text.substring(0, 1));
    //System.out.println("hostFlag is " +hostFlag);
    if (hostFlag == true) {
      double[] maxAndAvgDistances = meet.getMaxAndAvgDistance();
      double maximumDistanceInMiles = maxAndAvgDistances[0] / 1000.0 * 0.621;
      double averageDistanceInMiles = maxAndAvgDistances[1] / 1000.0 * 0.621;
      java.text.DecimalFormat distanceToStringFormat = new java.text.DecimalFormat();
      String maximumDistanceInMilesStr = distanceToStringFormat.format(maximumDistanceInMiles);
      String averageDistanceInMilesStr = distanceToStringFormat.format(averageDistanceInMiles);
      button = new HostingLocationPinButton(new HostingLocationToolTip(averageDistanceInMilesStr, maximumDistanceInMilesStr));
      //System.out.println(maximumDistanceInMiles);
      //System.out.println(averageDistanceInMiles);
      /*distanceStr = "Host : "
                             + meet.toString()
                             + "\n"
                             + "Stage : "
                             + meet.getStage().getStageTitle()
                             + "\n"
                             + "Max distance = " 
                             + new java.text.DecimalFormat("0").format(maximumDistanceInMiles)
                             + " miles"
                             + "\n"
                             + "Average distance = " 
                             + new java.text.DecimalFormat("0").format(averageDistanceInMiles)
                             + " miles";
    } else {
      button = new GMapPinButton(new RegularSchoolToolTip()); 
      distanceStr = "You clicked on school " + text 
                              + "\n"
                              + "Host : "
                              + meet.toString()
                              + "\n"
                              + "Stage : "
                              + meet.getStage().getStageTitle();*/
    }
    // button.setSize(24, 24);
    // button.setPreferredSize(new Dimension(24, 24));
    button.addMouseListener(new SwingWaypointMouseListener());
    button.setVisible(true);
    this.meet = meet;
  }

  public SwingHostingLocationWaypoint(GeoPosition coord) {
    super(coord);
  }

  JButton getButton() {
    return button;
  }
  
  class HostingLocationToolTip extends JToolTip {
	  private JPanel panel;
	  private JLabel label;
	  private JPanel detailsPanel;
	  public HostingLocationToolTip(String averageDistance, String maximumDistance) {
		  panel = new JPanel();
		  panel.setBorder(new EmptyBorder(2,2,2,2));
		  //panel.setLayout(new BorderLayout());
		  panel.setLayout(new BorderLayout());
		  label = new javax.swing.JLabel("hi");
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		panel.add(new javax.swing.JLabel("Hosting Location"), BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);
		/*panel.add(new javax.swing.JLabel("Max / Avg: " 
		+ averageDistance 
		+ " / " 
		+ maximumDistance
		+ " miles"), BorderLayout.SOUTH);*/
		panel.setOpaque(false);
		
		//detailsPanel = new JPanel();
		//panel.add(detailsPanel, BorderLayout.SOUTH);
		this.add(panel, BorderLayout.WEST);
	  }
	  
	  public void setTipText(String text) {
		  label.setText(text);
	  }
	  
	  public Dimension getPreferredSize() {
		  
		  return panel.getPreferredSize();
	  }
  }

  private class SwingWaypointMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      //JOptionPane.showMessageDialog(
      //    button, distanceStr, "Information", JOptionPane.INFORMATION_MESSAGE);
          /*if (button instanceof HostGMapPinButton) {
            mapAssistantController.showPinHostInfoSidePane(meet, school);
          } else {
            if (meet.isSectionalMeet()) {
              mapAssistantController.showPinRegularInfoSidePane(meet, school);
            }
          }*/
    	mapAssistantController.hideSidePane();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      if (button instanceof HostingLocationPinButton) {
        button.setToolTipText(meet.getLocation().getName());
      } else {
        button.setToolTipText("unknown location");
      }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
  }
}
