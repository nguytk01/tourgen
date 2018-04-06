package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import tourgen.model.School;

import java.awt.Insets;
import java.awt.geom.Point2D;



public class MapPanel extends JPanel implements Observer{
	private ArrayList<MapSiteItem> gSiteList;
	
	private JXMapViewer mapViewer;
	Set<SwingWaypoint> waypoints ;
	WaypointPainter<SwingWaypoint> swingWaypointPainter;
	private JButton zoomInButton;
	private JButton zoomOutButton;
	
	public MapPanel() {
		setLayout(new BorderLayout(0, 0));
		
		gSiteList = new ArrayList<MapSiteItem>();
		
		//this.setPreferredSize(new Dimension(500, 500));
		mapViewer = new JXMapViewer();
		//mapViewer.setPreferredSize(new Dimension(400,400));
        
        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
     
        // Use 8 threads in parallel to load the tiles
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		tileFactory.setThreadPoolSize(2);
		mapViewer.setTileFactory(tileFactory);
		
		
		GeoPosition fortwayne = new GeoPosition(41.10, -85.12);
		GeoPosition fort2 = new GeoPosition(40.10, -85.12);
		
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		
		waypoints = new HashSet<SwingWaypoint>();//Arrays.asList(
	                //new SwingWaypoint(null,"Fort Wayne", fortwayne)));
				//));
		swingWaypointPainter = new SwingWaypointOverlayPainter();
		
		swingWaypointPainter.setWaypoints(waypoints);
		mapViewer.setZoom(9);
		mapViewer.setAddressLocation(fortwayne);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
		mapViewer.setOverlayPainter(swingWaypointPainter);

		
		
		// Add the JButtons to the map viewer
		for (SwingWaypoint w : waypoints) {
			mapViewer.add(w.getButton());
		}

		mapViewer.setLayout(new GridBagLayout());
		
		zoomInButton = new JButton("+");
		zoomInButton.setFont(new Font("Tahoma",Font.PLAIN, 18));
		zoomInButton.setPreferredSize(new Dimension(35,35));
		zoomInButton.setMargin(new Insets(0,0,0,0));
		zoomOutButton = new JButton("\u2015");
		zoomOutButton.setFont(new Font("Tahoma",Font.PLAIN, 18));
		zoomOutButton.setPreferredSize(new Dimension(35,35));
		zoomOutButton.setMargin(new Insets(0,0,0,0));
		
		JPanel sideBar = new JPanel();
		sideBar.setLayout(new GridBagLayout());
		sideBar.setOpaque(false);
		
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0,0,10,0);
		sideBar.add(zoomInButton, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(10,0,0,0);
		sideBar.add(zoomOutButton, gridBagConstraints);
		
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		//mapViewer.add(sideBar, gridBagConstraints);
		
		//mapViewer.add(sideBar, BorderLayout.WEST);
		
		
		this.add(mapViewer);
	}

	public void addSchool(School school){
		MapSiteItem newItem = new MapSiteItem(this,school);
		gSiteList.add(newItem);
		/*for (SwingWaypoint w : waypoints) {
			mapViewer.remove(w.getButton());
		}*/
		boolean result = true;// waypoints.add(newItem.getSwingWaypoint());
		swingWaypointPainter.setWaypoints(waypoints);
		
		Set<Waypoint> waypoints = new HashSet<Waypoint>(Arrays.asList(new DefaultWaypoint(newItem.getGpsLocation())));
		WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
		//waypointPainter.setRenderer(new MyWaypointRenderer());
		waypointPainter.setWaypoints(waypoints);
		
		mapViewer.setOverlayPainter(waypointPainter);
		/*for (Waypoint w : waypoints) {
			mapViewer.add(w.getButton());
		}*/
		//mapViewer.add(newItem.getButton());
		Point2D point = mapViewer.getTileFactory().geoToPixel(newItem.getSwingWaypoint().getPosition(), mapViewer.getZoom());
		JButton abtn = new JButton("mybutton");
		mapViewer.add(abtn);
		abtn.setLocation((int)point.getX(), (int)point.getY());
		mapViewer.repaint();
	}
	
	public void addSite(School school) {
		MapSiteItem newItem = new MapSiteItem(this,school);
		gSiteList.add(newItem);
		boolean result = true;//waypoints.add(newItem.getSwingWaypoint());
		swingWaypointPainter.setWaypoints(waypoints);
		mapViewer.add(newItem.getButton());
		mapViewer.revalidate();
		mapViewer.repaint();
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
		
	}

	

