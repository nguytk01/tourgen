package tourgen.controller;

import tourgen.model.School;
import tourgen.model.SchoolFormMVCData;
import tourgen.model.SchoolManager;
import tourgen.util.IAddSchoolForm;
import tourgen.util.ISchoolListView;

public class RemoveSchoolUseCaseController {
	private ISchoolListView schoolList;
	private SchoolListListeners listeners;
	private SchoolManager manager;
	public RemoveSchoolUseCaseController(ISchoolListView schoolListArg, 
				SchoolListListeners listenersArg,
				SchoolManager managerArg) {
		schoolList = schoolListArg;
		listeners = listenersArg;
		manager = managerArg;
		listeners.setRemoveController(this);
	}
	
	public void removeSchool(SchoolFormMVCData school){
		manager.removeSchool(school);
	}
	
	public void successRemoveSchool(School school){
		schoolList.remove(school);
	}
	
	public void failureRemoveSchool(String errorMessage){
		schoolList.showErrorMessage(errorMessage);
	}
	
	
}
