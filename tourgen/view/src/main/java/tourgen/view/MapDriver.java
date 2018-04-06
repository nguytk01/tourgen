package tourgen.view;

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

import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.School;
import tourgen.model.SchoolManager;
import tourgen.model.Stage;
import tourgen.model.StageType;
import tourgen.model.Tournament;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;
import javax.tools.DocumentationTool.Location;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A sample application demonstrating usage of Swing components as waypoints
 * using JXMapViewer.
 *
 * @author Daniel Stahr
 */
public class Main3{
	
	

	private static School schoolTest = new School("Fake","Officialy Fake", "2300", "fort Wayne", 4500,180,true,false);
	private static Meet meet;
	private static ArrayList<School> schools = new ArrayList<School>();
	private static tourgen.model.Location a =  schoolTest.getSchoolLoc();
	private static Set<SwingWaypoint> waypoints;
    
	private static WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
	private static JXMapViewer mapViewer;
	
    public static void main3(Repository tournament) {
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

        
        //Meet class
       // Meet meet = new Meet(new Stage(StageType.SECTIONAL,"",""),new Date());
        
        //meet = tournament.getGirlList().get(0).getStageList().get(0).getListMeet().get(0);
        
        displayMeets(tournament, meet);
        

        schools = meet.getParticipatingSchool();
        
        GeoPosition TestLoc = new GeoPosition(schools.get(0).getSchoolLoc().getLatitude(),schools.get(0).getSchoolLoc().getLongitude());

		
		
        ////Geo position school
        
        //example locations for schools///////
		
		a.setLatitude(41);
		a.setLongitude(-85);
		
		GeoPosition fortwayne = new GeoPosition(a.getLatitude(),  a.getLongitude());
		
		//click on a school Pin to get the index in the school List >> Replace 1 for i as any index
		tourgen.model.Location Host = schools.get(0).getHostLoc();
		//gets the host location
		//hihglight host pin
		//GeoPosition HostPin = new GeoPosition(Host.getLatitude(),  Host.getLongitude());
		//Set<SwingWaypoint> HostPointTest = new HashSet<SwingWaypoint>(Arrays.asList(
          //      new SwingWaypoint(schools.get(1).getHostName(), HostPin)));
		
		
		
		
		////////////////////////////////////

        // Set the focus
        mapViewer.setZoom(10);
        mapViewer.setAddressLocation(fortwayne);
      

        // Add interactions
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));
       
        
      
        
        
        
        // Create waypoints from the geo-positions
        waypoints = new HashSet<SwingWaypoint>(Arrays.asList(
                new SwingWaypoint("Fort Wayne", fortwayne)));
     
       
        
        
        //Iteration for all schools to be displayed from the File of database of shcools and pins
        for(int i = 0; i < schools.size() ; i++)
		{
			a = schools.get(i).getSchoolLoc();
			GeoPosition points = new GeoPosition(a.getLatitude(),a.getLongitude());
			waypoints.add(new SwingWaypoint(schools.get(i).getDisplayName(),points));
		}
      
        
        // Set the overlay painter
        /*WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
         * 
         */
        swingWaypointPainter.setWaypoints(waypoints);
        
        mapViewer.setOverlayPainter(swingWaypointPainter);
        

        // Add the JButtons to the map viewer
        for (SwingWaypoint w : waypoints) {
            mapViewer.add(w.getButton());
        }

        
        
        
        // Display the viewer in a JFrame
        JFrame frame = new JFrame("JXMapviewer2 Example 7");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void displayMeets(Repository tournaments ,Meet meeting)
    {
    	 meeting = tournaments.getGirlList().get(0).getStageList().get(0).getListMeet().get(0);
    	 
    }
    
    public static void displaySchools(Repository tournaments ,Meet meet)
    {
    	 //tourgen.model.Location loc = tournaments.getGirlList().get(0).getStageList().get(0).getListMeet().get(0).getParticipatingSchool().get(0).getSchoolLoc().;
    	 
    }
    
    public static Meet getMapPin()
    {
    	return meet;
    }
    
   
    		
    
    
    
}
