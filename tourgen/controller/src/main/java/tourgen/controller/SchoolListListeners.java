package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import tourgen.model.School;
import tourgen.model.SchoolFormMvcData;
import tourgen.util.ICustomizedButton;
import tourgen.util.ISchoolListView;

public class SchoolListListeners {
  private AddSchoolUseCaseController addController;
  private EditSchoolUseCaseController editController;
  private RemoveSchoolUseCaseController removeController;
  private ViewSchoolListUseCaseController viewSchoolListController;

  public void setAddController(AddSchoolUseCaseController arg) {
    addController = arg;
  }

  public void setEditController(EditSchoolUseCaseController arg) {
    editController = arg;
  }

  public void setRemoveController(RemoveSchoolUseCaseController arg) {
    removeController = arg;
  }

  public void setViewSchoolListController(ViewSchoolListUseCaseController arg) {
    viewSchoolListController = arg;
  }

  public class RemoveSchoolListener implements ActionListener {
    
    /**
     * This method is called by an action of removing school from the list. 
     */
    
    public void actionPerformed(ActionEvent arg0) {
      String name;
      String displayName;
      String cityName;
      int enroll;
      boolean boyStatus;
      boolean girlStatus;
      boolean eligible;
      String streetAddr;
      int zipCode;

      if (arg0.getSource() instanceof ICustomizedButton
          && ((ICustomizedButton) arg0.getSource()).getSwingParent() instanceof ISchoolListView) {
        ISchoolListView schoolListView = 
            (ISchoolListView) (((ICustomizedButton) arg0.getSource()).getSwingParent());
        School school = (School) schoolListView.getSelectedSchool();

        name = school.getName();
        streetAddr = school.getStreetAddress();
        zipCode = school.getZipCode();
        cityName = school.getCityName();

        Object ticket = new Object();
        SchoolFormMvcData formData = 
            new SchoolFormMvcData(ticket, "", name, streetAddr, cityName, zipCode, 0, false,
            false);
        schoolListView.setTicket(ticket);

        removeController.removeSchool(formData);
      }

    }

  }

  public class AddSchoolListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      addController.startProcess();
    }

  }

  public class EditSchoolListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      editController.startProcess();
    }

  }

  public class ShowSchoolListActionsListener implements MenuListener {
    @Override
    public void menuCanceled(MenuEvent arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void menuDeselected(MenuEvent arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void menuSelected(MenuEvent arg0) {
      viewSchoolListController.toggleEditButtonBasedOnState();
      viewSchoolListController.toggleRemoveButtonBasedOnState();
    }

  }
}
