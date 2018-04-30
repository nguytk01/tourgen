package tourgen.view;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.SchoolManager;
import tourgen.model.Stage;
import tourgen.model.StageType;
import tourgen.model.Tournament;
import tourgen.view.ReportContentRenderer;

public class ReportContentRendererTest {
  private Repository repo;
  private SchoolManager manager;

  /**
   * Construct ReportContentRendererTest object.
   */
  
  public ReportContentRendererTest() {
    repo = Repository.getInstance1();
    manager = new SchoolManager();
    manager.initSchools();
    RepositoryInitialization.init("tournamentDataForViewTests.txt", repo, manager);
  }

  @Test
  public void reportContentRendererSectionalTest() {
    Meet meet = repo.getGirlList().get(0).getStageList().get(0).getMeetList().get(0);
    Stage stage = repo.getGirlList().get(0).getStageList().get(0);
    ReportContentRenderer renderer = new ReportContentRenderer();
    // System.out.println("meet time : "+ renderer.buildMeetTime(meet));
    // System.out.println("stage header : "+ renderer.buildStageHeader(stage));
    String report = renderer.getMeetReport(1, meet);
    //System.out.println("meet report : " + report);
    assertEquals(
    		"<b>1. Hammond Gavit (2) | 10/10:45 am CT (Riverside Park) Max: 5.1 Miles Avg: 3.0 Miles<br/></b>"
    		+ "<b></b> <span style=\"background-color: yellow\">Hammond Gavit</span>"
    										+ ", Calumet<br/><br/>", report);
  }

  @Test
  public void reportContentRendererGetMeetReportTest() {
    Meet meet = repo.getGirlList().get(0).getStageList().get(0).getMeetList().get(2);
    Stage stage = repo.getGirlList().get(0).getStageList().get(0);
    ReportContentRenderer renderer = new ReportContentRenderer();
    String report = renderer.getMeetReport(1, meet);
    //System.out.println("meet report : " + report);
    // System.out.println("stage header : "+ renderer.buildStageHeader(stage));
    // System.out.println(Arrays.deepToString(DateTimeZone.getAvailableIDs().toArray()));
    assertEquals(
    		"<b>1. Ben Davis (2) | 10/10:45 am CT Max: 7.2 Miles Avg: 3.6 Miles<br/></b>"
    		+ "<b></b> <span style=\"background-color: yellow\">Ben Davis</span>"
    										+ ", Herron<br/><br/>", report);
  }

  
  @Test
  public void reportContentRendererGetStageReportTest() {
    Stage stage = repo.getGirlList().get(0).getStageList().get(1);
    ReportContentRenderer renderer = new ReportContentRenderer();
    //System.out.println("regional stage report : " + renderer.getStageReport(stage));
    // System.out.println("stage header : "+ renderer.buildStageHeader(stage));
    // System.out.println(Arrays.deepToString(DateTimeZone.getAvailableIDs().toArray()));
  }
  
  @Test
  public void testBuildStageHeader(){
	  Stage stage = repo.getGirlList().get(0).getStageList().get(0);
	  ReportContentRenderer renderer = new ReportContentRenderer();
	  String report = renderer.buildStageHeader(stage);
	  //System.out.println("sectional stage header: " + report);
	  assertEquals("<b>Entry List Deadline: </b>Monday, Oct. 2, 2017, 4 pm ET/3 pm CT"
	  		+ "<br /><br /><b>Date: </b>Saturday, Oct. 7, 2017<br />"
	  		+ "<b>Admission: </b>$5 per person<br />"
	  		+ "<b>Advancement: </b>"
	  		+ "The top 10 individuals from non-advancing teams and the first 5 "
	  		+ "qualifying teams from each sectional shall advance to"
	  		+ "<br/>designated regionals.<br /><b>"
	  		+ "Note: </b>Races conducted at host school unless indicated otherwise"
	  		+ ".<br /><br />", report);
  }
  
  @Test
  public void testBuildStateFinalHeader(){
	  Stage stage = repo.getGirlList().get(0).getStageList().get(3);
	  ReportContentRenderer renderer = new ReportContentRenderer();
	  String report = renderer.buildStateFinalHeader(stage);
	  //System.out.println("sectional stage header: " + report);
	  assertEquals("<b>Date: </b>Saturday, Oct. 28, 2017<br />"
	  		+ "<b>Site: </b>LaVern Gibson Championship Cross Country Course<br />"
	  		+ "<b>Times: </b>Boys at 1 pm ET; Girls at 1:45 pm ET<br />"
	  		+ "<b>Admission: </b>$10 per person<br />", report);
  }
  
  @Test
  public void testGetTournamentReport(){
	  ReportContentRenderer renderer = new ReportContentRenderer();
	  HashMap<String, String> reportHash = renderer.getTournamentReport(repo.getGirlList().get(0));
	  
	  Object[] arr = reportHash.keySet().toArray();
	  //System.out.println(arr[0]);
	  assertEquals("Sectionals      Max./Avg. :  09.50 /  05.36 (miles)",arr[0]);
	  //System.out.println(reportHash.get(arr[0]));
	  assertEquals("<b>Entry List Deadline: </b>"
	  		+ "Monday, Oct. 2, 2017, 4 pm ET/3 pm CT"
	  		+ "<br /><br />"
	  		+ "<b>Date: </b>Saturday, Oct. 7, 2017<br />"
	  		+ "<b>Admission: </b>$5 per person<br />"
	  		+ "<b>Advancement: </b>The top 10 individuals from "
	  		+ "non-advancing teams and the first 5 qualifying teams "
	  		+ "from each sectional shall advance to<br/>"
	  		+ "designated regionals.<br />"
	  		+ "<b>Note: </b>Races conducted at host school unless indicated otherwise.<br /><br />"
	  		+ "<b>1. Hammond Gavit (2) | 10/10:45 am CT (Riverside Park) Max: 5.1 Miles Avg: 3.0 Miles<br/>"
	  		+ "</b><b></b> "
	  		+ "<span style=\"background-color: yellow\">"
	  		+ "Hammond Gavit</span>, Calumet<br/><br/><b>"
	  		+ "2. Crown Point (1) | 10/10:45 am CT (Lemon Lake County Park) Max: 9.5 Miles Avg: 9.5 Miles<br/>"
	  		+ ""
	  		+ "</b><b></b> <span style=\"background-color: yellow\"></span>"
	  		+ "A<span style=\"background-color: yellow\"></span>n"
	  		+ "<span style=\"background-color: yellow\"></span>d<span style=\"background-color: yellow\"></span>r<span style=\"background-color: yellow\"></span>e<span style=\"background-color: yellow\"></span>a<span style=\"background-color: yellow\"></span>n<span style=\"background-color: yellow\"></span><br/><br/><b>3. Ben Davis (2) | 10/10:45 am CT Max: 7.2 Miles Avg: 3.6 Miles<br/></b><b></b> <span style=\"background-color: yellow\">Ben Davis</span>, Herron<br/><br/>", reportHash.get(arr[0]));
  }
}
