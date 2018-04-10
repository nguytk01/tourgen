package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class RepositoryLoadSaveTest {

  /*@Test
  public void test() {
    // RepositoryIOManager rim = new RepositoryIOManager();
    Tournament tourney = new Tournament("Test Boys Tournament", TournamentParticipants.BOYS);
    Repository.getInstance().addTournament(tourney);
    Repository tempSave = Repository.getInstance();
    RepositoryIoManager.saveRepository("save.bin");
    Repository tempLoad = (Repository) RepositoryIoManager.loadRepository("save.bin");
    Repository.getInstance().setBoyList(tempLoad.getBoyList());
    Repository.getInstance().setGirlList(tempLoad.getGirlList());

    assertTrue(Repository.getInstance() != null);
    assertTrue(Repository.getInstance().getBoyList().size() != 0);
    assertTrue(Repository.getInstance().getBoyList().get(0) != null);
    System.out.println(Repository.getInstance().getBoyList().get(0).toString());
  }

  @Test
  public void LoadSaveTest() {
    // RepositoryIOManager rim = new RepositoryIOManager();
    Tournament tourney = new Tournament("Best Boys Tournament", TournamentParticipants.BOYS);
    Repository.getInstance().addTournament(tourney);
    // Repository tempSave = Repository.getInstance();
    SchoolManager sm = new SchoolManager(Repository.getInstance());
    sm.initSchools();
    RepositoryInitialization.init(Repository.getInstance(), sm);
    RepositoryIoManager.saveSchoolManager("saveX.bin", sm);
    SchoolManager smn = (SchoolManager) RepositoryIoManager.loadSchoolManager("saveX.bin");
    assertTrue(smn != null);
    assertTrue(smn.getRepository().getBoyList().size() != 0);
    assertTrue(smn.getRepository().getBoyList().get(0) != null);
    System.out.println(smn.getRepository().getBoyList().get(0).toString());
  }*/

  @Test
  public void test3() {
    Tournament tourney = new Tournament("Test Boys Tournament", TournamentParticipants.BOYS);
    Repository.getInstance1().addTournament(tourney);
    Repository tempSave = Repository.getInstance1();
    IoManager.saveRepository("save.bin");
    Repository xyz = (Repository) IoManager.loadRepository("abc.bin");
    assertEquals(xyz.getBoyList().size(), 1);
    System.out.println(xyz.getBoyList().get(0));
  }
}
