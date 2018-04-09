package tourgen.model;

import java.io.IOException;
import java.util.HashMap;

import com.google.maps.DistanceMatrixApi;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.DistanceMatrix;

import java.util.List;
import java.util.HashMap;
import tourgen.model.GoogleMapsApiKey;

public class TourgenDistanceMatrix {
  private HashMap<String, HashMap<String, Long>> distanceMatrix;
  private GoogleMapsApiHelper helper;

  public TourgenDistanceMatrix(GoogleMapsApiHelper helperArg) {
    distanceMatrix = new HashMap<String, HashMap<String, Long>>();
    helper = helperArg;
  }

  public long[] getDistance1ToN(Location a, List<Location> locationList) {
    String[] origLatLon = new String[] { a.getCoordinateString() };
    String[] destLatLons = new String[locationList.size()];
    for (int i = 0; i < locationList.size(); i++) {
      destLatLons[i] = locationList.get(i).getCoordinateString();
    }
    long[][] distance = helper.getDistance(origLatLon, destLatLons);
    if (distance != null && distance.length == 1 && distance[0].length == locationList.size()) {
      String origLocationName = a.getName();
      for (int i = 0; i < distance[0].length; i++) {
        if (distanceMatrix.get(origLocationName) == null) {
          distanceMatrix.put(origLocationName, new HashMap<String, Long>());
        }
        distanceMatrix.get(origLocationName).put(locationList.get(i).getName(), distance[0][i]);
      }
      return distance[0];
    } else
      return null;
  }
}
