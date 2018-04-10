package tourgen.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;


class TourgenDistanceMatrix implements Serializable {

  private static HashMap<String, HashMap<String, Long>> instance;
  private static GoogleMapsApiHelper helper;

  private TourgenDistanceMatrix() {
    instance = new HashMap<String, HashMap<String, Long>>();
    //helper = helperArg;
  }

  /**
   * return an array of long representing the distance from one location to a list of Locations.
   * @param a center point
   * @param locationList a list of clients
   * @return an array of long
   */
  static long[] getDistance1ToN(Location a, List<Location> locationList) {
    String[] origLatLon = new String[] { a.getCoordinateString() };
    String[] destLatLons = new String[locationList.size()];
    for (int i = 0; i < locationList.size(); i++) {
      destLatLons[i] = locationList.get(i).getCoordinateString();
    }
    long[][] distance = helper.getDistance(origLatLon, destLatLons);
    if (distance != null && distance.length == 1 && distance[0].length == locationList.size()) {
      String origLocationName = a.getName();
      for (int i = 0; i < distance[0].length; i++) {
        if (instance.get(origLocationName) == null) {
          instance.put(origLocationName, new HashMap<String, Long>());
        }
        instance.get(origLocationName).put(locationList.get(i).getName(), distance[0][i]);
      }
      return distance[0];
    } else {
      return null;
    }
  }
  
  static void storeDataBothWays(String origLocationName, 
      String destLocationName, long distance) {
    storeDataOneWay(origLocationName, destLocationName, distance);
    storeDataOneWay(destLocationName, origLocationName, distance);
  }
  
  private static void storeDataOneWay(String origLocationName, 
      String destLocationName, long distance) {
    if (instance.get(origLocationName) == null) {
      instance.put(origLocationName, new HashMap<String, Long>());
    }
    instance.get(origLocationName).put(destLocationName, distance);
  }
  
  static void setGoogleMapsApiHelper(GoogleMapsApiHelper helperArg) {
    helper = helperArg;
  }
  
}
