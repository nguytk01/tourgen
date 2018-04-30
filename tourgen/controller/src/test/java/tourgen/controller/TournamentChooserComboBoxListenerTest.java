package tourgen.controller;

import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Location;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.School;

public class TournamentChooserComboBoxListenerTest extends BaseTestUtils {
	private School crownPoint;
	private NewMainViewController controller;
	private MainViewPanelMock leftPanelMock;
	private MainViewPanelMock rightPanelMock;
 
	@Before
	public void setUp() {
		clearRepositorySingleton(); 
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
		Location crownPointLocation = new Location("n", "n", 12);
		crownPointLocation.setLatitude(41.389664);
		crownPointLocation.setLongitude(-87.359668);
		crownPoint = new School("Crown Point", "Crown Point High School", "n", "n", 123, 123, true, true);
		crownPoint.setSchoolLoc(crownPointLocation);

		leftPanelMock = new MainViewPanelMock();
		rightPanelMock = new MainViewPanelMock();
		controller = new NewMainViewController(leftPanelMock, rightPanelMock, null);
	}
	 MainViewPanelMock newPane = new MainViewPanelMock();
	 	private tourgen.util.INewMainViewPane newMainPane = newPane;
		private NewMainViewController newMainViewController =  new NewMainViewController(newMainPane, newMainPane, schoolManager );
		
		
	@Test
	public void test() {
		TournamentChooserComboBoxListener comboBoxListener = new TournamentChooserComboBoxListener();
		comboBoxListener.setNewMainViewController(controller);
		JComboBox comboBox = new TournamentComboBoxMock();
		ActionEvent event = new ActionEvent(comboBox, 0, null);
		comboBoxListener.actionPerformed(event);
		
		ItemEvent e = new ItemEvent(comboBox, ItemEvent.SELECTED, null,1);
		comboBoxListener.itemStateChanged(e);
	}

}
