package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tourgen.controller.IController;
import tourgen.model.School;
import tourgen.util.IEditSchoolForm;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class EditSchoolForm extends JFrame implements Observer, IEditSchoolForm{
	
	private JTextField namesField;
	private JTextField AddrField;
	private JTextField EnrollField;
	private JTextField zipField;
	private JCheckBox chBoys;
	private JCheckBox chGirls;
	private JCheckBox chEg;
	private IController controller;
	private Object ticket;
	private JTextField cityNameTextField;
	private JTextField displayNameTextField;
	
	public void setTicket(Object obj)
	{
		ticket = obj;
	}

	public String getName()
	{
		return namesField.getText();
	}
	
	public String getAddr()
	{
		return AddrField.getText();
	}
	
	public int getEnroll()
	{
		return Integer.parseInt(EnrollField.getText());
	}
	
	public boolean getBoyStatus()
	{
		return chBoys.isSelected();
	}
	
	public boolean getGirlStatus()
	{
		return chGirls.isSelected();
	}
	
	public boolean getHost()
	{
		return chEg.isSelected();
	}
	
	public int getZip()
	{
		return Integer.parseInt(zipField.getText());
	}
			
	
	public void displaySchool(School school)
	{
		namesField.setText(school.getName());
		AddrField.setText(school.getStreetAddress());
		EnrollField.setText(new Integer(school.getEnroll()).toString());
		chBoys.setSelected(school.getBStatus());
		chGirls.setSelected(school.getGStatus());
		
		
	}
	
	public EditSchoolForm(IController controllerArg) {
		setTitle("Edit A School");
		
		setSize(400,400);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		controller = controllerArg;
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(29, 52, 46, 14);
		getContentPane().add(lblName);
	
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(29, 77, 63, 14);
		getContentPane().add(lblAddress);
		
		JLabel lblEnrollment = new JLabel("Enrollment:");
		lblEnrollment.setBounds(29, 166, 79, 14);
		getContentPane().add(lblEnrollment);
		
		JLabel lblParticipationStatus = new JLabel("Participation Status:");
		lblParticipationStatus.setBounds(30, 184, 126, 14);
		getContentPane().add(lblParticipationStatus);
		
		JLabel lblZip = new JLabel("ZIP: ");
		lblZip.setBounds(224, 132, 46, 14);
		getContentPane().add(lblZip);
		
		namesField = new JTextField();
		namesField.setEnabled(false);
		namesField.setBounds(120, 46, 200, 20);
		getContentPane().add(namesField);
		namesField.setColumns(10);
		
		
		AddrField = new JTextField();
		AddrField.setEnabled(false);
		AddrField.setColumns(10);
		AddrField.setBounds(120, 71, 200, 20);
		getContentPane().add(AddrField);
		
		EnrollField = new JTextField();
		EnrollField.setColumns(10);
		EnrollField.setBounds(120, 163, 200, 20);
		getContentPane().add(EnrollField);
		
		JCheckBox chGirls = new JCheckBox("Girls");
		chGirls.setBounds(173, 186, 97, 23);
		getContentPane().add(chGirls);
		
		JCheckBox chBoys = new JCheckBox("Boys");
		chBoys.setBounds(173, 212, 97, 23);
		getContentPane().add(chBoys);
		
		zipField = new JTextField();
		zipField.setBounds(249, 129, 71, 20);
		getContentPane().add(zipField);
		zipField.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		MyListener listener = new MyListener();
		btnNewButton.addActionListener(listener);
		btnNewButton.setBounds(299, 325, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setBounds(398, 325, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(29, 134, 56, 16);
		getContentPane().add(lblCity);
		
		cityNameTextField = new JTextField();
		cityNameTextField.setBounds(120, 128, 101, 22);
		getContentPane().add(cityNameTextField);
		cityNameTextField.setColumns(10);
		
		JLabel lblDisplayName = new JLabel("Display Name");
		lblDisplayName.setBounds(29, 104, 79, 16);
		getContentPane().add(lblDisplayName);
		
		displayNameTextField = new JTextField();
		displayNameTextField.setBounds(119, 100, 116, 22);
		getContentPane().add(displayNameTextField);
		displayNameTextField.setColumns(10);
	}

	public class MyListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			controller.editSchool(arg0);
		}
	};
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
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
		return getZip();
	}

	@Override
	public int getEnrollment() {
		return getEnroll();
	}

	@Override
	public boolean getBoysStatus() {
		return getBoyStatus();
	}

	@Override
	public boolean getGirlsStatus() {
		return getGirlStatus();
	}

	@Override
	public void showView() {
		this.setVisible(true);	
	}
}