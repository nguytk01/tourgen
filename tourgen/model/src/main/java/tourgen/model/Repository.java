package tourgen.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository {

    private ArrayList<Tournament> girlsTourList;
    private ArrayList<Tournament> boysTourList;

    public Repository() {
    	girlsTourList = new ArrayList<Tournament>();
    	boysTourList = new ArrayList<Tournament>();
    }
    
    List<Tournament> getBoyList()
    {
        return Collections.unmodifiableList(boysTourList);
    }

    public List<Tournament> getGirlList()
    {
    	return Collections.unmodifiableList(girlsTourList);
    }

    void setBoyList(ArrayList<Tournament> list)
    {
        boysTourList = list;
    }

    void setGirlList(ArrayList<Tournament> list)
    {
        girlsTourList = list;
    }
    
    public void addTournament(Tournament tour) {
    	if (tour.getTourParticipantsType() == TournamentParticipants.BOYS) {
    		boysTourList.add(tour);
    	} else girlsTourList.add(tour);
    }
	

}