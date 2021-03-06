package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.cache.FileBasedLocalCache;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.viewer.util.GeoUtil;

import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.controller.MapAssistantController;
import tourgen.controller.MapController;
import tourgen.model.Location;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.School;
import tourgen.util.IMapDriver;

/**
 * A sample application demonstrating usage of Swing components as waypoints
 * using JXMapViewer.
 *
 * @author Daniel Stahr
 */
public class MapDriver implements IMapDriver {

  private School schoolTest = 
      new School("Fake", "Officialy Fake", "2300", "fort Wayne", 4500, 180, true, false);
  private Meet meet;
  private List<School> schools;

  private tourgen.model.Location tempLoc = schoolTest.getSchoolLoc();
  private Set<SwingWaypoint> waypoints;
  

  private WaypointPainter<SwingWaypoint> swingWaypointPainter = 
      new SwingWaypointOverlayPainter();

  
  
  private Set<HostSwingWaypoint> hostwaypoints;
  private JXMapViewer mapViewer;

  //private Meet currentMeet;
  private Repository repo;

  public List<Meet> meetList;

  private ArrayList<GeoPosition> geo = new ArrayList<GeoPosition>();

  // private int j,i;
  private CheckBoxTreeCustomCheckBoxListener checkBoxListener;
  private MapController mapController;

  /* public MapDriver() {
  //
  // meetList = new ArrayList<Meet>();
  // waypoints = new HashSet<SwingWaypoint>();
  // TileFactoryInfo info = new OSMTileFactoryInfo();
  // DefaultTileFactory tileFactory = new DefaultTileFactory(info);
  // mapViewer = new JXMapViewer();
  // mapViewer.setTileFactory(tileFactory);
  //
  // JFrame frame = new JFrame("THE MAP");
  //
  // frame.getContentPane().setLayout(new BorderLayout());
  // frame.add(mapViewer);
  //
  // frame.setSize(800, 600);
  // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  // frame.setVisible(true);
   }*/
  
  private javax.swing.JPanel checkBoxPanel;
  private MapAssistantController mapAssistantController;
  private TileFactoryInfo info; 
  /**
   * The driver for the map view.
   * @param repository of tournaments
   */
  public MapDriver(Repository repository) {
    // Create a TileFactoryInfo for OSM
    info = new OSMTileFactoryInfo();
    
    DefaultTileFactory tileFactory = new DefaultTileFactory(info);
    tileFactory.setThreadPoolSize(8);

    // Setup local file cache
    File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
    //LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);
    tileFactory.setLocalCache(new FileBasedLocalCache(cacheDir, false));
    // Setup JXMapViewer
    mapViewer = new JXMapViewer();
    mapViewer.setTileFactory(tileFactory);
    repo = repository;

    // Meet class
    // Meet meet = new Meet(new Stage(StageType.SECTIONAL,"",""),new Date());

    // meet =
    // tournament.getGirlList().get(0).getStageList().get(0).getListMeet().get(0);

    // meet = displayHostSchool(tournament, meet);

    // meetList =
    // tournament.getGirlList().get(0).getStageList().get(0).getListMeet();
    meetList = new ArrayList<Meet>();

    // schools = meet.getParticipatingSchool();

    //// Geo position school

    // example locations for schools///////

    tempLoc.setLatitude(41);
    tempLoc.setLongitude(-85);

    GeoPosition fortwayne = new GeoPosition(tempLoc.getLatitude(), tempLoc.getLongitude());

    ////////////////////////////////////

    // Set the focus
    mapViewer.setZoom(13);
    mapViewer.setAddressLocation(fortwayne);

    // Add interactions
    MouseInputListener mia = new PanMouseInputListener(mapViewer);
    mapViewer.addMouseListener(mia);
    mapViewer.addMouseListener(new MapMouseListener());
    mapViewer.addMouseMotionListener(mia);
    mapViewer.addMouseListener(new CenterMapListener(mapViewer));
    mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
    mapViewer.addKeyListener(new PanKeyListener(mapViewer));

    // Create waypoints from the geo-positions
    waypoints = new HashSet<SwingWaypoint>();
    hostwaypoints = new HashSet<HostSwingWaypoint>();
    

    // Iteration for all schools to be displayed from the File of database of
    // shcools and pins

    /*
     * for(j= 0 ; j < meetList.size() ; j++) {
     * 
     * for(i =0; i < meetList.get(j).getParticipatingSchool().size() ; i++) {
     * schools = meetList.get(j).getParticipatingSchool(); //schools.set(i,
     * meet.getParticipatingSchool().get(i));
     * 
     * tempLoc = schools.get(i).getSchoolLoc(); GeoPosition points = new
     * GeoPosition(tempLoc.getLatitude(), tempLoc.getLongitude());
     * System.out.println(meetList.get(j).getHostSchool().getName());
     * System.out.println("1" + schools); System.out.println("2" + schools.get(i));
     * System.out.println("2.5" + schools.get(i).getDisplayName());
     * System.out.println("3" + meetList.get(j)); System.out.println("4" +
     * waypoints); waypoints.add(new SwingWaypoint(schools.get(i).getDisplayName(),
     * points, meetList.get(j)));
     * 
     * 
     * 
     * }
     * 
     * 
     * }
     */

    // Set the overlay painter
    /*
     * WaypointPainter<SwingWaypoint> swingWaypointPainter = new
     * SwingWaypointOverlayPainter();
     * 
     */

  }
  
  /**
   * GUI initialization for the map.
   */
  
  public void initGui() {
    swingWaypointPainter.setWaypoints(waypoints);

    mapViewer.setOverlayPainter(swingWaypointPainter);
    SwingUtilities.invokeLater( new Runnable() {
    	public void run() {
    // Add the JButtons to the map viewer
    for (SwingWaypoint w : waypoints) {
      mapViewer.add(w.getButton());
    }
    }
    	});
    
    

    // Test waypoint

    // Display the viewer in a JFrame
    JFrame frame = new JFrame("THE MAP");
    // CheckBoxTreeFrame frame2 = new CheckBoxTreeFrame(repo, checkBoxListener,
    // mapController);
    checkBoxPanel = new CheckBoxTreePanel(mapController);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(checkBoxPanel);
    frame.getContentPane().add(mapViewer, BorderLayout.CENTER);
    frame.setSize(1400, 900);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setVisible(true);
  }

  public void setCon(CheckBoxTreeCustomCheckBoxListener checkBoxListener,
      MapController mapController) {
    this.checkBoxListener = checkBoxListener;
    this.mapController = mapController;
  }

  /**
   * add a meet to the meetList.
   * @param meet the meet to be added
   */
  /*public void addMeet(Meet meet) {

    meetList.add(meet);

    refresh();

  }*/
  
  /**
   * show on the map the list of the meets.
   */
  
  public void showMeetList(List meetsArg) {
	  mapAssistantController.hideSidePane();
    meetList.clear();
    // System.out.println("meetlist cleared. size = " + meetList.size());
    SwingUtilities.invokeLater( new Runnable() {
    	public void run() {
    		
    	}
    });
    mapViewer.removeAll();
    waypoints.clear();
    hostwaypoints.clear();
    
    
    // System.out.println("showMeetList ");
    List<Meet> meets = new ArrayList<Meet>();
    for (Object i : meetsArg) {
      meets.add((Meet) i);
    }
    meetList.addAll(meetsArg);
    // for (SwingWaypoint w : waypoints) {
    // mapViewer.add(w.getButton());
    // }

    /*
     * School school = new School("ASd", " asd", "3123123", "asdd", 0, 0, false,
     * false); Meet meet = new Meet(); tourgen.model.Location loc = new
     * tourgen.model.Location("asdasd", "asdasd", 0); loc.setLatitude(42.5);
     * loc.setLongitude(-79); school.setSchoolLoc(loc);
     * meet.addSchooltoMeet(school);
     * 
     * addMeet(meet); meetList.add(meet);
     */
    // waypoints.addAll(waypoints);
    // mapViewer.revalidate();
    // mapViewer.repaint();
    // System.out.println("waypoints size " + waypoints.size());
    SwingUtilities.invokeLater( new Runnable() {
    	public void run() {
    		
    	}
    });
    refresh();
  }

  /**
   * refresh the Map.
   */
  
  public void refresh() {

   // waypoints.clear();
	  System.out.println("refresh called with meetList size " + meetList.size());
	School hostSchoolOfAMeet = null;

	
	for (int j = 0; j < meetList.size(); j++) {
      schools = meetList.get(j).getSectionalSchoolsOfMeet();
      System.out.println("schools list size " + schools.size());
      hostSchoolOfAMeet = meetList.get(j).getHostSchool();
      if (meetList.get(j).getHostSchool() == null 
    	        || meetList.get(j).getHostSchool().getSchoolLoc() != meetList.get(j).getLocation()) { 
    	         //(meetList.get(j).getLocation() != null || meetList.get(j).getHostSchool() == null) {
    		        /* if there is no host school (state final),
    		         * display the hosting location, which always exists for a meet*/
    		        GeoPosition hostingLocationPoint = 
    		            new GeoPosition(meetList.get(j).getLocation().getLatitude(),
    		                meetList.get(j).getLocation().getLongitude());
    		        waypoints.add(new
    		                SwingHostingLocationWaypoint(meetList.get(j).getLocation().getName(),
    		                hostingLocationPoint, meetList.get(j).getHostSchool(), meetList.get(j),true, mapAssistantController));
    		        }
      for (int i = 0; i < schools.size(); i++) {
        // schools.set(i, meet.getParticipatingSchool().get(i));
        // System.out.println("school " + schools.get(i).getDisplayName());
        tempLoc = schools.get(i).getSchoolLoc();
        // System.out.println("school Loc" + tempLoc.getLatitude());
        // System.out.println("school Loc" + tempLoc.getLongitude());
        
        /* the host school, if present, will be displayed with a different pin */
        if (schools.get(i) != hostSchoolOfAMeet) {
        	GeoPosition points = new GeoPosition(tempLoc.getLatitude(), tempLoc.getLongitude());
        	waypoints.add(new SwingWaypoint(schools.get(i).getDisplayName(), 
        			points, schools.get(i), meetList.get(j),false, mapAssistantController));
        }
        /* display the host school, which could be null or not. */
        if (hostSchoolOfAMeet != null 
        		&& schools.get(i) == hostSchoolOfAMeet) {
            GeoPosition hostPoint = 
                    new GeoPosition(meetList.get(j).getHostSchool().getSchoolLoc().getLatitude(),
                        meetList.get(j).getHostSchool().getSchoolLoc().getLongitude());
            waypoints.add(new
                    SwingWaypoint(meetList.get(j).getHostSchool().getName(),
                    hostPoint, meetList.get(j).getHostSchool(), meetList.get(j),true, mapAssistantController));
        }
        
        
        // GeoPosition hostPoints = new
        // GeoPosition(meetList.get(j).getHostSchool().getSchoolLoc().getLatitude(),
        // meetList.get(j).getHostSchool().getSchoolLoc().getLatitude());
        // Hostwaypoints.add(new
        // HostSwingWaypoint(meetList.get(j).getHostSchool().getDisplayName(),
        // hostPoints, meetList.get(j)));

      }
    }
	
    if (meetList.size() == 1) {
    	configureZoomLevel();
    }

    // host schools
    // hostSwingWaypointPainter.setWaypoints(Hostwaypoints);
    // all schoolls
    // hostSwingWaypointPainter.setWaypoints(Hostwaypoints);
    //
    /* for (HostSwingWaypoint b : Hostwaypoints) {
      mapViewer.add(b.getButton());
    }
    HostSwingWaypointPainter.setWaypoints(Hostwaypoints);
    mapViewer.setOverlayPainter(HostSwingWaypointPainter);
     */
      

    // Add the JButtons to the map viewer
    // System.out.println("waypoints full size" + waypoints.size());
    for (SwingWaypoint w : waypoints) {
      mapViewer.add(w.getButton());
    }
    if (meetList.size() == 1 && hostSchoolOfAMeet != null) {
    	mapViewer.setCenterPosition(new GeoPosition(hostSchoolOfAMeet.getSchoolLoc().getLatitude(), hostSchoolOfAMeet.getSchoolLoc().getLongitude()));
    }
    swingWaypointPainter.setWaypoints(waypoints);
    mapViewer.setOverlayPainter(swingWaypointPainter);
    
    mapViewer.revalidate();
    
    //mapViewer.repaint();  
    
      
     
    // mapViewer.repaint();

  }
  
  private void configureZoomLevel(){
	  Meet onlyMeet = meetList.get(0);
	  Location hostLocation = null;
	  Point2D hostSchoolPoint = null;
	  if (onlyMeet.getHostSchool() == null) {
		  hostLocation = onlyMeet.getLocation();
	  } else {
		  hostLocation = onlyMeet.getHostSchool().getSchoolLoc();
	  }
	  GeoPosition hostGeoPos = new GeoPosition(hostLocation.getLatitude(), hostLocation.getLongitude());
	  hostSchoolPoint = mapViewer.getTileFactory().geoToPixel( hostGeoPos, mapViewer.getZoom());
	  
	  /* find the furthest school */
	  double maxDistance = 0;
	  SwingWaypoint furthestWaypoint = null;
	  for (SwingWaypoint a : waypoints) {
		  Point2D schoolPos = mapViewer.getTileFactory().geoToPixel( a.getPosition(), mapViewer.getZoom());
		  double distance = schoolPos.distance(hostSchoolPoint);
		  if (distance > maxDistance) {
			  maxDistance = distance;
			  furthestWaypoint = a;
		  }
	  }
	  
	  mapViewer.setCenterPosition(hostGeoPos);;
	  mapViewer.setZoom(16);
	  int currentZoom = mapViewer.getZoom();
	  while (true){
		  mapViewer.setCenterPosition(hostGeoPos);;
		  System.out.println("currentZoom " + currentZoom);
		  if (currentZoom <= 2) {
			  break;
		  }
		  Point2D schoolPos = mapViewer.getTileFactory().geoToPixel( furthestWaypoint.getPosition(), currentZoom);
		  hostSchoolPoint = mapViewer.getTileFactory().geoToPixel( hostGeoPos, currentZoom);
		  
		  
		  
		  double width = mapViewer.getViewportBounds().getWidth();
		  double height = mapViewer.getViewportBounds().getHeight();
		  if (schoolPos.getX() <= mapViewer.getViewportBounds().getX() + width && 
				  schoolPos.getX() >= mapViewer.getViewportBounds().getX()
				  && schoolPos.getY() <= mapViewer.getViewportBounds().getY() + height &&
				  schoolPos.getY() >= mapViewer.getViewportBounds().getY()) {
			  currentZoom --;
			  mapViewer.setZoom(currentZoom);
		              
		  
			  /*if (((schoolPos.getX() > hostSchoolPoint.getX() 
				  && schoolPos.getX() - hostSchoolPoint.getX() 
				  <= mapViewer.getCenter().getX() + width/2 - hostSchoolPoint.getX())
				  || (schoolPos.getX() < hostSchoolPoint.getX()
			  && hostSchoolPoint.getX() - schoolPos.getX() 
			  <= hostSchoolPoint.getX() - mapViewer.getCenter().getX() + width/2 ))
			&& (schoolPos.getY() > hostSchoolPoint.getY() 
					  && schoolPos.getY() - hostSchoolPoint.getY() <= mapViewer.getCenter().getY() + height/2 - hostSchoolPoint.getY()
					  || (schoolPos.getY() < hostSchoolPoint.getY()
				  && hostSchoolPoint.getY() - schoolPos.getY() <= hostSchoolPoint.getY() - mapViewer.getCenter().getY() + height/2))) 
			  {
			  	currentZoom--;
			  	mapViewer.setZoom(currentZoom);
			  } */
		  }else {
				  break;
			  }  
	  }
	  currentZoom++;
	  mapViewer.setZoom(currentZoom);
  }
  

  public List getMeetList() {
    return meetList;
  }

    javax.swing.JPanel getMapPanel() {
    return mapViewer;
  }
  
  javax.swing.JPanel getCheckBoxTreePanel() {
    return checkBoxPanel;
  }
  
  void setMapAssistantController(tourgen.controller.MapAssistantController controllerArg) {
    mapAssistantController = controllerArg;
  }
  
  private class MapMouseListener implements java.awt.event.MouseListener{

    @Override
    public void mouseClicked(MouseEvent arg0) {
      System.out.println("mapDriver clicked");
      mapAssistantController.hideSidePane();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}
    
  }

  @Override
  public void mapReplace(Object oldSchool, Object newSchool) {
    double oldSchoolLatitude = 0;
    double oldSchoolLongitude = 0;  
    GeoPosition newSchoolGeoPosition = null;
    if (newSchool != null) {
      newSchoolGeoPosition = new GeoPosition(((School)newSchool).getSchoolLoc().getLatitude(), ((School)newSchool).getSchoolLoc().getLongitude());
    }
      //javax.swing.SwingUtilities.invokeLater( new Runnable(){
      //public void doRun(){
      if (oldSchool != null) {
      oldSchoolLatitude = ((tourgen.model.School)oldSchool).getSchoolLoc().getLatitude();
      oldSchoolLongitude = ((tourgen.model.School)oldSchool).getSchoolLoc().getLongitude();
      for (final java.util.Iterator<SwingWaypoint> iter = waypoints.iterator(); iter.hasNext();) {
        SwingWaypoint waypoint = iter.next();
        if (waypoint.getPosition().getLatitude() - oldSchoolLatitude < 0.005 
        && waypoint.getPosition().getLongitude() - oldSchoolLongitude < 0.005 
        && waypoint instanceof SwingWaypointTemporaryHost) {
          iter.remove();
          mapViewer.remove(((SwingWaypointTemporaryHost)waypoint).getButton());
          
        }
        
       }
    }
      if (newSchool != null) {
      SwingWaypointTemporaryHost tempHostSwingWaypoint = 
          new SwingWaypointTemporaryHost("Hey", newSchoolGeoPosition, (tourgen.model.School)newSchool, mapAssistantController);
          waypoints.add(tempHostSwingWaypoint);
          mapViewer.add(tempHostSwingWaypoint.getButton());
      }
          swingWaypointPainter.setWaypoints(waypoints);
          mapViewer.setOverlayPainter(swingWaypointPainter);
          mapViewer.revalidate();
          //mapViewer.repaint();
          return;
          //}

          //@Override
          //public void run() {
          // TODO Auto-generated method stub
        
          //}});
    
    
  }
}
