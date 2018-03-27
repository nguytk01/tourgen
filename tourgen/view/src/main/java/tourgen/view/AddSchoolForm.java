package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tourgen.controller.IController;
import tourgen.util.IAddSchoolForm;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class AddSchoolForm extends JFrame implements Observer, IAddSchoolForm{
	
	private JTextField namesField;
	private JTextField AddrField;
	private JTextField EnrollField;
	private JTextField zipField;
	private JCheckBox chBoys;
	private JCheckBox chGirls;
	private JCheckBox chEg;
	
	private IController controller;
	
	private Object ticket;
	private JTextField displayNameTextField;
	private JTextField cityNameTextField;

	
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
	
	
	
	public boolean getHost()
	{
		return chEg.isSelected();
	}
	
	public int getZip()
	{
		return Integer.parseInt(zipField.getText());
	}
			
	
	
	public AddSchoolForm(ActionListener listener) {
		setTitle("Add A School");
		this.setSize(480,323);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(29, 52, 46, 14);
		getContentPane().add(lblName);
	
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(29, 77, 60, 14);
		getContentPane().add(lblAddress);
		
		JLabel lblEnrollment = new JLabel("Enrollment:");
		lblEnrollment.setBounds(30, 171, 82, 14);
		getContentPane().add(lblEnrollment);
		
		JLabel lblParticipationStatus = new JLabel("Participation Status:");
		lblParticipationStatus.setBounds(30, 201, 123, 14);
		getContentPane().add(lblParticipationStatus);
		
		JLabel lblZip = new JLabel("ZIP: ");
		lblZip.setBounds(282, 104, 46, 14);
		getContentPane().add(lblZip);
		
		namesField = new JTextField();
		namesField.setBounds(124, 47, 264, 20);
		getContentPane().add(namesField);
		namesField.setColumns(10);
	
		
		AddrField = new JTextField();
		AddrField.setColumns(10);
		AddrField.setBounds(124, 72, 264, 20);
		getContentPane().add(AddrField);
		
		EnrollField = new JTextField();
		EnrollField.setColumns(10);
		EnrollField.setBounds(124, 168, 264, 20);
		getContentPane().add(EnrollField);
		
		JCheckBox chGirls = new JCheckBox("Girls");
		chGirls.setBounds(159, 197, 97, 23);
		getContentPane().add(chGirls);
		
		JCheckBox chBoys = new JCheckBox("Boys");
		chBoys.setBounds(159, 226, 97, 23);
		getContentPane().add(chBoys);
		
		zipField = new JTextField();
		zipField.setBounds(307, 101, 81, 20);
		getContentPane().add(zipField);
		zipField.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		
		btnNewButton.addActionListener(listener);

		btnNewButton.setBounds(243, 250, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setBounds(342, 250, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblDisplayName = new JLabel("Display name");
		lblDisplayName.setBounds(30, 142, 82, 16);
		getContentPane().add(lblDisplayName);
		
		displayNameTextField = new JTextField();
		displayNameTextField.setBounds(124, 133, 264, 22);
		getContentPane().add(displayNameTextField);
		displayNameTextField.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(29, 106, 56, 16);
		getContentPane().add(lblCity);
		
		cityNameTextField = new JTextField();
		cityNameTextField.setBounds(125, 101, 106, 22);
		getContentPane().add(cityNameTextField);
		cityNameTextField.setColumns(10);
	}

		
	@Override
	public void update(Observable arg0, Object arg1) {
		
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
	public boolean getBoysStatus(){
		return chBoys.isSelected();
	}
	@Override
	public boolean getGirlsStatus()
	{
		return chGirls.isSelected();
	}

	@Override
	public void showView() {
		
		this.setVisible(true);
		
	}

	@Override
	public void showErrorMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHidden(boolean flag) {
		setVisible(flag);
		
	}

	@Override
	public void cleanUp() {
		namesField.setText("");
		EnrollField.setText("");
		cityNameTextField.setText("");
		zipField.setText("");
		chBoys.setSelected(false);
		chGirls.setSelected(false);
		displayNameTextField.setText("");
	}
}


	