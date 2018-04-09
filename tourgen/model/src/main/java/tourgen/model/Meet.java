package tourgen.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.text.WordUtils;

public class Meet implements Serializable {
  // SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * Example: 1. Hammond Gavit (14) | 10/10:45 am CT (Riverside Park) | Boys
   * Results | Girls Results Calumet, East Chicago Central, Gary West Side,
   * Griffith, Hammond, Hammond Academy of Science & Technology, Hammond Bishop
   * Noll, Hammond Clark, Hammond Gavit, Hammond Morton, Highland, Lake Central,
   * Munster, Whiting.
   */
  private Stage meetStage;
  @Deprecated
  Date meetDate;

  org.joda.time.DateTime meetingTime;
  org.joda.time.DateTime alternateMeetingTime;
  School hostSchool;
  java.util.ArrayList<School> participantSchools;
  Location location;

  /**
   * construct a meet from the given stage and the given meetDate.
   * The meetDate paramter is obsolete. It is there for backward compatibility.
   * The client can pass a null object.
   * @param meetStage the stage of the meet.
   * @param meetDate the meeting date of the meet (obsolete).
   */
  public Meet(Stage meetStage, Date meetDate) {
    this.meetStage = meetStage;
    this.meetDate = meetDate;
    participantSchools = new java.util.ArrayList<School>();
    // String meetDate = dtFormat(meetDate);
  }

  public Meet() {
    participantSchools = new java.util.ArrayList<School>();
  }

  public School getHostSchool() {
    return hostSchool;
  }

  public Stage getStage() {
    return meetStage;
  }

  @Deprecated
  public Date getDate() {
    return meetDate;
  }

  public void setHostSchool(School hostSchoolArg) {
    this.hostSchool = hostSchoolArg;
  }

  public void setStage(Stage arg) {
    this.meetStage = arg;
  }

  /**
   * Return the prefix String before the listing of the schools of each meet.
   * @return a string
   */
  public String getFeederHeader() {
    switch (meetStage.getStageType()) {
      case SECTIONAL:
        return "";
      case REGIONAL:
        return "Feeder Sectionals: ";
      case SEMISTATE:
        return "Feeder Regionals:";
      case STATEFINAL:
        return "Feeder Semi-states:";
      default:
        return "Stage";
    }
  }

  @Deprecated
  void setDate(Date arg) {
    this.meetDate = arg;
  }

  org.joda.time.DateTime getmeetingDate() {
    return meetingTime;
  }

  void setMeetingTime(org.joda.time.DateTime time) {
    meetingTime = time;
  }

  public void addSchooltoMeet(School newSchool) {
    participantSchools.add(newSchool);
  }

  public void removeSchoolfromMeet(School oldSchool) {
    participantSchools.remove(oldSchool);
  }

  void setAlternateMeetingTime(org.joda.time.DateTime time) {
    alternateMeetingTime = time;
  }

  public void setLocation(Location arg) {
    location = arg;
  }

  public Location getLocation() {
    return location;
  }

  public List<School> getParticipatingSchool() {
    return Collections.unmodifiableList(participantSchools);
  }

  public org.joda.time.DateTime getAlternateMeetingTime() {
    return alternateMeetingTime;
  }

  public org.joda.time.DateTime getPrimaryMeetingTime() {
    return meetingTime;
  }

  /**
   * returning a string of a meet.
   */
  
  public String toString() {
    if (hostSchool != null) {
      return hostSchool.getDisplayName();
    } else {
      return location.getName();
    }
  }
}
