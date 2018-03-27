package tourgen.view;

import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;

import tourgen.controller.AddSchoolFormListeners;
import tourgen.controller.AddSchoolUseCaseController;
import tourgen.controller.EditSchoolFormListeners;
import tourgen.controller.EditSchoolUseCaseController;
import tourgen.controller.IController;
import tourgen.controller.RemoveSchoolUseCaseController;
import tourgen.controller.ReportViewListeners;
import tourgen.controller.ReportViewUseCaseController;
import tourgen.controller.SchoolListListeners;
import tourgen.controller.ViewSchoolListUseCaseController;
import tourgen.model.Repository;
import tourgen.model.SchoolManager;
import tourgen.util.IAddSchoolForm;
import tourgen.util.IEditSchoolForm;
import tourgen.util.IReportTableFrame;
import tourgen.util.IReportTableView;
import tourgen.util.IRepositoryView;
import tourgen.util.ISchoolListView;

public class Main {
	public static void main(String[] args) {
		SchoolManager manager = new SchoolManager();
		Repository repo = new Repository();
		
		AddSchoolFormListeners addSchoolFormListeners = new AddSchoolFormListeners();
		ActionListener addListener = addSchoolFormListeners.new AddSchoolListener();
		
		EditSchoolFormListeners editSchoolFormListeners = new EditSchoolFormListeners();
		ActionListener editListener = editSchoolFormListeners.new EditSchoolListener();
		
		SchoolListListeners listListeners = new SchoolListListeners();
		ActionListener listAddButtonListener = listListeners.new AddSchoolListener();
		ActionListener listEditButtonListener = listListeners.new EditSchoolListener();
		ActionListener listRemoveButtonListener = listListeners.new RemoveSchoolListener();
		
		IAddSchoolForm addForm = new AddSchoolForm(addListener);
		IEditSchoolForm editForm = new EditSchoolForm(editListener);
		ISchoolListView schoolList =  new SchoolListView(listAddButtonListener, listEditButtonListener, listRemoveButtonListener,manager);
		
		AddSchoolUseCaseController addUseCaseController = new AddSchoolUseCaseController(manager,schoolList,addForm,addSchoolFormListeners);
		EditSchoolUseCaseController editUseCaseController = new EditSchoolUseCaseController(manager,schoolList,editForm,editSchoolFormListeners);
		RemoveSchoolUseCaseController removeUseCaseController = new RemoveSchoolUseCaseController(schoolList, listListeners, manager);
		listListeners.setAddController(addUseCaseController);
		listListeners.setEditController(editUseCaseController);
		listListeners.setRemoveController(removeUseCaseController);
		
		ReportViewListeners reportViewListeners = new ReportViewListeners();
		ActionListener reportViewManageSchoolButtonListener = reportViewListeners.new ManageSchoolButtonListener();
		ListSelectionListener tournamentSelectionListener = reportViewListeners.new TournamentSelectionListener(); 
		IReportTableFrame reportFrame = new ReportTableFrame(reportViewManageSchoolButtonListener,tournamentSelectionListener);
		IReportTableView reportTableView = reportFrame.returnReportTableView();
		IRepositoryView repositoryView = reportFrame.returnRepositoryView();
		
		ReportViewUseCaseController reportViewUseCaseController = new ReportViewUseCaseController(reportTableView, repositoryView);

		ViewSchoolListUseCaseController viewSchoolListUseCaseController = new ViewSchoolListUseCaseController(schoolList);
		reportViewListeners.setViewSchoolListUseCaseController(viewSchoolListUseCaseController);
		reportFrame.showView();
		//schoolList.showView();
		manager.initSchools();
		schoolList.populate();
		
	}
}
