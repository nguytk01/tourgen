package tourgen.view;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import tourgen.model.Location;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.School;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A waypoint that is represented by a button on the map.
 *
 * @author Daniel Stahr
 */
public class SwingWaypoint extends DefaultWaypoint {
  private final JButton button;
  private final String text;
  private MapDriver mapDriver;
  private Meet meet;
  private Repository repo;
  private List meetList;

  /**
   * Build the SwingWaypoint.
   * @param text the text for the point. 
   * @param coord the coordinates of the point
   * @param meet the meet associated to the point.
   */
  public SwingWaypoint(String text, GeoPosition coord, Meet meet) {
    super(coord);
    this.text = text;
    button = new GMapPinButton(text.substring(0, 1));
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
      JOptionPane.showMessageDialog(button, "You clicked on " + text);

      // System.out.println(meet.getHostSchool().getSchoolLoc().getLatitude() + " " +
      // meet.getHostSchool().getSchoolLoc().getLongitude());

      // System.out.println(HostSchool.getLatitude() + " "
      // +HostSchool.getLongitude());

    }

    public void addMeet(Meet meet) {

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
