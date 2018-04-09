package tourgen.util;

import java.util.Observer;

public interface IAddSchoolForm extends Observer {
  public String getSchoolName();

  public String getDisplayName();

  public String getAddr();

  public String getCityName();

  public int getZipCode();

  public int getEnrollment();

  public boolean getBoysStatus();

  public boolean getGirlsStatus();

  public void showView();

  public void showErrorMessage(String errorMessage);

  public void setHidden(boolean flag);

  public void cleanUp();

  public void setAddSchoolUseCaseController(Object controllerArg);

  public void setTicket(Object ticket);
}