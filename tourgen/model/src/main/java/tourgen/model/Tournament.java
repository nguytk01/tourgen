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

  /**
   * Construct a tournament from a tournament name, and gender of the partipants of that tournament.
   * @param name tournament's name
   * @param genderArg genderType of type TournamentParticipants
   */
  public Tournament(String name, TournamentParticipants genderArg) {
    tournamentName = name;
    stageList = new ArrayList<Stage>();
    gender = genderArg;
  }

  public void removeStage(Stage stage) {
    stageList.remove(stage);
  }

  public void addStage(Stage stage) {
    stageList.add(stage);
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
}