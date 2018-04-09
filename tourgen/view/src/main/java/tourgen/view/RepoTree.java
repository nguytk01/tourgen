package tourgen.view;

import java.awt.List;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTree;

import tourgen.util.ICheckNode;
import tourgen.util.IRepoTree;
import tourgen.view.CheckBoxTreeComponents.CheckNode;

public class RepoTree extends JTree implements IRepoTree {

  @Override
  public Set getMeetList() {
    Set<ICheckNode> nodeSet = getAllLeafNodes((CheckNode) this.getModel().getRoot());
    System.out.println("set size: " + nodeSet.size());
    return nodeSet;

  }

  /**
   * Get a set of all ICheckNodes from the given parent node.
   * @param node the node to start searching
   * @return a set of child nodes.
   */
  public Set<ICheckNode> getAllLeafNodes(CheckNode node) {
    Set<ICheckNode> leafNodesSet = new HashSet<ICheckNode>();
    if (node.isLeaf() && node.isSelected()) {
      leafNodesSet.add(node);
    } else {
      for (int i = 0; i < node.getChildCount(); i++) {
        leafNodesSet.addAll(getAllLeafNodes((CheckNode) node.getChildAt(i)));
      }
    }
    return leafNodesSet;
  }

  public RepoTree(CheckNode node) {
    super(node);

  }

}
