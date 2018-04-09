package tourgen.view;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import tourgen.model.Meet;
import tourgen.model.Stage;
import tourgen.model.StageType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import tourgen.view.ReportContentRenderer;
import tourgen.model.Repository;
import tourgen.model.SchoolManager;
import tourgen.model.RepositoryInitialization;
import java.util.Arrays;
public class ReportContentRendererTest{
	private Repository repo;
	private SchoolManager manager;
	public ReportContentRendererTest(){
		repo = Repository.getInstance();
		manager = new SchoolManager(repo);
		manager.initSchools();
		RepositoryInitialization.init(repo, manager);
	}	

	@Test
	public void ReportContentRendererSectionalTest(){
		Meet meet = repo.getGirlList().get(0).getStageList().get(0).getMeetList().get(0);
		Stage stage = repo.getGirlList().get(0).getStageList().get(0);
		ReportContentRenderer renderer = new ReportContentRenderer();
		//System.out.println("meet time : "+ renderer.buildMeetTime(meet));
		//System.out.println("stage header : "+ renderer.buildStageHeader(stage));
	}
	
	@Test
	public void ReportContentRendererGetMeetReportTest(){
		Meet meet = repo.getGirlList().get(0).getStageList().get(0).getMeetList().get(0);
		Stage stage = repo.getGirlList().get(0).getStageList().get(0);
		ReportContentRenderer renderer = new ReportContentRenderer();
		System.out.println("meet report : "+ renderer.getMeetReport(1,meet));
		//System.out.println("stage header : "+ renderer.buildStageHeader(stage));
		//System.out.println(Arrays.deepToString(DateTimeZone.getAvailableIDs().toArray()));
	}
	
	@Test
	public void ReportContentRendererGetStageReportTest(){
		Stage stage = repo.getGirlList().get(0).getStageList().get(1);
		ReportContentRenderer renderer = new ReportContentRenderer();
		System.out.println("regional stage report : "+ renderer.getStageReport(stage));
		//System.out.println("stage header : "+ renderer.buildStageHeader(stage));
		//System.out.println(Arrays.deepToString(DateTimeZone.getAvailableIDs().toArray()));
	}
}	
