package tourgen.controller;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Location;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.School;

public class MenuItemSaveAsTournamentListenerTest extends BaseTestUtils {

	private NewMainViewController newMainViewController;
	
	private NewMainViewController controller;
	private MainViewPanelMock leftPanelMock;
	private MainViewPanelMock rightPanelMock;
 
	@Before
	public void setUp() {
		clearRepositorySingleton(); 
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
		Location crownPointLocation = new Location("n", "n", 12);
  //		crownPointLocation.setLatitude(41.389664);
  //		crownPointLocation.setLongitude(-87.359668);
  //		crownPoint = new School("Crown Point", "Crown Point High School", "n", "n", 123, 123, true, true);
  //		crownPoint.setSchoolLoc(crownPointLocation);

		leftPanelMock = new MainViewPanelMock();
		rightPanelMock = new MainViewPanelMock();
		controller = new NewMainViewController(leftPanelMock, rightPanelMock, null);
		controller.setMainView(new NewMainMock());
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
	}
	
	@Test
	public void test() {
		
		MenuItemSaveAsTournamentListener saveAsListener = new MenuItemSaveAsTournamentListener();
		
		saveAsListener.setNewMainViewController(controller);
		saveAsListener.actionPerformed(null);
		
		
	}

}
