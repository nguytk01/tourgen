package tourgen.model;

import java.io.Serializable;

public class School implements Serializable {

  private int id;
  @Deprecated
  private Location diffLoc;
  private String name;
  private Location schoolLoc;

  private boolean eligibleToHost;
  
  @Deprecated
  private Location hostLoc;
  private int enroll;
  private boolean bStatus;
  private boolean gStatus;
  @Deprecated
  private Location hostAdd;
  @Deprecated
  private String hostName;
  private String displayName;
  
  private java.beans.PropertyChangeSupport propertyChangeSupport;
  
  /**
   * to construct a school object from parameters passed to it.
   * @param displayNameArg is the display name of school
   * @param schoolName is the full name of school
   * @param streetAddress is the street address of school
   * @param cityName is the city name of school
   * @param zipCode is the five-digit zipcode of school
   * @param enrollmentNumber is the number of students enrolled in the school
   * @param girlsTournamentParticipatingStatusArg is the girls' participation flag
   * @param boysTournamentParticipatingStatusArg is the boys' participation flag
   */
  public School(String displayNameArg, String schoolName, String streetAddress,
      String cityName, int zipCode, int enrollmentNumber,
      boolean girlsTournamentParticipatingStatusArg, boolean boysTournamentParticipatingStatusArg) {
    displayName = displayNameArg;
    name = schoolName;
    schoolLoc = new Location(streetAddress, cityName, zipCode);
    schoolLoc.setName(displayName);
    enroll = enrollmentNumber;
    gStatus = girlsTournamentParticipatingStatusArg;
    bStatus = boysTournamentParticipatingStatusArg;
    propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
  }
  
  /**
   * to construct a new school object from the data passed to it from the school form.
   * @param info is a school form object to add a new school
   */
  public School(SchoolFormMvcData info) {
    name = info.getSchoolName();
    schoolLoc = new Location(info.getStreetAddress(), info.getCityName(), info.getZipCode());
    enroll = info.getEnrollmentNumber();
    bStatus = info.getBoysParticipationStatus();
    gStatus = info.getGirlsParticipationStatus();
    schoolLoc.setName(displayName);
    displayName = info.getSchoolDisplayName();
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Deprecated
  public Location getDiffLoc() {
    return diffLoc;
  }

  public String getStreetAddress() {
    return schoolLoc.getStreetAddress();
  }

  public String getCityName() {
    return schoolLoc.getCityName();
  }

  public int getZipCode() {
    return schoolLoc.getZipCode();
  }

  @Deprecated
  public void setDiffLoc(Location loc) {
    diffLoc = loc;
  }

  public Location getSchoolLoc() {
    return schoolLoc;
  }

  public void setSchoolLoc(Location loc) {
    schoolLoc = loc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Deprecated
  public Location getHostLoc() {
    return hostLoc;
  }

  @Deprecated
  public void setHostLoc(Location loc) {
    hostLoc = loc;
  }

  public int getEnroll() {
    return enroll;
  }

  public void setEnroll(int enroll) {
    this.enroll = enroll;
  }

  public boolean getBStatus() {
    return bStatus;
  }

  public void setBStatus(boolean bStatus) {
    this.bStatus = bStatus;
  }

  public boolean getGStatus() {
    return gStatus;
  }

  public void setGStatus(boolean gStatus) {
    this.gStatus = gStatus;
  }

  /*
   * public Location getHostAdd() { return hostAdd; }
   * 
   * public void setHostAdd(Location loc) { hostAdd = loc; }
   * 
   * public String getHostName() { return hostName; }
   * 
   * public void setHostName(String host) { hostName = host; }
   */
  
  /**
   * compares an object passed to it check for equality with local school object.
   */
  public boolean equals(Object obj) {
    if (obj instanceof School) {
      // compare school's name, address
      School sch = (School) obj;
      if (name.equals(sch.name) && schoolLoc.equals(sch.schoolLoc)) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }

  }

  public String toString() {
    return name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setEligibleToHost(boolean arg) {
    eligibleToHost = arg;
    this.propertyChangeSupport.firePropertyChange("CHANGED", false, true);
  }
  
  public boolean isEligibleToHost() {
    return eligibleToHost;
  }
  
  public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
	  this.propertyChangeSupport.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
	  this.propertyChangeSupport.addPropertyChangeListener(listener);
  }
}
