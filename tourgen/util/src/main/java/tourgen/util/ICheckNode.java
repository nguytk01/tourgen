package tourgen.util;

import javax.swing.tree.TreeNode;

public interface ICheckNode extends TreeNode {
  public void setSelectionMode(int mode);

  public int getSelectionMode();

  public void setSelected(boolean isSelected);

  public boolean isSelected();

  public Object getValue();
}
