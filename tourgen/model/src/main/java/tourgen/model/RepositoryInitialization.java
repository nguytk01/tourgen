package tourgen.model;
import java.io.InputStream;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class RepositoryInitialization{
	public void init(Repository repo, SchoolManager manager){
		String sectionalStageHeader = "Entry List Deadline: Monday, Oct. 2, 2017, 4 pm ET / 3 pm CT.\n" + 
				"\n" + 
				"Date: Saturday, Oct. 7, 2017.\n" + 
				"Admission: $5 per person.\n\n" + 
				"Advancement: The top 10 individuals from non‐advancing teams and the first 5 qualifying teams from each sectional shall advance to designated regionals.\n\n" + 
				"Note: Races conducted at host school unless indicated otherwise.";
		
		String regionalStageHeader = "Date: Saturday, Oct. 14, 2017.\n\n" + 
				"Admission: $5 per person.\n\n" + 
				"Advancement: The top 10 individuals from non‐advancing teams and the first 5 qualifying teams from each regional shall advance to designated semi‐states.\n\n" + 
				"Note: Races conducted at host school unless indicated otherwise.";
		
		String semiStateStageHeader = "Date: Saturday, Oct. 21, 2017.\n\n" + 
				"Admission: $5 per person.\n\n" + 
				"Advancement: The top 10 individuals from non-advancing teams and the first 6 qualifying teams from each semi-state shall advance to the state finals.\n\n" + 
				"Note: Races conducted at host school unless indicated otherwise.";
		
		String stateFinalStageHeader = "Date: Saturday, Oct. 28, 2017.\n\n"
				+ "Site: LaVern Gibson Championship Cross Country Course, Wabash Valley Sports Center, 599 S. Tabortown Road, Terre Haute, IN  47803."
				+ "Course Layout\n\n" + 
				"Times: Boys at 1 pm ET; Girls at 1:45 pm ET.\n\n" + 
				"Admission: $10 per person.";
		
		
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("tournamentData.txt");
		
    	Scanner scanner = new Scanner(stream);
    	//scanner.useDelimiter("\\||\\n?\\n|\\r");
		List<Stage> stageList = new ArrayList<Stage>();
		Stage sectionalStage =  new Stage(StageType.SECTIONAL, sectionalStageHeader, "");
		Stage regionalStage = new Stage(StageType.REGIONAL, regionalStageHeader, "Feeder Sectionals:");
		Stage semiStateStage = new Stage(StageType.SEMISTATE, semiStateStageHeader,"Feeder Regionals:");
		Stage stateFinalStage = new Stage(StageType.STATEFINAL, stateFinalStageHeader,"Feeder Semi-states:");
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
				meet = buildMeet(sectionalStage,manager, date, time, location, host, participants);
				sectionalStage.addMeetToStage(meet);
			}
			if (line.equals("Regional")){
				date = scanner.nextLine();
				time = scanner.nextLine();
				location = scanner.nextLine();
				host = scanner.nextLine();
				participants = scanner.nextLine();		
				meet = buildMeet(regionalStage,manager,date, time, location, host, participants);
				regionalStage.addMeetToStage(meet);
			}
			if (line.equals("Semi-State")){
				date = scanner.nextLine();
				time = scanner.nextLine();
				location = scanner.nextLine();
				host = scanner.nextLine();
				participants = scanner.nextLine();	
				meet = buildMeet(semiStateStage,manager,date, time, location, host, participants);
				semiStateStage.addMeetToStage(meet);
			}
			if (line.equals("State-Final")){
				date = scanner.nextLine();
				time = scanner.nextLine();
				location = scanner.nextLine();
				host = scanner.nextLine();
				participants = scanner.nextLine();	
				meet = buildMeet(stateFinalStage,manager,date, time, location, host, participants);
				stateFinalStage.addMeetToStage(meet);
			}
			//sScanner scanner = new Scanner();
		}
		Tournament girls2017Tournament = new Tournament("2017-2018 Girls Cross Country Tournament", TournamentParticipants.GIRLS);
		girls2017Tournament.addStage(sectionalStage);
		girls2017Tournament.addStage(regionalStage);
		girls2017Tournament.addStage(semiStateStage);
		girls2017Tournament.addStage(stateFinalStage);
		repo.addTournament(girls2017Tournament);
	}
	
	public static Meet buildMeet(Stage parentStage, SchoolManager manager, String date, String time, String location, String host, String participants){
		SimpleDateFormat formatter = new SimpleDateFormat("EEEEE, MMM dd,yyyy");
		Date meetingDate = null;
		try {
			meetingDate = formatter.parse(date.replace(".", ""));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Meet meet = new Meet(parentStage, meetingDate);
		
		if (host.equals("Null")) meet.setHostSchool(null); 
		else meet.setHostSchool(manager.getSchoolFromDisplayName(host));
		meet.setMeetingTime(time);
		meet.setLocation(new Location(location));
		String[] teams = participants.split(",");
		School schoolA = null;
		for (String team: teams) {
			 schoolA = manager.getSchoolFromDisplayName(team);
			if (schoolA != null) meet.addSchooltoMeet(schoolA);
		}
		return meet;
	}
	
}	