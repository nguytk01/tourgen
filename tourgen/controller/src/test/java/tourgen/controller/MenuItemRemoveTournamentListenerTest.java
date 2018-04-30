package tourgen.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Location;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;

public class MenuItemRemoveTournamentListenerTest extends BaseTestUtils {

	private NewMainViewController controller;
	private MainViewPanelMock leftPanelMock;
	private MainViewPanelMock rightPanelMock;

	@Before
	public void setup()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
		clearRepositorySingleton();
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
		Location crownPointLocation = new Location("n", "n", 12);
		leftPanelMock = new MainViewPanelMock();
		rightPanelMock = new MainViewPanelMock();
		controller = new NewMainViewController(leftPanelMock, rightPanelMock, null);
		controller.setMainView(new NewMainMock());
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
	}

	@Test
	public void test() {

		MenuItemRemoveTournamentListener removeListener = new MenuItemRemoveTournamentListener();
		removeListener.setNewMainViewController(controller);
		removeListener.actionPerformed(null);

	}

}
