package tourgen.controller;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Location;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;

public class MenuItemSaveTournamenListenerTest extends BaseTestUtils {

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

	MainViewPanelMock newPane = new MainViewPanelMock();
	private tourgen.util.INewMainViewPane newMainPane = newPane;
	private NewMainViewController newMainViewController = new NewMainViewController(newMainPane, newMainPane,
			schoolManager);

	@Test
	public void test() {
		MenuItemSaveTournamentListener saveListener = new MenuItemSaveTournamentListener();

		saveListener.setNewMainViewController(controller);

		ActionEvent event = new ActionEvent(saveListener, 1, "Test");

		saveListener.actionPerformed(event);

	}

}
