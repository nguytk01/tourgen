package tourgen.util;

public interface ISchoolListView {
	public void addSchoolToList(Object a);
	public void removeSchoolFromList(Object a);
	public void showView();
	public void populate();
	public Object getSelectedSchool();
}
