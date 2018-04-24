package tourgen.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Tournament implements Serializable {
  TournamentParticipants gender;
  List<Stage> stageList;
  String tournamentName;
  List<School> schoolsNotWillingToHostList;
  org.joda.time.Instant schoolsNotWillingToHostSnapshotDate;
  private boolean savingNeeded = false;
  /**
   * Construct a tournament from a tournament name, and gender of the partipants of that tournament.
   * @param name tournament's name
   * @param genderArg genderType of type TournamentParticipants
   */
  public Tournament(String name, TournamentParticipants genderArg) {
    tournamentName = name;
    stageList = new ArrayList<Stage>();
    gender = genderArg;
    schoolsNotWillingToHostList = new ArrayList<School>();
  }

  public void removeStage(Stage stage) {
    stageList.remove(stage);
    stage.setTournament(null);
  }

  public void addStage(Stage stage) {
    stageList.add(stage);
    stage.setTournament(this);
  }

  public List<Stage> getStageList() {
    return Collections.unmodifiableList(stageList);
  }

  public TournamentParticipants getTourParticipantsType() {
    return gender;
  }

  public String toString() {
    return tournamentName;
  }
  
  public List<Meet> getSectionalMeetSuggestions(Object school){
    List<Meet> sectionalMeetsSuggestion = new ArrayList<Meet>();
    Stage sectional = stageList.get(0);
    for (Meet meet: sectional.getMeetList()) {
      School host = meet.getHostSchool();
      if ( host != school && ! meet.getParticipatingSchool().contains(school) ) {
        sectionalMeetsSuggestion.add(meet);
      }
    }
    return Collections.unmodifiableList(sectionalMeetsSuggestion);
  }
  
  void setName(String nameArg) {
    tournamentName = nameArg;
  }
  void setSchoolsNotWillingToHostList(List<School> schoolsNotWillingToHostListArg) {
    this.schoolsNotWillingToHostList = schoolsNotWillingToHostListArg;
    schoolsNotWillingToHostSnapshotDate = new org.joda.time.Instant();
  }

  public List<School> getListOfSchoolsNotWillingToHost() {
    return this.schoolsNotWillingToHostList;
  }
  
  public boolean isSavingNeeded() {
    return savingNeeded;
  }
  
  public String getName() {
    return tournamentName;
  }

  public void setModified(boolean b) {
    savingNeeded = b;
  }
  
  boolean containsMeet(Meet meet) {
    for (Stage stage: stageList) {
      if (stage.containsMeet(meet)) {
        return true;
      }
    }
    return false;
    
  }

  void removeAllPropertyChangeListenersForSerialization() {
    for (Stage stage: stageList) {
      stage.removeAllPropertyChangeListenersForSerialization();
    }
  }
  
  Stage getStageOfStageType(StageType inputStageType) {
	for (int i = 0; i < stageList.size(); i++) {
		if (stageList.get(i).getStageType() == inputStageType) {
			return stageList.get(i);
		}
	}
	return null;
  }
  
  void initiateRecursiveUpdate() {
	  for (Stage stage: stageList) {
		  if (stage.getStageType().equals(StageType.STATEFINAL)) {
			  stage.initiateRecursiveUpdate();
		  }
	  }
  }
}