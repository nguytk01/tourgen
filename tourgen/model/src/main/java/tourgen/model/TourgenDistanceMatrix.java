package tourgen.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

class TourgenDistanceMatrix implements Serializable {

  private static HashMap<String, HashMap<String, Long>> instance;
  private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
  private static GoogleMapsApiHelper helper = new GoogleMapsApiHelper();
  
  private static boolean GEOMETRIC_DISTANCE = true;

  private TourgenDistanceMatrix() {
    instance = new HashMap<String, HashMap<String, Long>>();
    // helper = helperArg;
  }

  static HashMap<String, HashMap<String, Long>> getInstance() {
    return instance;
  }

  static void setInstance(Object instance1) {
    if (instance == null) {
      instance = new HashMap<String, HashMap<String, Long>>();
    }
    // System.out.println(instance1);
    HashMap<Object, Object> temp = (HashMap<Object, Object>) instance1;
    if (temp == null) {
      return;
    }
    java.util.Set<Object> keys = temp.keySet();
    HashMap<String, Long> innerHashMap = null;
    for (Object key : keys) {
      String orig = (String) key;
      HashMap<Object, Object> keyContent = (HashMap<Object, Object>) (temp.get(key));
      java.util.Set<Object> destDistanceMapKey = keyContent.keySet();
      innerHashMap = new HashMap<String, Long>();
      for (Object dest : destDistanceMapKey) {
        String destString = (String) dest;
        Long distance = (Long) (keyContent.get(dest));
        innerHashMap.put(destString, distance);
        // System.out.println(
        // "created a hash map, destStr" + destString + " distance : " +
        // distance);
      }
      System.out.println("mapped orig " + orig + " to hashmap with size " + innerHashMap.size());
      instance.put(orig, innerHashMap);
    }
  }

  /**
   * return an array of long representing the distance from one location to a
   * list of Locations.
   * 
   * @param a
   *          center point
   * @param locationList
   *          a list of clients
   * @return an array of long
   */
  static long[] getDistance1ToN(Location a, List<Location> locationList) {
    if (GEOMETRIC_DISTANCE) {
      return getGeometricDistance1ToN(a, locationList);
    } else {
      return getDrivingDistance1ToN(a, locationList); 
    }
  }
  private static long[] getGeometricDistance1ToN(Location a, List<Location> locationList) {
    double origLat = a.getLatitude();
    double origLong = a.getLongitude();
    String origName = a.getName();
    long distance;
    long[] distanceArr = new long[locationList.size()];
    
    if (instance == null) {
      instance = new HashMap<String, HashMap<String, Long>>();
    }
    
    int distanceArrIndex = 0;
    for (Location i : locationList){
      
      if (!instance.keySet().contains(a.getName())){
        instance.put(origName, new HashMap<String, Long>());
      }
      if (!instance.keySet().contains(i.getName())){
        instance.put(i.getName(), new HashMap<String, Long>());
      }
      if (!instance.get(origName).keySet().contains(i.getName())){
        distance = (long) getGeometricDistance(a.getLatitude(), a.getLongitude(), i.getLatitude(), i.getLongitude());
        instance.get(origName).put(i.getName(), distance);
      } else {
        distance = instance.get(origName).get(i.getName());
      }
      if (!instance.get(i.getName()).keySet().contains(origName)){
        distance = (long) getGeometricDistance(a.getLatitude(), a.getLongitude(), i.getLatitude(), i.getLongitude());
        instance.get(i.getName()).put(origName, distance);
      } else {
        distance = instance.get(origName).get(i.getName());
      }
      distanceArr [ distanceArrIndex ] = distance;
      distanceArrIndex ++;
    }
    return distanceArr;
  }
  private static long[] getDrivingDistance1ToN(Location a, List<Location> locationList) {
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

  static void storeDataBothWays(String origLocationName, String destLocationName, long distance) {
    storeDataOneWay(origLocationName, destLocationName, distance);
    storeDataOneWay(destLocationName, origLocationName, distance);
  }

  private static void storeDataOneWay(String origLocationName, String destLocationName, long distance) {
    if (instance.get(origLocationName) == null) {
      instance.put(origLocationName, new HashMap<String, Long>());
    }
    instance.get(origLocationName).put(destLocationName, distance);
  }

  static void setGoogleMapsApiHelper(GoogleMapsApiHelper helperArg) throws NullPointerException{
    helper = helperArg;
  }

  /**
   * Credits to Jason Winn for the following code.
   * 
   * @param startLat
   *          Latitude of origin
   * @param startLong
   *          Longitude of origin
   * @param endLat
   *          Latitude of destination
   * @param endLong
   *          Longitude of destination
   * @return the geometric distance between the origin and the destination
   */
  private static double getGeometricDistance(double startLat, double startLong, double endLat, double endLong) {

    double haversineLat = Math.toRadians((endLat - startLat));
    double haversineLong = Math.toRadians((endLong - startLong));

    startLat = Math.toRadians(startLat);
    endLat = Math.toRadians(endLat);

    double a = haversin(haversineLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(haversineLong);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    //System.out.println("distance is " + c );
    return EARTH_RADIUS * c * 1000; // <-- d
  }

  private static double haversin(double val) {
    return Math.pow(Math.sin(val / 2), 2);
  }

}
