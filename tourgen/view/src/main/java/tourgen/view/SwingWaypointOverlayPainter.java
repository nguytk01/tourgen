package tourgen.view;

import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JButton;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 * "Paints" the Swing waypoints. In fact, just takes care of correct positioning
 * of the representing button.
 *
 * @author Daniel Stahr
 */
public class SwingWaypointOverlayPainter extends WaypointPainter<SwingWaypoint> {

  @Override
  protected void doPaint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {
    // System.out.println("swing painter called");
    for (SwingWaypoint swingWaypoint : getWaypoints()) {
      // System.out.println("waypoint x: " +
      // swingWaypoint.getPosition().getLatitude());
      Point2D point = jxMapViewer.getTileFactory().geoToPixel(
          swingWaypoint.getPosition(), jxMapViewer.getZoom());
      Rectangle rectangle = jxMapViewer.getViewportBounds();
      int buttonX = (int) (point.getX() - rectangle.getX());
      int buttonY = (int) (point.getY() - rectangle.getY());
      JButton button = swingWaypoint.getButton();
      button.setLocation(buttonX - button.getWidth() / 2, buttonY - button.getHeight() / 2);
    }
  }
}
