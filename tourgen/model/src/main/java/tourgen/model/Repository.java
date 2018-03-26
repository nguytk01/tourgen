package tourgen.model;
import java.util.ArrayList;

public class Repository {

    private ArrayList<Tournament> girlsTourList;
    private ArrayList<Tournament> boysTourList;

    public ArrayList<Tournament> getBoyList()
    {
        return boysTourList;
    }

    public ArrayList<Tournament> getGirlList()
    {
        return girlsTourList;
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