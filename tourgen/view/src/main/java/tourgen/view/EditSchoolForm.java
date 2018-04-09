package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tourgen.controller.EditSchoolUseCaseController;
import tourgen.controller.IController;
import tourgen.model.IOperationResult;
import tourgen.model.School;
import tourgen.util.IEditSchoolForm;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class EditSchoolForm extends JFrame implements IEditSchoolForm {

  private JTextField namesField;
  private JTextField addrField;
  private JTextField enrollField;
  private JTextField zipField;
  private JCheckBox chBoys;
  private JCheckBox chGirls;
  private JCheckBox chEg;
  private Object ticket;
  private JTextField cityNameTextField;
  private JTextField displayNameTextField;
  private EditSchoolUseCaseController editSchoolUseCaseController;

  public EditSchoolForm(ActionListener listener) {
    setTitle("Edit A School");

    setSize(600, 400);
    setDefaultCloseOperation(HIDE_ON_CLOSE);

    getContentPane().setLayout(null);

    JLabel lblName = new JLabel("Name:");
    lblName.setBounds(29, 25, 136, 33);
    getContentPane().add(lblName);

    JLabel lblAddress = new JLabel("Address:");
    lblAddress.setBounds(29, 77, 136, 23);
    getContentPane().add(lblAddress);

    JLabel lblEnrollment = new JLabel("Enrollment:");
    lblEnrollment.setBounds(29, 205, 175, 36);
    getContentPane().add(lblEnrollment);

    JLabel lblParticipationStatus = new JLabel("Participation Status:");
    lblParticipationStatus.setBounds(29, 243, 219, 36);
    getContentPane().add(lblParticipationStatus);

    JLabel lblZip = new JLabel("ZIP: ");
    lblZip.setBounds(393, 164, 79, 28);
    getContentPane().add(lblZip);

    namesField = new JTextField();
    namesField.setEnabled(false);
    namesField.setBounds(206, 25, 347, 33);
    getContentPane().add(namesField);
    namesField.setColumns(10);

    addrField = new JTextField();
    addrField.setEnabled(false);
    addrField.setColumns(10);
    addrField.setBounds(206, 72, 347, 33);
    getContentPane().add(addrField);

    enrollField = new JTextField();
    enrollField.setColumns(10);
    enrollField.setBounds(206, 205, 347, 36);
    getContentPane().add(enrollField);

    chGirls = new JCheckBox("Girls");
    chGirls.setBounds(256, 250, 97, 23);
    getContentPane().add(chGirls);

    chBoys = new JCheckBox("Boys");
    chBoys.setBounds(383, 250, 97, 23);
    getContentPane().add(chBoys);

    zipField = new JTextField();
    zipField.setBounds(468, 164, 85, 28);
    zipField.setEnabled(false);
    getContentPane().add(zipField);
    zipField.setColumns(10);

    JCustomizedButton okButton = new JCustomizedButton(this, "OK");
    okButton.setHorizontalAlignment(SwingConstants.CENTER);
    okButton.addActionListener(listener);
    okButton.setBounds(245, 292, 101, 36);
    getContentPane().add(okButton);

    JButton cancelBtn = new JButton("Cancel");
    cancelBtn.setBounds(378, 292, 131, 36);
    getContentPane().add(cancelBtn);
    cancelBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent a) {
        setVisible(false);
      }
    });

    JLabel lblCity = new JLabel("City");
    lblCity.setBounds(29, 164, 79, 28);
    getContentPane().add(lblCity);

    cityNameTextField = new JTextField();
    cityNameTextField.setBounds(206, 164, 131, 30);
    cityNameTextField.setEnabled(false);
    getContentPane().add(cityNameTextField);
    cityNameTextField.setColumns(10);

    JLabel lblDisplayName = new JLabel("Display Name");
    lblDisplayName.setBounds(29, 118, 163, 33);
    getContentPane().add(lblDisplayName);

    displayNameTextField = new JTextField();
    displayNameTextField.setEditable(false);
    displayNameTextField.setBounds(204, 118, 349, 33);
    getContentPane().add(displayNameTextField);
    displayNameTextField.setColumns(10);
  }

  @Override
  public void update(Observable o, Object arg) {
    IOperationResult result = (IOperationResult) arg;
    if (result.getTicket() == ticket) {
      if (result.isOk()) {
        ticket = null;
        editSchoolUseCaseController.successEditSchool((School) result.getAttachedObject());
      } else {
        editSchoolUseCaseController.failureEditSchool(result.getErrorMessage());
      }
    }
  }

  public String getSchoolName() {
    return namesField.getText();
  }

  @Override
  public String getDisplayName() {
    return displayNameTextField.getText();
  }

  @Override
  public String getCityName() {
    return cityNameTextField.getText();
  }

  @Override
  public int getZipCode() {
    return Integer.parseInt(zipField.getText());
  }

  @Override
  public int getEnrollment() {
    return Integer.parseInt(enrollField.getText());
  }

  @Override
  public boolean getBoysStatus() {
    return chBoys.isSelected();
  }

  @Override
  public boolean getGirlsStatus() {
    return chGirls.isSelected();
  }

  @Override
  public void showView() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setHidden(boolean flag) {
    this.setVisible(!flag);

  }

  @Override
  public String getAddr() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setTicket(Object ticketArg) {
    ticket = ticketArg;

  }

  @Override
  public void cleanUp() {
    // TODO Auto-generated method stub

  }

  @Override
  public void display(Object schoolArg) {
    School school = (School) schoolArg;
    displayNameTextField.setText(school.getDisplayName());
    addrField.setText(school.getStreetAddress());
    namesField.setText(school.getName());
    cityNameTextField.setText(school.getCityName());
    zipField.setText(new Integer(school.getZipCode()).toString());
    enrollField.setText(new Integer(school.getEnroll()).toString());
    chBoys.setSelected(school.getBStatus());
    chGirls.setSelected(school.getGStatus());
  }

  @Override
  public void showErrorMesage(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage, "error", JOptionPane.ERROR_MESSAGE);

  }

  @Override
  public void setAddSchoolUseCaseController(Object controllerArg) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setEditSchoolUseCaseController(Object controllerArg) {
    editSchoolUseCaseController = (EditSchoolUseCaseController) controllerArg;

  }
}