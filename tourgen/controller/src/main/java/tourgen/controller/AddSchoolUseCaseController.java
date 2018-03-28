package tourgen.controller;

import tourgen.model.School;
import tourgen.model.SchoolFormMVCData;
import tourgen.model.SchoolManager;
import tourgen.util.IAddSchoolForm;
import tourgen.util.ISchoolListView;

public class AddSchoolUseCaseController {
	private ISchoolListView schoolList;
	private IAddSchoolForm form;
	private AddSchoolFormListeners listeners;
	private SchoolManager manager;
	
	public AddSchoolUseCaseController(SchoolManager managerArg,
				ISchoolListView schoolListArg, 
				IAddSchoolForm formArg, 
				AddSchoolFormListeners listenersArg) {
		listeners = listenersArg;
		listeners.setCoordinator(this);
		manager = managerArg;
		this.form = formArg;
		this.schoolList = schoolListArg;
		this.listeners = listenersArg;
		form.setAddSchoolUseCaseController(this);
	}
	
	public void addSchool(SchoolFormMVCData obj) {
		manager.addSchool(obj);
	}
	
	public void successAddSchool(School school) {
		form.setHidden(true);
		schoolList.addSchoolToList(school);
	}
	
	public void failureAddSchool(String errorMessage) {
		form.showErrorMessage(errorMessage);
	}
	
	public void startProcess() {
		form.showView();
	}
}
