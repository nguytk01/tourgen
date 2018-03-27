package tourgen.model;
import java.text.SimpleDateFormat;
import java.util.Date;

class Meet {
//SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

private Stage meetStage;
private Date meetDate;
private School hostSchool;
private java.util.ArrayList<School> participantSchools;

    private Meet(Stage meetStage, Date meetDate) {
        this.meetStage = meetStage;
        this.meetDate = meetDate;
        participantSchools = new java.util.ArrayList<School>();
        //String meetDate = dtFormat(meetDate);
    }

    private School getHostSchool() {
        return hostSchool;
    }

    private Stage getStage() {
        return meetStage;
    }

    private Date getDate() {
        return meetDate;
    }

    private void setHostSchool() {
        this.hostSchool = hostSchool;
    }

    private void setStage() {
        this.meetStage = meetStage;
    }

    private void setDate() {
        this.meetDate = meetDate;
    }

    private void addSchooltoMeet(School newSchool) {
        participantSchools.add(newSchool);
    }

    private void removeSchoolfromMeet(School oldSchool) {
        participantSchools.remove(oldSchool);
    }

}