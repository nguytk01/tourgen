package tourgen.util;

import java.util.Observer;

public interface IEditSchoolForm extends IAddSchoolForm, Observer {

  void display(Object school);

  void setEditSchoolUseCaseController(Object controllerArg);

  void showErrorMesage(String errorMessage);

}
