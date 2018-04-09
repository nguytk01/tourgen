package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tourgen.controller.EditSchoolFormListeners.EditSchoolListener;
import tourgen.model.SchoolFormMvcData;
import tourgen.util.IAddSchoolForm;
import tourgen.util.ICustomizedButton;
import tourgen.util.IEditSchoolForm;

public class EditSchoolFormListeners {
  EditSchoolUseCaseController controller;
  EditSchoolListener editSchoolListener;

  public EditSchoolFormListeners() {
  }

  public void setCoordinator(EditSchoolUseCaseController controllerArg) {
    controller = controllerArg;
  }

  public void createListeners() {
    editSchoolListener = new EditSchoolListener();
  }

  public class EditSchoolListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      String name;
      String displayName;
      String cityName;
      int enroll;
      boolean boyStatus;
      boolean girlStatus;
      boolean eligible;
      String streetAddr;
      int zipCode;

      if (arg0.getSource() instanceof ICustomizedButton
          && ((ICustomizedButton) arg0.getSource()).getSwingParent() instanceof IEditSchoolForm) {
        IEditSchoolForm form = (IEditSchoolForm) ((ICustomizedButton) arg0.getSource())
            .getSwingParent();

        name = form.getSchoolName();
        displayName = form.getDisplayName();

        streetAddr = form.getAddr();
        cityName = form.getCityName();
        zipCode = form.getZipCode();

        try {
          enroll = form.getEnrollment();
          if (enroll < 0 || enroll > 900000) {
            throw new Exception();
          }
        } catch (Exception e) {
          form.showErrorMessage("Invalid enrollment number. Please try again.");
          return;
        }
        boyStatus = form.getBoysStatus();
        girlStatus = form.getGirlsStatus();

        Object ticket = new Object();
        SchoolFormMvcData formData = 
            new SchoolFormMvcData(ticket, displayName, name, streetAddr, cityName, zipCode,
            enroll, girlStatus, boyStatus);
        form.setTicket(ticket);

        controller.editSchool(formData);
      }

    }

  }
}
