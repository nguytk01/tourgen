package tourgen.model;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.junit.Before;
import org.junit.Test;

public class RepositoryTest extends BaseTournamentCommonTestUtils{
	private Tournament tournament;
	private Stage stage;
	private Meet meetA;
	
	@Before
	public void setUp() throws Exception {
		clearRepositorySingleton();
	}
	
	@Test
	public void testAddTournament() {
		Tournament tour = new Tournament("a", TournamentParticipants.BOYS);
		Repository.getInstance1().addTournament(tour);
		Repository.getInstance1().addTournament(tour);
		assertEquals(2,Repository.getInstance1().getBoyList().size());
		
		Tournament tour1 = new Tournament("a", TournamentParticipants.GIRLS);
		Repository.getInstance1().addTournament(tour1);
		Repository.getInstance1().addTournament(tour1);
		assertEquals(2,Repository.getInstance1().getGirlList().size());
	}
	
	@Test
	public void testStoreBackupTournament() {
		Tournament tour1 = new Tournament("a", TournamentParticipants.GIRLS);
		Repository.getInstance1().addTournament(tour1);
		Repository.getInstance1().saveTournamentAsNewTournament(tour1, "abc");
		assertEquals(2,Repository.getInstance1().getGirlList().size());

	}

	@Test
	public void testChangeCompetitionSiteForSchool() {
		
	}

	@Test
	public void testRemoveAllPropertyChangeListenersForSerialization() {
		Repository.getInstance1().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Repository.getInstance1().removeAllPropertyChangeListenersForSerialization();
	}

	@Test
	public void testRemoveTournament() {
	}

	@Test
	public void testHasUnsavedTournament() {
	}

}
