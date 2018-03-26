package tourgen.model;

public class SchoolFormMVCData {
    private String streetAddress;
    private String cityName;
    private int zipCode;
    private int enrollmentNumber;
    private boolean girlsParticipationStatus;
    private boolean boysParticipationStatus;
    private String schoolName;
    private Object observerTicket;

    public SchoolFormMVCData(Object observerTicketArg,
                            String schoolName,
                            String streetAddress, String cityName, int zipCode,
                            int enrollmentNumber,
                            boolean girlsParticipationStatus, boolean boysParticipationStatus){
        this.observerTicket = observerTicketArg;
        this.schoolName = schoolName;
        this.streetAddress = streetAddress;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.enrollmentNumber = enrollmentNumber;
        this.girlsParticipationStatus = girlsParticipationStatus;
        this.boysParticipationStatus = boysParticipationStatus;
    }

    public String getSchoolName(){
        return schoolName;
    }

    public String getStreetAddress(){
        return streetAddress;
    }

    public String getCityName(){
        return cityName;
    }

    public int getZipCode(){
        return zipCode;
    }

    public int getEnrollmentNumber(){
        return enrollmentNumber;
    }
    
    public void setEnrollmentNumber(int enrollmentNumber){
        this.enrollmentNumber = enrollmentNumber;
    }

    public boolean getBoysParticipationStatus(){
        return boysParticipationStatus;
    }

    public boolean getGirlsParticipationStatus(){
        return girlsParticipationStatus;
    }

    public Object getTicket(){
        return observerTicket;
    }
}