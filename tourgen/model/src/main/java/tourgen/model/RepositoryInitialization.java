package tourgen.model;
import java.io.InputStream;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.DateTimeZone;
import java.util.HashMap;
import java.util.Map;
public final class RepositoryInitialization{
	private static final Map<String, DateTimeZone> mapEtCtToTimeZone = new HashMap<String, DateTimeZone>();
	static {
			
			mapEtCtToTimeZone.put("CT", DateTimeZone.forID("America/Chicago"));
			mapEtCtToTimeZone.put("ET", DateTimeZone.forID("America/New_York"));
	}

	private static final org.joda.time.format.DateTimeFormatter hourMinuteFormatter = 
			new org.joda.time.format.DateTimeFormatterBuilder()
			.appendClockhourOfHalfday(1)
			.appendLiteral(":")
			.appendMinuteOfHour(0)
			.toFormatter();
	private static final org.joda.time.format.DateTimeFormatter hourMinuteAmPmTimeZoneFormatter = 
			new org.joda.time.format.DateTimeFormatterBuilder()
			.appendClockhourOfHalfday(1)
			.appendLiteral(":")
			.appendMinuteOfHour(1)
			.appendLiteral(" ")
			.appendHalfdayOfDayText()
			.appendLiteral(" ")
			.appendTimeZoneName(mapEtCtToTimeZone)
			.toFormatter();
	private static final org.joda.time.format.DateTimeFormatter hourMinuteAmPmFormatter =
			new org.joda.time.format.DateTimeFormatterBuilder()
			.appendClockhourOfHalfday(1)
			.appendLiteral(":")
			.appendMinuteOfHour(0)
			.appendLiteral(" ")
			.appendHalfdayOfDayText()
			.toFormatter();
	private static final org.joda.time.format.DateTimeFormatter singleHourFormatter = 
			new org.joda.time.format.DateTimeFormatterBuilder()
			.appendClockhourOfHalfday(1).toFormatter();
	private static String centralUSTimeZone = "America/Chicago";
	private static String easternUSTimeZone = "America/New_York";

	public static void init(Repository repo, SchoolManager manager){
		InputStream stream = RepositoryInitialization.class.getClassLoader().getResourceAsStream("tournamentData.txt");
		
    	Scanner scanner = new Scanner(stream);
    	//scanner.useDelimiter("\\||\\n?\\n|\\r");
		List<Stage> stageList = new ArrayList<Stage>();
		
		Stage sectionalStage =  new Stage(StageType.SECTIONAL, "", "");
		sectionalStage.setAdmissionFee(5);
		sectionalStage.setEntryListDeadline(
				new org.joda.time.DateTime(2017, 10, 2, 16, 0, 
						org.joda.time.DateTimeZone.forID("America/Fort_Wayne")));
		sectionalStage.setStageMeetDate(new org.joda.time.DateTime(2017, 10, 7, 0, 0 ));
		sectionalStage.setRacesConductedAtHost(true);
		sectionalStage.setNoteOnChangingHostLocation(true);
		sectionalStage.setAdvancementRule("The top 10 individuals from "
				+ "non-advancing teams and the first 5 qualifying teams from "
				+ "each sectional shall advance to designated regionals.");
		
		Stage regionalStage =  new Stage(StageType.REGIONAL, "", "Feeder sectionals:");

		regionalStage.setAdmissionFee(5);
		regionalStage.setStageMeetDate(new org.joda.time.DateTime(2017, 10, 14, 0, 0 ));
		regionalStage.setRacesConductedAtHost(true);
		regionalStage.setNoteOnChangingHostLocation(true);
		regionalStage.setAdvancementRule("The top 10 individuals from "
				+ "non-advancing teams and the first 5 qualifying teams from "
				+ "each regional shall advance to designated semi-states.");
	
		Stage semiStateStage =  new Stage(StageType.SEMISTATE, "", "Feeder regionals:");
		semiStateStage.setAdmissionFee(5);
		semiStateStage.setStageMeetDate(new org.joda.time.DateTime(2017, 10, 21, 0, 0 ));
		semiStateStage.setRacesConductedAtHost(true);
		semiStateStage.setNoteOnChangingHostLocation(true);
		semiStateStage.setAdvancementRule("The top 10 individuals from "
				+ "non‐advancing teams and the first 5 qualifying teams from "
				+ "each semi-state shall advance to state finals.");

		Stage stateFinalStage =  new Stage(StageType.STATEFINAL, "", "Feeder semi-states:");
		stateFinalStage.setAdmissionFee(10);
		stateFinalStage.setStageMeetDate(new org.joda.time.DateTime(2017, 10, 28, 0, 0 ));
		stateFinalStage.setRacesConductedAtHost(true);
		stateFinalStage.setNoteOnChangingHostLocation(true);

		String date;
		String time;
		String location;
		String host;
		String participants;
		
		Meet meet;
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			
			if (line.equals("Sectional")){
				date = scanner.nextLine();
				time = scanner.nextLine();
				location = scanner.nextLine();
				host = scanner.nextLine();
				participants = scanner.nextLine();
				meet = buildMeet(sectionalStage,manager, date, sectionalStage.getStageMeetDate(), time, location, host, participants);
				sectionalStage.addMeetToStage(meet);
			}
			if (line.equals("Regional")){
				date = scanner.nextLine();
				time = scanner.nextLine();
				location = scanner.nextLine();
				host = scanner.nextLine();
				participants = scanner.nextLine();		
				meet = buildMeet(regionalStage,manager,date, regionalStage.getStageMeetDate(), time, location, host, participants);
				regionalStage.addMeetToStage(meet);
			}
			if (line.equals("Semi-State")){
				date = scanner.nextLine();
				time = scanner.nextLine();
				location = scanner.nextLine();
				host = scanner.nextLine();
				participants = scanner.nextLine();	
				meet = buildMeet(semiStateStage,manager,date, semiStateStage.getStageMeetDate(), time, location, host, participants);
				semiStateStage.addMeetToStage(meet);
			}
			if (line.equals("State Finals")){
				date = scanner.nextLine();
				time = scanner.nextLine();
				location = scanner.nextLine();
				host = scanner.nextLine();
				participants = scanner.nextLine();	
				meet = buildMeet(stateFinalStage,manager,date, stateFinalStage.getStageMeetDate(), time, location, host, participants);
				stateFinalStage.addMeetToStage(meet);
			}
			//sScanner scanner = new Scanner();
		}
		Tournament girls2017Tournament = new Tournament("2017-2018 Girls Cross Country", TournamentParticipants.GIRLS);
		girls2017Tournament.addStage(sectionalStage);
		girls2017Tournament.addStage(regionalStage);
		girls2017Tournament.addStage(semiStateStage);
		girls2017Tournament.addStage(stateFinalStage);
		repo.addTournament(girls2017Tournament);
	}
	
	public static Meet buildMeet(Stage parentStage, SchoolManager manager, String deprecatedDate,org.joda.time.DateTime stageMeetDate, String time, String location, String host, String participants){
		Date meetingDate = null;
		/*try {
			meetingDate = (date.replace(".", ""));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Meet meet = new Meet(parentStage, null);
		org.joda.time.DateTime[] meetTimes = getMeetTime(time, parentStage.getStageMeetDate());
		
		if (host.equals("Null")) meet.setHostSchool(null); 
		else meet.setHostSchool(manager.getSchoolFromDisplayName(host));
		meet.setMeetingTime(meetTimes[0]);
		meet.setAlternateMeetingTime(meetTimes[1]);
		
		//System.out.println(parentStage.getStageTitle());
		//System.out.println("Host school " + host);
		//System.out.println(meet.getHostSchool());
		if (location.equals("Null")) meet.setLocation(meet.getHostSchool().getSchoolLoc());
		else meet.setLocation(new Location(location));
		String[] teams = participants.split(",");
		School schoolA = null;
		for (String team: teams) {
			schoolA = manager.getSchoolFromDisplayName(team);
			if (schoolA != null) meet.addSchooltoMeet(schoolA);
		}
		return meet;
	}
	
	public static org.joda.time.DateTime[] getMeetTime(String time, DateTime stageMeetDate){
		int amOrPm = 0;
		int zerothSecond = 0;
//		System.out.println("time is >" + time + "<");
		String[] timeAndAmPmAndZone = time.trim().split("/");
		String timeZoneRetrieved = timeAndAmPmAndZone[1].trim().split(" ")[2].toLowerCase(); 
		String timeZone = easternUSTimeZone;
		String alternateTimeStr =  timeAndAmPmAndZone[0].trim();
		String primaryTimeStr = timeAndAmPmAndZone[1].trim();
		
		org.joda.time.DateTime primaryMeetTimeTemp;
		org.joda.time.DateTime alternateMeetTimeTemp;
			primaryMeetTimeTemp = hourMinuteAmPmTimeZoneFormatter.parseDateTime(primaryTimeStr);
		try{
			primaryMeetTimeTemp = hourMinuteAmPmTimeZoneFormatter.parseDateTime(primaryTimeStr);
		} catch (IllegalArgumentException e) {
			//e.printStackTrace();
//			primaryMeetTimeTemp = singleHourFormatter.parseDateTime(primaryTimeStr);
		}
		
		try{
			alternateMeetTimeTemp = hourMinuteFormatter.parseDateTime(alternateTimeStr);
		} catch (IllegalArgumentException e) {
			try{
				alternateMeetTimeTemp = singleHourFormatter.parseDateTime(alternateTimeStr);
			} catch (IllegalArgumentException f){
				try {
					alternateMeetTimeTemp = hourMinuteFormatter.parseDateTime(alternateTimeStr);
				} catch (IllegalArgumentException g) {
					alternateMeetTimeTemp = hourMinuteAmPmFormatter.parseDateTime(alternateTimeStr);
				}
			}
		}

//		if (timeZoneRetrieved.equals("ct")) timeZone = centralUSTimeZone;

		org.joda.time.DateTime primaryMeetTime = new org.joda.time.DateTime(
				stageMeetDate.getYear(), 
				stageMeetDate.getMonthOfYear(), 
				stageMeetDate.getDayOfMonth(),
				primaryMeetTimeTemp.getHourOfDay(),
				primaryMeetTimeTemp.getMinuteOfHour(),
				zerothSecond,
				primaryMeetTimeTemp.getZone());
		
		if (primaryMeetTime.getHourOfDay() >=12) amOrPm = 12;
		org.joda.time.DateTime alternateMeetTime = new org.joda.time.DateTime(
				stageMeetDate.getYear(), 
				stageMeetDate.getMonthOfYear(), 
				stageMeetDate.getDayOfMonth(),
				alternateMeetTimeTemp.getHourOfDay() + amOrPm,
				alternateMeetTimeTemp.getMinuteOfHour(),
				zerothSecond,
				primaryMeetTime.getZone());
		
		return new org.joda.time.DateTime[] {primaryMeetTime, alternateMeetTime};
	}

}	
