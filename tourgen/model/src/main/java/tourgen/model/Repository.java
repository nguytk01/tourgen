package tourgen.model;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository implements Serializable{

  /**
   * serial id for the serialization.
   */
  
  private static final long serialVersionUID = 1L;

  private static Repository instance = null;

  private List<Tournament> girlsTourList;
  private List<Tournament> boysTourList;
  private Tournament unsavedTournament;
  private java.beans.PropertyChangeSupport propertyChangeSupport; 
  
  public static final String TOURNAMENT_MODIFIED = "Tournament modified";
  public static final String TOURNAMENT_OVERWRITTEN = "Tournament saved";
  public static final String TOURNAMENT_SAVEAS = "Tournament saved as a new tournament.";
  public static final String TOURNAMENT_REMOVED = "Tournament removed.";

  private Repository() {
    girlsTourList = new ArrayList<Tournament>();
    boysTourList = new ArrayList<Tournament>();
    propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
  }

  /**
   * This method initializes the singleton repository if it
   * hasn't already been initialized otherwise it returns
   * the instance of the singleton repository.
   * @return a Repository of Tournaments
   */
  
  public static Repository getInstance1() {
    if (instance == null) {
      synchronized (Repository.class) {
        if (instance == null) {
          instance = new Repository();
        }
      }
    }
    return instance;
  }

  public Object readResolve() throws ObjectStreamException {
    return getInstance1();
  }

  public List<Tournament> getBoyList() {
    return Collections.unmodifiableList(boysTourList);
  }

  public List<Tournament> getGirlList() {
    return Collections.unmodifiableList(girlsTourList);
  }

  void setBoyList(List<Object> list) {
    for (int i = 0; i < list.size(); i++) {
      boysTourList.add((Tournament)(list.get(i)));
    }
  }

  void setGirlList(List<Object> list) {
    //girlsTourList.addAll(list);
    for (int i = 0; i < list.size(); i++) {
      System.out.println((Tournament)(list.get(i)));
      girlsTourList.add((Tournament)(list.get(i)));
    }
  }
  
  /**
   * This method checks the tournament for its gender
   * and add it to the appropriate list in the repository.
   * @param tour is the 
   */
  public void addTournament(Tournament tour) {
    if (tour.getTourParticipantsType() == TournamentParticipants.BOYS) {
      if (!boysTourList.contains(tour)) {
        boysTourList.add(tour);
      } else { 
        boysTourList.add(org.apache.commons.lang3.SerializationUtils.clone(tour));
      }
    } else {
      if (!girlsTourList.contains(tour)) {
        girlsTourList.add(tour);
      } else {
        girlsTourList.add(org.apache.commons.lang3.SerializationUtils.clone(tour));
      }
    }
    System.out.println("girls List size " + girlsTourList.size());

    //setChanged();
    //notifyObservers();
  }
  
  /**
   * This method checks the tournament for its gender
   * and add it to the appropriate list in the repository.
   * @param tour is the 
   */
  private void storeBackupTournament(SchoolManager schoolManager, Tournament tour) {
    if (tour.getTourParticipantsType() == TournamentParticipants.BOYS) {
      if (!boysTourList.contains(tour)) {
        boysTourList.add(tour);
      } else { 
        boysTourList.add(org.apache.commons.lang3.SerializationUtils.clone(tour));
      }
    } else {
      if (!girlsTourList.contains(tour)) {
        girlsTourList.add(tour);
      } else {
        Tournament backupCloneOfTheTournament = org.apache.commons.lang3.SerializationUtils.clone(tour);
        unsavedTournament = backupCloneOfTheTournament;
      }
    }
  }
  
  public void changeHostOfMeet(Object ticket, 
      SchoolManager schoolManager,
      Tournament tournament, 
      Meet meet, 
      School school) {
    if (unsavedTournament == null) {
      storeBackupTournament(schoolManager, tournament);
      tournament.setModified(true);
      propertyChangeSupport.firePropertyChange(TOURNAMENT_MODIFIED, false, true);
    }
    meet.setLocation(school.getSchoolLoc());
    meet.setHostSchool(school);
    IOperationResult result = null;
    //TODO: check if the school is the host 
    

    if (tournament.getListOfSchoolsNotWillingToHost().contains(school)) {
      result = new OperationResult(ticket, OperationResultEnum.FAILURE, 
          "The school " + school + " is not willing to test based on the tournament's list of schools willing to serve as hosts.", null);
    } else {
      result = new OperationResult(ticket, OperationResultEnum.SUCCESS,
          "", null);
    }
    //setChanged();
    //notifyObservers(result);
    
  }
  
  public void changeHostEligibility(Object ticket, School school) {

	school.setEligibleToHost( ! school.isEligibleToHost() );
    IOperationResult result = new OperationResult(ticket, OperationResultEnum.SUCCESS,
        "", null);
    //setChanged();
    //notifyObservers(result);
  }
  
  public void changeCompetitionSiteForSchool(Object ticket, 
      Tournament tournament, 
      Meet newMeet, 
      Meet oldMeet, 
      School school,
      SchoolManager schoolManager) {
    assert(girlsTourList.contains(tournament) == true);
    assert(tournament.containsMeet(newMeet) == true);
    assert(tournament.containsMeet(oldMeet) == true);
    
    IOperationResult result = null;
    oldMeet.removeSchoolfromMeet(school);
    newMeet.addSchooltoMeet(school);
    
    if (unsavedTournament == null) {
      storeBackupTournament(schoolManager, tournament);
      propertyChangeSupport.firePropertyChange(TOURNAMENT_MODIFIED, false, true);
      tournament.setModified(true);
    }

    if (! tournament.getStageList().get(0).getMeetList().contains(newMeet)) {
      result = new OperationResult(ticket, OperationResultEnum.FAILURE,
          "Meet " + newMeet + " is not a sectional meet of this tournament.", null);
    } else {
      result = new OperationResult(ticket, OperationResultEnum.SUCCESS,
          "", null);
    }
    
    
    
    //setChanged();
    //notifyObservers(result);
  }
  
  public void updateTournamentListOfSchoolsNotWillingToHost(Tournament tournament, List<School> schoolsNotWillingToHostListArg) {
    tournament.setSchoolsNotWillingToHostList(schoolsNotWillingToHostListArg);
  }
  
  public void saveTournamentAsNewTournament(Tournament tournament, String newName) {
    Tournament newTournament = null;;
    if (newName == null) {

       /*overwrite the old tournament*/       
       if (unsavedTournament != null && tournament.isSavingNeeded()) {
         tournament.setModified(false);
         unsavedTournament = null;
         propertyChangeSupport.firePropertyChange(TOURNAMENT_MODIFIED, true, false);

         /* send this tournament to the view*/
         newTournament = tournament;
       }
       
       /*otherwise, there is no need to rewrite a tournament that is not modified.*/
       return; 
    } else {
      if ( tournament.isSavingNeeded() ) {
        
        /*save the modified tournament as a new tournament with a new name
         keep the old tournament(unsavedTournament) */
        
        girlsTourList.add(girlsTourList.indexOf(tournament), unsavedTournament);
        /* tournament is already a modified tournament. the changes are finalized.*/
        tournament.setModified(false);
        tournament.setName(newName);
        propertyChangeSupport.firePropertyChange(TOURNAMENT_SAVEAS, false, true);

        /* send this tournament to the view*/
        newTournament = tournament; 
        unsavedTournament = null; 
      } else {
        
        /* save an existing unmodified tournament as a new tournament with a new name */
        if (unsavedTournament == null) {
          
          /* send this tournament to the view*/
          newTournament = org.apache.commons.lang3.SerializationUtils.clone(tournament);
          newTournament.setName(newName);
          girlsTourList.add(newTournament);
          propertyChangeSupport.firePropertyChange(TOURNAMENT_SAVEAS, false, true);

        }
      }
    }
    
    //setChanged();
    //notifyObservers(newTournament);
  }
  
  void removeAllPropertyChangeListenersForSerialization() {
    for (Tournament tour: girlsTourList) {
      tour.removeAllPropertyChangeListenersForSerialization();
    }
    for (java.beans.PropertyChangeListener listener : propertyChangeSupport.getPropertyChangeListeners()) {
      propertyChangeSupport.removePropertyChangeListener(listener);
    }
  }
  
  public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  public void removeTournament(Tournament currentTournament) {
	  girlsTourList.remove(girlsTourList.indexOf(currentTournament));
	  propertyChangeSupport.firePropertyChange(TOURNAMENT_REMOVED, null, currentTournament);
  }
  
  public boolean hasUnsavedTournament(){
	  for (Tournament tour : girlsTourList) {
	      System.out.println(tour.toString() + "is unsaved ? " + tour.isSavingNeeded());
		  if (tour.isSavingNeeded()){
		      System.out.println(tour.toString() + "is unsaved");
			  return true;
		  }
	  }
	  return false;
  }
}
