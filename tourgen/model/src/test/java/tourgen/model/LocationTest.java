package tourgen.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testGetStreetAddress() {
		Location a = new Location("25 Main St.", "Fort Wayne", 46815);
		assertTrue(a.getStreetAddress().equals("25 Main St."));
	}

}
