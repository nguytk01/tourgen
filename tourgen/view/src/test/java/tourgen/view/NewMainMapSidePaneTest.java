package tourgen.view;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.junit.Before;
import org.junit.Test;

import tourgen.controller.MapAssistantController;
import tourgen.controller.MapController;
import tourgen.controller.NewMainViewController;
import tourgen.controller.TournamentChooserComboBoxListener;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.Stage;

public class NewMainMapSidePaneTest extends BaseTestUtils {

	@Before
	public void setUp() throws Exception {
		RepositoryInitialization.init("tournamentDataForViewTests.txt", Repository.getInstance1(), schoolManager);
		
	}

	@Test
	public void testDisplayPinRegularInformation() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException {
		ReportTableView reportTableView = new ReportTableView();

		HostChooserPanel panel = new HostChooserPanel();
		MapDriver mapDriver = new MapDriver(Repository.getInstance1());

		MapController mapController = new MapController(null, mapDriver);
		
		TournamentChooserComboBoxListener tournamentChooserComboBoxListener = new TournamentChooserComboBoxListener();

		DetailsPanel detailsPanel = new DetailsPanel();
	    HostChooserPanel hostChooserPanel = new HostChooserPanel();
	    AvailableMeetsPanel availableMeetsPanel = new AvailableMeetsPanel();
	    NewMainMapSidePane mapSidePane = new NewMainMapSidePane(detailsPanel, hostChooserPanel, availableMeetsPanel);

		MapAssistantController mapAssistantController = new MapAssistantController(mapSidePane);
		mapDriver.setMapAssistantController(mapAssistantController);

		NewMain newMain = new NewMain(mapController, 
				mapDriver.getMapPanel(), reportTableView, mapSidePane, schoolManager);
		/* Hack here */
		java.lang.reflect.Field field = null;
	    field = HostChooserPanel.class.getDeclaredField("newMainViewController");
	    field.setAccessible(true);
	    NewMainViewController controller = (NewMainViewController)field.get(hostChooserPanel);
	    
	    controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		
		newMain.initUserInterface();
		
		
		mapSidePane.displayPinHostInformation(
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0), 
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0).getParticipatingSchool().get(0));
		Thread.sleep(1000);
		
		mapSidePane.displayPinRegularInformation(Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0),
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0).getParticipatingSchool().get(0));
		Thread.sleep(1000);
		
		
		java.util.List<Meet> meetList = new java.util.ArrayList<Meet>();
		Stage sectionalStage = Repository.getInstance1().getGirlList().get(0).getStageList().get(0);
		meetList.add(sectionalStage.getMeetList().get(0));
		//System.out.println("asdf" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(0, mapDriver.getMapPanel().getComponentCount());
		mapDriver.showMeetList(meetList);
		assertEquals(3, mapDriver.getMapPanel().getComponentCount());
		Object obj = mapDriver.getMapPanel().getComponents()[1];
		for (java.awt.Component com : mapDriver.getMapPanel().getComponents()) {
			((javax.swing.JButton)com).doClick();
			((javax.swing.JButton)com).createToolTip();
			for (MouseListener e : ((javax.swing.JButton)com).getMouseListeners()) {
				e.mouseClicked(new MouseEvent(((javax.swing.JButton)com), MouseEvent.MOUSE_CLICKED,99,0, 5,5,0, 0, 1, false,0));
				e.mouseEntered(new MouseEvent(((javax.swing.JButton)com), MouseEvent.MOUSE_ENTERED,99,0, 5,5,0, 0, 1, false,0));
			}
		}
		
		availableMeetsPanel.getSelectedSchool();
		availableMeetsPanel.getOldMeet();
		availableMeetsPanel.getNewMeet();
		java.util.List<Meet> meetList1 = new java.util.ArrayList<Meet>();
		Stage finalStage = Repository.getInstance1().getGirlList().get(0).getStageList().get(3);
		meetList1.add(finalStage.getMeetList().get(0));
		//System.out.println("asdf" + mapDriver.getMapPanel().getComponentCount());
		assertEquals(3, mapDriver.getMapPanel().getComponentCount());
		mapDriver.showMeetList(meetList1);
		assertEquals(3, mapDriver.getMapPanel().getComponentCount());
		
		mapController.mapReplace(null, meetList1.get(0).getParticipatingSchool().get(0));
		
		for (java.awt.Component com : mapDriver.getMapPanel().getComponents()) {
			((javax.swing.JButton)com).doClick();
			((javax.swing.JButton)com).createToolTip();
			for (MouseListener e : ((javax.swing.JButton)com).getMouseListeners()) {
				e.mouseClicked(new MouseEvent(((javax.swing.JButton)com), MouseEvent.MOUSE_CLICKED,99,0, 5,5,0, 0, 1, false,0));
				e.mouseEntered(new MouseEvent(((javax.swing.JButton)com), MouseEvent.MOUSE_ENTERED,99,0, 5,5,0, 0, 1, false,0));
			}
		}
		
		
		//((tourgen.view.HostGMapPinButton)mapDriver.getMapPanel().getComponents()[1]).doClick();
		//((tourgen.view.GMapPinButton)mapDriver.getMapPanel().getComponents()[1]).doClick();
		//Thread.sleep(2000);


		
	}

	@Test
	public void testDisplayPinHostInformation() {
		
	}

}
