package tourgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;

import java.io.IOException;

import org.junit.Test;
import tourgen.model.GoogleMapsApiKey;

public class GooglePlacesApiTest{
	@Test
	public void googlePlacesApiTest(){
	  new GoogleMapsApiKey();
	  GeoApiContext.Builder builder = null;
	  try {
		   builder = new GeoApiContext.Builder()
		      .apiKey(GoogleMapsApiKey.getGooglePlacesApiKey());
		} catch ( Exception e ) {
		  return;
		}
		
	  
	    
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
		} catch (Exception e) {
          System.out.println("Please have your Google API Key ready.");
          return;
		}
	}
}
