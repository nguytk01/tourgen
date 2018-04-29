package tourgen.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;

import org.junit.Before;

public class MapControllerTest extends BaseTestUtils{

	private MapController mapController;
	private MapDriverMock mapDriver;
	private RepoTreeMock repoTree;
	@Before
	public void setUp(){
		mapDriver = new MapDriverMock();
		mapController = new MapController(null, mapDriver);
		clearRepositorySingleton();
		RepositoryInitialization.init("tournamentDataForControllerTests.txt", Repository.getInstance1(), schoolManager);
		repoTree = new RepoTreeMock();
		mapController.setTree(repoTree);
	}
	
	@Test
	public void testEmptyMap() {
		java.util.List<Meet> meetList  = Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList();
		CheckNodeMock checkNodeMock1 = new CheckNodeMock(meetList.get(0));
		CheckNodeMock checkNodeMock2 = new CheckNodeMock(meetList.get(1));
		repoTree.checkNodeSet.add(checkNodeMock1);
		repoTree.checkNodeSet.add(checkNodeMock2);
		
		
		mapController.emptyMap();
		assertEquals(0, mapDriver.getMeetList().size());
	}

	@Test
	public void testTreeCheckBoxClicked() {
		java.util.List<Meet> meetList  = Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList();
		CheckNodeMock checkNodeMock1 = new CheckNodeMock(meetList.get(0));
		CheckNodeMock checkNodeMock2 = new CheckNodeMock(meetList.get(1));
		repoTree.checkNodeSet.add(checkNodeMock1);
		repoTree.checkNodeSet.add(checkNodeMock2);
		
		
		mapController.treeCheckBoxClicked();
		
		assertEquals(2, mapDriver.getMeetList().size());
	}

	@Test
	public void testMapReplace() {
		Object school1 = new Object();
		Object school2 = new Object();
		mapController.mapReplace(school1, school2);
		assertEquals(school1, mapDriver.oldSchool);
		assertEquals(school2, mapDriver.newSchool);
	}

}
