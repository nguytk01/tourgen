package tourgen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class SchoolListListeners {
	private AddSchoolUseCaseController addController;
	private EditSchoolUseCaseController editController;
	private RemoveSchoolUseCaseController removeController;
	private ViewSchoolListUseCaseController viewSchoolListController;
	public void setAddController(AddSchoolUseCaseController arg) {
		addController = arg;
	}
	
	public void setEditController(EditSchoolUseCaseController arg) {
		editController = arg;
	}
	
	public void setRemoveController(RemoveSchoolUseCaseController arg) {
		removeController = arg;
	}
	
	public void setViewSchoolListController(ViewSchoolListUseCaseController arg){
		viewSchoolListController = arg;
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
	
	public class ShowSchoolListActionsListener implements MenuListener{
		@Override
		public void menuCanceled(MenuEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuDeselected(MenuEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuSelected(MenuEvent arg0) {
			viewSchoolListController.toggleEditButtonBasedOnState();
		}
		
	}
}
