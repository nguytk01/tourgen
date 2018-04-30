package tourgen.view;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ReportContentRendererTest.class, 
	MapDriverTest.class, 
	HostChooserPanelTest.class,
	ReportTableViewTest.class,
	TournamentChooserComboBoxTest.class,
	NewMainMapSidePaneTest.class,
	HostListViewTest.class,
	MainTest.class})
public class ViewAllTests {

}
