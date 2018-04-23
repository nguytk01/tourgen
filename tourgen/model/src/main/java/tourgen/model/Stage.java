package tourgen.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stage implements Serializable {

  private ArrayList<Meet> stageMeets;
  private StageType stageType;
  private String stageHeader;
  private String participantsHeader;

  /**
   * Entry List Deadline: Monday, Oct. 2, 2017, 4 pm ET / 3 pm CT. Date: Saturday,
   * Oct. 7, 2017. Admission: $5 per person. Advancement: The top 10 individuals
   * from non-advancing teams and the first 5 qualifying teams from each
   * sectional shall advance to designated regionals. Note: Races conducted at
   * host school unless indicated otherwise.
   */
  private org.joda.time.DateTime entryListDeadline;
  private org.joda.time.DateTime stageMeetDate;
  private double admissionFee;
  private String advancementRule;
  private boolean racesConductedAtHost;
  private boolean noteOnChangingHostLocation;

  /**
   * These are for Stage Final.
   */
  private Location stageMeetLocation;

  private org.joda.time.DateTime girlsMeetingTime;
  private org.joda.time.DateTime boysMeetingTime;

  /**
   * Construct a stage from an enum, a header string (deprecated), feeder header (deprecated).
   * @param stageType an enum of type StageType
   * @param stageHeaderArg deprecated
   * @param participantsHeaderArg deprecated
   */
  public Stage(StageType stageType, String stageHeaderArg, String participantsHeaderArg) {
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

  /**
   * return the stage type string.
   * @return a string containing the stage's type.
   */
  public String getStageTitle() {
    switch (stageType) {
      case SECTIONAL:
        return "Sectionals";
      case REGIONAL:
        return "Regionals";
      case SEMISTATE:
        return "Semi-states";
      case STATEFINAL:
        return "State finals";
      default:
        return "Stage";
    }
  }

  public List<Meet> getMeetList() {
    return Collections.unmodifiableList(stageMeets);
  }

  void setAdmissionFee(double fee) {
    admissionFee = fee;
  }

  void setStageMeetDate(org.joda.time.DateTime date) {
    stageMeetDate = date;
  }

  void setEntryListDeadline(org.joda.time.DateTime date) {
    entryListDeadline = date;
  }

  void setAdvancementRule(String advancementRuleArg) {
    advancementRule = advancementRuleArg;
  }

  void setBoysMeetingTime(org.joda.time.DateTime boysMeetingTimeArg) {
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

  public org.joda.time.DateTime getStageMeetDate() {
    return stageMeetDate;
  }

  public org.joda.time.DateTime getEntryListDeadline() {
    return entryListDeadline;
  }

  public String getAdvancementRules() {
    return advancementRule;
  }

  public double getAdmissionFee() {
    return admissionFee;
  }

  public boolean isRacesConductedAtHost() {
    return racesConductedAtHost;
  }

  public boolean isNoteOnChangingHostLocation() {
    return noteOnChangingHostLocation;
  }

  public org.joda.time.DateTime getGirlsMeetingTime() {
    return girlsMeetingTime;
  }

  public org.joda.time.DateTime getBoysMeetingTime() {
    return boysMeetingTime;
  }

  @Deprecated
  public List<Meet> getListMeet() {
    return Collections.unmodifiableList(stageMeets);
  }

  public StageType getStageType() {
    return stageType;
  }

  /**
   * Return an array of 2 doubles: maximum distance and average distance of a stage.
   * @return
   */
  public double[] getMaxAndAvgDistance() {
    double maxDistance = 0;
    double sumDistance = 0;
    for (int i = 0; i < stageMeets.size(); i++) {
      double[] meetDistance = stageMeets.get(i).getMaxAndAvgDistance();
      if (meetDistance[0] > maxDistance) {
        maxDistance = meetDistance[0];
      }
      sumDistance += meetDistance[1];
    }
    return new double[] {maxDistance, sumDistance / stageMeets.size()};
  }

  public boolean containsMeet(Meet meet) {
    return stageMeets.contains(meet);
  }

  void removeAllPropertyChangeListenersForSerialization() {
    for (Meet meet: stageMeets) {
      meet.removeAllPropertyChangeListenersForSerialization();
    }
    
  }
}
