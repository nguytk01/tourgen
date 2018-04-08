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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;
import javax.tools.DocumentationTool.Location;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A sample application demonstrating usage of Swing components as waypoints
 * using JXMapViewer.
 *
 * @author Daniel Stahr
 */
public class MapDriver{
	
	

	private  School schoolTest = new School("Fake","Officialy Fake", "2300", "fort Wayne", 4500,180,true,false);
	private  Meet meet;
	private  List<School> schools = new ArrayList<School>();
	
	private  tourgen.model.Location tempLoc =  schoolTest.getSchoolLoc();
	private  Set<SwingWaypoint> waypoints;
    
	private WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
	private JXMapViewer mapViewer;
	
	private Meet CurrentMeet;
	private Repository repo;
	
	List<Meet> meetList;
	
	private ArrayList<GeoPosition> Geo = new ArrayList<GeoPosition>();
	
//	private int j,i;
	
	public MapDriver() {
		
		meetList = new ArrayList<Meet>();
        waypoints = new HashSet<SwingWaypoint>();
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(tileFactory);
        JFrame frame = new JFrame("THE MAP");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
    public MapDriver(Repository tournament) {
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
        repo = tournament;
        
        //Meet class
       // Meet meet = new Meet(new Stage(StageType.SECTIONAL,"",""),new Date());
        
        //meet = tournament.getGirlList().get(0).getStageList().get(0).getListMeet().get(0);
        
       
        //meet = displayHostSchool(tournament, meet);
        
        //meetList = tournament.getGirlList().get(0).getStageList().get(0).getListMeet();
        meetList = new ArrayList<Meet>();
        
       // schools = meet.getParticipatingSchool();
        
        
      
        ////Geo position school
        
        //example locations for schools///////
		
		tempLoc.setLatitude(41);
		tempLoc.setLongitude(-85);
		
		
		GeoPosition fortwayne = new GeoPosition(tempLoc.getLatitude(),  tempLoc.getLongitude());
		
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
        waypoints = new HashSet<SwingWaypoint>();

      
        
        
        //Iteration for all schools to be displayed from the File of database of shcools and pins
        
        
       /* for(j= 0 ; j < meetList.size() ; j++)
        {
        	
        	 for(i =0; i < meetList.get(j).getParticipatingSchool().size() ; i++)
             {
             	schools = meetList.get(j).getParticipatingSchool();
             	//schools.set(i, meet.getParticipatingSchool().get(i));

             	tempLoc = schools.get(i).getSchoolLoc();
    			GeoPosition points = new GeoPosition(tempLoc.getLatitude(),  tempLoc.getLongitude());
    			System.out.println(meetList.get(j).getHostSchool().getName());
    			System.out.println("1" + schools);
    			System.out.println("2" + schools.get(i));
    			System.out.println("2.5" + schools.get(i).getDisplayName());
    			System.out.println("3" + meetList.get(j));
    			System.out.println("4" + waypoints);
    			waypoints.add(new SwingWaypoint(schools.get(i).getDisplayName(), points, meetList.get(j)));
    			
    			
    			
             }
        	 
        	 
        }*/

        
        
        
        
       
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
        JFrame frame = new JFrame("THE MAP");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public Meet displayHostSchool(Repository tournaments ,Meet meeting)
    {
    	
    	 meeting = tournaments.getGirlList().get(0).getStageList().get(0).getListMeet().get(0);
    	 return meeting;

    }
    
    
  public void addMeet(Meet meet)
  {
	  
	  meetList.add(meet);
	  refresh();

  }
  
  public void showMeetList(List<Meet> meets)
  {
	  meetList.clear();
	  mapViewer.removeAll();
	  waypoints.clear();
	  
	  meetList.addAll(meets);
	  for (SwingWaypoint w : waypoints) {
          mapViewer.add(w.getButton());
      }
	  
	  waypoints.addAll(waypoints);
	  
	  
  }
  
  
  
  public void refresh()
  {
	  
	  waypoints.clear();
	  
	  for(int j= 0 ; j < meetList.size() ; j++) {
		  for(int i =0; i < meetList.get(j).getParticipatingSchool().size() ; i++)
		  	{
			      	schools = meetList.get(j).getParticipatingSchool();
			      	//schools.set(i, meet.getParticipatingSchool().get(i));
			
			      	tempLoc = schools.get(i).getSchoolLoc();
						GeoPosition points = new GeoPosition(tempLoc.getLatitude(),  tempLoc.getLongitude());
						/*System.out.println(meetList.get(j).getHostSchool().getName());
						System.out.println("1" + schools);
						System.out.println("2" + schools.get(i));
						System.out.println("2.5" + schools.get(i).getDisplayName());
						System.out.println("3" + meetList.get(j));
						System.out.println("4" + waypoints);*/
						waypoints.add(new SwingWaypoint(schools.get(i).getDisplayName(), points, meetList.get(j)));
			
			
      	}
	  }
	  swingWaypointPainter.setWaypoints(waypoints);
      
      mapViewer.setOverlayPainter(swingWaypointPainter);
      

      // Add the JButtons to the map viewer
      for (SwingWaypoint w : waypoints) {
          mapViewer.add(w.getButton());
      }
  }
  
  

    
    
}
