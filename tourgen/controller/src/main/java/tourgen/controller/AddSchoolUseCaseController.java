package tourgen.controller;

import tourgen.model.School;
import tourgen.model.SchoolFormMvcData;
import tourgen.model.SchoolManager;
import tourgen.util.IAddSchoolForm;
import tourgen.util.ISchoolListView;

public class AddSchoolUseCaseController {
  private ISchoolListView schoolList;
  private IAddSchoolForm form;
  private AddSchoolFormListeners listeners;
  private SchoolManager manager;
  
  /**
   * Controller for the Add School Use Case.
   * @param managerArg schoolManager object
   * @param schoolListArg the schools' ListView
   * @param formArg the Add School Form
   * @param listenersArg The listener for the Add School Form
   */
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
  
  public void addSchool(SchoolFormMvcData obj) {
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
