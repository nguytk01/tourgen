package tourgen.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
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
   * @param checkBoxListener
   *          an ActionListener
   * @param repo
   *          a Repository object
   * @param controller
   *          a MapController object
   */
  private CheckBoxTreeCustomCheckBoxListener checkBoxListenerParam;
  private MapController mapControllerParam;

  /**
   * Construct a CheckBoxTreePanel.
   * 
   * @param checkBoxListener
   *          the listener for all checkboxes
   * @param controller
   *          the map controller object
   */
  
  public CheckBoxTreePanel(CheckBoxTreeCustomCheckBoxListener checkBoxListener, 
      MapController controller) {
    mapControllerParam = controller;
    checkBoxListenerParam = checkBoxListener;
    Repository.getInstance1().addObserver(this);
    commonInit();
  }

  private void commonInit() {
    this.removeAll();
    CheckNode repositoryChildren = new CheckNode("Repository");
    for (int tourIndex = 0; tourIndex < Repository.getInstance1().getGirlList().size();
        tourIndex++) {

      Tournament tour = Repository.getInstance1().getGirlList().get(tourIndex);
      CheckNode tourChildren = new CheckNode(tour.toString());
      repositoryChildren.add(tourChildren);

      for (int stageIndex = 0; stageIndex < tour.getStageList().size(); stageIndex++) {
        Stage stage = tour.getStageList().get(stageIndex);
        CheckNode stageChildren = new CheckNode(stage.getStageTitle());
        tourChildren.add(stageChildren);

        for (int meetIndex = 0; meetIndex < stage.getListMeet().size(); meetIndex++) {
          Meet meet = stage.getListMeet().get(meetIndex);
          CheckNode meetCheckNode = new CheckNode(meet, false, false);
          stageChildren.add(meetCheckNode);
        }
      }
    }

    // RepoTree tree = new RepoTree(nodes[0]);
    CheckBoxTreeNew tree = new CheckBoxTreeNew(repositoryChildren);
    tree.setCellRenderer(new CheckRenderer(checkBoxListenerParam));
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.putClientProperty("JTree.lineStyle", "Angled");
    //treeListener.setIRepoTree(tree);
    CheckBoxTreeListener listener = new CheckBoxTreeListener();
    listener.setJTree(tree);
    listener.setMapController(mapControllerParam);
    tree.addMouseListener(listener);
    this.setLayout(new BorderLayout());
    tree.setSelectPartialOnToggling(true);
    JScrollPane sp = new JScrollPane(tree);

    this.add(sp, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }

  public void update(java.util.Observable source, Object arg) {
    System.out.println("notified");
    commonInit();
  }
}
