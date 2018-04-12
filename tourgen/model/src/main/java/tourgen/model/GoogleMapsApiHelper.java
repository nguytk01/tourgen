package tourgen.model;

import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import java.io.IOException;
import org.joda.time.Duration;
import org.joda.time.Instant;

class GoogleMapsApiHelper {
  private GeoApiContext distanceMatrixContext;
  private GeoApiContext geocodingContext;
  private GeoApiContext placesContext;

  GoogleMapsApiHelper() {
    distanceMatrixContext = (new GeoApiContext.Builder()
        .apiKey(tourgen.model.GoogleMapsApiKey.getGoogleDistanceMatrixApiKey())).build();
    geocodingContext = (new GeoApiContext.Builder()
        .apiKey(tourgen.model.GoogleMapsApiKey.getGoogleGeocodeApiKey()))
        .build();
    placesContext = (new GeoApiContext.Builder()
        .apiKey(tourgen.model.GoogleMapsApiKey.getGooglePlacesApiKey()))
        .build();
  }

  long[][] getDistance(String[] originCoordinates, String[] destinationsCoordinates) {
    DistanceMatrix matrix = null;
    try {
      matrix = new DistanceMatrixApiRequest(distanceMatrixContext)
          .origins(originCoordinates)
          .destinations(destinationsCoordinates)
          .mode(TravelMode.DRIVING)
          .trafficModel(TrafficModel.BEST_GUESS)
          .departureTime(new Instant().withDurationAdded(Duration.standardMinutes(2), 1)).await();
      DistanceMatrixRow[] rows = matrix.rows;
      long[][] distances = new long[rows.length][];
      for (int i = 0; i < rows.length; i++) {
        distances[i] = new long[rows[i].elements.length];
        for (int j = 0; j < distances[i].length; j++) {
          distances[i][j] = rows[i].elements[j].distance.inMeters;
        }
      }
      return distances;
    } catch (ApiException | InterruptedException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  long[][] getDistanceWithPlaceId(String[] originPlaceId, String[] destinationPlaceIds) {
    DistanceMatrix matrix = null;
    try {
      matrix = new DistanceMatrixApiRequest(distanceMatrixContext)
          .origins(originPlaceId)
          .destinations(destinationPlaceIds)
          .mode(TravelMode.DRIVING)
          .trafficModel(TrafficModel.BEST_GUESS)
          .departureTime(
              new Instant().withDurationAdded(Duration.standardMinutes(2), 1))
          .await();
      DistanceMatrixRow[] rows = matrix.rows;
      long[][] distances = new long[rows.length][];
      for (int i = 0; i < rows.length; i++) {
        distances[i] = new long[rows[i].elements.length];
        for (int j = 0; j < rows[i].elements.length; j++) {
          distances[i][j] = rows[i].elements[j].distance.inMeters;
        }
      }
      return distances;
    } catch (ApiException | InterruptedException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  String getPlaceIdOfSchoolName(String schoolName) {
    /*
     * PlacesSearchResponse response = null; try{ response =
     * PlacesApi.textSearchQuery(placesContext, schoolName).await();
     * 
     * System.out.println("PlaceID: " + response.results[0].placeId);
     * 
     * response = PlacesApi //.textSearchQuery(context,
     * "Terre Haute North Vigo High School") .textSearchQuery(context,
     * "Ben Davis High School") .await();
     * System.out.println("North vigo school name: " + response.results[0].name);
     * System.out.println("PlaceID: " + response.results[0].placeId);
     * 
     * } catch (ApiException | InterruptedException | IOException e) {
     * e.printStackTrace(); }
     */ 
    return null;
  }
}
