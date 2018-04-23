package tourgen.view;

import java.util.HashSet;
import java.util.Set;

import javax.swing.tree.TreePath;

import tourgen.util.ICheckNode;

public class CheckBoxTreeNew extends com.jidesoft.swing.CheckBoxTree implements tourgen.util.IRepoTree {
  public CheckBoxTreeNew(javax.swing.tree.TreeNode node) {
    super(node);
  }
  
  public CheckBoxTreeNew(javax.swing.tree.TreeModel treeModel) {
    super(treeModel);
  }

  @Override
  public Set<ICheckNode> getMeetList() {
    Set<ICheckNode> leafNodesSet = new HashSet<ICheckNode>();
    TreePath[] paths = this.getCheckBoxTreeSelectionModel().getSelectionPaths();
    if (paths == null) {
      return leafNodesSet;
    }
    for (int i = 0; i < paths.length; i++) {
      if ( !((ICheckNode)paths[i].getLastPathComponent()).isLeaf()){
        Set<ICheckNode> set = getAllLeafNodes((tourgen.view.CheckBoxTreeComponents.CheckNode)paths[i].getLastPathComponent());
        leafNodesSet.addAll(set);
      } else {
        leafNodesSet.add( (ICheckNode)paths[i].getLastPathComponent());
      }
    }
    return leafNodesSet;
  }

private Set<ICheckNode> getAllLeafNodes(tourgen.view.CheckBoxTreeComponents.CheckNode node) {
  Set<ICheckNode> leafNodesSet = new HashSet<ICheckNode>();
  if (node.isLeaf()) {
    leafNodesSet.add(node);
  } else {
    for (int i = 0; i < node.getChildCount(); i++) {
      leafNodesSet.addAll(getAllLeafNodes((tourgen.view.CheckBoxTreeComponents.CheckNode) node.getChildAt(i)));
    }
  }
  return leafNodesSet;
}
}