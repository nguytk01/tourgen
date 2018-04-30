package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class MeetFeederTests extends BaseTournamentCommonTestUtils{
  
  private Meet tempMeet;
  
  @Test
  public void getFeederHeaderSwitchCaseTest() {
    tempMeet = new Meet();
    /*Stage tempDefault = new Stage();
    tempMeet.setStage(tempDefault);
    assertEquals(tempMeet.getFeederHeader(), "Stage");
    assertEquals(tempMeet.getFeederHeader(), "Stage");*/
    Stage tempSectional = new Stage(StageType.SECTIONAL, "test", "test");
    tempMeet.setStage(tempSectional);
    assertEquals(tempMeet.getFeederHeader(), "");
    //if((tempMeet.getStage().getStageType()).equals(StageType.SECTIONAL)) {
    Stage tempRegional = new Stage(StageType.REGIONAL, "test", "test");
    tempMeet.setStage(tempRegional);
    //}
    assertEquals(tempMeet.getFeederHeader(), "Feeder Sectionals: ");
    Stage tempSemi = new Stage(StageType.SEMISTATE, "test", "test");
    tempMeet.setStage(tempSemi);
    assertEquals(tempMeet.getFeederHeader(), "Feeder Regionals:");
    Stage tempFinal = new Stage(StageType.STATEFINAL, "test", "test");
    tempMeet.setStage(tempFinal);
    assertEquals(tempMeet.getFeederHeader(), "Feeder Semi-states:");
    
    tempMeet.getHostSchool();
    tempMeet.getStage();
    
    tempMeet.getmeetingDate();
    tempMeet.getLocation();
    tempMeet.getParticipatingSchool();
    tempMeet.getPrimaryMeetingTime();
    tempMeet.isSectionalMeet();
    tempMeet.announceNewHostForALowerStageMeet(null, null);
    

    
    
    
  }

  @Test
  public void testDateAndTimeGetters() {
    //tempMeet = Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0);
    /*tempMeet = new Meet(new Stage(), new Date());
    tempMeet.setAlternateMeetingTime(DateTime.now());
    tempMeet.setMeetingTime(DateTime.now());
    assertNotNull(tempMeet.getAlternateMeetingTime());
    assertNotNull(tempMeet.getPrimaryMeetingTime());
    assertNotNull(tempMeet.getmeetingDate());*/
  }
}
