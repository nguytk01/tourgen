package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tourgen.util.ICheckBoxTreeCustomCheckBox;
import tourgen.util.ICheckNode;

public class CheckBoxTreeCustomCheckBoxListener implements ActionListener{

	private ICheckBoxTreeCustomCheckBox checkBox;
	private MapController controller;
	public CheckBoxTreeCustomCheckBoxListener(MapController controllerArg) {
		this.controller = controllerArg;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("event received.");
		controller.treeCheckBoxClicked();
	}

}
