package tourgen.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TournamentSaveTestAssertions extends BaseTournamentCommonTestUtils{

  private Meet meetA;
  private Meet meetB;
  private Tournament tournament;
  private Stage stage;
  private School schoolA;
  
  @Before
  public void beforeEachTest() {
    tournament = new Tournament("testTournament",TournamentParticipants.GIRLS);
    Repository.getInstance1().addTournament(tournament);
    
    stage = new Stage(StageType.SECTIONAL, "", "");
    tournament.addStage(stage);
    
    meetA = new Meet(stage, null);
    stage.addMeetToStage(meetA);
    
    schoolA = new School("school A", "School A", "211 Main St.", "City", 51121, 21, true, false);
    meetA.addSchooltoMeet(schoolA);
    
    meetB = new Meet(stage, null);
    stage.addMeetToStage(meetB);

  }

  @Test(expected=AssertionError.class)
  public void testSaveAsTournamentWithStrangerMeet() {
    assertEquals(meetA.getParticipatingSchool().size(), 1);
    assertEquals(0, meetA.getParticipatingSchool().indexOf(schoolA));
    
    Tournament tournament1 = new Tournament("testTournament1",TournamentParticipants.GIRLS);
    // This tournament1 is not in the repository. The following method will check if the tournament
    // and if the tournament contains both meets. It will throw an AssertionError if those conditions
    // are not satisfied.
    Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament1, meetB, meetA, schoolA, schoolManager);
    
    assertEquals(true, tournament.isSavingNeeded());
    assertEquals(1, Repository.getInstance1().getGirlList().size());
    
    Repository.getInstance1().saveTournamentAsNewTournament(tournament, "newTournament");
    
    assertEquals(false, tournament.isSavingNeeded());
    assertEquals(2, Repository.getInstance1().getGirlList().size());
  }
}
