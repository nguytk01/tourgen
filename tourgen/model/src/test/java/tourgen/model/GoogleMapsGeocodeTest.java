package tourgen.model;

import static org.junit.Assert.assertEquals;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;
import org.junit.Test;

import tourgen.model.GoogleMapsApiKey;

public class GoogleMapsGeocodeTest{
	@Test
	public void googleMapsGeocodeTest(){
		com.google.maps.GeoApiContext.Builder builder = null;
		com.google.maps.GeocodingApiRequest request = null;
		try {
		  builder = new com.google.maps.GeoApiContext.Builder()
		      .apiKey(GoogleMapsApiKey.getGoogleGeocodeApiKey());
	        com.google.maps.GeoApiContext context = builder.build();
	        request = new com.google.maps.GeocodingApiRequest(context);
		} catch (Exception e) {
		    System.out.println("Please have your Google API Key ready.");
		    return;
		}
			

		GeocodingResult[] response = null;
		try {
			response = request.place("ChIJb6T-dVuobIgRfz7RNYfKz0Y").await();
			System.out.println(response[0].formattedAddress);
			System.out.println(response[0].geometry.location.lat);
			System.out.println(response[0].geometry.location.lng);
		} catch (ApiException | InterruptedException | IOException e){
			e.printStackTrace();
		} catch (Exception e) {
		  System.out.println("Please have your Google API Key ready.");
          return;
		}
		
	}
}
