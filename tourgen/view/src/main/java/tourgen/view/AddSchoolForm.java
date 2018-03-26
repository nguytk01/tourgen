package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class AddSchoolForm extends JFrame implements Observer{
	
	private JTextField namesField;
	private JTextField AddrField;
	private JTextField EnrollField;
	private JTextField zipField;
	private JCheckBox chBoys;
	private JCheckBox chGirls;
	private JCheckBox chEg;
	
	
	private Object ticket;

	
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
			
	
	
	
	public AddSchoolForm(final IController controller) {
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(29, 52, 46, 14);
		getContentPane().add(lblName);
	
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(29, 77, 46, 14);
		getContentPane().add(lblAddress);
		
		JLabel lblEnrollment = new JLabel("Enrollment:");
		lblEnrollment.setBounds(29, 102, 59, 14);
		getContentPane().add(lblEnrollment);
		
		JLabel lblParticipationStatus = new JLabel("Participation Status:");
		lblParticipationStatus.setBounds(29, 163, 101, 14);
		getContentPane().add(lblParticipationStatus);
		
		JLabel lblZip = new JLabel("ZIP: ");
		lblZip.setBounds(299, 77, 46, 14);
		getContentPane().add(lblZip);
		
		namesField = new JTextField();
		namesField.setBounds(85, 49, 200, 20);
		getContentPane().add(namesField);
		namesField.setColumns(10);
	
		
		AddrField = new JTextField();
		AddrField.setColumns(10);
		AddrField.setBounds(85, 74, 200, 20);
		getContentPane().add(AddrField);
		
		EnrollField = new JTextField();
		EnrollField.setColumns(10);
		EnrollField.setBounds(85, 99, 200, 20);
		getContentPane().add(EnrollField);
		
		JCheckBox chGirls = new JCheckBox("Girls");
		chGirls.setBounds(136, 159, 97, 23);
		getContentPane().add(chGirls);
		
		JCheckBox chBoys = new JCheckBox("Boys");
		chBoys.setBounds(136, 185, 97, 23);
		getContentPane().add(chBoys);
		
		zipField = new JTextField();
		zipField.setBounds(324, 74, 86, 20);
		getContentPane().add(zipField);
		zipField.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				controller.addSchool(arg0);
				
			}
		});
		btnNewButton.setBounds(299, 325, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setBounds(398, 325, 89, 23);
		getContentPane().add(btnNewButton_1);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}

	
	