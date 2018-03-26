package tourgen.model;

public class SchoolManager extends java.util.Observable{
    private java.util.ArrayList<School> schoolList;

    public SchoolManager() {
        schoolList = new java.util.ArrayList<School>();
    }

    public void addSchool(SchoolFormMVCData info){
        School school = new School(info.getSchoolName(), info.getStreetAddress(), info.getCityName(),
                                    info.getZipCode(), info.getEnrollment(), info.getBoysPariticipationStatus(),
                                    info.getGirlsParticipationStatus());
        schoolList.add(school);
        IOperationResult result = new OperationResult(info.getTicket(), OperationResultCodeEnum.SUCCESS, "", school);
        setChanged();
        notifyObservers(result);
    }

    public void removeSchool(SchoolMVCFormData info){
        School school = new School(info.getSchoolName(), info.getStreetAddress(), info.getCityName(),
                                    info.getZipCode(), info.getEnrollment(), info.getBoysParticipationStatus(),
                                    info.getGirlsParticipationStatus());
        int index = schoolList.indexOf(school);
        IOperationResult result;
        if (index != -1){
            schoolList.remove(index);
            result = new OperationResult(info.getTicket(), OperationResultCodeEnum.SUCCESS, "", school);
        }
        result= new OperationResult(info.getTicket(), OperationResultCodeEnum.FAILURE, "Remove failure", null);
        setChanged();
        notifyObservers(result);
    }

    public void editSchool(SchoolFormData info){
        School school = new School(info.getSchoolName(), info.getStreetAddress(), info.getCityName(),
                                    info.getZipCode(), info.getEnrollment(), info.getBoysParticipationStatus(),
                                    info.getGirlsParticipationStatus());
        int index = schoolList.indexOf(school);

        IOperationResult result;
        if (index != -1){
            schoolList.get(index).setEnrollment(info.getEnrollment);
            schoolList.get(Index).setBoysParticipationStatus(info.getBoysParticipationStatus());
            schoolList.get(Index).setGirlsParticipationStatus(info.getGirlsParticipationStatus());
            result = new OperationResult(info.getTicket(), OperationResultCodeEnum.SUCCESS, "", school);
        }
        result= new OperationResult(info.getTicket(), OperationResultCodeEnum.FAILURE, "Remove failure", null);
        setChanged();
        notifyObservers(result);
    }
}