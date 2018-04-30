package tourgen.model;

import static org.junit.Assert.assertEquals;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

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
	  clearRepositorySingleton();
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
    Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament, meetB, meetA, schoolA, schoolManager);
    Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament, meetB, meetA, schoolA, schoolManager);
   
  }
  
  @Test
  public void testTournamentChangedWhenCompetitionSiteChanged() {
    assertEquals(meetA.getParticipatingSchool().size(), 1);
    assertEquals(0, meetA.getParticipatingSchool().indexOf(schoolA));
    Repository.getInstance1().addTournament(tournament);
    Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament, meetB, meetA, schoolA, schoolManager);
    boolean b = tournament.isSavingNeeded();
    assertEquals(true, b);
    boolean a = Repository.getInstance1().hasUnsavedTournament();
    assertEquals(true, a);
  }
  

  @Test(expected=AssertionError.class)
  public void test() {
	  Stage stage = new Stage(StageType.REGIONAL, "" , "");
	    Meet meet = new Meet(stage, null);
	  Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament, meet, meetA, schoolA, schoolManager);
	    Repository.getInstance1().changeCompetitionSiteForSchool(null, tournament, meet, meet, schoolA, schoolManager);

  }
}
