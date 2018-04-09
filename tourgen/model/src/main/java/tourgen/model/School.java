package tourgen.model;

import java.io.Serializable;

public class School implements Serializable {

  private int schoolID;
  private Location diffLoc;
  private String name;
  private Location schoolLoc;

  @Deprecated
  private Location hostLoc;
  private int enroll;
  private boolean boysTournamentParticipationStatus;
  private boolean girlsTournamentParticipationStatus;
  @Deprecated
  private Location hostAdd;
  @Deprecated
  private String hostName;
  private String displayName;

  public School(String displayNameArg, String schoolName, String streetAddress, String cityName, int zipCode,
      int enrollmentNumber, 
      boolean girlsTournamentParticipatingStatusArg, boolean boysTournamentParticipatingStatusArg) {
    displayName = displayNameArg;
    name = schoolName;
    schoolLoc = new Location(streetAddress, cityName, zipCode);
    schoolLoc.setName(displayName);
    enroll = enrollmentNumber;
    girlsTournamentParticipationStatus = girlsTournamentParticipatingStatusArg;
    boysTournamentParticipationStatus = boysTournamentParticipatingStatusArg;
  }

  public School(SchoolFormMvcData info) {
    name = info.getSchoolName();
    schoolLoc = new Location(info.getStreetAddress(), info.getCityName(), info.getZipCode());
    enroll = info.getEnrollmentNumber();
    boysTournamentParticipationStatus = info.getBoysParticipationStatus();
    girlsTournamentParticipationStatus = info.getGirlsParticipationStatus();
    schoolLoc.setName(displayName);
    displayName = info.getSchoolDisplayName();
  }

  public int getID() {
    return schoolID;
  }

  public void setID(int ID) {
    schoolID = ID;
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

  public void setEnroll(int enrollment) {
    enroll = enrollment;
  }

  public boolean getBStatus() {
    return boysTournamentParticipationStatus;
  }

  public void setBStatus(boolean boys) {
    boysTournamentParticipationStatus = boys;
  }

  public boolean getGStatus() {
    return girlsTournamentParticipationStatus;
  }

  public void setGStatus(boolean girls) {
    girlsTournamentParticipationStatus = girls;
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

  public boolean equals(Object obj) {
    if (obj instanceof School) {
      // compare school's name, address
      School sch = (School) obj;
      if (name.equals(sch.getStreetAddress()) && schoolLoc.equals(sch.getCityName())) {
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

}
