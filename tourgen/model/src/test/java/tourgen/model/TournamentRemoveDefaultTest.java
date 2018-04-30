package tourgen.model;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TournamentRemoveDefaultTest extends BaseTournamentCommonTestUtils{
	
	@Test
	public void denyTournamentRemovalTest() {
		/* create a schoolManager */
		SchoolManager schoolManager = new SchoolManager();
		SchoolFormMvcData sfmd = new SchoolFormMvcData(null, "Hammond Gavit", 
            "Hammond Gavit", 
            "testStreetAddress",
            "testCityName",
            10000, 
            5, 
            true,
            true);
		sfmd.setEnrollmentNumber(5);
		schoolManager.addSchool(sfmd);
		schoolManager.addSchool(new SchoolFormMvcData(null, "Calumet", 
						"Calumet High School", 
						"testStreetAddress",
						"testCityName",
						10000, 
						5, 
						true,
						true));;

		/* create a default tournament */
		RepositoryInitialization.init("tournamentDefaultRemovalTestData.txt", 
				Repository.getInstance1(), 
				schoolManager);
		
		/* check the size of the list of tournaments */
		assertEquals(1, Repository.getInstance1().getGirlList().size());
		
		/* remove the tournament */
		Repository.getInstance1().removeTournament(Repository.getInstance1().getGirlList().get(0));
		
		/* check the list of tournaments */
		assertEquals(1, Repository.getInstance1().getGirlList().size());
	}
	
}
