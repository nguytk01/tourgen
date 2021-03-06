package tourgen.controller;

import org.junit.BeforeClass;

import tourgen.model.Repository;
import tourgen.model.SchoolManager;

public class BaseTestUtils {
	protected static SchoolManager schoolManager;
	@BeforeClass
	  public static void setUpBeforeClass() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
	    schoolManager = new SchoolManager();
		schoolManager.initSchools();
		clearRepositorySingleton();
	  }
	  
	  private static void clearRepo() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
	    java.lang.reflect.Field field = null;
	    field = Repository.class.getDeclaredField("instance");
	    field.setAccessible(true);
	    field.set(field, null);
	    schoolManager = new SchoolManager();
	    schoolManager.initSchools();
	  }
	   
	  protected static void clearRepositorySingleton(){
		  try {
			  clearRepo();
		  } catch (Exception e){
			  
		  }
	  }
}
