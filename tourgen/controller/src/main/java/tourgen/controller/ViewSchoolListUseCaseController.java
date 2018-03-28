package tourgen.controller;

import tourgen.util.ISchoolListView;

public class ViewSchoolListUseCaseController {
	private ISchoolListView schoolListView;
	
	public ViewSchoolListUseCaseController(ISchoolListView schoolListViewArg) {
		schoolListView = schoolListViewArg;
	}

	public void startProcess() {
		schoolListView.showView();
	}
	
	public void toggleEditButtonBasedOnState(){
		schoolListView.toggleEditButtonBasedOnState();
	}

	public void toggleRemoveButtonBasedOnState() {
		schoolListView.toggleRemoveButtonBasedOnState();
	}
	
	
}
