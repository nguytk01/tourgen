package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testGetStreetAddress() {
		Location a = new Location("25 Main St.", "Fort Wayne", 46815);
		assertTrue(a.getStreetAddress().equals("25 Main St."));
		
		a.getCoordinateString();
		a.equals(null);
		a.getFullStreetAddress();
	
	}
	
	@Test
	public void testLocation1() {
		Location a = new Location("25 Main St.", "Fort Wayne", 46815);
		a.setLatitude(41.0);
		a.setLongitude(-85.0);
		assertEquals("41.0,-85.0", a.getCoordinateString());
	}

}
