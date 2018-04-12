package tourgen.view;

import java.util.HashSet;
import java.util.Set;

import javax.swing.tree.TreePath;

import tourgen.util.ICheckNode;

public class CheckBoxTreeNew extends com.jidesoft.swing.CheckBoxTree implements tourgen.util.IRepoTree {
  public CheckBoxTreeNew(javax.swing.tree.TreeNode node) {
    super(node);
  }

  @Override
  public Set<ICheckNode> getMeetList() {
    Set<ICheckNode> leafNodesSet = new HashSet<ICheckNode>();
    TreePath[] paths = this.getCheckBoxTreeSelectionModel().getSelectionPaths();
    if (paths == null) {
      return leafNodesSet;
    }
    for (int i = 0; i < paths.length; i++) {
      leafNodesSet.add( (ICheckNode)paths[i].getLastPathComponent());
    }
    return leafNodesSet;
  }
}
