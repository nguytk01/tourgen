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
   * Griffith, Hammond, Hammond Academy of Science and Technology, Hammond Bishop
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
  java.util.ArrayList<School> sectionalSchoolsOfMeet;
  Location location;
  java.beans.PropertyChangeSupport propertyChangeSupport;
  
  public final static String SCHOOL_REMOVED = "School removed";
  public final static String SCHOOL_ADDED = "School added";
  public final static String HOST_SCHOOL_CHANGED = "Host school changed";
  double maxDistance = 0;
  double averageDistance = 0;
  
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
    propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    // String meetDate = dtFormat(meetDate);
    sectionalSchoolsOfMeet = new ArrayList<School> ();
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
    School oldHostSchool = hostSchool;
    this.hostSchool = hostSchoolArg;
    propertyChangeSupport.firePropertyChange(HOST_SCHOOL_CHANGED, oldHostSchool, hostSchool);
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
    propertyChangeSupport.firePropertyChange(SCHOOL_ADDED, newSchool, null);
  }

  public void removeSchoolfromMeet(School oldSchool) {
    participantSchools.remove(oldSchool);
    propertyChangeSupport.firePropertyChange(SCHOOL_REMOVED, oldSchool, null);
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
  
  /**
   * Return the maximum and average distance of a meet.
   * @return an array of 2 doubles: maximum and average distance of a meet.
   */
  public double[] getMaxAndAvgDistance() {
    List<Location> locationList = new ArrayList<Location>();
    List<School> poolOfSchools = null;
    if ( meetStage.getStageType() != StageType.SECTIONAL ) { 
    	if (sectionalSchoolsOfMeet.size() == 0) {
    		recursiveUpdateSectionalSchoolsList();
    	}
    	poolOfSchools = sectionalSchoolsOfMeet;
    } else poolOfSchools = participantSchools;
    
    for (int i = 0; i < poolOfSchools.size(); i++) {
      locationList.add(poolOfSchools.get(i).getSchoolLoc());
    }
    long[] distanceMatrix = TourgenDistanceMatrix.getDistance1ToN(location, locationList);

    long maximumDistance = -1;
    long sumDistance = 0;
    for (int i = 0; i < distanceMatrix.length; i++) {
      sumDistance += distanceMatrix[i];
      if (distanceMatrix[i] > maximumDistance) {
        maximumDistance = distanceMatrix[i];
      }
    }
    maxDistance = maximumDistance;
    averageDistance = 1.0 * sumDistance / distanceMatrix.length;
    return new double[] { maximumDistance, 1.0 * sumDistance / distanceMatrix.length};
  }
  
  public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  void removeAllPropertyChangeListenersForSerialization() {
    for (java.beans.PropertyChangeListener listener : propertyChangeSupport.getPropertyChangeListeners()) {
      propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
  }
  
  public List<School> getSectionalSchoolsOfMeet(){
	  if (meetStage.getStageType().equals(StageType.SECTIONAL)) { 
		  return participantSchools;
	  } else {
		  if (sectionalSchoolsOfMeet.size() == 0) {
			  recursiveUpdateSectionalSchoolsList();
		  }
		  return sectionalSchoolsOfMeet;
	  }
  }
  
  void recursiveUpdateSectionalSchoolsList() {
	  if (meetStage.getStageType().equals(StageType.SECTIONAL)) { 
		  return;
	  } else {
		  System.out.println("meetstage stage type is " + meetStage.getStageType());
		  sectionalSchoolsOfMeet.clear();
		  for (School school : participantSchools) {
			  try {
				  sectionalSchoolsOfMeet.addAll(
						  this.getStage()
						  .getTournament()
						  .getStageOfStageType(meetStage.getStageType().getLowerStageType())
						  .getMeetOfHostSchool(school)
						  .getSectionalSchoolsOfMeet());
			  } catch (NullPointerException e) {
				  System.out.print("Stage type " + meetStage.getStageType() + " Lower Stage type " + meetStage.getStageType().getLowerStageType());
				  System.out.print(" looking for Meet of school :" + school);
				  e.printStackTrace();
				  throw e;
			  }
					  
		  }
	  }
  }
  
  public boolean isSectionalMeet() {
    return this.getStage().isSectionalStage();
  }

 public void announceNewHostForALowerStageMeet(School oldHost, School newHost) {
	for (int i = 0; i < participantSchools.size(); i++){
		if ( participantSchools.get(i) == oldHost ) {
			participantSchools.set(i, newHost);
			return;
		}
	}
	
}
}
