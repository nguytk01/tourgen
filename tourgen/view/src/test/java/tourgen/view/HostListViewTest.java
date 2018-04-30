package tourgen.view;

import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.School;
import tourgen.model.SchoolManager;

public class HostListViewTest extends BaseTestUtils{

	@Before
	public void setUp() throws Exception {
		RepositoryInitialization.init("tournamentDataForViewTests.txt", Repository.getInstance1(), schoolManager);
	}

	@Test
	public void test() {
		HostListView listView = new HostListView(schoolManager);
		javax.swing.JList obj = (javax.swing.JList) 
				((java.awt.Container) 
				(((java.awt.Container)
				(listView.getContentPane().getComponents()[1])).getComponents()[0])).getComponents()[0];
		School school = Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(0).getParticipatingSchool().get(0);
		obj.getCellRenderer().getListCellRendererComponent(obj, school, 0, false, false);
		obj.getCellRenderer().getListCellRendererComponent(obj, school, 1, false, false);
		schoolManager.toggleSchoolHostEligibility(school);
		obj.getCellRenderer().getListCellRendererComponent(obj, school, 0, false, false);
		obj.getCellRenderer().getListCellRendererComponent(obj, school, 1, false, false);
		
		obj.getCellRenderer().getListCellRendererComponent(obj, school, 1, true, false);
		for (MouseListener e : obj.getMouseListeners()) {
			e.mouseClicked(new MouseEvent(obj,MouseEvent.MOUSE_CLICKED, 99, 0, 10,10,0, 0,1, false, 1));
			e.mouseClicked(new MouseEvent(obj,MouseEvent.MOUSE_CLICKED, 99, 0, 10,10,0, 0,2, false, 1));
		}
	}

}
