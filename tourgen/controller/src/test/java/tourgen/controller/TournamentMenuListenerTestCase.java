package tourgen.controller;

import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Location;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.School;

public class TournamentMenuListenerTestCase extends BaseTestUtils {

	private School crownPoint;
	private NewMainViewController controller;
	private MainViewPanelMock leftPanelMock;
	private MainViewPanelMock rightPanelMock;

	private javax.swing.JMenuItem saveTournamentMenuItem;
	private javax.swing.JMenuItem saveAsTournamentMenuItem;
	private javax.swing.JMenuItem removeTournamentMenuItem;

	@Before
	public void setUp() {
		clearRepositorySingleton();
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
		Location crownPointLocation = new Location("n", "n", 12);
		crownPointLocation.setLatitude(41.389664);
		crownPointLocation.setLongitude(-87.359668);
		crownPoint = new School("Crown Point", "Crown Point High School", "n", "n", 123, 123, true, true);
		crownPoint.setSchoolLoc(crownPointLocation);

		leftPanelMock = new MainViewPanelMock();
		rightPanelMock = new MainViewPanelMock();
		controller = new NewMainViewController(leftPanelMock, rightPanelMock, null);

		saveTournamentMenuItem = new JMenuItem();
		saveAsTournamentMenuItem = new JMenuItem();
		removeTournamentMenuItem = new JMenuItem();
	}

	@Test
	public void test() {
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		TournamentMenuListener menuListener = new TournamentMenuListener();
		menuListener.setNewMainViewController(controller);
		menuListener.setSaveAsTournamentMenuItem(saveAsTournamentMenuItem);
		menuListener.setSaveTournamentMenuItem(saveTournamentMenuItem);
		menuListener.setRemoveTournamentMenuItem(removeTournamentMenuItem);
		menuListener.actionPerformed(null);
		menuListener.menuCanceled(null);
		menuListener.menuDeselected(null);
		MenuEvent event = new MenuEvent(menuListener);

		menuListener.menuSelected(event);
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		controller.setMainView(new NewMainMock());
		controller.saveAsTournamentClicked();
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(1));
		menuListener.menuSelected(null);
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		controller.changeCompetitionSite(
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0).getParticipatingSchool().get(0), 
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0),
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(1));
	    
		menuListener.menuSelected(null);

	}

}
