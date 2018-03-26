package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.JComboBox;

public class ReportTableView extends JFrame{
	public ReportTableView() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IHSAA CROSS COUNTRY TOURNAMENT");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("BankGothic Md BT", Font.BOLD, 25));
		lblNewLabel.setBounds(47, 11, 659, 27);
		getContentPane().add(lblNewLabel);
		
		JTree tree = new JTree();
		tree.setBounds(0, 66, 177, 360);
		getContentPane().add(tree);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Sectionals");
		comboBox.setBounds(252, 125, 247, 27);
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(252, 150, 247, 27);
		getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(252, 175, 247, 27);
		getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(252, 201, 247, 27);
		getContentPane().add(comboBox_3);
	}
}

	
	