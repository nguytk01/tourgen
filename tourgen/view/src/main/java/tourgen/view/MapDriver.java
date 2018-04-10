package tourgen.view;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.controller.MapController;
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
  private WaypointPainter<SwingWaypoint> SwingWaypointPainter = new SwingWaypointOverlayPainter();

  
  
  private Set<HostSwingWaypoint> Hostwaypoints;
  //private WaypointPainter<HostSwingWaypoint> HostSwingWaypointPainter = new HostSwingWaypointOverlayPainter();
  
  private JXMapViewer mapViewer;

  //private Meet currentMeet;
  private Repository repo;

  public List<Meet> meetList;

  private ArrayList<GeoPosition> geo = new ArrayList<GeoPosition>();

  // private int j,i;
  private CheckBoxTreeCustomCheckBoxListener checkBoxListener;
  private MapController mapController;

  // public MapDriver() {
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
  // }
  
  private javax.swing.JPanel checkBoxPanel;
  
  /**
   * The driver for the map view.
   * @param repository of tournaments
   */
  public MapDriver(Repository repository) {
    // Create a TileFactoryInfo for OSM
    TileFactoryInfo info = new OSMTileFactoryInfo();
    DefaultTileFactory tileFactory = new DefaultTileFactory(info);
    tileFactory.setThreadPoolSize(8);

    // Setup local file cache
    File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
    LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);

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
    mapViewer.addMouseMotionListener(mia);
    mapViewer.addMouseListener(new CenterMapListener(mapViewer));
    mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
    mapViewer.addKeyListener(new PanKeyListener(mapViewer));

    // Create waypoints from the geo-positions
    waypoints = new HashSet<SwingWaypoint>();
    Hostwaypoints = new HashSet<HostSwingWaypoint>();
    

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

    // Add the JButtons to the map viewer
    for (SwingWaypoint w : waypoints) {
      mapViewer.add(w.getButton());
    }
    
    

    // Test waypoint

    // Display the viewer in a JFrame
    JFrame frame = new JFrame("THE MAP");
    // CheckBoxTreeFrame frame2 = new CheckBoxTreeFrame(repo, checkBoxListener,
    // mapController);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(
        new CheckBoxTreePanel(checkBoxListener, mapController), BorderLayout.WEST);
    frame.getContentPane().add(mapViewer, BorderLayout.CENTER);
    frame.setSize(1400, 900);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
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
  public void addMeet(Meet meet) {

    meetList.add(meet);

    refresh();

  }
  
  /**
   * show on the map the list of the meets.
   */
  
  public void showMeetList(List meetsArg) {
    meetList.clear();
    // System.out.println("meetlist cleared. size = " + meetList.size());
    mapViewer.removeAll();
    waypoints.clear();
    Hostwaypoints.clear();
    
    
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
    refresh();
  }

  /**
   * refresh the Map.
   */
  
  public void refresh() {

    // waypoints.clear();

    for (int j = 0; j < meetList.size(); j++) {
      schools = meetList.get(j).getParticipatingSchool();
      for (int i = 0; i < meetList.get(j).getParticipatingSchool().size(); i++) {
        // schools.set(i, meet.getParticipatingSchool().get(i));
        // System.out.println("school " + schools.get(i).getDisplayName());
        tempLoc = schools.get(i).getSchoolLoc();
        // System.out.println("school Loc" + tempLoc.getLatitude());
        // System.out.println("school Loc" + tempLoc.getLongitude());
        GeoPosition points = new GeoPosition(tempLoc.getLatitude(), tempLoc.getLongitude());
        waypoints.add(new SwingWaypoint(schools.get(i).getDisplayName(),
            points, meetList.get(j),false));
        
        
        GeoPosition hostPoint = 
            new GeoPosition(meetList.get(j).getLocation().getLatitude(),
                meetList.get(j).getLocation().getLongitude());
        waypoints.add(new
                SwingWaypoint(meetList.get(j).getLocation().getName(),
                hostPoint, meetList.get(j),true));
     
        
        // GeoPosition hostPoints = new
        // GeoPosition(meetList.get(j).getHostSchool().getSchoolLoc().getLatitude(),
        // meetList.get(j).getHostSchool().getSchoolLoc().getLatitude());
        // Hostwaypoints.add(new
        // HostSwingWaypoint(meetList.get(j).getHostSchool().getDisplayName(),
        // hostPoints, meetList.get(j)));

      }
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
    swingWaypointPainter.setWaypoints(waypoints);
    mapViewer.setOverlayPainter(swingWaypointPainter);
    
    mapViewer.revalidate();
    mapViewer.repaint();  
    
      
     
    // mapViewer.repaint();

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
}
