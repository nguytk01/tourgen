package tourgen.controller;

import java.awt.event.ActionEvent;

import tourgen.model.Repository;
import tourgen.model.SchoolFormMVCData;
import tourgen.model.SchoolManager;
import tourgen.util.IAddSchoolForm;
import tourgen.util.IEditSchoolForm;
import tourgen.util.IReportTableView;
import tourgen.util.ISchoolListView;

public class TourGenController implements IController {

    private SchoolManager schoolManager;
    private Repository tournamentRepository;
    
    private IAddSchoolForm addSchoolForm;
    private IEditSchoolForm editSchoolForm;
    private ISchoolListView schoolListView;
    private IReportTableView reportTableView;
    //private AddSchoolListener addSchoolListener;
    //private EditSchoolListener editSchoolListener;
    //private RemoveSchoolListener removeSchoolListener;

    public TourGenController( Repository tournamentRepositoryArg, SchoolManager schoolManagerArg ){
        schoolManager = schoolManagerArg;
        tournamentRepository = tournamentRepositoryArg;
        //addSchoolListener = new AddSchoolListener();
    }

    /*public void getAddSchoolListener(){
        return addSchoolListener;
    }*/

    public void addSchool(ActionEvent arg0) {
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
                IAddSchoolForm form = (IAddSchoolForm) arg0;
                if (addSchoolForm == null) addSchoolForm = form;
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

                schoolManager.addSchool(formData);
            }
    }

    public void editSchool(ActionEvent arg0) {
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
                if (editSchoolForm == null) editSchoolForm = form;
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

                schoolManager.editSchool(formData);
            }
		}

    public void removeSchool(ActionEvent arg0) {
            String name;
		    String cityName;
		    int enroll;
		    boolean Bstatus;
		    boolean Gstatus;
		    boolean Eligible;
            String streetAddr;
           
            String displayName;
            int zipCode;

            if (arg0.getSource() instanceof IAddSchoolForm){
                IAddSchoolForm form = (IAddSchoolForm) arg0;
                
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

                schoolManager.editSchool(formData);
            }
		}

	@Override
	public void listAddEvent(ActionEvent a) {
		addSchoolForm.showView();
	}

	
	public void setAddSchoolForm(IAddSchoolForm form) {
		addSchoolForm = form;
	}
	
	public void setEditSchoolForm(IEditSchoolForm form) {
		editSchoolForm = form;
	}
	
	public void setSchoolListView(ISchoolListView view) {
		schoolListView = view;
	}
	
	public void setReportTableView(IReportTableView view) {
		reportTableView = view;
	}

	@Override
	public void listEditEvent(ActionEvent a, Object school) {
		editSchoolForm.showView();
	}

	@Override
	public void removeSchoolEvent(ActionEvent a, Object school) {
		
	}

	@Override
	public void listRemoveEvent(ActionEvent e, Object school) {
		
	}

	@Override
	public void showReportEvent() {
		reportTableView.showReport();
	}
}
