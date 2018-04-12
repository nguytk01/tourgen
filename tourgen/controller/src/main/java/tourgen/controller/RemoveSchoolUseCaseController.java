package tourgen.controller;

import tourgen.model.School;
import tourgen.model.SchoolFormMvcData;
import tourgen.model.SchoolManager;
import tourgen.util.IAddSchoolForm;
import tourgen.util.ISchoolListView;

public class RemoveSchoolUseCaseController {
  private ISchoolListView schoolList;
  private SchoolListListeners listeners;
  private SchoolManager manager;

  /**
   * Controller class for the school removal use case.
   * @param schoolListArg school ListView
   * @param listenersArg ActionListener object
   * @param managerArg schoolManager object
   */
  public RemoveSchoolUseCaseController(ISchoolListView schoolListArg, 
      SchoolListListeners listenersArg,
      SchoolManager managerArg) {
    schoolList = schoolListArg;
    listeners = listenersArg;
    manager = managerArg;
    listeners.setRemoveController(this);
  }

  public void removeSchool(SchoolFormMvcData school) {
    manager.removeSchool(school);
  }

  public void successRemoveSchool(School school) {
    schoolList.remove(school);
  }

  public void failureRemoveSchool(String errorMessage) {
    schoolList.showErrorMessage(errorMessage);
  }

}
