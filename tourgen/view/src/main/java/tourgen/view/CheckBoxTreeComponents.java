package tourgen.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.Enumeration;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.model.Meet;

public class CheckBoxTreeComponents {
  static class CheckRenderer extends JPanel implements TreeCellRenderer {
    protected CheckBoxTreeCustomCheckBox checkBox;

    protected TreeLabel label;

    public CheckRenderer(CheckBoxTreeCustomCheckBoxListener listener) {
      //setLayout(null);
      //checkBox = new CheckBoxTreeCustomCheckBox();
      // checkBox.addActionListener(listener);
      System.out.println("actionListener added");
      //add(checkBox);
      add(label = new TreeLabel());
      setBackground(new Color(0,0,0,0));
      //checkBox.setBackground(UIManager.getColor("Tree.textBackground"));
      //label.setForeground(UIManager.getColor("Tree.textForeground"));
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, 
        boolean isSelected, boolean expanded,
        boolean leaf, int row, boolean hasFocus) {
      
      setEnabled(tree.isEnabled());
      //checkBox.setSelected(((CheckNode) value).isSelected());
      label.setFont(tree.getFont());
      
      String stringValue = tree.convertValueToText(value, isSelected, 
          expanded, leaf, row, hasFocus);
      label.setText(stringValue);
      
      label.setSelected(isSelected);
      label.setFocus(hasFocus);
      /*if (leaf) {
        label.setIcon(UIManager.getIcon("Tree.leafIcon"));
      } else if (expanded) {
        label.setIcon(UIManager.getIcon("Tree.openIcon"));
      } else {
        label.setIcon(UIManager.getIcon("Tree.closedIcon"));
      }*/
      //checkBox.setCheckNode(value);
      return this;
    }


    public class TreeLabel extends JLabel {
      boolean isSelected;

      boolean hasFocus;

      public TreeLabel() {
      }

      public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
      }

      public void setFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
      }
    }
  }

  static class CheckNode extends DefaultMutableTreeNode implements tourgen.util.ICheckNode, java.beans.PropertyChangeListener {

    public static final int SINGLE_SELECTION = 0;

    public static final int DIG_IN_SELECTION = 4;

    protected int selectionMode;

    protected boolean isSelected;

    private Object value;

    private java.beans.PropertyChangeSupport propertyChangeSupport;
    public CheckNode() {
      this(null);
    }

    public CheckNode(Object userObject) {
      this(userObject, true, false);
    }

    public CheckNode(Object userObject, boolean allowsChildren, boolean isSelected) {
      super(userObject, allowsChildren);
      this.value = userObject;
      this.isSelected = isSelected;
      if ( !allowsChildren ) {
        tourgen.model.Meet meet = (tourgen.model.Meet) userObject;
        meet.addPropertyChangeListener(this);
      }
      setSelectionMode(DIG_IN_SELECTION);
      propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    }

    public void setSelectionMode(int mode) {
      selectionMode = mode;
    }

    public int getSelectionMode() {
      return selectionMode;
    }

    public void setSelected(boolean isSelected) {
      this.isSelected = isSelected;

      if ((selectionMode == DIG_IN_SELECTION) && (children != null)) {
        Enumeration e = children.elements();
        while (e.hasMoreElements()) {
          CheckNode node = (CheckNode) e.nextElement();
          node.setSelected(isSelected);
        }
      }
    }
    
    /*public void add (CheckNode node) {
      add(node);
    }*/

    public boolean isSelected() {
      return isSelected;
    }

    /*
     * public Object getMeet() { return value; }
     */

    public Object getValue() {
      return value;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {      
        propertyChangeSupport.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }
    
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
      propertyChangeSupport.addPropertyChangeListener(listener);
    }

    // If you want to change "isSelected" by CellEditor,
    /*
     * public void setUserObject(Object obj) { if (obj instanceof Boolean) {
     * setSelected(((Boolean)obj).booleanValue()); } else {
     * super.setUserObject(obj); } }
     */

  }
}
