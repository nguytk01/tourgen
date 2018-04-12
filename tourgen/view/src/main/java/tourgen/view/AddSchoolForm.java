package tourgen.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import tourgen.controller.AddSchoolUseCaseController;
import tourgen.controller.IController;
import tourgen.model.IOperationResult;
import tourgen.model.School;
import tourgen.util.IAddSchoolForm;


public class AddSchoolForm extends JFrame implements IAddSchoolForm {

  private JTextField namesField;
  private JTextField addrField;
  private JTextField enrollField;
  private JTextField zipField;
  private JCheckBox chBoys;
  private JCheckBox chGirls;
  private JCheckBox chEg;

  private AddSchoolUseCaseController addSchoolUseCaseController;

  private Object ticket;
  private JTextField displayNameTextField;
  private JTextField cityNameTextField;

  public void setTicket(Object obj) {
    ticket = obj;
  }

  public String getSchoolName() {
    return namesField.getText();
  }

  public String getAddr() {
    return addrField.getText();
  }

  /**
   * Return the enrollment number field of the form.
   * @return an integer
   */
  public int getEnroll() {
    if (enrollField.getText().length() == 0) {
      return 0;
    }
    return Integer.parseInt(enrollField.getText());
  }

  public boolean getHost() {
    return chEg.isSelected();
  }

  public int getZip() {
    return Integer.parseInt(zipField.getText());
  }

  /**
   * Construct an add school form with an ActionListener passed in.
   * @param listener the ActionListener for this form.
   */
  public AddSchoolForm(ActionListener listener) {
    setTitle("Add A School");
    this.setSize(600, 400);
    this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    getContentPane().setLayout(null);

    JLabel lblName = new JLabel("Name");
    lblName.setBounds(29, 44, 82, 20);
    getContentPane().add(lblName);

    JLabel lblAddress = new JLabel("Address");
    lblAddress.setBounds(29, 77, 97, 20);
    getContentPane().add(lblAddress);

    JLabel lblEnrollment = new JLabel("Enrollment");
    lblEnrollment.setBounds(29, 191, 123, 20);
    getContentPane().add(lblEnrollment);

    JLabel lblParticipationStatus = new JLabel("Participation Status");
    lblParticipationStatus.setBounds(29, 228, 203, 35);
    getContentPane().add(lblParticipationStatus);

    JLabel lblZip = new JLabel("ZIP");
    lblZip.setBounds(382, 114, 56, 27);
    getContentPane().add(lblZip);

    namesField = new JTextField();
    namesField.setBounds(240, 46, 264, 26);
    getContentPane().add(namesField);
    namesField.setColumns(10);

    addrField = new JTextField();
    addrField.setColumns(10);
    addrField.setBounds(240, 77, 264, 26);
    getContentPane().add(addrField);

    enrollField = new JTextField();
    enrollField.setColumns(10);
    enrollField.setBounds(240, 191, 264, 26);
    getContentPane().add(enrollField);

    chGirls = new JCheckBox("Girls");
    chGirls.setBounds(240, 234, 97, 23);
    getContentPane().add(chGirls);

    chBoys = new JCheckBox("Boys");
    chBoys.setBounds(341, 234, 97, 23);
    getContentPane().add(chBoys);

    zipField = new JTextField();
    zipField.setBounds(422, 110, 82, 28);
    getContentPane().add(zipField);
    zipField.setColumns(10);

    JCustomizedButton btnNewButton = new JCustomizedButton(this, "OK");
    btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);

    btnNewButton.addActionListener(listener);

    btnNewButton.setBounds(284, 285, 89, 35);
    getContentPane().add(btnNewButton);

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBounds(383, 285, 129, 35);
    getContentPane().add(cancelButton);
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent a) {
        setVisible(false);
      }
    });
    JLabel lblDisplayName = new JLabel("Display name");
    lblDisplayName.setBounds(29, 148, 199, 35);
    getContentPane().add(lblDisplayName);

    displayNameTextField = new JTextField();
    displayNameTextField.setBounds(240, 153, 264, 25);
    getContentPane().add(displayNameTextField);
    displayNameTextField.setColumns(10);

    JLabel lblCity = new JLabel("City");
    lblCity.setBounds(29, 110, 60, 31);
    getContentPane().add(lblCity);

    cityNameTextField = new JTextField();
    cityNameTextField.setBounds(239, 113, 123, 28);
    getContentPane().add(cityNameTextField);
    cityNameTextField.setColumns(10);
  }

  @Override
  public void update(Observable arg0, Object arg1) {
    IOperationResult result = (IOperationResult) arg1;
    if (ticket == result.getTicket()) {
      if (result.isOk()) {
        ticket = null;
        addSchoolUseCaseController.successAddSchool((School) result.getAttachedObject());
      } else {
        addSchoolUseCaseController.failureAddSchool(result.getErrorMessage());
      }
    }
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
    // TODO Auto-generated method stub
    return getZip();
  }

  @Override
  public int getEnrollment() {
    // TODO Auto-generated method stub
    return getEnroll();
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
    this.cleanUp();
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setHidden(boolean flag) {
    setVisible(!flag);
  }

  @Override
  public void cleanUp() {
    namesField.setText("");
    addrField.setText("");
    enrollField.setText("");
    cityNameTextField.setText("");
    zipField.setText("");
    chBoys.setSelected(false);
    chGirls.setSelected(false);
    displayNameTextField.setText("");
  }

  public void setAddSchoolUseCaseController(AddSchoolUseCaseController controllerArg) {
    addSchoolUseCaseController = controllerArg;
  }

  @Override
  public void setAddSchoolUseCaseController(Object controllerArg) {
    this.addSchoolUseCaseController = (AddSchoolUseCaseController) controllerArg;
  }
}