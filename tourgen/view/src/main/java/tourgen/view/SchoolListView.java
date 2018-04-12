package tourgen.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.event.MenuListener;

import tourgen.controller.RemoveSchoolUseCaseController;
import tourgen.model.IOperationResult;
import tourgen.model.School;
import tourgen.model.SchoolManager;
import tourgen.util.ISchoolListView;

public class SchoolListView extends JFrame implements ISchoolListView, Observer {

  private Vector<School> schoolVector;
  private JList swingJList;
  private SchoolManager manager;
  private JScrollPane scrollPane;
  private JMenuItem mntmEdit;
  private JMenuItem mntmRemove;
  private JCustomizedButton dummyCustomizedRemoveButton;
  private Object ticket;
  private RemoveSchoolUseCaseController removeSchoolUseCaseController;

  /**
   * Build the schools ListView.
   * @param listAddListener a listener listening to action of adding a school 
   * @param listEditListener a listener listening to action of editing a school 
   * @param listRemoveListener a listener listening to action of removing a school 
   * @param showSchoolListActionsListener a listener listening to action of showing a school 
   * @param managerArg a school Manager arg.
   */
  public SchoolListView(ActionListener listAddListener, ActionListener listEditListener,
      ActionListener listRemoveListener, 
      MenuListener showSchoolListActionsListener, SchoolManager managerArg) {
    manager = managerArg;
    setTitle("School List");
    this.setBounds(300, 100, 400, 700);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    dummyCustomizedRemoveButton = new JCustomizedButton(this, "Dummy");
    dummyCustomizedRemoveButton.addActionListener(listRemoveListener);

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu mnSchool = new JMenu("School");
    menuBar.add(mnSchool);
    mnSchool.addMenuListener(showSchoolListActionsListener);

    JMenuItem mntmAdd = new JMenuItem("Add");
    mnSchool.add(mntmAdd);
    mntmAdd.addActionListener(listAddListener);

    mntmEdit = new JMenuItem("Edit");
    mnSchool.add(mntmEdit);
    mntmEdit.addActionListener(listEditListener);

    mntmRemove = new JMenuItem("Remove");
    mnSchool.add(mntmRemove);
    mntmRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent a) {
        dummyCustomizedRemoveButton.doClick();
      }
    });

    getContentPane().setLayout(null);

    schoolVector = new Vector<School>();
    swingJList = new JList(schoolVector);
    swingJList.setBounds(61, 35, 379, 330);
    scrollPane = new JScrollPane();
    scrollPane.setViewportView(swingJList);
    getContentPane().setLayout(new BorderLayout());
    swingJList.setVisibleRowCount(10);
    getContentPane().add(scrollPane);

  }

  public void setRemoveUseCaseController(Object controllerArg) {
    removeSchoolUseCaseController = (RemoveSchoolUseCaseController) controllerArg;
  }

  @Override
  public void addSchoolToList(Object a) {
    School school = (School) a;
    Vector<School> newVector = new Vector<School>();
    newVector.add(school);
    newVector.addAll(schoolVector);
    schoolVector = newVector;
    swingJList.setListData(newVector);
    // repaint();
  }

  @Override
  public void remove(Object a) {
    School school = (School) a;
    for (int i = 0; i < schoolVector.size(); i++) {
      if (school.getName().equals(schoolVector.get(i).getName())) {
        schoolVector.remove(i);
        repaint();
        break;

      }
    }

    // schoolVector.remove(school);
  }

  @Override
  public void showView() {
    this.setVisible(true);
  }

  public void populate(List<School> list) {
    schoolVector.addAll(list);
  }

  @Override
  public void populate() {
    List<School> list = manager.getSchoolList();
    schoolVector.clear();
    schoolVector.addAll(list);
  }

  @Override
  public Object getSelectedSchool() {
    if (swingJList.getSelectedIndex() != -1) {
      return schoolVector.get(swingJList.getSelectedIndex());
    } else {
      return null;
    }

  }

  @Override
  public void toggleEditButtonBasedOnState() {
    if (swingJList.getSelectedIndex() == -1) {
      mntmEdit.setEnabled(false);
    } else {
      mntmEdit.setEnabled(true);
    }
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    IOperationResult result = (IOperationResult) arg1;
    if (ticket == result.getTicket()) {
      if (result.isOk()) {
        ticket = null;
        removeSchoolUseCaseController.successRemoveSchool((School) result.getAttachedObject());
      } else {
        removeSchoolUseCaseController.failureRemoveSchool(result.getErrorMessage());
      }
    }
  }

  @Override
  public void toggleRemoveButtonBasedOnState() {
    if (swingJList.getSelectedIndex() == -1) {
      mntmRemove.setEnabled(false);
    } else {
      mntmRemove.setEnabled(true);
    }

  }

  public void setTicket(Object ticketArg) {
    ticket = ticketArg;
  }

  public void showErrorMessage(String errorMessage) {

  }
}
