package tourgen.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.BeforeClass;

@RunWith(Suite.class)
@SuiteClasses({ TournamentSaveTest.class, 
  
  StageTypeEnumTest.class,
  LocationTest.class,
  RepositoryInitializationTest.class,
  RepositoryLoadSaveTest.class,
  RepositoryTest.class,
  MeetFeederTests.class,
  MeetTest.class,
  TestChangeCompetitionSite.class,
  OperationResultTests.class,
  StageTests.class,
  SchoolManagerInitFromTextFileTest.class,
  EnumTester.class,
  TournamentAddTest.class,
  TournamentSaveTestAssertions.class, 
  TournamentRemoveTest.class, 
  TourgenDistanceMatrixTest.class,
  TournamentRemoveDefaultTest.class,
  TournamentAddTest.class,
  TournamentChangeHostForMeetTest.class,
  TournamentSaveMeetWhenHostChangedTest.class,
  IoManagerTest.class
  })
public class AllTournamentChangeSaveTests {

}
