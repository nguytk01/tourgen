package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import tourgen.model.School;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;

public class MapFrame extends JFrame{
	
	
	
	public MapFrame() {
		setBounds(50,50,600,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		MapPanel mapPanel = new MapPanel();
		getContentPane().add(mapPanel, BorderLayout.CENTER);
		

		
		
		
		
		revalidate();
		repaint();
	}

}
