package tourgen.model;

public class ISchoolFormData{
    private String streetAddress;
    private String cityName;
    private int zipCode;
    private int enrollmentNumber;
    private boolean girlsParticipationStatus;
    private boolean boysParticipationStatus;
    private String schoolName;

    public ISchoolFormData(String schoolName, String streetAddress, String cityName, int zipCode, int enrollmentNumber, boolean girlsPartcipationStatus, boolean boysParticipationStatus){
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

    public boolean getBoysParticipationStatus(){
        return boysParticipationStatus;
    }

    public boolean getGirlsParticipationStatus(){
        return girlsParticipationStatus;
    }
}