package tourgen.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tourgen.model.SchoolManager;

public class SchoolManagerInitFromTextFileTest {

  @Test
  public void schoolManagerInitFromTextTest() {
    SchoolManager manager = new SchoolManager();
    manager.initSchools();
    School school = manager.getSchoolFromDisplayName("Roncalli");
    assertEquals(Math.abs(school.getSchoolLoc().getLatitude() - 39.6905) < 0.1, true);
    assertEquals(Math.abs(school.getSchoolLoc().getLongitude() + 86.107) < 0.1, true);
  }
}
