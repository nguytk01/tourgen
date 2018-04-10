package tourgen.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public final class RepositoryInitialization {
  private static final Map<String, DateTimeZone> mapEtCtToTimeZone = 
      new HashMap<String, DateTimeZone>();
  
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
      .appendClockhourOfHalfday(1)
      .toFormatter();
  
  private static final org.joda.time.format.DateTimeFormatter singleHourAmPmTimeZoneFormatter =
      new org.joda.time.format.DateTimeFormatterBuilder()
      .appendClockhourOfHalfday(1)
      .appendLiteral(" ")
      .appendHalfdayOfDayText()
      .appendLiteral(" ")
      .appendTimeZoneName(mapEtCtToTimeZone)
      .toFormatter();
  private static String centralUSTimeZone = "America/Chicago";
  private static String easternUSTimeZone = "America/New_York";
  
  /**
   * This will import data from text files into repository. 
   * @param repo an empty instance of repository
   * @param manager a school manager object fully-loaded with data.
   */
  public static void init(Repository repo, SchoolManager manager) {
    InputStream stream = RepositoryInitialization.class
        .getClassLoader().getResourceAsStream("tournamentData.txt");

    
    // scanner.useDelimiter("\\||\\n?\\n|\\r");
    List<Stage> stageList = new ArrayList<Stage>();

    Stage sectionalStage = new Stage(StageType.SECTIONAL, "", "");
    sectionalStage.setAdmissionFee(5);
    sectionalStage.setEntryListDeadline(
        new org.joda.time.DateTime(2017, 10, 2, 16, 0, 
            org.joda.time.DateTimeZone.forID("America/Fort_Wayne")));
    sectionalStage.setStageMeetDate(new org.joda.time.DateTime(2017, 10, 7, 0, 0));
    sectionalStage.setRacesConductedAtHost(true);
    sectionalStage.setNoteOnChangingHostLocation(true);
    sectionalStage.setAdvancementRule(
        "The top 10 individuals from " 
        + "non-advancing teams and the first 5 qualifying teams from "
        + "each sectional shall advance to designated regionals.");

    Stage regionalStage = new Stage(StageType.REGIONAL, "", "Feeder sectionals:");

    regionalStage.setAdmissionFee(5);
    regionalStage.setStageMeetDate(new org.joda.time.DateTime(2017, 10, 14, 0, 0));
    regionalStage.setRacesConductedAtHost(true);
    regionalStage.setNoteOnChangingHostLocation(true);
    regionalStage.setAdvancementRule(
        "The top 10 individuals from " 
        + "non-advancing teams and the first 5 qualifying teams from "
        + "each regional shall advance to designated semi-states.");

    Stage semiStateStage = new Stage(StageType.SEMISTATE, "", "Feeder regionals:");
    semiStateStage.setAdmissionFee(5);
    semiStateStage.setStageMeetDate(new org.joda.time.DateTime(2017, 10, 21, 0, 0));
    semiStateStage.setRacesConductedAtHost(true);
    semiStateStage.setNoteOnChangingHostLocation(true);
    semiStateStage.setAdvancementRule(
        "The top 10 individuals from " 
        + "non‐advancing teams and the first 5 qualifying teams from "
        + "each semi-state shall advance to state finals.");

    Stage stateFinalStage = new Stage(StageType.STATEFINAL, "", "Feeder semi-states:");
    stateFinalStage.setAdmissionFee(10);
    stateFinalStage.setStageMeetDate(new org.joda.time.DateTime(2017, 10, 28, 0, 0));
    stateFinalStage.setRacesConductedAtHost(true);
    stateFinalStage.setNoteOnChangingHostLocation(true);

    String date;
    String time;
    String location;
    String host;
    String participants;

    Meet meet;
    Scanner scanner = new Scanner(stream);
    while (scanner.hasNext()) {
      String line = scanner.nextLine();

      if (line.equals("Sectional")) {
        date = scanner.nextLine();
        time = scanner.nextLine();
        location = scanner.nextLine();
        host = scanner.nextLine();
        participants = scanner.nextLine();
        meet = buildMeet(
            sectionalStage, manager, date, 
            sectionalStage.getStageMeetDate(), 
            time, location, host,
            participants);
        sectionalStage.addMeetToStage(meet);
      }
      if (line.equals("Regional")) {
        date = scanner.nextLine();
        time = scanner.nextLine();
        location = scanner.nextLine();
        host = scanner.nextLine();
        participants = scanner.nextLine();
        meet = buildMeet(
            regionalStage, manager, date, 
            regionalStage.getStageMeetDate(), 
            time, location, host,
            participants);
        regionalStage.addMeetToStage(meet);
      }
      if (line.equals("Semi-State")) {
        date = scanner.nextLine();
        time = scanner.nextLine();
        location = scanner.nextLine();
        host = scanner.nextLine();
        participants = scanner.nextLine();
        meet = buildMeet(
            semiStateStage, manager, date, 
            semiStateStage.getStageMeetDate(), 
            time, location, host,
            participants);
        semiStateStage.addMeetToStage(meet);
      }
      if (line.equals("State-Final")) {
        date = scanner.nextLine();
        time = scanner.nextLine();
        location = scanner.nextLine();
        host = scanner.nextLine();
        participants = scanner.nextLine();
        meet = buildMeet(
            stateFinalStage, manager, date, 
            stateFinalStage.getStageMeetDate(), 
            time, location, host,
            participants);
        stateFinalStage.addMeetToStage(meet);
      }
    }
    Tournament girls2017Tournament = new Tournament("2017-2018 Girls Cross Country", 
        TournamentParticipants.GIRLS);
    girls2017Tournament.addStage(sectionalStage);
    girls2017Tournament.addStage(regionalStage);
    girls2017Tournament.addStage(semiStateStage);
    girls2017Tournament.addStage(stateFinalStage);
    repo.addTournament(girls2017Tournament);
  }

  /**
   * Build a meet based on the input parameters.
   * @param parentStage a Stage object representing the parent stage of the meet
   * @param manager the school manager object
   * @param deprecatedDate a string for the date (deprecated)
   * @param stageMeetDate  an actual date for the stage meet
   * @param time the string containing the time for the meet
   * @param location the string containing the location of the meet
   * @param host the string containing the name of the host location
   * @param participants a comma-separated string containing all participating schools in the list.
   * @return a Meet built from the the parameters.
   */
  public static Meet buildMeet(Stage parentStage, SchoolManager manager, String deprecatedDate,
      org.joda.time.DateTime stageMeetDate, String time, String location, 
      String host, String participants) {
    Date meetingDate = null;
    /*
     * try { meetingDate = (date.replace(".", "")); } catch (ParseException e) { //
     * TODO Auto-generated catch block e.printStackTrace(); }
     */

    Meet meet = new Meet(parentStage, null);
    org.joda.time.DateTime[] meetTimes = getMeetTime(time, parentStage.getStageMeetDate());

    if (host.equals("Null")) {
      meet.setHostSchool(null);
    } else {
      meet.setHostSchool(manager.getSchoolFromDisplayName(host));
    }
    meet.setMeetingTime(meetTimes[0]);
    meet.setAlternateMeetingTime(meetTimes[1]);

    if (parentStage.getStageType() == StageType.STATEFINAL) {
      parentStage.setBoysMeetingTime(meetTimes[1]);
      parentStage.setGirlsMeetingTime(meetTimes[0]);
    }
    // System.out.println(parentStage.getStageTitle());
    // System.out.println("Host school " + host);
    // System.out.println(meet.getHostSchool());
    processLocation(meet, location);
    String[] teams = participants.split(",");
    School schoolA = null;
    for (String team : teams) {
      schoolA = manager.getSchoolFromDisplayName(team);
      if (schoolA != null) {
        meet.addSchooltoMeet(schoolA);
      }
    }
    return meet;
  }

  /**
   * Return an array of 2 DateTime objects. The first one is the primary meeting time.
   * The second one is the alternate meeting time. 
   * The meeting time of a meet is as follows when printed out:
   * alternate meeting time pm/am /_primary meeting time_ _pm/am_ _timezone:ET, CT, etc._
   * @param time the string containing the time of the meet
   * @param stageMeetDate the joda library's DateTime object of the stage containing this meet.
   * @return an array of 2 joda library's DateTime objects.
   */
  public static org.joda.time.DateTime[] getMeetTime(String time, DateTime stageMeetDate) {
    int amOrPm = 0;
    final int zerothSecond = 0;
    // System.out.println("time is >" + time + "<");
    String[] timeAndAmPmAndZone = time.trim().split("/");
    String timeZoneRetrieved = timeAndAmPmAndZone[1].trim().split(" ")[2].toLowerCase();
    String timeZone = easternUSTimeZone;
    String alternateTimeStr = timeAndAmPmAndZone[0].trim();
    String primaryTimeStr = timeAndAmPmAndZone[1].trim();

    org.joda.time.DateTime primaryMeetTimeTemp;
    org.joda.time.DateTime alternateMeetTimeTemp;
    primaryMeetTimeTemp = hourMinuteAmPmTimeZoneFormatter.parseDateTime(primaryTimeStr);
    try {
      primaryMeetTimeTemp = hourMinuteAmPmTimeZoneFormatter.parseDateTime(primaryTimeStr);
    } catch (IllegalArgumentException e) {
      // e.printStackTrace();
      // primaryMeetTimeTemp = singleHourFormatter.parseDateTime(primaryTimeStr);
    }

    try {
      alternateMeetTimeTemp = hourMinuteFormatter.parseDateTime(alternateTimeStr);
    } catch (IllegalArgumentException e) {
      try {
        alternateMeetTimeTemp = singleHourFormatter.parseDateTime(alternateTimeStr);
      } catch (IllegalArgumentException f) {
        try {
          alternateMeetTimeTemp = hourMinuteFormatter.parseDateTime(alternateTimeStr);
        } catch (IllegalArgumentException g) {
          alternateMeetTimeTemp = hourMinuteAmPmFormatter.parseDateTime(alternateTimeStr);
        }
      }
    }

    // if (timeZoneRetrieved.equals("ct")) timeZone = centralUSTimeZone;

    org.joda.time.DateTime primaryMeetTime = new org.joda.time.DateTime(stageMeetDate.getYear(),
        stageMeetDate.getMonthOfYear(), 
        stageMeetDate.getDayOfMonth(), 
        primaryMeetTimeTemp.getHourOfDay(),
        primaryMeetTimeTemp.getMinuteOfHour(), 
        zerothSecond, 
        primaryMeetTimeTemp.getZone());

    if (primaryMeetTime.getHourOfDay() >= 12 && alternateMeetTimeTemp.getHourOfDay() < 12) {
      amOrPm = 12;
    }
    org.joda.time.DateTime alternateMeetTime = new org.joda.time.DateTime(
        stageMeetDate.getYear(),
        stageMeetDate.getMonthOfYear(), 
        stageMeetDate.getDayOfMonth(), 
        alternateMeetTimeTemp.getHourOfDay() + amOrPm,
        alternateMeetTimeTemp.getMinuteOfHour(), zerothSecond, primaryMeetTime.getZone());

    return new org.joda.time.DateTime[] { primaryMeetTime, alternateMeetTime };
  }

  /**
   * Assign the correct location to the meet based on the input location string.
   * @param meet the meet object
   * @param location location object
   */
  
  public static void processLocation(Meet meet, String location) {
    // System.out.println("Location : " + location);
    if (location.equals("Null")) {
      meet.setLocation(meet.getHostSchool().getSchoolLoc());
    } else {
      String[] arr = location.split("\\|");
      // System.out.println(java.util.Arrays.deepToString(arr));
      String locationDisplayName = arr[0].trim();
      String latitudeStr = arr[1].trim();
      String longitudeStr = arr[2].trim();
      String locationFullName = "";
      String locationStreetAddress = "";
      String locationCityName = "";
      String locationZipCode = "";
      Location a = new Location(locationDisplayName);
      if (arr.length > 3) {
        locationFullName = arr[3].trim();
        locationStreetAddress = arr[4].trim();
        locationCityName = arr[5].trim();
        locationZipCode = arr[6].trim();
        a.setFullName(locationFullName);
        a.setStreetAddress(locationStreetAddress);
        a.setCityName(locationCityName);
        a.setZipCode(Integer.parseInt(locationZipCode));
      }
      a.setLatitude(Double.parseDouble(latitudeStr));
      a.setLongitude(Double.parseDouble(longitudeStr));
      meet.setLocation(a);
    }
  }
}
