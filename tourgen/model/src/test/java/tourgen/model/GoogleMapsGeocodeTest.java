package tourgen.model;

import org.junit.Test;


import com.google.maps.GeocodingApiRequest;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import tourgen.model.GoogleMapsAPIKey;

public class GoogleMapsGeocodeTest{
	@Test
	public void GoogleMapsGeocodeTest(){
		GeoApiContext.Builder builder = new GeoApiContext.Builder()
			.apiKey(GoogleMapsAPIKey.getGoogleGeocodeApiKey());
		GeoApiContext context = builder.build();
		GeocodingApiRequest request = new GeocodingApiRequest(context);
		GeocodingResult[] response = null;
		try {
			response = request.place("ChIJb6T-dVuobIgRfz7RNYfKz0Y").await();
			System.out.println(response[0].formattedAddress);
			System.out.println(response[0].geometry.location.lat);
			System.out.println(response[0].geometry.location.lng);
		} catch (ApiException | InterruptedException | IOException e){
			e.printStackTrace();
		}
		
	}
}
