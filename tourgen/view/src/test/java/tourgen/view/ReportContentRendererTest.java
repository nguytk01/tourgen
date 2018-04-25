package tourgen.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.SchoolManager;
import tourgen.model.Stage;
import tourgen.model.StageType;

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
    RepositoryInitialization.init("tournamentData.txt", repo, manager);
  }

  @Test
  public void reportContentRendererSectionalTest() {
    Meet meet = repo.getGirlList().get(0).getStageList().get(0).getMeetList().get(0);
    Stage stage = repo.getGirlList().get(0).getStageList().get(0);
    ReportContentRenderer renderer = new ReportContentRenderer();
    // System.out.println("meet time : "+ renderer.buildMeetTime(meet));
    // System.out.println("stage header : "+ renderer.buildStageHeader(stage));
  }

  @Test
  public void reportContentRendererGetMeetReportTest() {
    Meet meet = repo.getGirlList().get(0).getStageList().get(0).getMeetList().get(0);
    Stage stage = repo.getGirlList().get(0).getStageList().get(0);
    ReportContentRenderer renderer = new ReportContentRenderer();
    System.out.println("meet report : " + renderer.getMeetReport(1, meet));
    // System.out.println("stage header : "+ renderer.buildStageHeader(stage));
    // System.out.println(Arrays.deepToString(DateTimeZone.getAvailableIDs().toArray()));
  }

  @Test
  public void reportContentRendererGetStageReportTest() {
    Stage stage = repo.getGirlList().get(0).getStageList().get(1);
    ReportContentRenderer renderer = new ReportContentRenderer();
    System.out.println("regional stage report : " + renderer.getStageReport(stage));
    // System.out.println("stage header : "+ renderer.buildStageHeader(stage));
    // System.out.println(Arrays.deepToString(DateTimeZone.getAvailableIDs().toArray()));
  }
}
