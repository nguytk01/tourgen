package tourgen.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import tourgen.util.ICheckBoxTreeCustomCheckBox;

public class CheckBoxTreeCustomCheckBox extends JCheckBox implements ICheckBoxTreeCustomCheckBox{
	private Object checkNode;
	public void setCheckNode(Object arg) {
		checkNode = arg;
	}
	

}
