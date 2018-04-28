package tourgen.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.BeforeClass;

@RunWith(Suite.class)
@SuiteClasses({ TournamentSaveTest.class, 
  TournamentSaveTestAssertions.class, 
  TournamentRemoveTest.class, 
  TourgenDistanceMatrixTest.class,
  TournamentRemoveDefaultTest.class,
  StageTypeEnumTest.class,
  LocationTest.class,
  GoogleMapsGeocodeTest.class,
  RepositoryLoadSaveTest.class})
public class AllTournamentChangeSaveTests {

}
