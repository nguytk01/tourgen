package tourgen.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuListener;
import javax.swing.plaf.FontUIResource;

import tourgen.controller.AddSchoolFormListeners;
import tourgen.controller.AddSchoolUseCaseController;
import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.controller.EditSchoolFormListeners;
import tourgen.controller.EditSchoolUseCaseController;
import tourgen.controller.MapController;
import tourgen.controller.NewMainViewController;
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

public class Main {

  private static MapDriver mainMap;// = new MapDriver();

  /**
   * Main entrance of the program.
   * @param args command line arguments of the program.
   */
  public static void main(String[] args) {
	          
    FontUIResource resource = new FontUIResource(new Font("Tahoma", Font.PLAIN, 16));
    setUiFont(resource);
    SchoolManager manager = null; // new SchoolManager(repo);
    manager = tourgen.model.IoManager.loadEverythingUp();
    if (manager == null) {
      manager = new SchoolManager();
      manager.initSchools();
      RepositoryInitialization.init("tournamentData.txt", Repository.getInstance1(), manager);
    }

    /*AddSchoolFormListeners is just a container for the listeners. It does not have any other meanings.*/
    AddSchoolFormListeners addSchoolFormListeners = new AddSchoolFormListeners();
    ActionListener addListener = addSchoolFormListeners.new AddSchoolListener();

    /*EditSchoolFormListeners is just a container for the listeners. It does not have any other meanings.*/
    EditSchoolFormListeners editSchoolFormListeners = new EditSchoolFormListeners();
    ActionListener editListener = editSchoolFormListeners.new EditSchoolListener();

    /* SchoolListListeners is a logic container for the listeners that listen to events related to the SchoolList */
    /* in order to create the listeners, I have to create an instance of the container. Hence, pay attention to the 
     * child listeners only.
     */
    SchoolListListeners listListeners = new SchoolListListeners();
    ActionListener listAddButtonListener = listListeners.new AddSchoolListener();
    ActionListener listEditButtonListener = listListeners.new EditSchoolListener();
    ActionListener listRemoveButtonListener = listListeners.new RemoveSchoolListener();
    MenuListener listShowSchoolListActions = listListeners.new ShowSchoolListActionsListener();

    /* the following 3 classes represent 2 forms and 1 view */
    /* schoolListView depends on 4 listeners (add, remove, edit, show) and schoolManager */
    /* the following code plugs 4 listeners into the schoolListView.*/
    /* the listeners do not know anything about the caller, or the view. The view just calls them.*/
    IAddSchoolForm addForm = new AddSchoolForm(addListener);
    IEditSchoolForm editForm = new EditSchoolForm(editListener);
    ISchoolListView schoolList = new SchoolListView(listAddButtonListener, listEditButtonListener,
        listRemoveButtonListener, listShowSchoolListActions, manager);

    /* the following creates 3 controller for adding a school, editing a school and remove a school */
    /* each controller needs a handle to the schoolListView, the form it is working with and the listeners */
    /* User's actions > listener's actionPerformed > controller > model */
    /* changes in the model > view classes' update methods -> ask controllers for what to do */
    /* > controller tells the view  classes what to do  > calls the corresponding view's method */
    AddSchoolUseCaseController addUseCaseController = 
        new AddSchoolUseCaseController(manager, schoolList, addForm,
        addSchoolFormListeners);
    EditSchoolUseCaseController editUseCaseController = 
        new EditSchoolUseCaseController(manager, schoolList, editForm,
        editSchoolFormListeners);
    RemoveSchoolUseCaseController removeUseCaseController = 
        new RemoveSchoolUseCaseController(schoolList, listListeners,
        manager);
        
    /* the following set the controller for the listeners. The listeners will use method calls to call the controller*/
    /* the flow is : View -> Listener -> Controller. */
    listListeners.setAddController(addUseCaseController);
    listListeners.setEditController(editUseCaseController);
    listListeners.setRemoveController(removeUseCaseController);
    schoolList.setRemoveUseCaseController(removeUseCaseController);

    /*ReportViewListeners is just a container for the listeners. It does not have any other meanings.*/
    ReportViewListeners reportViewListeners = new ReportViewListeners();
    ActionListener reportViewManageSchoolButtonListener = 
        reportViewListeners.new ManageSchoolButtonListener();
    ListSelectionListener tournamentSelectionListener = 
        reportViewListeners.new TournamentSelectionListener();

    /* the following creates the reportTableFrame */
    /* the reportTableFrame creates reportTableView and repositoryView. It provides getter for other classes to access these */
    IReportTableFrame reportFrame = new ReportTableFrame(reportViewManageSchoolButtonListener,
        tournamentSelectionListener, Repository.getInstance1());
    IReportTableView reportTableView = reportFrame.returnReportTableView();
    IRepositoryView repositoryView = reportFrame.returnRepositoryView();

    /* this creates a tournament menu item listener that listens to add tournament event.*/
    tourgen.controller.AddTournamentMenuItemListener addTournamentMenuItemListener = 
        new tourgen.controller.AddTournamentMenuItemListener();

    /* this creates a controller and plugs 2 views and one listener into that controller */
    /* that controller depends on the 2 views and the listener.*/
    ReportViewUseCaseController reportViewUseCaseController = 
        new ReportViewUseCaseController(reportTableView,
        repositoryView, addTournamentMenuItemListener);
        
    /* Since listeners in the reportViewListeners need to access the controller, I put the controller in 
    the listeners reportViewListeners container */
    reportViewListeners.setCoordinator(reportViewUseCaseController);
    
    /* this creates a menuListener for the Tournament Menu of a frame*/
    tourgen.controller.TournamentMenuListener tournamentMenuListener = 
        new tourgen.controller.TournamentMenuListener();
        
    /* this creates a controller for the view school list use case. It needs a handle to the schoolListView
    * because it will call the schoolListView. 
    * The listener -> controller -> calls the view to show
    */
    ViewSchoolListUseCaseController viewSchoolListUseCaseController = 
        new ViewSchoolListUseCaseController(schoolList);
    
    listListeners.setViewSchoolListController(viewSchoolListUseCaseController);
    reportViewListeners.setViewSchoolListUseCaseController(viewSchoolListUseCaseController);
    tournamentMenuListener.setReportViewUseCaseController(reportViewUseCaseController);
    // manager.initSchools();
    // schoolList.populate();

    // RepositoryInitialization init = new RepositoryInitialization();
    // RepositoryInitialization.init(repo, manager);
    repositoryView.populate();

    //reportFrame.showView();

    /* the editForm, the addForm and the schoolList will listen to any changes occuring to the manager */
    /* each of them has an update method that will be called whenever the schoolManager has any changes */
    manager.addObserver(editForm);
    manager.addObserver(addForm);
    manager.addObserver(schoolList);

    
    
    
    
    MapDriver mainMap = new MapDriver(Repository.getInstance1());
    MapController mapController2 = new MapController(null, mainMap);
    CheckBoxTreeCustomCheckBoxListener checkBoxListener2 = 
        new CheckBoxTreeCustomCheckBoxListener(mapController2);
    mainMap.setCon(checkBoxListener2, mapController2);
    mainMap.initGui();
    
    
    
    
    /**
     * TODO This can be moved into the NewMain class's contruction method.
     * Inject the mapAssistantController to the mapPanel through MapDriver's setter
     *       
     * Requirements: Make a MapSidePane since the controller requires it.
     *   Requirements: Make a detailsPanel, a hostChooserPanel, an availableMeetsPanel since those panels are required by the mapSidePane
     *    Requirements availableMeetsPanel needs a ChangeCompetitionSiteListener
     *      same for hostChooserPanel
     */
    DetailsPanel detailsPanel = new DetailsPanel();
    HostChooserPanel hostChooserPanel = new HostChooserPanel();
    AvailableMeetsPanel availableMeetsPanel = new AvailableMeetsPanel();
    NewMainMapSidePane mapSidePane = new NewMainMapSidePane(detailsPanel, hostChooserPanel, availableMeetsPanel);
    
    tourgen.controller.MapAssistantController mapAssistantController = new tourgen.controller.MapAssistantController(mapSidePane);
    mainMap.setMapAssistantController(mapAssistantController);
    /* injection done */
    
   
    
    tourgen.controller.MainWindowCloseListener mainWindowListener = 
        new tourgen.controller.MainWindowCloseListener(manager);
    

    TabbedFrame tabbedFrame = new TabbedFrame(reportViewManageSchoolButtonListener,
        tournamentSelectionListener,
        mainWindowListener,
        (javax.swing.JPanel) (reportFrame.returnRepositoryView()),
        (javax.swing.JPanel) (reportFrame.returnReportTableView()),
        mainMap.getCheckBoxTreePanel(),
        mainMap.getMapPanel(),
        addTournamentMenuItemListener,
        tournamentMenuListener);
    tabbedFrame.setVisible(true);
    
    /* create newMain */
    
    NewMain newMain = new NewMain(mapController2, 
        mainMap.getMapPanel(), 
        (ReportTableView)reportTableView, 
        mapSidePane, 
        manager);
    newMain.addWindowListener(mainWindowListener);
    
    newMain.initUserInterface();
    newMain.setVisible(true);
    //Repository.getInstance1().addObserver(repositoryView);
    
    
    
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
    //uiDef.put("defaultFont", 20);
    java.util.Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof javax.swing.plaf.FontUIResource) {
        uiDef.put(key, f);
      }
    }
    com.jidesoft.plaf.LookAndFeelFactory.installJideExtension();
  }
}
