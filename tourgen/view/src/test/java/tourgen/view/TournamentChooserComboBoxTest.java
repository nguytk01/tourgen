package tourgen.view;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.junit.Before;
import org.junit.Test;

import tourgen.controller.MainViewPanelMock;
import tourgen.controller.MapAssistantController;
import tourgen.controller.MapController;
import tourgen.controller.NewMainViewController;
import tourgen.controller.TournamentChooserComboBoxListener;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.SchoolManager;
import tourgen.util.IMapSidePane;

public class TournamentChooserComboBoxTest extends BaseTestUtils {

	@Before
	public void setUp() throws Exception {
		RepositoryInitialization.init("tournamentDataForViewTests.txt", Repository.getInstance1(), schoolManager);
		
	}

	@Test
	public void testShowHostList() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException {
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

		NewMainMock newMain = new NewMainMock(mapController, 
				mapDriver.getMapPanel(), reportTableView, mapSidePane, schoolManager);
		newMain.initUserInterface();
		
		/* Hack here */
		java.lang.reflect.Field field = null;
	    field = HostChooserPanel.class.getDeclaredField("newMainViewController");
	    field.setAccessible(true);
	    NewMainViewController controller = (NewMainViewController)field.get(hostChooserPanel);
	    
	    controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		
		Meet sectionalMeet = 
				Repository.getInstance1().getGirlList().get(0).getStageList()
				.get(0).getMeetList().get(0);
		
		
		hostChooserPanel.showHostList(sectionalMeet, sectionalMeet.getHostSchool());
		Object[] leftPane = ((java.awt.Container)newMain.getContentPane()
		  .getComponents()[0]).getComponents();
		javax.swing.JComboBox comboBox = 
		  (javax.swing.JComboBox)
		  ((java.awt.Container)
		  ((java.awt.Container)leftPane[0])
		  .getComponents()[0]).getComponents()[1];
		
		
		assertEquals(comboBox.getModel().getSize(), 1);
		
		controller.saveAsTournamentClicked();
		assertEquals(2, Repository.getInstance1().getGirlList().size());
		controller.saveAsTournamentClicked();
		assertEquals(3, Repository.getInstance1().getGirlList().size());
		Thread.sleep(1000);
		assertEquals(3,comboBox.getModel().getSize());
		controller.removeTournamentClicked();
		assertEquals(2, Repository.getInstance1().getGirlList().size());
		Thread.sleep(1000);
		assertEquals(2,comboBox.getModel().getSize());
		
	}

}
