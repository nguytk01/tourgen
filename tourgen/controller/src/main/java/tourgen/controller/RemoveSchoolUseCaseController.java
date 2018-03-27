package tourgen.controller;

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
	
	
	
}
