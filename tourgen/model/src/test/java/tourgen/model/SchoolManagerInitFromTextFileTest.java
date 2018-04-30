package tourgen.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tourgen.model.SchoolManager;

public class SchoolManagerInitFromTextFileTest extends BaseTournamentCommonTestUtils{

  @Test
  public void schoolManagerInitFromTextTest() {
    SchoolManager manager = new SchoolManager();
    manager.initSchools();
    School school = manager.getSchoolFromDisplayName("Roncalli");
    assertEquals(Math.abs(school.getSchoolLoc().getLatitude() - 39.6905) < 0.1, true);
    assertEquals(Math.abs(school.getSchoolLoc().getLongitude() + 86.107) < 0.1, true);
  }
  
  @Test
  public void testGetIndexOfSchool() {
	  School school = schoolManager.getSchoolList().get(0);
	  SchoolFormMvcData info = new SchoolFormMvcData(null,
			  school.getDisplayName(), school.getName(), 
			  school.getStreetAddress(), school.getCityName(), school.getZipCode(), 
			  school.getEnroll(), school.getGStatus(), school.getBStatus());
	  assertEquals(0, schoolManager.getSchoolIndex(info));
	  assertEquals(0, schoolManager.getSchoolIndexForAddition(info));
  }
}
