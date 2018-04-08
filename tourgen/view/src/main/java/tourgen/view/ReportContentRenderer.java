package tourgen.view;

import tourgen.model.Tournament;
import tourgen.model.Stage;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import tourgen.model.Meet;
import tourgen.model.StageType;
import tourgen.model.School;
import org.apache.commons.text.WordUtils;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.DateTimeZone;
import java.text.DecimalFormat;
public class ReportContentRenderer {
    //private Tournament tournament;
	private static final Map<String, DateTimeZone> mapEtCtToTimeZone = new HashMap<String, DateTimeZone>();
	static {
			
			mapEtCtToTimeZone.put("CT", DateTimeZone.forID("America/Chicago"));
			mapEtCtToTimeZone.put("ET", DateTimeZone.forID("America/New_York"));
	}
	private String newlineCharacter = "<br />";
    public ReportContentRenderer(){
    }

    public HashMap<String, String> getTournamentReport(Tournament tournament){
        List<Stage> stageList = tournament.getStageList();
	    StringBuilder builder = new StringBuilder();
    	HashMap<String, String> map = new HashMap<String, String>();
    	for (Stage stage : stageList) {
    		map.put(stage.getStageTitle(), getStageReport(stage));
    	}
    	return map;
    }

    public String getStageReport(Stage stage){
        StringBuilder builder = new StringBuilder();
        List<Meet> stageMeets = stage.getMeetList();
    	String stageHeader = buildStageHeader(stage);
	builder.append(stageHeader);
	for (int i = 1; i <= stageMeets.size(); i++) {
    		if (stageMeets.size() < 2)
    			builder.append(
    			    getMeetReport(0, stageMeets.get(i-1)));
    		else
    			builder.append(
    			    getMeetReport(i, stageMeets.get(i-1)));
    	}
    	return builder.toString();
    }

    public String getMeetReport(int number, Meet meet){
        String hostSchoolString;
        School hostSchool = meet.getHostSchool();
    	StringBuilder builder = new StringBuilder();
    	if (hostSchool == null) hostSchoolString = "";
    	else hostSchoolString = hostSchool.getDisplayName();
    	String[] arr = null;

    	String numbering = new Integer(number).toString() + ". ";
    	if (number < 1 )
    		arr = new String[] {};
    	else
    	if (meet.getLocation().getName().equals("Null")){
    	    arr = new String[] {numbering, 
		    		hostSchoolString,
    				" (", 
				new Integer(meet.getParticipatingSchool().size()).toString(),
			       	") ",
				"| ",
				buildMeetTime(meet),
				" (",
				meet.getLocation().getName(),
			       	")", 
				"<br/>"};
    	} else {
    		arr = new String[] {numbering, hostSchoolString,
					" (", new Integer(meet.getParticipatingSchool().size()).toString(), ") ",
					"| ",
					buildMeetTime(meet),
				       	"<br/>"};
    	}
    	builder.append("<b>");
    	for (int i = 0; i < arr.length; i++) {
    		builder.append(arr[i]);
    	}
    	builder.append("</b>");
    	StringBuilder builder2 = new StringBuilder();
    	String hostSchoolName = "";
    	for (int i = 0; i < meet.getParticipatingSchool().size(); i++) {
    		if (hostSchool == meet.getParticipatingSchool().get(i)) {
    			hostSchoolName = meet.getParticipatingSchool().get(i).getDisplayName();
    			hostSchoolName = hostSchoolName.replaceAll(" ", "*");
    			builder2.append(hostSchoolName);
    		}
    		else builder2.append( meet.getParticipatingSchool().get(i).getDisplayName());
    		if (i < meet.getParticipatingSchool().size()-1)
    			builder2.append(", ");
    	}
    	String wrappedLine = WordUtils.wrap(builder2.toString(), 120, "<br/>", false);
    	//System.out.println(wrappedLine);
    	wrappedLine = wrappedLine.replace(hostSchoolName, "<span style=\"background-color: yellow\">" + hostSchoolName + "</span>");
    	wrappedLine = wrappedLine.replace("*", " ");
    	//System.out.println(wrappedLine);
    	//wrappedLine = wrappedLine.replaceAll("</b>", "</span>");

	String  meetFeederHeader = "<b>" + meet.getFeederHeader() + "</b>";
    	builder.append(meetFeederHeader);
	builder.append(wrappedLine);
    	builder.append("<br/><br/>");
		return builder.toString();
    }

    public String buildStageHeader(Stage stage){
	String entryListDeadline = "";
	String stageMeetDate = "";
	String admissionFee = "";
	String advancementRule = "";
	String note = "";
	StringBuilder builder = new StringBuilder();
	org.joda.time.DateTime entryListDeadlineDate = stage.getEntryListDeadline();
	org.joda.time.format.DateTimeFormatter easternTimeEntryListDeadlineFormatter;
	org.joda.time.format.DateTimeFormatter centralTimeEntryListDeadlineFormatter;
	//System.out.println("stage meet date " + stage.getStageMeetDate());
	//System.out.println("stage meet date hour " + stage.getStageMeetDate().getHourOfDay());
	if (entryListDeadlineDate != null) {
		//System.out.println("entry deadline " + stage.getEntryListDeadline());
		easternTimeEntryListDeadlineFormatter =
			new DateTimeFormatterBuilder()
			.appendDayOfWeekText()
			.appendLiteral(", ")
			.appendMonthOfYearShortText()
			.appendLiteral(". ")
			.appendDayOfMonth(1)
			.appendLiteral(", ")
			.appendYear(4,4)
			.appendLiteral(", ")
			.appendClockhourOfHalfday(1)
			.appendLiteral(" ")
			.appendHalfdayOfDayText()
			.appendLiteral(" ")
			.appendTimeZoneShortName(mapEtCtToTimeZone)
			.toFormatter();

		centralTimeEntryListDeadlineFormatter = 
			new DateTimeFormatterBuilder()
			.appendClockhourOfHalfday(1)
			.appendLiteral(" ")
			.appendHalfdayOfDayText()
			.appendLiteral(" ")
			.appendTimeZoneShortName(mapEtCtToTimeZone)
			.toFormatter();

		entryListDeadline = 
			"<b>Entry List Deadline: </b>"+
			easternTimeEntryListDeadlineFormatter.print(entryListDeadlineDate)
			.replace("EDT","ET")
			.replace("CDT", "CT")
			.replace("AM","am")
			.replace("PM","pm") + 
			"/" + 
			centralTimeEntryListDeadlineFormatter.print(
					entryListDeadlineDate.toDateTime(
						DateTimeZone.forID("America/Chicago")))
					.replace("EDT","ET")
					.replace("CDT", "CT")
					.replace("AM","am")
					.replace("PM","pm")+
			newlineCharacter +
			newlineCharacter;
		
	}
	org.joda.time.format.DateTimeFormatter stageDateFormatter = 
			new DateTimeFormatterBuilder()
			.appendDayOfWeekText()
			.appendLiteral(", ")
			.appendMonthOfYearShortText()
			.appendLiteral(". ")
			.appendDayOfMonth(1)
			.appendLiteral(", ")
			.appendYear(4,4)
			.toFormatter();

	stageMeetDate = stageDateFormatter.print(stage.getStageMeetDate()).replace("EDT","ET").replace("CDT", "CT").replace("AM","am").replace("PM","pm");
	admissionFee = "$" + new DecimalFormat("0").format(stage.getAdmissionFee()) + " per person";
	advancementRule = stage.getAdvancementRules();
	if (stage.isRacesConductedAtHost()) {
		note = "Races conducted at host school";
		if (stage.isNoteOnChangingHostLocation()){
			note = note + " unless indicated otherwise.";
		}
	}
	builder.append(entryListDeadline);
	builder.append("<b>Date: </b>");
	builder.append(stageMeetDate + newlineCharacter);
	builder.append("<b>Admission: </b>");
	builder.append(admissionFee + newlineCharacter);
	if (advancementRule != null && advancementRule.length() > 0) {
		advancementRule = WordUtils.wrap(advancementRule, 120, "<br/>", false);
		builder.append("<b>Advancement: </b>");
		builder.append(advancementRule + newlineCharacter);
	}
	if (advancementRule != null & note.length() > 0) {
		builder.append("<b>Note: </b>");
	       builder.append(note + newlineCharacter + newlineCharacter);	
	}
       return builder.toString();
    }

    public String buildMeetTime(Meet meet){
	org.joda.time.format.DateTimeFormatterBuilder meetPrimaryDateFormatterBuilder = 
			new DateTimeFormatterBuilder()
			.appendClockhourOfHalfday(1)
			.appendLiteral(":")
			.appendMinuteOfHour(1)
			.appendLiteral(" ")
			.appendHalfdayOfDayText()
			.appendLiteral(" ")
			.appendTimeZoneShortName(mapEtCtToTimeZone);
	org.joda.time.format.DateTimeFormatterBuilder meetAlternateDateFormatterBuilder =
		new DateTimeFormatterBuilder()
		.appendClockhourOfHalfday(1);
	if (meet.getAlternateMeetingTime().getMinuteOfHour() > 0) 
			meetAlternateDateFormatterBuilder
				.appendLiteral(":")
				.appendMinuteOfHour(1);
	if (meet.getAlternateMeetingTime().getHourOfDay() > 11) 
			meetAlternateDateFormatterBuilder
				.appendLiteral(" ")
				.appendHalfdayOfDayText();
	//System.out.println("alternate meet time " + meet.getAlternateMeetingTime());
	String meetAlternateTime = meetAlternateDateFormatterBuilder.toFormatter().print(meet.getAlternateMeetingTime()).replace("EDT","ET").replace("CDT", "CT").replace("AM","am").replace("PM","pm");
	String meetPrimaryTime = meetPrimaryDateFormatterBuilder.toFormatter().print(meet.getPrimaryMeetingTime()).replace("EDT","ET").replace("CDT", "CT").replace("AM","am").replace("PM","pm");
	return meetAlternateTime + "/" + meetPrimaryTime;
    }
}
