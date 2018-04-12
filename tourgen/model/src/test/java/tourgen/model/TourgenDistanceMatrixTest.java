package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;

import tourgen.model.GoogleMapsApiHelper;
import tourgen.model.Location;
import tourgen.model.TourgenDistanceMatrix;

public class TourgenDistanceMatrixTest{

	@Test
	public void getDistance1ToNTest(){
		Location orig = new Location("Austin High School");
		orig.setLatitude(38.73886);
		orig.setLongitude(-85.800183);

		Location dest1 = new Location("Avon High School");
		dest1.setLatitude(39.739265);
		dest1.setLongitude(-86.38787);

		Location dest2 = new Location("Attica High School");
		dest2.setLatitude(40.276822);
		dest2.setLongitude(-87.251381);

		GoogleMapsApiHelper helper = new GoogleMapsApiHelper();
		TourgenDistanceMatrix.setGoogleMapsApiHelper(helper);
		ArrayList<Location> locationList = new ArrayList<Location>();
		locationList.add(dest1);
		locationList.add(dest2);

		long[] result = TourgenDistanceMatrix.getDistance1ToN(orig, locationList);
		assertTrue(result != null);
		System.out.println(result.length);
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
}
