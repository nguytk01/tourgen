package tourgen.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RepositoryInitializationTest {

	@Test
	public void testParseMeetTime() {
		org.joda.time.DateTime[] dateTimeArr = 
				RepositoryInitialization.getMeetTime(
				"9/11:30 am ET", new org.joda.time.DateTime(2017,10,10,10,10));
		assertEquals(dateTimeArr[0].hourOfDay().get(), 11);
		assertEquals(dateTimeArr[1].hourOfDay().get(), 9);
		assertEquals(dateTimeArr[0].getZone().toString(), "America/New_York");
	}
	
	@Test
	public void testParseMeetTime1() {
		org.joda.time.DateTime[] dateTimeArr = 
				RepositoryInitialization.getMeetTime(
				"9/11:30 PM CT", new org.joda.time.DateTime(2017,10,10,10,10));
		//System.out.println(dateTimeArr[0].hourOfDay().get());
		assertEquals(dateTimeArr[0].hourOfDay().get(), 23);
		assertEquals(dateTimeArr[1].hourOfDay().get(), 21);
		assertEquals(dateTimeArr[0].getZone().toString(), "America/Chicago");
		org.joda.time.DateTime[] dateTimeArr1 = 
				RepositoryInitialization.getMeetTime(
				"9/12:15 pm ET", new org.joda.time.DateTime(2017,10,10,10,10));
		//System.out.println(dateTimeArr[0].hourOfDay().get());
		assertEquals(dateTimeArr[0].hourOfDay().get(), 23);
		assertEquals(dateTimeArr[1].hourOfDay().get(), 21);
		assertEquals(dateTimeArr[0].getZone().toString(), "America/Chicago");
	}

	@Test	
	public void runImportTest(){
		Repository repo = new Repository();
		SchoolManager manager = new SchoolManager(repo);
		manager.initSchools();
		RepositoryInitialization.init(repo, manager);
	}
}
