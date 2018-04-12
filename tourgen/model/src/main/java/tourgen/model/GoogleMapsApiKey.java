package tourgen.model;

public class GoogleMapsApiKey {
  private static String geocodeApiKey = "";
  private static String placesApiKey = "";
  private static String distanceMatrixApiKey = "AIzaSyAvT_idXMpl5RrsLjOSLfPYZUPv0VgVrSo";

  /**
   * return geocodeApikey from the Environment or from the value preset in this class. 
   * @return the Geocode API key string
   */
  
  public static String getGoogleGeocodeApiKey() {
    if (geocodeApiKey.equals("")) {
      geocodeApiKey = System.getenv("GOOGLE_GEOCODE_API_KEY");
    }
    return geocodeApiKey;
  }

  /**
   * return Google Places API key from the environment or from the value preset in this class.
   * @return the Places API key string
   */
  
  public static String getGooglePlacesApiKey() {
    if (placesApiKey.equals("")) {
      placesApiKey = System.getenv("GOOGLE_PLACES_API_KEY");
    }
    return placesApiKey;
  }

  /**
   * return Google DistanceMatrix API Key from the environment or from the value preset in
   *  this class.
   * @return the Distance Matrix API Key String
   */
  
  public static String getGoogleDistanceMatrixApiKey() {
    if (distanceMatrixApiKey.equals("")) {
      distanceMatrixApiKey = System.getenv("GOOGLE_DISTANCE_MATRIX_API_KEY");
    }
    return distanceMatrixApiKey;
  }
}
