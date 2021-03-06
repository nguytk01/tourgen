package tourgen.model;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class TournamentRemoveTest extends BaseTournamentCommonTestUtils{
  @Test
  public void removeNewlyCreatedTournamentTest() {
    Tournament tournament = new Tournament("testTournament",TournamentParticipants.GIRLS);
    Repository.getInstance1().addTournament(tournament);
    
    Stage stage = new Stage(StageType.SECTIONAL, "", "");
    tournament.addStage(stage);
    
    Meet meetA = new Meet(stage, null);
    stage.addMeetToStage(meetA);
    
    School schoolA = new School("school A", "School A", "211 Main St.", "City", 51121, 21, true, false);
    meetA.addSchooltoMeet(schoolA);
    
    Meet meetB = new Meet(stage, null);
    stage.addMeetToStage(meetB);
    assertEquals(1, Repository.getInstance1().getGirlList().size());
    Repository.getInstance1().removeTournament(tournament);
    assertEquals(1, Repository.getInstance1().getGirlList().size());
  }
  
  @Test
  public void removeRemovableTournamentTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    clearRepositorySingleton();
    Tournament tournament = new Tournament("testTournament",TournamentParticipants.GIRLS);
    Repository.getInstance1().addTournament(tournament);
    
    Stage stage = new Stage(StageType.SECTIONAL, "", "");
    tournament.addStage(stage);
    
    Meet meetA = new Meet(stage, null);
    stage.addMeetToStage(meetA);
    
    School schoolA = new School("school A", "School A", "211 Main St.", "City", 51121, 21, true, false);
    meetA.addSchooltoMeet(schoolA);
    
    Meet meetB = new Meet(stage, null);
    stage.addMeetToStage(meetB);
    
    tournament.setRemovable(true);
    assertEquals(1, Repository.getInstance1().getGirlList().size());
    Repository.getInstance1().removeTournament(tournament);
    assertEquals(0, Repository.getInstance1().getGirlList().size());
  }
}
