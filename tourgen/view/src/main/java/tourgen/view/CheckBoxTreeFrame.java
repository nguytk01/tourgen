package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.controller.CheckBoxTreeListener;
//import tourgen.controller.CheckNode;
import tourgen.controller.MapController;
import tourgen.model.Meet;
import tourgen.model.Repository;
import tourgen.model.School;
import tourgen.model.Stage;
import tourgen.model.Tournament;

public class CheckBoxTreeFrame extends JFrame {

  private Repository repo;

  /**
   * Construct a CheckBoxTreeFrame. 
   * @param repository a repository Object
   * @param checkBoxListener an ActionListener for the CheckBox
   * @param controller a MapController instance
   */
  public CheckBoxTreeFrame(Repository repository, 
      CheckBoxTreeCustomCheckBoxListener checkBoxListener,
      MapController controller) {
    super("Tournament 2017-18 Tree");
    setBounds(50, 50, 650, 650);
    repo = repository;

    getContentPane().add(
        new CheckBoxTreePanel(checkBoxListener, repository, controller), 
        BorderLayout.CENTER);
    // getContentPane().add(panel, BorderLayout.EAST);
    // getContentPane().add(textPanel, BorderLayout.SOUTH);
  }

}