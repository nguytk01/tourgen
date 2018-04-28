package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tourgen.model.GoogleMapsApiHelper;


public class GoogleMapsApiHelperTests{
	
	@Test
	public void getDistanceWithPlaceIdTest(){
		String originPlaceId = "place_id:ChIJ386Ird9daogRIfL2WDoH99U";	
		String destPlaceId1 = "place_id:ChIJidwp0ZhvbYgRRO17SY_cCBA";
		String destPlaceId2 = "place_id:ChIJb6T-dVuobIgRfz7RNYfKz0Y";
		GoogleMapsApiHelper helper = new GoogleMapsApiHelper();
		try {
			long[][] distance = helper.getDistanceWithPlaceId(new String[]{originPlaceId}, new String[]{destPlaceId1, destPlaceId2});
			assertTrue(distance != null);
			assertTrue(distance[0].length == 2);
			assertTrue(distance[0][0] > 0);
			assertTrue(distance[0][1] > 0);
		} catch (NullPointerException e) {
			assertEquals("GoogleMapsApiKey",e.getStackTrace()[0].getClassName());
			assertEquals("getGoogleGeocodeApiKey",e.getStackTrace()[0].getMethodName());
		}
		
		//System.out.println(Arrays.deepToString(distance));
	}
}
