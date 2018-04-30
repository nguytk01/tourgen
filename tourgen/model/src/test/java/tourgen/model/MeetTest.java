package tourgen.model;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MeetTest extends BaseTournamentCommonTestUtils{
	
	@Before
	public void setUp() {
		clearRepositorySingleton();
		RepositoryInitialization.init("tournamentData.txt", Repository.getInstance1(), schoolManager);
	}
	
	@Test
	public void testDistance() {
		double[] arr = Repository.getInstance1().getGirlList().get(0)
		.getStageList().get(0).getMeetList().get(0).getMaxAndAvgDistance();
		assertEquals(12271.0, arr[0], 1e-5);
		assertEquals(6897.785714, arr[1], 1e-5);
		double[] stageDistArr = Repository.getInstance1().getGirlList().get(0)
		.getStageList().get(0).getMaxAndAvgDistance();
	}
	
	@Test
	public void testRecursiveUpdateMeet() {
		Repository.getInstance1().getGirlList().get(0).getStageList().get(2).getMeetList().get(0).getMaxAndAvgDistance();
		Repository.getInstance1().getGirlList().get(0).getStageList().get(2).getMeetList().get(0).recursiveUpdateSectionalSchoolsList();
		assertEquals(Repository.getInstance1().getGirlList().get(0).getStageList().get(2).getMeetList().get(0).toString(),"Brown County");
	}
	
	@Test
	public void testChangeHostOfMeet() {
	    Repository.getInstance1().changeHostOfMeet(null, 
	    		schoolManager, Repository.getInstance1().getGirlList().get(0), Repository.getInstance1().getGirlList().get(0).getStageList().get(2).getMeetList().get(0),Repository.getInstance1().getGirlList().get(0).getStageList().get(2).getMeetList().get(0).getParticipatingSchool().get(0) );

	}
	
}
