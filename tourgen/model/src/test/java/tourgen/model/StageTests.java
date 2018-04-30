package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class StageTests extends BaseTournamentCommonTestUtils{
  
  @Test
  public void getStageTitleSwitchCaseTest() {
    Stage tempSectional = new Stage(StageType.SECTIONAL, "test", "test");
    assertEquals(tempSectional.getStageTitle(), "Sectionals");
    Stage tempRegional = new Stage(StageType.REGIONAL, "test", "test");
    assertEquals(tempRegional.getStageTitle(), "Regionals");
    Stage tempSemi = new Stage(StageType.SEMISTATE, "test", "test");
    assertEquals(tempSemi.getStageTitle(), "Semi-states");
    Stage tempFinal = new Stage(StageType.STATEFINAL, "test", "test");
    assertEquals(tempFinal.getStageTitle(), "State finals");
    //StageType
  }

}
