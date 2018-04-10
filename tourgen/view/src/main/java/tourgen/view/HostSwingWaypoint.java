package tourgen.view;

import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import tourgen.model.Meet;

/**
 * A waypoint that is represented by a button on the map.
 *
 * @author Daniel Stahr
 */
public class HostSwingWaypoint extends DefaultWaypoint {
  private final JButton button;
  private final String text;
  private MapDriver mapDriver;
  private Meet meet;
  private List meetList;

  /**
   * Construct a HostSwingWaypoint.
   * @param text text
   * @param coord coord
   * @param meet meet
   */
  public HostSwingWaypoint(String text, GeoPosition coord, Meet meet) {
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
