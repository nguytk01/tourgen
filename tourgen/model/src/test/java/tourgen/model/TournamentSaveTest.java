package tourgen.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TournamentSaveTest extends BaseTournamentCommonTestUtils{

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
  
  @Test
  public void testSaveAsTournament() {
    assertEquals(meetA.getParticipatingSchool().size(), 1);
    assertEquals(0, meetA.getParticipatingSchool().indexOf(schoolA));
    
    
    Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament, meetB, meetA, schoolA, schoolManager);
    
    assertEquals(true, tournament.isSavingNeeded());
    assertEquals(1, Repository.getInstance1().getGirlList().size());
    
    Repository.getInstance1().saveTournamentAsNewTournament(tournament, "newTournament");
    
    assertEquals(false, tournament.isSavingNeeded());
    assertEquals(2, Repository.getInstance1().getGirlList().size());
  }

}
