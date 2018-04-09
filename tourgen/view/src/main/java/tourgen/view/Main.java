package tourgen.view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuListener;
import javax.swing.plaf.FontUIResource;

import tourgen.controller.AddSchoolFormListeners;
import tourgen.controller.AddSchoolUseCaseController;
import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.controller.CheckBoxTreeListener;
import tourgen.controller.EditSchoolFormListeners;
import tourgen.controller.EditSchoolUseCaseController;
import tourgen.controller.IController;
import tourgen.controller.MapController;
import tourgen.controller.RemoveSchoolUseCaseController;
import tourgen.controller.ReportViewListeners;
import tourgen.controller.ReportViewUseCaseController;
import tourgen.controller.SchoolListListeners;
import tourgen.controller.ViewSchoolListUseCaseController;
import tourgen.model.Repository;
import tourgen.model.RepositoryInitialization;
import tourgen.model.SchoolManager;
import tourgen.util.IAddSchoolForm;
import tourgen.util.IEditSchoolForm;
import tourgen.util.IReportTableFrame;
import tourgen.util.IReportTableView;
import tourgen.util.IRepositoryView;
import tourgen.util.ISchoolListView;

import tourgen.model.RepositoryIoManager;

import javax.swing.JFrame;

public class Main {

  private static MapDriver mainMap;// = new MapDriver();

  /**
   * Main entrance of the program.
   * @param args command line arguments of the program.
   */
  public static void main(String[] args) {
    FontUIResource resource = new FontUIResource(new Font("Tahoma", Font.PLAIN, 24));
    setUiFont(resource);
    Repository repo = null;
    SchoolManager manager = null; // new SchoolManager(repo);
    manager = RepositoryIoManager.loadEverythingUp();
    if (manager == null) {
      repo = Repository.getInstance();
      manager = new SchoolManager(repo);
      manager.initSchools();
      RepositoryInitialization.init(repo, manager);
    } else {
      repo = manager.getRepository();
    }

    AddSchoolFormListeners addSchoolFormListeners = new AddSchoolFormListeners();
    ActionListener addListener = addSchoolFormListeners.new AddSchoolListener();

    EditSchoolFormListeners editSchoolFormListeners = new EditSchoolFormListeners();
    ActionListener editListener = editSchoolFormListeners.new EditSchoolListener();

    SchoolListListeners listListeners = new SchoolListListeners();
    ActionListener listAddButtonListener = listListeners.new AddSchoolListener();
    ActionListener listEditButtonListener = listListeners.new EditSchoolListener();
    ActionListener listRemoveButtonListener = listListeners.new RemoveSchoolListener();
    MenuListener listShowSchoolListActions = listListeners.new ShowSchoolListActionsListener();
    IAddSchoolForm addForm = new AddSchoolForm(addListener);
    IEditSchoolForm editForm = new EditSchoolForm(editListener);
    ISchoolListView schoolList = new SchoolListView(listAddButtonListener, listEditButtonListener,
        listRemoveButtonListener, listShowSchoolListActions, manager);

    AddSchoolUseCaseController addUseCaseController = 
        new AddSchoolUseCaseController(manager, schoolList, addForm,
        addSchoolFormListeners);
    EditSchoolUseCaseController editUseCaseController = 
        new EditSchoolUseCaseController(manager, schoolList, editForm,
        editSchoolFormListeners);
    RemoveSchoolUseCaseController removeUseCaseController = 
        new RemoveSchoolUseCaseController(schoolList, listListeners,
        manager);
    listListeners.setAddController(addUseCaseController);
    listListeners.setEditController(editUseCaseController);
    listListeners.setRemoveController(removeUseCaseController);
    schoolList.setRemoveUseCaseController(removeUseCaseController);

    ReportViewListeners reportViewListeners = new ReportViewListeners();
    ActionListener reportViewManageSchoolButtonListener = 
        reportViewListeners.new ManageSchoolButtonListener();
    ListSelectionListener tournamentSelectionListener = 
        reportViewListeners.new TournamentSelectionListener();

    IReportTableFrame reportFrame = new ReportTableFrame(reportViewManageSchoolButtonListener,
        tournamentSelectionListener, repo);
    IReportTableView reportTableView = reportFrame.returnReportTableView();
    IRepositoryView repositoryView = reportFrame.returnRepositoryView();

    ReportViewUseCaseController reportViewUseCaseController = 
        new ReportViewUseCaseController(reportTableView,
        repositoryView);
    reportViewListeners.setCoordinator(reportViewUseCaseController);
    ViewSchoolListUseCaseController viewSchoolListUseCaseController = 
        new ViewSchoolListUseCaseController(schoolList);
    listListeners.setViewSchoolListController(viewSchoolListUseCaseController);
    reportViewListeners.setViewSchoolListUseCaseController(viewSchoolListUseCaseController);

    // manager.initSchools();
    // schoolList.populate();

    // RepositoryInitialization init = new RepositoryInitialization();
    // RepositoryInitialization.init(repo, manager);
    repositoryView.populate();

    reportFrame.showView();

    manager.addObserver(editForm);
    manager.addObserver(addForm);
    manager.addObserver(schoolList);

    MapDriver mainMap = new MapDriver(repo);
    MapController mapController2 = new MapController(null, mainMap);
    CheckBoxTreeCustomCheckBoxListener checkBoxListener2 = 
        new CheckBoxTreeCustomCheckBoxListener(mapController2);
    mainMap.setCon(checkBoxListener2, mapController2);
    mainMap.initGui();

    /* test GMapPinButton resources in Maven case */
    // JFrame myframe = new JFrame();
    // myframe.setBounds(50,50,300,300);
    // myframe.add(new GMapPinButton("hey"));
    // myframe.setVisible(true);
  }

  /**
   * Set UIFont for the entire running instance.
   * @param f the fontUIResource used for the entire running instance
   */
  public static void setUiFont(javax.swing.plaf.FontUIResource f) {
    UIDefaults uiDef = UIManager.getLookAndFeelDefaults();
    uiDef.put("Menu.font", f);
    UIManager.put("Menu.font", f);
    uiDef.put("MenuItem.font", f);
    UIManager.put("MenuItem.font", f);
    uiDef.put("TextPane.font", f);
    UIManager.put("TextPane.font", f);
    uiDef.put("defaultFont", 20);
    java.util.Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof javax.swing.plaf.FontUIResource) {
        uiDef.put(key, f);
      }
    }
  }
}
