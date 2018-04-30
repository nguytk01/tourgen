package tourgen.view;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import tourgen.controller.MapAssistantController;
import tourgen.controller.MapController;
import tourgen.controller.NewMainViewController;
import tourgen.controller.TournamentChooserComboBoxListener;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.Tournament;

public class ReportTableViewTest extends BaseTestUtils{

	@Before
	public void setUp() throws Exception {
		RepositoryInitialization.init("tournamentDataForViewTests.txt", 
				Repository.getInstance1(), schoolManager);
	}

	@Test
	public void testDisplayObject() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Tournament tournament = Repository.getInstance1().getGirlList().get(0);
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
		reportTableView.display(tournament);
		Object collapsibleStagePanel = (((java.awt.Container)(reportTableView.getComponents()[0])).getComponents()[0]);
		Object obj = ((java.awt.Container)(((java.awt.Container)((java.awt.Container)collapsibleStagePanel).getComponents()[0]).getComponents()[0])).getComponents()[0];
		JButton bt = (JButton) obj;
		bt.doClick();
		
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		controller.changeCompetitionSite(Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0).getParticipatingSchool().get(1), 
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0),
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(1));
		assertEquals(true,reportTableView.isDamaged());
		//reportTableView.setActiveTournament(null);
		reportTableView.display(tournament);
		reportTableView.display();
		
	}

	@Test
	public void testSetActiveTournament() {
		
	}

}
