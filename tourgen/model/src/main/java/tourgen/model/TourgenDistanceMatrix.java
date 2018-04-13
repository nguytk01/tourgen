package tourgen.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;


class TourgenDistanceMatrix implements Serializable {

  private static HashMap<String, HashMap<String, Long>> instance;
  private static GoogleMapsApiHelper helper = new GoogleMapsApiHelper();

  private TourgenDistanceMatrix() {
    instance = new HashMap<String, HashMap<String, Long>>();
    //helper = helperArg;
  }
  
  static HashMap<String, HashMap<String, Long>> getInstance() {
    return instance;
  }
  
  static void setInstance(Object instance1) {
    if (instance == null) {
      instance = new HashMap<String, HashMap<String, Long>>();
    }
    //System.out.println(instance1);
    HashMap<Object,Object> temp = (HashMap<Object,Object>) instance1;
    if (temp == null) {
      return;
    }
    java.util.Set<Object> keys = temp.keySet();
    HashMap<String,Long> innerHashMap = null;
    for (Object key : keys) {
      String orig = (String) key;
      HashMap<Object,Object> keyContent = (HashMap<Object,Object>) (temp.get(key));
      java.util.Set<Object> destDistanceMapKey = keyContent.keySet();
      innerHashMap = new HashMap<String, Long>();
      for (Object dest : destDistanceMapKey) {
        String destString = (String) dest;
        Long distance = (Long) (keyContent.get(dest));
        innerHashMap.put(destString, distance);
        //System.out.println(
        //"created a hash map, destStr" + destString + " distance : " + distance);
      }
      System.out.println("mapped orig " + orig + " to hashmap with size " + innerHashMap.size());
      instance.put(orig, innerHashMap);
    }
  }

  /**
   * return an array of long representing the distance from one location to a list of Locations.
   * @param a center point
   * @param locationList a list of clients
   * @return an array of long
   */
  static long[] getDistance1ToN(Location a, List<Location> locationList) {
    if (instance == null) {
      instance = new HashMap<String, HashMap<String, Long>>();
    }
    if (!instance.keySet().contains(a.getName())) {
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
    } else {
      
      java.util.Collection distancesFromStorage = instance.get(a.getName()).values();
      Object[] distanceObjectArray = distancesFromStorage.toArray();
      long[] distanceLongArr = new long[distanceObjectArray.length];
      for (int i = 0; i < distanceObjectArray.length; i++) {
        distanceLongArr[i] = (long) distanceObjectArray[i];
      }
      return distanceLongArr;
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
