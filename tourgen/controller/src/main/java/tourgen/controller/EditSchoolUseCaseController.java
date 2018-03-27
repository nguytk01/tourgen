package tourgen.controller;

import tourgen.model.School;
import tourgen.model.SchoolFormMVCData;
import tourgen.model.SchoolManager;
import tourgen.util.IEditSchoolForm;
import tourgen.util.ISchoolListView;

public class EditSchoolUseCaseController {
	private ISchoolListView schoolList;
	private IEditSchoolForm form;
	private EditSchoolFormListeners listeners;
	private SchoolManager manager;
	
	public EditSchoolUseCaseController(SchoolManager managerArg,
				ISchoolListView schoolListArg, 
				IEditSchoolForm formArg, 
				EditSchoolFormListeners listenersArg) {
		listeners = listenersArg;
		listeners.setCoordinator(this);
		this.form = formArg;
		this.schoolList = schoolListArg;
		this.listeners = listenersArg;
	}
	
	public void editSchool(SchoolFormMVCData obj) {
		manager.editSchool(obj);
	}
	
	public void successEditSchool(School school) {
		form.setHidden(true);
	}
	
	public void failureEditSchool() {
		form.showErrorMessage();
	}
	
	public void startProcess() {
		System.out.println("hey");
		Object school = schoolList.getSelectedSchool();
		form.display(school);
		form.showView();
		
	}
}
