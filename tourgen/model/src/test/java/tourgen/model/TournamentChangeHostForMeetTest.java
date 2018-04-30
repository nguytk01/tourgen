package tourgen.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TournamentChangeHostForMeetTest extends BaseTournamentCommonTestUtils{
  private Meet meetA;
  private Meet meetB;
  private Tournament tournament;
  private Stage stage;
  private School schoolA;
  private School hostSchoolA;
  private School newHostSchool;
  
  @Before
  public void beforeEachTest() {
    tournament = new Tournament("testTournament",TournamentParticipants.GIRLS);
    stage = new Stage(StageType.SECTIONAL, "", "");
    tournament.addStage(stage);
    
    meetA = new Meet(stage, null);
    schoolA = new School("school A", "School A", "211 Main St.", "City", 51121, 21, true, false);
    
    hostSchoolA = new School("host school A", "Host School A", "211 Main St.", "City", 51121, 21, true, false);
    
    meetA.addSchooltoMeet(schoolA);
    meetA.addSchooltoMeet(hostSchoolA);
    meetA.setHostSchool(hostSchoolA);

    newHostSchool = new School("new host school A", "New Host School A", "211 Main St.", "City", 51121, 21, true, false);
    
    stage.addMeetToStage(meetA);
  }
  
  @Test
  public void testChangeHostForMeet() {
    assertEquals(0, Repository.getInstance1().getGirlList().size());
    Repository.getInstance1().addTournament(tournament);
    
    Repository.getInstance1().changeHostOfMeet(null, schoolManager, tournament, meetA, newHostSchool);
    
    assertEquals(true, tournament.isSavingNeeded());
    Repository.getInstance1().saveTournamentAsNewTournament(tournament, null);
    assertEquals(newHostSchool, meetA.getHostSchool());
    meetA.recursiveUpdateSectionalSchoolsList();
    
  }

}
