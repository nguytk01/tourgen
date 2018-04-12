package tourgen.model;

public class SchoolFormMvcData {
  private String streetAddress;
  private String cityName;
  private int zipCode;
  private int enrollmentNumber;
  private boolean girlsParticipationStatus;
  private boolean boysParticipationStatus;
  private String schoolName;
  private Object observerTicket;
  private String schoolDisplayName;
  
  /**
 * Data box to be passed from the view to the model.
 * @param observerTicketArg a ticket for the observer know which message is for itself.
 * @param schoolDisplayName the display name of the school
 * @param schoolName the full name of the school
 * @param streetAddress the street address of the school
 * @param cityName the city name of the school
 * @param zipCode the zip code of the school
 * @param enrollmentNumber the enrollment number
 * @param girlsParticipationStatus the participation in girls' tournament of the school
 * @param boysParticipationStatus the participation in boys' tournament of the school
 */
  
  public SchoolFormMvcData(Object observerTicketArg, String schoolDisplayName, 
      String schoolName, String streetAddress,
      String cityName, int zipCode, int enrollmentNumber, boolean girlsParticipationStatus,
      boolean boysParticipationStatus) {
    this.observerTicket = observerTicketArg;
    this.schoolDisplayName = schoolDisplayName;
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

  public String getSchoolDisplayName() {
    return schoolDisplayName;
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

  public void setEnrollmentNumber(int enrollmentNumber) {
    this.enrollmentNumber = enrollmentNumber;
  }

  public boolean getBoysParticipationStatus() {
    return boysParticipationStatus;
  }

  public boolean getGirlsParticipationStatus() {
    return girlsParticipationStatus;
  }

  public Object getTicket() {
    return observerTicket;
  }
}