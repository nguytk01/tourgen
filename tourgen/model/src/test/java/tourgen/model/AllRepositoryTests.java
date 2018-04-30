package tourgen.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RepositoryInitializationTest.class, 
  RepositoryLoadSaveTest.class,
  })
public class AllRepositoryTests {

}
