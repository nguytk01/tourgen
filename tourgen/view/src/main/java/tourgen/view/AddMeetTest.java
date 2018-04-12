package tourgen.view;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import tourgen.model.Location;
import tourgen.model.Meet;
import tourgen.model.School;

public class AddMeetTest {
  public static void main(String[] args) {
    // addTest();
    test2();
  }

  /**
   * This test will test the map driver's ability to add a meet.
   */
  public static void addTest() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        School school = new School("ASd", " asd", "3123123", "asdd", 0, 0, false, false);
        
        Location loc = new Location("asdasd", "asdasd", 0);
        loc.setLatitude(41.5);
        loc.setLongitude(-85);
        school.setSchoolLoc(loc);
        Meet meet = new Meet();
        meet.addSchooltoMeet(school);

        MapDriver map = new MapDriver(null);
        map.addMeet(meet);
      }
    });
  }

  /**
   * this tests the map driver's ability to show a list of 2 meets.
   */
  public static void test2() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        School school = new School("ASd", " asd", "3123123", "asdd", 0, 0, false, false);
        Location loc = new Location("asdasd", "asdasd", 0);
        loc.setLatitude(42.5);
        loc.setLongitude(-79);
        school.setSchoolLoc(loc);
        Meet meet = new Meet();
        meet.addSchooltoMeet(school);

        School school2 = new School("ASd", " asd", "3123123", "asdd", 0, 0, false, false);
        
        Location loc2 = new Location("asdasd", "asdasd", 0);
        loc2.setLatitude(44.5);
        loc2.setLongitude(-89);
        school2.setSchoolLoc(loc2);
        
        Meet meet2 = new Meet();
        meet2.addSchooltoMeet(school2);

        ArrayList<Meet> meetList = new ArrayList<Meet>();

        meetList.add(meet);
        meetList.add(meet2);

        MapDriver map = new MapDriver(null);

        map.showMeetList(meetList);

        map.addMeet(meet2);
      }
    });
  }

}
