package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import tourgen.model.GoogleMapsApiHelper;
import tourgen.model.Location;
import tourgen.model.TourgenDistanceMatrix;

public class TourgenDistanceMatrixTest{

    @Test//(expected=ExceptionInInitializerError.class)
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

		GoogleMapsApiHelper helper = null;
		try {
		  helper = new GoogleMapsApiHelper();
		  TourgenDistanceMatrix.setGoogleMapsApiHelper(helper);
		} catch (NullPointerException e) {
		  return;
		}
		
		ArrayList<Location> locationList = new ArrayList<Location>();
		locationList.add(dest1);
		locationList.add(dest2);

		long[] result = TourgenDistanceMatrix.getDistance1ToN(orig, locationList);
		assertTrue(result != null);
		System.out.println(result.length);
		System.out.println(result[0]);
		System.out.println(result[1]);
		
		TourgenDistanceMatrix tour = new TourgenDistanceMatrix();
		
		tour.setInstance(null);

	}
    @Test
    public void testSetInstance() {
    	HashMap<String,HashMap<String,Long>> hashMap = new HashMap<String,HashMap<String,Long>>();
    	HashMap<String,Long> innerHashMap = new HashMap<String,Long>();
    	innerHashMap.put("abc", new Long(5));
    	hashMap.put("new", innerHashMap);
    	TourgenDistanceMatrix tour = new TourgenDistanceMatrix();
    	tour.setInstance(hashMap);
    }
    
}
