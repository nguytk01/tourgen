package tourgen.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import tourgen.model.SchoolManager;

public class SchoolManagerInitFromTextFileTest{
	
	@Test
	public void SchoolManagerInitFromTextTest(){
		SchoolManager manager = new SchoolManager(null);
		manager.initSchools();
		School school = manager.getSchoolFromDisplayName("Roncalli");
		assertEquals(Math.abs(school.getSchoolLoc().getLatitude() - 39.6905) < 0.1, true);
		assertEquals(Math.abs(school.getSchoolLoc().getLongitude() + 86.107) < 0.1, true);
	}
}
