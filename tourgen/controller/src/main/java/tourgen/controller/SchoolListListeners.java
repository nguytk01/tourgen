package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchoolListListeners {
	private AddSchoolUseCaseController addController;
	private EditSchoolUseCaseController editController;
	private RemoveSchoolUseCaseController removeController;
	
	public void setAddController(AddSchoolUseCaseController arg) {
		addController = arg;
	}
	
	public void setEditController(EditSchoolUseCaseController arg) {
		editController = arg;
	}
	
	public void setRemoveController(RemoveSchoolUseCaseController arg) {
		removeController = arg;
	}
	
	public class RemoveSchoolListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		}
		
	}
	
	public class AddSchoolListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			addController.startProcess();
		}
		
	}
	
	public class EditSchoolListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			editController.startProcess();
		}
		
	}
}
