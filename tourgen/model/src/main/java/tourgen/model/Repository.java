package tourgen.model;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository extends java.util.Observable implements Serializable {

  private static Repository instance;

  private List<Tournament> girlsTourList;
  private List<Tournament> boysTourList;

  private Repository() {
    girlsTourList = new ArrayList<Tournament>();
    boysTourList = new ArrayList<Tournament>();
  }

  /**
   * This method returns the singleton instance of the repository.
   * It creates the instance if it does not exist.
   * @return a Repository of Tournaments
   */
  
  public static Repository getInstance() {
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
    return getInstance();
  }

  public List<Tournament> getBoyList() {
    return Collections.unmodifiableList(boysTourList);
  }

  public List<Tournament> getGirlList() {
    return Collections.unmodifiableList(girlsTourList);
  }

  void setBoyList(List<Tournament> list) {
    boysTourList = list;
  }

  void setGirlList(List<Tournament> list) {
    girlsTourList = list;
  }

  public void addTournament(Tournament tour) {
    if (tour.getTourParticipantsType() == TournamentParticipants.BOYS) {
      boysTourList.add(tour);
    } else
      girlsTourList.add(tour);
    setChanged();
    notifyObservers();
  }

  /*
   * private static class RepositoryHelper{ private static final Repository
   * instance = new Repository(); }
   * 
   * public static Repository getInstance(){ return Repository.instance; }
   */
}