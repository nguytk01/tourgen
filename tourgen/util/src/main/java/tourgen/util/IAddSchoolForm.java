package tourgen.util;

import java.util.Observer;

public interface IAddSchoolForm extends Observer{
	public String getName();
	public String getDisplayName();
	public String getAddr();
	public String getCityName();
	public int getZipCode();
	public int getEnrollment();
	public boolean getBoysStatus();
	public boolean getGirlsStatus();
	
	public void showView();
	public void showErrorMessage();
	public void setHidden(boolean flag);
	public void cleanUp();
	
	
	public void setTicket(Object ticket);
}