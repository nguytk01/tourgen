package tourgen.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import tourgen.model.Meet;
import tourgen.util.ICheckNode;
import tourgen.util.IRepoTree;

public class CheckBoxTreeListener extends MouseAdapter {
  IRepoTree repoTree;
  JTree tree;
  MapController mapController;

  public CheckBoxTreeListener() {
  }

  public void setJTree(JTree tree) {
    this.tree = tree;
    repoTree = (IRepoTree) tree;
  }

  /*
   * setter for mapController so listener can call mapController's showMeetOnMap
   */
  /**
   * This method will be called by an action of clicking on the tree.
   */
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    int row = tree.getRowForLocation(x, y);
    TreePath path = tree.getPathForRow(row);
    // TreePath path = tree.getSelectionPath();
    if (path != null) {
      ICheckNode node = (ICheckNode) path.getLastPathComponent();
      boolean isSelected = !(node.isSelected());
      node.setSelected(isSelected);
      if (node.getSelectionMode() == 4) {
        if (isSelected) {
          tree.expandPath(path);
        } else {
          tree.collapsePath(path);
        }
      }
      ((DefaultTreeModel) tree.getModel()).nodeChanged((TreeNode) node);

      if (row == 0) {
        tree.revalidate();
        tree.repaint();
      }
    }
    /// Clicked event class

    /* get list of meets from tree */

    mapController.treeCheckBoxClicked();

    /* call map controller's showMeetsOnMap */

    /* mapController will call the map to show the meets */

  }

  public void setMapController(MapController map) {
    mapController = map;
    mapController.setTree(repoTree);
  }
}