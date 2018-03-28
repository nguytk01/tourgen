package tourgen.model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Meet {
//SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

 Stage meetStage;
 Date meetDate;
 String meetingTime;
 School hostSchool;
 java.util.ArrayList<School> participantSchools;
 Location location;

     Meet(Stage meetStage, Date meetDate) {
        this.meetStage = meetStage;
        this.meetDate = meetDate;
        participantSchools = new java.util.ArrayList<School>();
        //String meetDate = dtFormat(meetDate);
    }

     School getHostSchool() {
        return hostSchool;
    }

     Stage getStage() {
        return meetStage;
    }

     Date getDate() {
        return meetDate;
    }

     void setHostSchool(School hostSchoolArg) {
        this.hostSchool = hostSchoolArg;
    }

    void setStage(Stage arg) {
        this.meetStage = arg;
    }

    void setDate(Date arg) {
        this.meetDate = arg;
    }

     void addSchooltoMeet(School newSchool) {
        participantSchools.add(newSchool);
    }

     void removeSchoolfromMeet(School oldSchool) {
        participantSchools.remove(oldSchool);
    }
     
     String getmeetingTime(String time) {
    	 return meetingTime;
     }
     
     void setMeetingTime(String time) {
    	 meetingTime = time;
     }

     void setLocation(Location arg) {
    	 location = arg;
     }
     
     public String getReport(int number, String participationHeader) {
    	String hostSchoolString;
    	StringBuilder builder = new StringBuilder();
    	if (hostSchool == null) hostSchoolString = "";
    	else hostSchoolString = hostSchool.getDisplayName();
    	String[] arr = null;
    	if (location.equals("Null"))
    	arr = new String[] {new Integer(number).toString(), ". ", hostSchoolString, 
    					" (", new Integer(participantSchools.size()).toString(), ") ",
    					"| ",
    					meetingTime, " (",location.getName(), ")", "\n"};
    	else {
    		arr = new String[] {new Integer(number).toString(), ". ", hostSchoolString, 
					" (", new Integer(participantSchools.size()).toString(), ") ",
					"| ",
					meetingTime, "\n"};
    	}
    	
    	for (int i = 0; i < arr.length; i++) {
    		builder.append(arr[i]);
    	}
    	builder.append(participantSchools.get(0).getDisplayName());
    	for (int i = 1; i < participantSchools.size(); i++) {
    		builder.append(",");
    		builder.append(participantSchools.get(i).getDisplayName());
    	}
    	builder.append("\n\n");
		return builder.toString();
     }
}