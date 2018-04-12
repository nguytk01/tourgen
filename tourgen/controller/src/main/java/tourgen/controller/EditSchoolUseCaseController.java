package tourgen.controller;

import tourgen.model.School;
import tourgen.model.SchoolFormMvcData;
import tourgen.model.SchoolManager;
import tourgen.util.IEditSchoolForm;
import tourgen.util.ISchoolListView;

public class EditSchoolUseCaseController {
  private ISchoolListView schoolList;
  private IEditSchoolForm form;
  private EditSchoolFormListeners listeners;
  private SchoolManager manager;

  /**
   * Controller for editing school's information use case.
   * @param managerArg schoolManager object
   * @param schoolListArg schoolList object
   * @param formArg Edit School Form object
   * @param listenersArg ActionListener object
   */
  
  public EditSchoolUseCaseController(SchoolManager managerArg, 
      ISchoolListView schoolListArg, IEditSchoolForm formArg,
      EditSchoolFormListeners listenersArg) {
    listeners = listenersArg;
    listeners.setCoordinator(this);
    manager = managerArg;
    this.form = formArg;
    this.schoolList = schoolListArg;
    this.listeners = listenersArg;
    form.setEditSchoolUseCaseController(this);
  }

  public void editSchool(SchoolFormMvcData obj) {
    manager.editSchool(obj);
  }

  public void successEditSchool(School school) {
    form.setHidden(true);
  }

  public void failureEditSchool(String errorMessage) {
    form.showErrorMessage(errorMessage);
  }

  /**
   * this will display the school in the edit school's dialog.
   */
  public void startProcess() {
    Object school = schoolList.getSelectedSchool();
    form.display(school);
    form.showView();
  }
}
