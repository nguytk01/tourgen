package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tourgen.controller.EditSchoolFormListeners.EditSchoolListener;
import tourgen.model.SchoolFormMVCData;
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
	
	public class EditSchoolListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name;
            String displayName;
		    String cityName;
		    int enroll;
		    boolean Bstatus;
		    boolean Gstatus;
		    boolean Eligible;
            String streetAddr;
            int zipCode;

            if (arg0.getSource() instanceof ICustomizedButton && ( (ICustomizedButton)arg0.getSource()).getSwingParent() instanceof IEditSchoolForm){
                IEditSchoolForm form = (IEditSchoolForm) ((ICustomizedButton) arg0.getSource()).getSwingParent();

				name = form.getSchoolName();
				displayName = form.getDisplayName();
				
				streetAddr = form.getAddr();
				cityName = form.getCityName();
				zipCode = form.getZipCode();

				try {
					enroll = form.getEnrollment();
					if (enroll < 0 || enroll > 900000) throw new Exception();
				} catch (Exception e) {
					form.showErrorMessage("Invalid enrollment number. Please try again.");
					return;
				}
				Bstatus = form.getBoysStatus();
				Gstatus = form.getGirlsStatus();


				Object ticket = new Object();
                SchoolFormMVCData formData = new SchoolFormMVCData(ticket, displayName, name, streetAddr, cityName, zipCode, enroll, Gstatus, Bstatus);
                form.setTicket(ticket);

                controller.editSchool(formData);
            }
			
		}
		
	}
}
