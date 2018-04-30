package tourgen.view;

import tourgen.util.IMapSidePane;

public class MapSidePaneMock implements IMapSidePane {

	boolean hideMethodCalled = false;
	@Override
	public void displayPinInformation(Object meet, Object school) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayPinRegularInformation(Object meet, Object school) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayPinHostInformation(Object meet, Object school) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		hideMethodCalled = true;
	}

}
