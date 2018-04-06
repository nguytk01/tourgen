package tourgen.view;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import tourgen.model.Meet;
import tourgen.model.Repository;

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
    private Main3 MapDriver;
    private Meet meet;
    private Repository repo;

    public SwingWaypoint(String text, GeoPosition coord) {
        super(coord);
        this.text = text;
        button = new GMapPinButton(text.substring(0, 1));
        //button.setSize(24, 24);
        //button.setPreferredSize(new Dimension(24, 24));
        button.addMouseListener(new SwingWaypointMouseListener());
        button.setVisible(true);
    }

    JButton getButton() {
        return button;
    }

    private class SwingWaypointMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(button, "You clicked on " + text);
            MapDriver.displayMeets(repo,meet);
            
            
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
