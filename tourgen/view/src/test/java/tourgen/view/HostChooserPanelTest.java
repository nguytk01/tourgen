package tourgen.view;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.junit.Before;
import org.junit.Test;

import tourgen.controller.MapAssistantController;
import tourgen.controller.MapController;
import tourgen.controller.NewMainViewController;
import tourgen.controller.TournamentChooserComboBoxListener;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.SchoolManager;
import tourgen.util.IMapSidePane;

public class HostChooserPanelTest extends BaseTestUtils {

	@Before
	public void setUp() throws Exception {
		RepositoryInitialization.init("tournamentDataForViewTests.txt", Repository.getInstance1(), schoolManager);
		
	}

	@Test
	public void testShowHostList() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ReportTableView reportTableView = new ReportTableView();

		HostChooserPanel panel = new HostChooserPanel();
		MapDriver mapDriver = new MapDriver(Repository.getInstance1());

		MapController mapController = new MapController(null, mapDriver);
		
		TournamentChooserComboBoxListener tournamentChooserComboBoxListener = new TournamentChooserComboBoxListener();

		DetailsPanel detailsPanel = new DetailsPanel();
	    HostChooserPanel hostChooserPanel = new HostChooserPanel();
	    AvailableMeetsPanel availableMeetsPanel = new AvailableMeetsPanel();
	    NewMainMapSidePane mapSidePane = new NewMainMapSidePane(detailsPanel, hostChooserPanel, availableMeetsPanel);

		MapAssistantController mapAssistantController = new MapAssistantController(mapSidePane);
		mapDriver.setMapAssistantController(mapAssistantController);

		NewMain newMain = new NewMain(mapController, 
				mapDriver.getMapPanel(), reportTableView, mapSidePane, schoolManager);
		newMain.initUserInterface();
		
		/* Hack here */
		java.lang.reflect.Field field = null;
	    field = HostChooserPanel.class.getDeclaredField("newMainViewController");
	    field.setAccessible(true);
	    NewMainViewController controller = (NewMainViewController)field.get(hostChooserPanel);
	    
	    controller.setActiveTournament(Repository.getInstance1().getGirlList().get(0));
		
		Meet sectionalMeet = 
				Repository.getInstance1().getGirlList().get(0).getStageList()
				.get(0).getMeetList().get(0);
		
		
		hostChooserPanel.showHostList(sectionalMeet, sectionalMeet.getHostSchool());
		Object[] leftPane = ((java.awt.Container)newMain.getContentPane()
		  .getComponents()[0]).getComponents();
		javax.swing.JComboBox comboBox = 
		  (javax.swing.JComboBox)
		  ((java.awt.Container)
		  ((java.awt.Container)leftPane[0])
		  .getComponents()[0]).getComponents()[1];
		
		
		assertEquals(comboBox.getModel().getSize(), 1);
		
		
		//comboBox.setSelectedIndex(0);
		javax.swing.JList list = (javax.swing.JList)
				(((java.awt.Container)
						((java.awt.Container)hostChooserPanel.getComponents()[1])
						.getComponents()[0]).getComponents()[0]);
		//System.out.println(list.getModel().getSize());
		//System.out.println(list.getModel().getElementAt(0).toString());
		for (MouseListener e : list.getMouseListeners()) {
			e.mouseExited(new MouseEvent(mapDriver.getMapPanel(), 
					MouseEvent.MOUSE_EXITED, 99, 0, 50,50, 1, false));
			e.mouseClicked(new MouseEvent(mapDriver.getMapPanel(), 
					MouseEvent.MOUSE_CLICKED, 99, 0, 50,50, 1, false));
			e.mouseClicked(new MouseEvent(mapDriver.getMapPanel(), 
					MouseEvent.MOUSE_CLICKED, 99, 0, 10,10, 1, false));
			e.mouseEntered(new MouseEvent(mapDriver.getMapPanel(), 
					MouseEvent.MOUSE_EXITED, 99, 0, 50,50, 1, false));
			e.mouseExited(new MouseEvent(mapDriver.getMapPanel(), 
					MouseEvent.MOUSE_EXITED, 99, 0, 50,50, 1, false));
		}
		for (MouseMotionListener e : list.getMouseMotionListeners()) {
			e.mouseMoved((new MouseEvent(mapDriver.getMapPanel(), 
				MouseEvent.MOUSE_MOVED, 99, 0, 50,50, 1, false)));
		}
		
		for (MouseWheelListener e : list.getMouseWheelListeners()) {
			e.mouseWheelMoved(new MouseWheelEvent(list, MouseWheelEvent.MOUSE_WHEEL, 99, 0, 99,5,0,false, MouseWheelEvent.WHEEL_UNIT_SCROLL,1,1));
		}
		Component componentRendered = 
				(Component)list.getCellRenderer()
				.getListCellRendererComponent(list, sectionalMeet.getHostSchool() , 0, false, false);
		assertEquals("Hammond Gavit Mdl/High School", ((javax.swing.JLabel) componentRendered).getText().trim());
		assertEquals("Calumet High School", list.getModel().getElementAt(0).toString().trim());
	}

}
