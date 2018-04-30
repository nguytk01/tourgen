package tourgen.view;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.junit.Before;
import org.junit.Test;
import org.jxmapviewer.JXMapViewer;

import tourgen.controller.MapAssistantController;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.Stage;
import tourgen.util.IMapSidePane;



public class MapDriverTest extends BaseTestUtils {

	private MapDriver mapDriver;
	@Before
	public void setUp() {
		
		RepositoryInitialization.init("tournamentDataForViewTests.txt", Repository.getInstance1(), schoolManager);
		
	}
	
	@Test
	public void testShowMeetList() {
		IMapSidePane mapSidePane = new MapSidePaneMock();
		MapAssistantController mapAssistantController = new MapAssistantController(mapSidePane);
		
		mapDriver = new MapDriver(Repository.getInstance1());
		mapDriver.setMapAssistantController(mapAssistantController);
		
		java.util.List<Meet> meetList = new java.util.ArrayList<Meet>();
		Stage sectionalStage = Repository.getInstance1().getGirlList().get(0).getStageList().get(0);
		meetList.add(sectionalStage.getMeetList().get(0));
		//System.out.println("asdf" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(0, mapDriver.getMapPanel().getComponentCount());
		mapDriver.showMeetList(meetList);
		//System.out.println("Qwer" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(3, mapDriver.getMapPanel().getComponentCount());
	}
	
	@Test
	public void testShowMeetList2() throws InterruptedException {
		IMapSidePane mapSidePane = new MapSidePaneMock();
		MapAssistantController mapAssistantController = new MapAssistantController(mapSidePane);
		
		mapDriver = new MapDriver(Repository.getInstance1());
		mapDriver.setMapAssistantController(mapAssistantController);
		mapDriver.getMapPanel().setSize(new Dimension(800,800));
		java.util.List<Meet> meetList = new java.util.ArrayList<Meet>();
		Stage sectionalStage = Repository.getInstance1().getGirlList().get(0).getStageList().get(0);
		meetList.add(sectionalStage.getMeetList().get(2));
		//System.out.println("asdf" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(0, mapDriver.getMapPanel().getComponentCount());
		mapDriver.showMeetList(meetList);
		//System.out.println("Qwer" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(2, mapDriver.getMapPanel().getComponentCount());
		/*mapDriver.getMapPanel().setSize(new Dimension(500,500));
		JXMapViewer vie = ((JXMapViewer)(mapDriver.getMapPanel()));
		vie.setZoom(15);
		
		mapDriver.getMapPanel().revalidate();
		mapDriver.getMapPanel().repaint();
		Thread.sleep(2000);*/
	}
	
	@Test
	public void testShowMeetListStageFinal() {
		IMapSidePane mapSidePane = new MapSidePaneMock();
		MapAssistantController mapAssistantController = new MapAssistantController(mapSidePane);
		
		mapDriver = new MapDriver(Repository.getInstance1());
		mapDriver.setMapAssistantController(mapAssistantController);
		mapDriver.getMapPanel().setSize(new Dimension(800,800));
		mapDriver.initGui();
		
		java.util.List<Meet> meetList = new java.util.ArrayList<Meet>();
		Stage finalStage = Repository.getInstance1().getGirlList().get(0).getStageList().get(3);
		meetList.add(finalStage.getMeetList().get(0));
		//System.out.println("asdf" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(0, mapDriver.getMapPanel().getComponentCount());
		mapDriver.showMeetList(meetList);
		//System.out.println("Qwer" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(3, mapDriver.getMapPanel().getComponentCount());
	}
	
	@Test
	public void testMouseListener() {
		MapSidePaneMock mapSidePane = new MapSidePaneMock();
		MapAssistantController mapAssistantController = new MapAssistantController(mapSidePane);
		
		mapDriver = new MapDriver(Repository.getInstance1());
		mapDriver.setMapAssistantController(mapAssistantController);
		mapDriver.getMapPanel().setSize(new Dimension(800,800));
		mapDriver.initGui();
		mapDriver.setCon(null, null);
		
		for (MouseListener e : mapDriver.getMapPanel().getMouseListeners()) {
			e.mouseClicked(new MouseEvent(mapDriver.getMapPanel(), 
					MouseEvent.MOUSE_CLICKED, 99, 0, 50,50, 1, false));
			e.mouseEntered(null);
			e.mouseExited(null);
			e.mousePressed(new MouseEvent(mapDriver.getMapPanel(), 
					MouseEvent.MOUSE_CLICKED, 99, 0, 50,50, 1, false));
			e.mouseReleased(new MouseEvent(mapDriver.getMapPanel(), 
					MouseEvent.MOUSE_CLICKED, 99, 0, 50,50, 1, false));
		}
		assertEquals(true,mapSidePane.hideMethodCalled);
	}

	
	@Test
	public void testMapReplace() {
		IMapSidePane mapSidePane = new MapSidePaneMock();
		MapAssistantController mapAssistantController = new MapAssistantController(mapSidePane);
		
		mapDriver = new MapDriver(Repository.getInstance1());
		mapDriver.setMapAssistantController(mapAssistantController);
		mapDriver.getMapPanel().setSize(new Dimension(800,800));
		mapDriver.initGui();
		
		java.util.List<Meet> meetList = new java.util.ArrayList<Meet>();
		Stage sectionalStage = Repository.getInstance1().getGirlList().get(0).getStageList().get(0);
		meetList.add(sectionalStage.getMeetList().get(2));
		//System.out.println("asdf" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(0, mapDriver.getMapPanel().getComponentCount());
		mapDriver.showMeetList(meetList);
		
		assertEquals(1, mapDriver.getMeetList().size());
		
		//System.out.println("Qwer" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(2, mapDriver.getMapPanel().getComponentCount());
		
		mapDriver.mapReplace(null, sectionalStage.getMeetList().get(0).getParticipatingSchool().get(0));
		assertEquals(3, mapDriver.getMapPanel().getComponentCount());
		mapDriver.mapReplace(sectionalStage.getMeetList().get(0).getParticipatingSchool().get(0), 
				sectionalStage.getMeetList().get(0).getParticipatingSchool().get(1));
		
		assertEquals(3, mapDriver.getMapPanel().getComponentCount());
	}
	
	@Test
	public void testOtherMethods() {
		mapDriver = new MapDriver(Repository.getInstance1());
		assertEquals(mapDriver.getCheckBoxTreePanel(), null);
	}
}
