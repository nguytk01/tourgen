package tourgen.view;

import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.School;


public class SwingWaypointTemporaryHost extends SwingWaypoint {
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
  public SwingWaypointTemporaryHost(String text, GeoPosition coord, School schoolArg,
      tourgen.controller.MapAssistantController mapAssistantControllerArg) {
    super(coord);
    this.text = text;
    school = schoolArg;
    mapAssistantController = mapAssistantControllerArg;
    // button = new GMapPinButton(text.substring(0, 1));
    //System.out.println("hostFlag is " +hostFlag);
    button = new GMapTempHostPinButton(text.substring(0, 1));
    /*double[] maxAndAvgDistances = meet.getMaxAndAvgDistance();
    double maximumDistanceInMiles = maxAndAvgDistances[0] / 1000.0 * 0.621;
    double averageDistanceInMiles = maxAndAvgDistances[1] / 1000.0 * 0.621;
    */
    //System.out.println(maximumDistanceInMiles);
    //System.out.println(averageDistanceInMiles);
      
    distanceStr = "1";
    
    // button.setSize(24, 24);
    // button.setPreferredSize(new Dimension(24, 24));
      
      button.addMouseListener(new SwingWaypointMouseListener());
      button.setVisible(true);
      
      //this.meet = meet;

  }

  JButton getButton() {
    return button;
  }

  private class SwingWaypointMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      //JOptionPane.showMessageDialog(
      //    button, distanceStr, "Information", JOptionPane.INFORMATION_MESSAGE);
          if (button instanceof HostGMapPinButton) {
            mapAssistantController.showPinHostInfoSidePane(meet, school);
          } else {
            mapAssistantController.showPinRegularInfoSidePane(meet, school);
          }
          // System.out.println(meet.getHostSchool().getSchoolLoc().getLatitude() + " " +
          // meet.getHostSchool().getSchoolLoc().getLongitude());

          // System.out.println(HostSchool.getLatitude() + " "+HostSchool.getLongitude());

    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      if (button instanceof HostGMapPinButton) {
        button.setToolTipText(meet.getLocation().getName());
      } else {
        button.setToolTipText(school.getName());
      }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
  }
}
