package tourgen.controller;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;

import org.junit.Before;
import org.junit.Test;

import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;

public class CheckBoxTreeListenerTest extends BaseTestUtils{

	 @Before
	  public void setup() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
	 //setUpBeforeClass();
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
	   }
	 
	 	CheckBoxTreeListener check = new CheckBoxTreeListener();
	 	MapDriverMock driver = new MapDriverMock();
	 	MapController controller = new MapController(check,driver);
	 
	 	RepoTreeMock repoTree = new RepoTreeMock();
	 	JTree tree = repoTree;
		
	@Test
	public void test() {
		
		
		
		 
		CheckBoxTreeListener checkBoxTreeListener = new CheckBoxTreeListener();
		checkBoxTreeListener.setJTree(tree);
		MouseEvent event = new MouseEvent(tree, 0, 0, 0, 0, 0, 0, false); 
		checkBoxTreeListener.setMapController(controller);
		
		checkBoxTreeListener.mouseClicked(event);
		
		TreeSelectionEvent event2 = new TreeSelectionEvent(event, null, false, null, null);
		
		checkBoxTreeListener.valueChanged(event2);
		
		
		
		 
		
	}

}
