package tourgen.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

import tourgen.model.Meet;
import tourgen.util.ICheckNode;
import tourgen.util.IMapDriver;
import tourgen.util.IRepoTree;

//import tourgen.view.TreeCheckBoxPT2.CheckNode;

public class MapController {

  //private CheckBoxTreeListener checkBoxListener;
  private IMapDriver mapDriver;
  // private List<Meet> meetList = new ArrayList<Meet>();
  private IRepoTree tree;
  
  /**
   * Controller for the map.
   * @param checkBoxListenerArg ActionListener for the CheckBoxTree
   * @param mapDriver mapDriver for the IMapDriver
   */
  
  public MapController(CheckBoxTreeListener checkBoxListenerArg, IMapDriver mapDriver) {
    // checkBoxListener = checkBoxListenerArg;
    // checkBoxListener.setMapController(this);
    this.mapDriver = mapDriver;
    // checkBoxListener.setMapController(this);

    // tree = treeArg;

  }
  
  public void emptyMap() {
    mapDriver.showMeetList(new java.util.ArrayList<Meet>());
  }
  
  /**
   * When the CheckBoxTreeListener receives an event from the tree, it will call 
   * this method of the controller.
   */
  
  public void treeCheckBoxClicked() {
        /*SwingUtilities.invokeLater( new Runnable() {
      

      @Override
      public void run() {*/
        Set<ICheckNode> meetSet = tree.getMeetList();
        Object[] arr = meetSet.toArray();
        List<Meet> meetList = new ArrayList<Meet>();
        System.out.println("set size:" + meetSet.size());
        for (int i = 0; i < arr.length; i++) {
          if (arr[i] instanceof ICheckNode && ((ICheckNode) arr[i]).getValue() instanceof Meet) {
            meetList.add((Meet) ((ICheckNode) arr[i]).getValue());
          }
        }

        mapDriver.showMeetList(meetList);
      }
  //});
  //}

  public void setTree(IRepoTree treeArg) {
    tree = treeArg;
  }

  public void mapReplace(Object oldSchool, Object newSchool) {
    mapDriver.mapReplace(oldSchool, newSchool);
  }

}
