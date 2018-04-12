package tourgen.model;

public class ISchoolFormData {
  private String streetAddress;
  private String cityName;
  private int zipCode;
  private int enrollmentNumber;
  private boolean girlsParticipationStatus;
  private boolean boysParticipationStatus;
  private String schoolName;

  
  /**
   * This plays a similar role to the SchoolFormMvcData. However, it is obsolete.
   * @param schoolName the school's name
   * @param streetAddress the school's street address
   * @param cityName the school's city name
   * @param zipCode the school's zip code
   * @param enrollmentNumber the school's enrollment number
   * @param girlsPartcipationStatus the school's participation status in girls' tournaments.
   * @param boysParticipationStatus the school's participation status in boys' tournaments.
   */
  @Deprecated
  public ISchoolFormData(String schoolName, 
      String streetAddress, String cityName, 
      int zipCode, int enrollmentNumber,
      boolean girlsPartcipationStatus, boolean boysParticipationStatus) {
    this.schoolName = schoolName;
    this.streetAddress = streetAddress;
    this.cityName = cityName;
    this.zipCode = zipCode;
    this.enrollmentNumber = enrollmentNumber;
    this.girlsParticipationStatus = girlsParticipationStatus;
    this.boysParticipationStatus = boysParticipationStatus;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public String getCityName() {
    return cityName;
  }

  public int getZipCode() {
    return zipCode;
  }

  public int getEnrollmentNumber() {
    return enrollmentNumber;
  }

  public boolean getBoysParticipationStatus() {
    return boysParticipationStatus;
  }

  public boolean getGirlsParticipationStatus() {
    return girlsParticipationStatus;
  }
}