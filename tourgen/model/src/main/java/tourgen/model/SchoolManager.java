package tourgen.model;

public class SchoolManager extends java.util.Observable{
    private java.util.ArrayList<School> schoolList;

    public SchoolManager() {
        schoolList = new java.util.ArrayList<School>();
    }

    public void addSchool(SchoolFormMVCData info){
        School school = new School(info);
        schoolList.add(school);
        IOperationResult result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
        setChanged();
        notifyObservers(result);
    }

    public void removeSchool(SchoolFormMVCData info){
        School school = new School(info);
        int index = schoolList.indexOf(school);
        IOperationResult result;
        if (index != -1){
            schoolList.remove(index);
            result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
        }
        result= new OperationResult(info.getTicket(), OperationResultEnum.FAILURE, "Remove failure", null);
        setChanged();
        notifyObservers(result);
    }

    public void editSchool(SchoolFormMVCData info){
        School school = new School(info);
        int index = schoolList.indexOf(school);

        IOperationResult result;
        if (index != -1){
            schoolList.get(index).setEnroll(info.getEnrollmentNumber());
            schoolList.get(index).setBStatus(info.getBoysParticipationStatus());
            schoolList.get(index).setGStatus(info.getGirlsParticipationStatus());
            result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
        }
        result= new OperationResult(info.getTicket(), OperationResultEnum.FAILURE, "Remove failure", null);
        setChanged();
        notifyObservers(result);
    }
}