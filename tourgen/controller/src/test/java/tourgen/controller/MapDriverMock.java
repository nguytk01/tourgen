package tourgen.controller;

import java.util.List;

import tourgen.model.School;
import tourgen.util.IMapDriver;

public class MapDriverMock implements IMapDriver {

	List meetList;
	private boolean showMeetList = false;
	Object oldSchool;
	Object newSchool;
	
	public MapDriverMock() {
		meetList = null;
	}
	@Override
	public void showMeetList(List list) {
		meetList = list;
	}

	@Override
	public List getMeetList() {
		return meetList;
	}

	@Override
	public void mapReplace(Object oldSchool, Object newSchool) {
		this.oldSchool = oldSchool;
		this.newSchool = newSchool;

	}
	
	boolean getShowMeetList(){
		return showMeetList;
	}

}
