package tourgen.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository {

    private ArrayList<Tournament> girlsTourList;
    private ArrayList<Tournament> boysTourList;

    public List<Tournament> getBoyList()
    {
        return boysTourList;
    }

    public List<Tournament> getGirlList()
    {
        return Collections.unmodifiableList(girlsTourList);
    }

    public void setBoyList(ArrayList<Tournament> list)
    {
        boysTourList = list;
    }

    public void setGirlList(ArrayList<Tournament> list)
    {
        girlsTourList = list;
    }

}