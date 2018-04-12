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


public class SwingWaypoint extends DefaultWaypoint {
  private  JButton button;
  private String text;
  private Meet meet;
  private String distanceStr;

  /**
   * Build the SwingWaypoint.
   * @param text the text for the point. 
   * @param coord the coordinates of the point
   * @param meet the meet associated to the point.
   */
  public SwingWaypoint(String text, GeoPosition coord, Meet meet, boolean hostFlag) {
    super(coord);
    this.text = text;
    // button = new GMapPinButton(text.substring(0, 1));
    //System.out.println("hostFlag is " +hostFlag);
    if (hostFlag == true) {
      button = new HostGMapPinButton(text.substring(0, 1));
      double[] maxAndAvgDistances = meet.getMaxAndAvgDistance();
      double maximumDistanceInMiles = maxAndAvgDistances[0] / 1000.0 * 0.621;
      double averageDistanceInMiles = maxAndAvgDistances[1] / 1000.0 * 0.621;
      
      //System.out.println(maximumDistanceInMiles);
      //System.out.println(averageDistanceInMiles);
      distanceStr = "Host : "
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
      button = new GMapPinButton(text.substring(0, 1)); 
      distanceStr = "You clicked on school " + text 
                              + "\n"
                              + "Host : "
                              + meet.toString()
                              + "\n"
                              + "Stage : "
                              + meet.getStage().getStageTitle();
    }
    // button.setSize(24, 24);
    // button.setPreferredSize(new Dimension(24, 24));
    button.addMouseListener(new SwingWaypointMouseListener());
    button.setVisible(true);
    this.meet = meet;

  }

  JButton getButton() {
    return button;
  }

  private class SwingWaypointMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      JOptionPane.showMessageDialog(
          button, distanceStr, "Information", JOptionPane.INFORMATION_MESSAGE);

      // System.out.println(meet.getHostSchool().getSchoolLoc().getLatitude() + " " +
      // meet.getHostSchool().getSchoolLoc().getLongitude());

      // System.out.println(HostSchool.getLatitude() + " "
      // +HostSchool.getLongitude());

    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
  }
}
