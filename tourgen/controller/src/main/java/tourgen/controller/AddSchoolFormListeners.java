package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tourgen.model.SchoolFormMVCData;
import tourgen.util.IAddSchoolForm;
import tourgen.util.ICustomizedButton;

public class AddSchoolFormListeners {
  AddSchoolUseCaseController controller;
  AddSchoolListener addSchoolListener;
  
  public AddSchoolFormListeners() {
  }
  
  public void setCoordinator(AddSchoolUseCaseController controllerArg) {
    controller = controllerArg;
  }
  
  public void createListeners() {
    addSchoolListener = new AddSchoolListener();
  }
  
  public class AddSchoolListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      String name = "";
      String displayName = "";
      String cityName = "";
      int enroll = 0;
      boolean boyStatus = false;
      boolean girlStatus = false;
      //boolean eligible;
      String streetAddr = "";
      int zipCode = 0;

      if (arg0.getSource() instanceof ICustomizedButton 
          && ((ICustomizedButton)arg0.getSource()).getSwingParent() instanceof IAddSchoolForm) {
        IAddSchoolForm form = (IAddSchoolForm) 
            (((ICustomizedButton) arg0.getSource()).getSwingParent());
                
        name = form.getSchoolName();
        if (name.length() == 0) {
          form.showErrorMessage("Please enter the school's name.");
          return;
        }
        
        displayName = form.getDisplayName();
        
        streetAddr = form.getAddr();
        if (streetAddr.length() == 0) {
          form.showErrorMessage("Please enter street address.");
          return;
        }
        
        cityName = form.getCityName();
        if (cityName.length() == 0) {
          form.showErrorMessage("Please enter the school's city name.");
          return;
        }
        
        
        try {
          zipCode = form.getZipCode();
          if (zipCode < 10000 || zipCode > 100000) {
            throw new Exception();
          }
        } catch (Exception e) {
          form.showErrorMessage("Invalid ZIP. Please try again.");
          return;
        }
        
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
        SchoolFormMVCData formData = new SchoolFormMVCData(
            ticket, displayName, name, streetAddr, 
            cityName, zipCode, enroll, girlStatus, boyStatus);
        
        form.setTicket(ticket);

        controller.addSchool(formData);
      }
      
    }
    
  }
}
