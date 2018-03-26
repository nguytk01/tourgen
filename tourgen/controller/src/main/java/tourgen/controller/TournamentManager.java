package tourgen.controller;

public class TourGenController implements IController {

    private SchoolManager schoolManager;
    private Repository tournamentRepository;
    private AddSchoolListener addSchoolListener;
    private EditSchoolListener editSchoolListener;
    private RemoveSchoolListener removeSchoolListener;

    public TourGenController( Repository tournamentRepositoryArg, SchoolManager schoolManagerArg ){
        schoolManager = schoolManagerArg;
        tournamentRepository = tournamentRepositoryArg;
        addSchoolListener = new AddSchoolListener();
    }

    public void getAddSchoolListener(){
        return addSchoolListener;
    }

    public void addSchool(ActionEvent arg0) {
            String name;
		    String cityName;
		    int enroll;
		    boolean Bstatus;
		    boolean Gstatus;
		    boolean Eligible;
            String streetAddr;
            int zipCode;

            if (arg0.getSource() instanceof IAddSchoolForm){
                IAddSchoolForm form = (IAddSchoolForm) arg0;
				name = form.getName();

				streetAddr = form.getAddr();
				cityName = form.getCityName();
				zipCode = form.getZipCode();

				enroll = form.getEnrollment();
				Bstatus = form.getBoysStatus();
				Gstatus = form.getGirlsStatus();

                Object ticket = new Object();
                SchoolFormMVCData formData = new SchoolFormMVCData(ticket, name, streetAddr, cityName, zipCode, enroll, Bstatus, GStatus);
                form.setTicket(ticket);

                schoolManager.addSchool(formData);
            }
    }

    public void editSchool(ActionEvent arg0) {
            String name;
		    String cityName;
		    int enroll;
		    boolean Bstatus;
		    boolean Gstatus;
		    boolean Eligible;
            String streetAddr;
            int zipCode;

            if (arg0.getSource() instanceof IAddSchoolForm){
                IAddSchoolForm form = (IAddSchoolForm) arg0;
				name = form.getName();

				streetAddr = form.getAddr();
				cityName = form.getCityName();
				zipCode = form.getZipCode();

				enroll = form.getEnrollment();
				Bstatus = form.getBoysStatus();
				Gstatus = form.getGirlsStatus();

				SchoolFormMVCData formData = new SchoolFormMVCData(ticket, name, streetAddr, cityName, zipCode, enroll, Bstatus, GStatus);
				Object ticket = new Object();
                SchoolFormMVCData formData = new SchoolFormMVCData(ticket, name, streetAddr, cityName, zipCode, enroll, Bstatus, GStatus);
                form.setTicket(ticket);

                schoolManager.editSchool(formData);
            }
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
            int zipCode;

            if (arg0.getSource() instanceof IAddSchoolForm){
                IAddSchoolForm form = (IAddSchoolForm) arg0;
				name = form.getName();

				streetAddr = form.getAddr();
				cityName = form.getCityName();
				zipCode = form.getZipCode();

				enroll = form.getEnrollment();
				Bstatus = form.getBoysStatus();
				Gstatus = form.getGirlsStatus();
            }
		}
    }
}