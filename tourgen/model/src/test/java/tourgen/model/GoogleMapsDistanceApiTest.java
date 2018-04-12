package tourgen.model;

import com.google.maps.DistanceMatrixApi;

import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.junit.Test;

import tourgen.model.GoogleMapsApiKey;

public class GoogleMapsDistanceApiTest{
	@Test
	public void testDistanceApi(){
		GeoApiContext.Builder builder = new GeoApiContext.Builder().apiKey(GoogleMapsApiKey.getGoogleDistanceMatrixApiKey());
		GeoApiContext context = builder.build();
		DistanceMatrix matrix = null;
		try {
			matrix = new DistanceMatrixApiRequest(context)
				.origins(new String[] {"Fort Wayne, USA"})
			        .destinations(new String[] {"Indianapolis, USA"})
				.mode(TravelMode.DRIVING)
				.trafficModel(TrafficModel.BEST_GUESS)
				.departureTime(new Instant().withDurationAdded(Duration.standardMinutes(2), 1))
				.await();
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		//Object[] matrix = new Object[]{"String", new Integer(23)};
		System.out.println(matrix.rows[0].elements[0].distance.toString());
		System.out.println(matrix.rows[0].elements[0].duration.toString());
	}
	
	@Test
	public void testDistanceApiForCoordinates(){
		GeoApiContext.Builder builder = new GeoApiContext.Builder().apiKey("AIzaSyDbK71KMNFhgo8ATDd4GffFpcjaSIeeizE");
		GeoApiContext context = builder.build();
		DistanceMatrix matrix = null;
		try {
			matrix = DistanceMatrixApi.getDistanceMatrix(context, new String[] {"41,-85"}, new String[] {"41,-87.62"}).await();
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(matrix.rows[0].elements[0].distance);
		System.out.println(matrix.rows[0].elements[0].duration.toString());
	}
}
