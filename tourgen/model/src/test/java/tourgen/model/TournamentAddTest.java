package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TournamentAddTest extends BaseTournamentCommonTestUtils{
  
  @Test
  public void addTournamentTest() {
    //ArrayList<Object> temp = new ArrayList<Object>();
    Tournament tourneyB = new Tournament("Lads", TournamentParticipants.BOYS);
    //Tournament tourneyb = new Tournament("Lads", TournamentParticipants.BOYS);
    Tournament tourneyG = new Tournament("Gals", TournamentParticipants.GIRLS);
    //Tournament tourneyg = new Tournament("Gals", TournamentParticipants.GIRLS);
    Repository.getInstance1().addTournament(tourneyB);
    Repository.getInstance1().addTournament(tourneyG);
    /*if(tourneyB.equals(tourneyb) && tourneyB == instanceof(Tournament)) {
      Repository.getInstance1().addTournament(tourneyb);
    }
    if(tourneyB.equals(tourneyb)) {
      Repository.getInstance1().addTournament(tourneyg);
    }*/
    assertNotNull(Repository.getInstance1().getBoyList());
    assertNotNull(Repository.getInstance1().getGirlList());
    assertNotNull(Repository.getInstance1().getBoyList().get(0));
    //assertEquals(Repository.getInstance1().getBoyList().get(0), Repository.getInstance1().getBoyList().get(1));
    //assertNotNull(Repository.getInstance1().getBoyList().get(1));
    //assertEquals(Repository.getInstance1().getBoyList().get(1), Repository.getInstance1().getBoyList().get(2));
    //assertEquals(Repository.getInstance1().getBoyList().size(), 2);
    //assertEquals(Repository.getInstance1().getGirlList().size(), 3);
    Stage stage = new Stage(StageType.SECTIONAL, null, null);
    
    tourneyB.addStage(stage);
    tourneyB.removeStage(stage);
    
    School school = new School("TEST", "TEST", "TEST", "TEST", 0, 0, false, false);
    tourneyB.setName("TEST");
    tourneyB.getName();
    
    tourneyB.getStageOfStageType(StageType.REGIONAL);
    
    
    
    
  }

}
