package tourgen.model;

import org.junit.Before;
import org.junit.Test;

public class IoManagerTest extends BaseTournamentCommonTestUtils{

	@Before
	public void setUp() {
		clearRepositorySingleton();
		RepositoryInitialization.init("tournamentData.txt", Repository.getInstance1(), schoolManager);
	}
	
	@Test
	public void test() {
		IoManager ioManager = new IoManager();
		ioManager.saveSchoolManager("TestSchoolData.bin", schoolManager);
		ioManager.loadSchoolManager("TestSchoolData.bin");
		ioManager.saveRepository("TestTournamentData.bin");
		ioManager.saveDistanceMatrix("TestDistance.bin");
		ioManager.loadDistanceMatrix("TestDistance.bin");
	}
	
	@Test
	public void testSaveLoadEverything() {
		IoManager.saveEverything(schoolManager);
		clearRepositorySingleton();
		IoManager.loadEverythingUp();

	}
	

}
