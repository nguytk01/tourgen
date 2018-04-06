package tourgen.model;

public class GoogleMapsAPIKey{
	private static String geocodeApiKey = "";
	private static String placesApiKey = "";
	private static String distanceMatrixApiKey ="";

	public static String getGoogleGeocodeApiKey(){
		if (geocodeApiKey.equals("")){
			geocodeApiKey = System.getenv("GOOGLE_GEOCODE_API_KEY");
		}
		return geocodeApiKey;
	}

	public static String getGooglePlacesApiKey(){
		if (placesApiKey.equals("")){
			placesApiKey = System.getenv("GOOGLE_PLACES_API_KEY");
		}
		return placesApiKey;
	}

	public static String getGoogleDistanceMatrixApiKey(){
		if (distanceMatrixApiKey.equals("")){
			distanceMatrixApiKey = System.getenv("GOOGLE_DISTANCE_MATRIX_API_KEY");
		}
		return distanceMatrixApiKey;
	}
}
