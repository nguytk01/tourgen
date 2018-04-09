package tourgen.controller;

import java.awt.event.ActionEvent;

import tourgen.model.School;
import tourgen.util.IAddSchoolForm;
import tourgen.util.IEditSchoolForm;
import tourgen.util.IReportTableView;
import tourgen.util.ISchoolListView;

public interface IController {
  /* these call the model */
  public void addSchool(ActionEvent a);

  public void editSchool(ActionEvent a);

  /*
   * these receive the events from the school List View and activate the add or
   * edit dialog
   */
  public void listAddEvent(ActionEvent a);

  public void listEditEvent(ActionEvent a, Object school);

  public void listRemoveEvent(ActionEvent e, Object school);
  // public void removeSchoolEvent(ActionEvent a, Object school);

  public void showReportEvent();

  public void setAddSchoolForm(IAddSchoolForm form);

  public void setEditSchoolForm(IEditSchoolForm form);

  public void setSchoolListView(ISchoolListView view);

  public void setReportTableView(IReportTableView view);

}