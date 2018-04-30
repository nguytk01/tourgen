package tourgen.controller;
import static org.junit.Assert.assertEquals;

import org.junit.Before;

import org.junit.Test;

import tourgen.model.Location;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.School;
import tourgen.model.SchoolManager;
import tourgen.model.Stage;
import tourgen.model.StageType;
import tourgen.model.Tournament;
import tourgen.model.TournamentParticipants;

public class NewMainViewControllerTest extends BaseTestUtils {
	private School crownPoint;
	private NewMainViewController controller;
	private MainViewPanelMock leftPanelMock;
	private MainViewPanelMock rightPanelMock;
 
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
	}

	@Test
	public void testSetActiveTournament() {
		Tournament tournament = new Tournament("", TournamentParticipants.GIRLS);
		controller.setActiveTournament(tournament);
		assertEquals(tournament, leftPanelMock.getTournamentJustSet());
		assertEquals(tournament, rightPanelMock.getTournamentJustSet());
	}

	@Test
	public void testGetSchoolManager() {
		NewMainViewController controller = new NewMainViewController(null, null, schoolManager);
		assertEquals(schoolManager, controller.getSchoolManager());
	}

	@Test
	public void testGetAvailableSectionalMeets() {
		java.util.List<School> schoolList = schoolManager.getSchoolList();

		School crownPointFromList = null;
		for (School school : schoolList) {
			if (school.getName().equals("Crown Point")) {
				crownPoint = school;
			}
		}
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		java.util.List<Meet> meetList = controller.getAvailableSectionalMeets(
				Repository.getInstance1().getGirlList().get(0).getStageList().get(1).getMeetList().get(0),
				crownPoint);
	    assertEquals(3, meetList.size());
		assertEquals("Hammond Gavit", meetList.get(0).getHostSchool().getDisplayName());
	}
	
	@Test
	public void testNullTournamentGetAvailableSectionalMeets() {
		java.util.List<School> schoolList = schoolManager.getSchoolList();

		School crownPointFromList = null;
		for (School school : schoolList) {
			if (school.getName().equals("Crown Point")) {
				crownPoint = school;
			}
		}
		java.util.List<Meet> meetList = controller.getAvailableSectionalMeets(
				Repository.getInstance1().getGirlList().get(0).getStageList().get(1).getMeetList().get(0),
				crownPoint);
	    assertEquals(null, meetList);
	
	}

	@Test
	public void testSaveCurrentTournamentClicked() {

	}
	
	@Test
	public void testChangeCompetitionSite(){
		java.util.List<School> schoolList = schoolManager.getSchoolList();

		School calumetFromList = null;
		for (School school : schoolList) {
			if (school.getDisplayName().equals("Calumet")) {
				calumetFromList = school;
			}
		}
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		controller.changeCompetitionSite(calumetFromList, 
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0),
				Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(1));
	    assertEquals(Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(1).getParticipatingSchool().size(),
	    	2);
	}
	
	@Test
	public void testChangeHostForMeet(){
		
		java.util.List<School> schoolList = schoolManager.getSchoolList();

		School calumetFromList = null;
		for (School school : schoolList) {
			if (school.getDisplayName().equals("Calumet")) {
				calumetFromList = school;
			}
		}
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		controller.changeHostForMeet(
				Repository.getInstance1().getGirlList().get(0).getStageList().get(1).getMeetList().get(0),
				calumetFromList);
	    assertEquals(Repository.getInstance1().getGirlList().get(0).getStageList().get(1).getMeetList().get(0).getHostSchool(),
	    		calumetFromList);
	}
	
	@Test
	public void testSaveTournamentClickedOriginalTournament(){

		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		controller.saveCurrentTournamentClicked();
	}
	
	@Test
	public void testSaveTournamentClickedModifedTournament(){
		java.util.List<School> schoolList = schoolManager.getSchoolList();

		School calumetFromList = null;
		for (School school : schoolList) {
			if (school.getDisplayName().equals("Calumet")) {
				calumetFromList = school;
			}
		}
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		Tournament tour = Repository.getInstance1().getGirlList().get(0);
		
		controller.changeHostForMeet(
				Repository.getInstance1().getGirlList().get(0).getStageList().get(1).getMeetList().get(0),
				calumetFromList);
		assertEquals(true,tour.isSavingNeeded());
		controller.saveCurrentTournamentClicked();
	}
	
	@Test
	public void testSaveAsTournamentClicked(){
		controller.setMainView(new NewMainMock());
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		assertEquals(Repository.getInstance1().getGirlList().get(0), controller.getCurrentlyDisplayTournament());
		assertEquals(1, Repository.getInstance1().getGirlList().size());
		controller.saveAsTournamentClicked();
		assertEquals(2, Repository.getInstance1().getGirlList().size());
	}
	
	@Test
	public void removeTournamentClicked(){
		controller.setMainView(new NewMainMock());
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		controller.saveAsTournamentClicked();
		controller.setActiveTournament(Repository.getInstance1().getGirlList().get(1));
		assertEquals(2, Repository.getInstance1().getGirlList().size());
		controller.removeTournamentClicked();
		assertEquals(1, Repository.getInstance1().getGirlList().size());
	}

}
