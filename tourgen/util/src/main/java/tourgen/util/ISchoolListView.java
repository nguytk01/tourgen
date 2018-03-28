package tourgen.util;

import java.util.Observer;

public interface ISchoolListView extends Observer{
	public void addSchoolToList(Object a);
	public void removeSchoolFromList(Object a);
	public void showView();
	public void populate();
	public Object getSelectedSchool();
	public void toggleEditButtonBasedOnState();
	public void toggleRemoveButtonBasedOnState();
}
