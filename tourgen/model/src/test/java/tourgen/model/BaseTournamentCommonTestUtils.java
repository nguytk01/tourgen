package tourgen.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseTournamentCommonTestUtils {
  protected static SchoolManager schoolManager;
  /**
   * Try to clear singleton instance before each test case is started.
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    clearRepositorySingleton();
  }
  
  protected static void clearRepositorySingleton() {
    java.lang.reflect.Field field = null;
    try {
      field = Repository.class.getDeclaredField("instance");
    } catch (NoSuchFieldException | SecurityException e1) {
      e1.printStackTrace();
    }
    field.setAccessible(true);
    try {
      field.set(field, null);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }
    schoolManager = new SchoolManager();
    schoolManager.initSchools();
  }
}
