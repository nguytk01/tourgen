package tourgen.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;


public class Stage {

private ArrayList<Meet> stageMeets;
private StageType stageType;
private String stageHeader;
private String participantsHeader;

    /**Entry List Deadline: Monday, Oct. 2, 2017, 4 pm ET / 3 pm CT.
    Date: Saturday, Oct. 7, 2017.
    Admission: $5 per person.
    Advancement: The top 10 individuals from nonâ€�advancing teams and the first 5 qualifying teams from each sectional shall advance to designated regionals.
    Note: Races conducted at host school unless indicated otherwise.
    */
    private org.joda.time.DateTime entryListDeadline;
    private org.joda.time.DateTime stageMeetDate;
    private double admissionFee;
    private String advancementRule;
    private boolean racesConductedAtHost;
    private boolean noteOnChangingHostLocation;

    /**
     * These are for Stage Final
     */
    private Location stageMeetLocation;

    private org.joda.time.DateTime girlsMeetingTime;
    private org.joda.time.DateTime boysMeetingTime;

    public Stage(StageType stageType, String stageHeaderArg,
    String participantsHeaderArg) {
        this.stageType = stageType;
        stageMeets = new ArrayList<Meet>();
        stageHeader = stageHeaderArg;
        participantsHeader = participantsHeaderArg;
    }

    void addMeetToStage(Meet newMeet) {
        stageMeets.add(newMeet);
    }

    void removeMeetFromStage(Meet oldMeet) {
        stageMeets.remove(oldMeet);
    }

    public String getStageTitle() {
    	switch (stageType) {
    		case SECTIONAL : return "Sectionals";
    		case REGIONAL: return "Regionals";
    		case SEMISTATE: return "Semi-states";
    		case STATEFINAL: return "State finals";
    		default: return "Stage";
    	}
    }

    public List<Meet> getMeetList(){
        return Collections.unmodifiableList(stageMeets);
    }

    void setAdmissionFee(double fee){
        admissionFee = fee;
    }

    void setStageMeetDate(org.joda.time.DateTime date){
        stageMeetDate = date;
    }

    void setEntryListDeadline(org.joda.time.DateTime date){
        entryListDeadline = date;
    }

    void setAdvancementRule(String advancementRuleArg){
        advancementRule = advancementRuleArg;
    }

    void setBoysMeetingTime(org.joda.time.DateTime boysMeetingTimeArg){
        	boysMeetingTime = boysMeetingTimeArg;
    }

    void setGirlsMeetingTime(org.joda.time.DateTime girlsMeetingTimeArg) {
        girlsMeetingTime = girlsMeetingTimeArg;
    }
    
    void setRacesConductedAtHost(boolean arg) {
    	racesConductedAtHost = arg;
    }
    
    void setNoteOnChangingHostLocation(boolean arg) {
    	noteOnChangingHostLocation = arg;
    }

    public org.joda.time.DateTime getStageMeetDate(){
        return stageMeetDate;
    }

    public org.joda.time.DateTime getEntryListDeadline(){
        return entryListDeadline;
    }

    public String getAdvancementRules(){
        return advancementRule;
    }

    public double getAdmissionFee(){
        return admissionFee;
    }

    public boolean isRacesConductedAtHost(){
        return racesConductedAtHost;
    }

    public boolean isNoteOnChangingHostLocation(){
        return noteOnChangingHostLocation;
    }

    public org.joda.time.DateTime getGirlsMeetingTime(){
        return girlsMeetingTime;
    }

    public org.joda.time.DateTime getBoysMeetingTime(){
        return boysMeetingTime;
    }

 }
