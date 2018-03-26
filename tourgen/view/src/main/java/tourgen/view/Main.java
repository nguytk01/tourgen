package tourgen.view;

import tourgen.controller.IController;
import tourgen.controller.TourGenController;
import tourgen.model.Repository;
import tourgen.model.SchoolManager;
import tourgen.util.IAddSchoolForm;
import tourgen.util.IEditSchoolForm;
import tourgen.util.IReportTableView;
import tourgen.util.ISchoolListView;

public class Main {
	public static void main(String[] args) {
		SchoolManager manager = new SchoolManager();
		Repository repo = new Repository();
		
		IController controller = new TourGenController(repo, manager);
		
		IAddSchoolForm addForm = new AddSchoolForm(controller);
		IEditSchoolForm editForm = new EditSchoolForm(controller);
		ISchoolListView listView = new SchoolListView(controller);
		IReportTableView tableView = new ReportTableView();
		
		controller.setAddSchoolForm(addForm);
		controller.setEditSchoolForm(editForm);
		controller.setReportTableView(tableView);
		controller.setSchoolListView(listView);
		listView.showView();
	}
}
