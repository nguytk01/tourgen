package tourgen.controller;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Location;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.School;
import tourgen.model.SchoolManager;

public class ChangeCompetitionSiteListenerTest extends BaseTestUtils {

	private NewMainViewController controller;
	private MainViewPanelMock leftPanelMock;
	private MainViewPanelMock rightPanelMock;
	private tourgen.util.IAvailableMeetsPanel availableMeetsPanel = new IAvailableMeetsPanelMock();

	@Before
	public void setUp() {
		clearRepositorySingleton();
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
		Location crownPointLocation = new Location("n", "n", 12);
		crownPointLocation.setLatitude(41.389664);
		crownPointLocation.setLongitude(-87.359668);

		leftPanelMock = new MainViewPanelMock();
		rightPanelMock = new MainViewPanelMock();
		controller = new NewMainViewController(leftPanelMock, rightPanelMock, null);
	}

	@Test
	public void test() {

		ChangeCompetitionSiteListener changeSiteListener = 
				new ChangeCompetitionSiteListener(controller,
				availableMeetsPanel);
		changeSiteListener.setNewMainViewController(controller);
		ActionEvent e = new ActionEvent(changeSiteListener, 0, "TEST");
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		changeSiteListener.actionPerformed(e);

	}

}
