package tourgen.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import org.junit.Test;
import tourgen.model.Repository;
public class TestChangeCompetitionSite extends BaseTournamentCommonTestUtils{
  private Meet meetA;
  private Meet meetB;
  private Tournament tournament;
  private Stage stage;
  private School schoolA;
  
  @Before
  public void beforeEachTest() {
    tournament = new Tournament("testTournament",TournamentParticipants.GIRLS);
    stage = new Stage(StageType.SECTIONAL, "", "");
    tournament.addStage(stage);
    Repository.getInstance1().addTournament(tournament);
    meetA = new Meet(stage, null);
    schoolA = new School("school A", "School A", "211 Main St.", "City", 51121, 21, true, false);
    meetA.addSchooltoMeet(schoolA);
    meetB = new Meet(stage, null);
    
    stage.addMeetToStage(meetA);
    stage.addMeetToStage(meetB);
  }
  
  @Test
  public void testMeetAddRemoveSchool() {

    assertEquals(meetA.getParticipatingSchool().size(), 1);
    assertEquals(0, meetA.getParticipatingSchool().indexOf(schoolA));
    
    Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament, meetB, meetA, schoolA, schoolManager);
    assertEquals(0, meetA.getParticipatingSchool().size());
    assertEquals(1, meetB.getParticipatingSchool().size());
  }
  
  //@Test
  public void testTournamentChangedWhenCompetitionSiteChanged() {
    assertEquals(meetA.getParticipatingSchool().size(), 1);
    assertEquals(0, meetA.getParticipatingSchool().indexOf(schoolA));
    Repository.getInstance1().addTournament(tournament);
    Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament, meetB, meetA, schoolA, schoolManager);
    assertEquals(true, tournament.isSavingNeeded());
  }
  

}
