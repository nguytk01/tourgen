package tourgen.model;

import tourgen.model.GoogleMapsApiHelper;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;

public class GoogleMapsApiHelperTests{
	
	@Test
	public void getDistanceWithPlaceIDTest(){
		String originPlaceID = "place_id:ChIJ386Ird9daogRIfL2WDoH99U";	
		String destPlaceID1 = "place_id:ChIJidwp0ZhvbYgRRO17SY_cCBA";
		String destPlaceID2 = "place_id:ChIJb6T-dVuobIgRfz7RNYfKz0Y";
		GoogleMapsApiHelper helper = new GoogleMapsApiHelper();
		long[][] distance = helper.getDistanceWithPlaceID(new String[]{originPlaceID}, new String[]{destPlaceID1, destPlaceID2});
		assertTrue(distance != null);
		assertTrue(distance[0].length == 2);
		assertTrue(distance[0][0] > 0);
		assertTrue(distance[0][1] > 0);
		//System.out.println(Arrays.deepToString(distance));
	}
}
