package tourgen.controller;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Location;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;

public class HostChooserListenerTest extends BaseTestUtils {

	private NewMainViewController controller;
	private MainViewPanelMock leftPanelMock;
	private MainViewPanelMock rightPanelMock;

	@Before
	public void setup()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		clearRepositorySingleton();
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
		Location crownPointLocation = new Location("n", "n", 12);
		leftPanelMock = new MainViewPanelMock();
		rightPanelMock = new MainViewPanelMock();
		controller = new NewMainViewController(leftPanelMock, rightPanelMock, null);
		controller.setMainView(new NewMainMock());
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
	}

	private HostChooserPanelMock hostMock = new HostChooserPanelMock();
	private tourgen.util.IHostChooserPanel hostChooserPanel = hostMock;

	@Test
	public void test() {

		HostChooserListener HostListener = new HostChooserListener(controller, hostChooserPanel);

		ActionEvent event = new ActionEvent(controller, 0, "TEST");

		HostListener.actionPerformed(event);

	}

}
