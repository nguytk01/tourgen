package tourgen.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.controller.CheckBoxTreeListener;
import tourgen.controller.MapController;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.Stage;
import tourgen.model.Tournament;
import tourgen.view.CheckBoxTreeComponents.CheckNode;
import tourgen.view.CheckBoxTreeComponents.CheckRenderer;

public class CheckBoxTreePanel extends JPanel implements java.util.Observer {
  /**
   * Construct a CheckBoxTreePanel.
   * 
   * @param checkBoxListener an ActionListener
   * @param repo a Repository object
   * @param controller a MapController object
   */
  private CheckBoxTreeCustomCheckBoxListener checkBoxListenerParam;
  private MapController mapControllerParam;
  private javax.swing.tree.DefaultTreeModel defaultTreeModel;

  /**
   * Construct a CheckBoxTreePanel.
   * 
   * @param checkBoxListener
   *          the listener for all checkboxes
   * @param controller
   *          the map controller object
   */
  
  public CheckBoxTreePanel(//CheckBoxTreeCustomCheckBoxListener checkBoxListener, 
      MapController controller) {
    mapControllerParam = controller;
    //checkBoxListenerParam = checkBoxListener;
    //Repository.getInstance1().addObserver(this);
    //commonInit();
  }

  private void commonInit(tourgen.model.Tournament tournament) {
    
    this.removeAll();
    CheckNode tourChildren = new CheckNode(tournament.toString());
    

    // RepoTree tree = new RepoTree(nodes[0]);
    defaultTreeModel = new javax.swing.tree.DefaultTreeModel(tourChildren); 
    for (int stageIndex = 0; stageIndex < tournament.getStageList().size(); stageIndex++) {
      Stage stage = tournament.getStageList().get(stageIndex);
      CheckNode stageChildren = new CheckNode(stage.getStageTitle());
      tourChildren.add(stageChildren);

      for (int meetIndex = 0; meetIndex < stage.getMeetList().size(); meetIndex++) {
        Meet meet = stage.getMeetList().get(meetIndex);
        CheckNode meetCheckNode = new CheckNode(meet, false, false);
        stageChildren.add(meetCheckNode);
        meetCheckNode.addPropertyChangeListener(new CheckBoxTreePropertyChangeListener());
      }
  }
    
    CheckBoxTreeNew tree = new CheckBoxTreeNew(defaultTreeModel);
    tree.setRowHeight(30);
    tree.setCellRenderer(new CheckRenderer(checkBoxListenerParam));
    tree.getCheckBoxTreeSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.putClientProperty("JTree.lineStyle", "Angled");
    //treeListener.setIRepoTree(tree);
    CheckBoxTreeListener listener = new CheckBoxTreeListener();
    listener.setJTree(tree);
    listener.setMapController(mapControllerParam);
    //tree.addMouseListener(listener);
    tree.getCheckBoxTreeSelectionModel().addTreeSelectionListener(listener);
    this.setLayout(new BorderLayout());
    tree.setSelectPartialOnToggling(true);
    JScrollPane sp = new JScrollPane(tree);

    this.add(sp, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }

  void setActiveTournament(Object tournament) {
    commonInit((Tournament) tournament);
  }
  
  public void update(java.util.Observable source, Object arg) {
    System.out.println("notified");
    
  }
  private class CheckBoxTreePropertyChangeListener implements java.beans.PropertyChangeListener{
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      System.out.println("Checkboxtreepanel propertylistener ");
      if (tourgen.model.Meet.SCHOOL_ADDED.equals(evt.getPropertyName())) {
        defaultTreeModel.nodeChanged((javax.swing.tree.DefaultMutableTreeNode) evt.getSource());
        mapControllerParam.treeCheckBoxClicked();
      } else if (tourgen.model.Meet.SCHOOL_REMOVED.equals(evt.getPropertyName())) {
        defaultTreeModel.nodeChanged((javax.swing.tree.DefaultMutableTreeNode) evt.getSource());
        mapControllerParam.treeCheckBoxClicked();
      } else if (tourgen.model.Meet.HOST_SCHOOL_CHANGED.equals(evt.getPropertyName())) {
        defaultTreeModel.nodeChanged((javax.swing.tree.DefaultMutableTreeNode) evt.getSource());
        mapControllerParam.treeCheckBoxClicked();
      }      
    }
  }
}


