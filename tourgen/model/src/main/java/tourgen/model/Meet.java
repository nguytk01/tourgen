package tourgen.model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.text.WordUtils;

public class Meet {
//SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Example:
	 * 1. Hammond Gavit (14) | 10/10:45 am CT (Riverside Park) | Boys Results | Girls Results 
	*Calumet, East Chicago Central, Gary West Side, Griffith, Hammond, Hammond Academy of Science & Technology, Hammond Bishop Noll, Hammond Clark, Hammond Gavit, Hammond Morton, Highland, Lake Central, Munster, Whiting.
	 */
 private Stage meetStage;
 @Deprecated
 Date meetDate;
 
 org.joda.time.DateTime meetingTime;
 org.joda.time.DateTime alternateMeetingTime;
 School hostSchool;
 java.util.ArrayList<School> participantSchools;
 Location location;


     Meet(Stage meetStage, Date meetDate) {
        this.meetStage = meetStage;
        this.meetDate = meetDate;
        participantSchools = new java.util.ArrayList<School>();
        //String meetDate = dtFormat(meetDate);
    }

    public School getHostSchool() {
        return hostSchool;
    }

     Stage getStage() {
        return meetStage;
    }

    @Deprecated
     Date getDate() {
        return meetDate;
    }

     void setHostSchool(School hostSchoolArg) {
        this.hostSchool = hostSchoolArg;
    }

    void setStage(Stage arg) {
        this.meetStage = arg;
    }

    @Deprecated
    void setDate(Date arg) {
        this.meetDate = arg;
    }

    void addSchooltoMeet(School newSchool) {
        participantSchools.add(newSchool);
    }

    void removeSchoolfromMeet(School oldSchool) {
        participantSchools.remove(oldSchool);
    }

    org.joda.time.DateTime getmeetingDate() {
    	 return meetingTime;
    }

    void setMeetingTime(org.joda.time.DateTime time) {
    	 meetingTime = time;
    }

    void setAlternateMeetingTime(org.joda.time.DateTime time){
      alternateMeetingTime = time;
    }

     void setLocation(Location arg) {
    	 location = arg;
     }
     
     public Location getLocation() {
    	 return location;
     }
     
     public List<School> getParticipatingSchool(){
    	 return Collections.unmodifiableList(participantSchools);
     }
     
     public org.joda.time.DateTime getAlternateMeetingTime(){
    	 return alternateMeetingTime;
     }
     
     public org.joda.time.DateTime getPrimaryMeetingTime(){
    	 return meetingTime;
     }
}