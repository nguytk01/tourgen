package tourgen.controller;

import org.junit.Test;

import tourgen.model.Meet;
import tourgen.model.School;

public class MapAssistantControllerTest {

	MapSidePaneMock sidePane = new MapSidePaneMock();
	
	private tourgen.util.IMapSidePane mapSidePane = sidePane;
	
	@Test
	public void test() {
		
		Meet meet = new Meet();
		School school = new School("TEST", "TEST","TEST", "TEST", 10, 20, false, false);

		MapAssistantController mapAssController = new MapAssistantController(mapSidePane);
 
		mapAssController.hideSidePane();
		 
		
		mapAssController.showPinHostInfoSidePane(meet, school);
		mapAssController.showPinRegularInfoSidePane(meet, school);
		 
	}

}
