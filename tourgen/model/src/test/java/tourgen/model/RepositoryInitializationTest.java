package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

public class RepositoryInitializationTest {

  @Test
  public void testSingletonRepository() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Constructor<Repository> repo = Repository.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(repo.getModifiers()));
    repo.setAccessible(true);
    repo.newInstance();
  }
  
  @Test
  public void testRepositoryInitializationHeader() {
    new RepositoryInitialization();
  }
  
  @Test
  public void testGetBoyList() {
    assertNotNull(Repository.getInstance1().getBoyList());
  }
  
  @Test
  public void testSetBoyList() {
    ArrayList<Object> temp = new ArrayList<Object>();
    Tournament tourney = new Tournament("Lads", TournamentParticipants.BOYS);
    temp.add(tourney);
    Repository.getInstance1().setBoyList(temp);
    assertNotNull(Repository.getInstance1().getBoyList());
    assertNotNull(Repository.getInstance1().getBoyList().size());
  }
  
  @Test
  public void testParseMeetTime() {
    org.joda.time.DateTime[] dateTimeArr = RepositoryInitialization.getMeetTime("9/11:30 am ET",
        new org.joda.time.DateTime(2017, 10, 10, 10, 10));
    assertEquals(dateTimeArr[0].hourOfDay().get(), 11);
    assertEquals(dateTimeArr[1].hourOfDay().get(), 9);
    assertEquals(dateTimeArr[0].getZone().toString(), "America/New_York");
  }

  @Test
  public void testParseMeetTime1() {
    org.joda.time.DateTime[] dateTimeArr = RepositoryInitialization.getMeetTime("9/11:30 PM CT",
        new org.joda.time.DateTime(2017, 10, 10, 10, 10));
    // System.out.println(dateTimeArr[0].hourOfDay().get());
    assertEquals(dateTimeArr[0].hourOfDay().get(), 23);
    assertEquals(dateTimeArr[1].hourOfDay().get(), 21);
    assertEquals(dateTimeArr[0].getZone().toString(), "America/Chicago");
    org.joda.time.DateTime[] dateTimeArr1 = RepositoryInitialization.getMeetTime("9/12:15 pm ET",
        new org.joda.time.DateTime(2017, 10, 10, 10, 10));
    // System.out.println(dateTimeArr[0].hourOfDay().get());
    assertEquals(dateTimeArr[0].hourOfDay().get(), 23);
    assertEquals(dateTimeArr[1].hourOfDay().get(), 21);
    assertEquals(dateTimeArr[0].getZone().toString(), "America/Chicago");
  }

  @Test
  public void runImportTest() {
    Repository repo = Repository.getInstance1();
    SchoolManager manager = new SchoolManager();
    manager.initSchools();
    RepositoryInitialization.init("tournamentData.txt", repo, manager);
  }
}
