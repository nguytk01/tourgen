package tourgen.model;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository extends java.util.Observable implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static Repository instance = null;

  private List<Tournament> girlsTourList;
  private List<Tournament> boysTourList;

  private Repository() {
    girlsTourList = new ArrayList<Tournament>();
    boysTourList = new ArrayList<Tournament>();
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
    };
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
      boysTourList.add(tour);
    } else {
      girlsTourList.add(tour);
    }
    setChanged();
    notifyObservers();
  }
}
