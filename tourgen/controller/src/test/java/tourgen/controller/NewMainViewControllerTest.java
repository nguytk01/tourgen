package tourgen.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import tourgen.model.Meet;
import tourgen.model.SchoolManager;
import tourgen.model.Stage;
import tourgen.model.StageType;
import tourgen.model.Tournament;
import tourgen.model.TournamentParticipants;

public class NewMainViewControllerTest {

  @Test
  public void testSetActiveTournament() {
    MainViewPanelMock leftPanelMock = new MainViewPanelMock();
    MainViewPanelMock rightPanelMock = new MainViewPanelMock();
    Tournament tournament = new Tournament("", TournamentParticipants.GIRLS);
    NewMainViewController controller = new NewMainViewController(leftPanelMock, rightPanelMock, null);
    controller.setActiveTournament(tournament);
    
    assertEquals(tournament, leftPanelMock.getTournamentJustSet());
    assertEquals(tournament, rightPanelMock.getTournamentJustSet());
  }
  
  @Test
  public void testGetSchoolManager(){
	  SchoolManager manager = new SchoolManager();
	  NewMainViewController controller = new NewMainViewController(null, null, manager);
	  assertEquals(manager, controller.getSchoolManager());
  }

  @Test
  public void testGetAvailableSectionalMeets(){
	  Tournament tournament = new Tournament("", TournamentParticipants.GIRLS);
	  Stage stage = new Stage(StageType.SECTIONAL, "" ,"");
	  tournament.addStage(stage);
	  Meet meetA = new Meet(stage, null);  
	  Meet meetB = new Meet(stage, null);  
  }
}
