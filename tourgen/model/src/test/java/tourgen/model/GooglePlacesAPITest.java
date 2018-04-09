package tourgen.model;

import org.junit.Test;

import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.TextSearchRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import tourgen.model.GoogleMapsAPIKey;

public class GooglePlacesAPITest{
	@Test
	public void GooglePlacesAPITest(){
		GeoApiContext.Builder builder = new GeoApiContext.Builder()
			.apiKey(GoogleMapsAPIKey.getGooglePlacesApiKey());

		GeoApiContext context = builder.build();
		PlacesSearchResponse response;
		try{
			response = PlacesApi.textSearchQuery(context, "Jac-Cen-Del School").await();
			assertEquals("Jac-Cen-Del School",response.results[0].name);
			System.out.println("PlaceID: " + response.results[0].placeId);

			response = PlacesApi
				.textSearchQuery(context, "Terre Haute North Vigo High School")
				.await();
			System.out.println("North vigo school name: " + response.results[0].name);
			System.out.println("PlaceID: " + response.results[0].placeId);

		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();	
		}
	}
}
