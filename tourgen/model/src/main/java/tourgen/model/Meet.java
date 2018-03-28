package tourgen.model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.text.WordUtils;

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
    	
    	String numbering = new Integer(number).toString() + ". ";
    	if (number < 1)
    		arr = new String[] {};
    	else
    	if (location.equals("Null"))
    	arr = new String[] {numbering, hostSchoolString, 
    					" (", new Integer(participantSchools.size()).toString(), ") ",
    					"| ",
    					meetingTime, " (",location.getName(), ")", "<br/>"};
    	else {
    		arr = new String[] {numbering, hostSchoolString, 
					" (", new Integer(participantSchools.size()).toString(), ") ",
					"| ",
					meetingTime, "<br/>"};
    	}
    	builder.append("<b>");
    	for (int i = 0; i < arr.length; i++) {
    		builder.append(arr[i]);
    	}
    	builder.append("</b>");
    	StringBuilder builder2 = new StringBuilder();
    	String hostSchoolName = "";
    	for (int i = 0; i < participantSchools.size(); i++) {
    		if (hostSchool == participantSchools.get(i)) {
    			hostSchoolName = participantSchools.get(i).getDisplayName();
    			hostSchoolName = hostSchoolName.replaceAll(" ", "*");
    			builder2.append(hostSchoolName);
    		}
    		else builder2.append( participantSchools.get(i).getDisplayName());
    		if (i < participantSchools.size()-1)
    			builder2.append(", ");
    	}
    	String wrappedLine = WordUtils.wrap(builder2.toString(), 120, "<br/>", false);
    	//System.out.println(wrappedLine);
    	wrappedLine = wrappedLine.replace(hostSchoolName, "<span style=\"background-color: yellow\">" + hostSchoolName + "</span>");
    	wrappedLine = wrappedLine.replace("*", " ");
    	//System.out.println(wrappedLine);
    	//wrappedLine = wrappedLine.replaceAll("</b>", "</span>");
    	builder.append(participationHeader);
    	builder.append(wrappedLine);
    	builder.append("<br/><br/>");
		return builder.toString();
     }
}