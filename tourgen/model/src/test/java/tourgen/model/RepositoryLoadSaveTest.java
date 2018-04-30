package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class RepositoryLoadSaveTest extends BaseTournamentCommonTestUtils {

  @Test
  public void test() {
    // RepositoryIOManager rim = new RepositoryIOManager();
    Tournament tourney = new Tournament("Test Boys Tournament", TournamentParticipants.GIRLS);
    Repository.getInstance1().addTournament(tourney);
    Repository tempSave = Repository.getInstance1();
    IoManager.saveRepository("save.bin");
    
    java.lang.reflect.Field field = null;
    try {
      field = Repository.class.getDeclaredField("instance");
    } catch (NoSuchFieldException | SecurityException e1) {
      fail("No Such Field Exception or Security Exception");
    }
    field.setAccessible(true);
    try {
      field.set(field, null);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      fail("Exception");
    }
    
    IoManager.loadRepository("save.bin");
    List<Object> boyList = new java.util.ArrayList<Object>();
    List<Object> girlList = new java.util.ArrayList<Object>();
    //boyList.addAll(tempLoad.getBoyList());
    //girlList.addAll(tempLoad.getGirlList());
    //Repository.getInstance1().setBoyList(boyList);
    //Repository.getInstance1().setGirlList(girlList);

    assertTrue(Repository.getInstance1() != null);
    assertTrue(Repository.getInstance1().getGirlList().size() != 0);
    assertTrue(Repository.getInstance1().getGirlList().get(0) != null);
    System.out.println(Repository.getInstance1().getGirlList().get(0).toString());
    
    
    
  }

  /*@Test
  public void LoadSaveTest() {
    // RepositoryIOManager rim = new RepositoryIOManager();
    Tournament tourney = new Tournament("Best Boys Tournament", TournamentParticipants.BOYS);
    Repository.getInstance().addTournament(tourney);
    // Repository tempSave = Repository.getInstance();
    SchoolManager sm = new SchoolManager(Repository.getInstance1());
    sm.initSchools();
    RepositoryInitialization.init(Repository.getInstance1(), sm);
    IoManager.saveSchoolManager("saveX.bin", sm);
    SchoolManager smn = (SchoolManager) IoManager.loadSchoolManager("saveX.bin");
    assertTrue(smn != null);
    assertTrue(smn.getRepository().getBoyList().size() != 0);
    assertTrue(smn.getRepository().getBoyList().get(0) != null);
    System.out.println(smn.getRepository().getBoyList().get(0).toString());
  }*/

  /*@Test
  public void test3() {
    Tournament tourney = new Tournament("Test Boys Tournament", TournamentParticipants.BOYS);
    Repository.getInstance1().addTournament(tourney);
    IoManager.saveRepository("save.bin");
    Repository xyz = (Repository) IoManager.loadRepository("save.bin");
    assertEquals(xyz.getBoyList().size(), 1);
    System.out.println(xyz.getBoyList().get(0));
  }*/
  
  
  
  
  
}
