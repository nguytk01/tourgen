package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tourgen.controller.AddSchoolFormListeners.AddSchoolListener;
import tourgen.model.SchoolFormMVCData;
import tourgen.util.IAddSchoolForm;
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

            if (arg0.getSource() instanceof IAddSchoolForm){
                IEditSchoolForm form = (IEditSchoolForm) arg0;

				name = form.getName();
				displayName = form.getDisplayName();
				
				streetAddr = form.getAddr();
				cityName = form.getCityName();
				zipCode = form.getZipCode();

				enroll = form.getEnrollment();
				Bstatus = form.getBoysStatus();
				Gstatus = form.getGirlsStatus();


				Object ticket = new Object();
                SchoolFormMVCData formData = new SchoolFormMVCData(ticket, displayName, name, streetAddr, cityName, zipCode, enroll, Bstatus, Gstatus);
                form.setTicket(ticket);

                controller.editSchool(formData);
            }
			
		}
		
	}
}
