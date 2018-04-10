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

public class CheckBoxTreePanel extends JPanel {
  /**
   * Construct a CheckBoxTreePanel.
   * @param checkBoxListener an ActionListener
   * @param repo a Repository object
   * @param controller a MapController object
   */
  public CheckBoxTreePanel(CheckBoxTreeCustomCheckBoxListener checkBoxListener,
      MapController controller) {
    String[] strs = { "Tournament 2017-18", "Tournaments", // 0
        "Sectionals", // 1
        "Regionals", // 2
        "Semi-State", // 3
        "State Finals" };

    CheckNode[] nodes = new CheckNode[strs.length];

    for (int i = 0; i < strs.length; i++) {
      nodes[i] = new CheckNode(strs[i]);
    }

    nodes[0].add(nodes[1]);
    nodes[1].add(nodes[2]);
    nodes[1].add(nodes[3]);
    nodes[1].add(nodes[4]);
    nodes[1].add(nodes[5]);

    ArrayList<Meet> sectionalHost = new ArrayList<Meet>();
    ArrayList<Meet> regionalHost = new ArrayList<Meet>();
    ArrayList<Meet> semiHost = new ArrayList<Meet>();
    ArrayList<Meet> finalHost = new ArrayList<Meet>();

    for (int tourIndex = 0; tourIndex < Repository.getInstance1().getGirlList().size(); tourIndex++) {
      Tournament tour = Repository.getInstance1().getGirlList().get(tourIndex);
      for (int stageIndex = 0; stageIndex < tour.getStageList().size(); stageIndex++) {
        Stage stage = tour.getStageList().get(stageIndex);

        for (int meetIndex = 0; meetIndex < stage.getListMeet().size(); meetIndex++) {
          Meet meet = stage.getListMeet().get(meetIndex);
          // for (int schoolIndex = 0; schoolIndex < meet.getParticipatingSchool().size();
          // schoolIndex ++)
          // sectionalHost.add(meet.getHostSchool().getDisplayName());
          if (stageIndex == 0) {
            sectionalHost.add(meet);
          }
          if (stageIndex == 1) {
            // regionalHost.add(stage.getListMeet()
            //.get(meetIndex).getHostSchool().getDisplayName());
            regionalHost.add(stage.getListMeet().get(meetIndex));

          }

          if (stageIndex == 2) {
            // semiHost.add(stage.getListMeet().get(meetIndex).getHostSchool().getDisplayName());
            semiHost.add(stage.getListMeet().get(meetIndex));

          }

          if (stageIndex == 3) {
            // finalHost.add(stage.getListMeet().get(meetIndex).getHostSchool().getDisplayName());
            finalHost.add(stage.getListMeet().get(meetIndex));
          }

        }

      }
    }
    CheckNode[] nodes2 = new CheckNode[sectionalHost.size()];
    for (int i = 0; i < sectionalHost.size(); i++) {
      nodes2[i] = new CheckNode(sectionalHost.get(i), false, false);
    }

    CheckNode[] nodes3 = new CheckNode[regionalHost.size()];
    for (int i = 0; i < regionalHost.size(); i++) {
      nodes3[i] = new CheckNode(regionalHost.get(i), false, false);
    }

    CheckNode[] nodes4 = new CheckNode[semiHost.size()];
    for (int i = 0; i < semiHost.size(); i++) {
      nodes4[i] = new CheckNode(semiHost.get(i), false, false);

    }

    CheckNode[] nodes5 = new CheckNode[finalHost.size()];
    for (int i = 0; i < finalHost.size(); i++) {
      nodes5[i] = new CheckNode(finalHost.get(i), false, false);
      System.out.println(finalHost.get(i));
    }

    for (int i = 0; i < sectionalHost.size(); i++) {
      nodes[2].add(nodes2[i]);

    }

    for (int i = 0; i < regionalHost.size(); i++) {
      nodes[3].add(nodes3[i]);

    }

    for (int i = 0; i < semiHost.size(); i++) {
      nodes[4].add(nodes4[i]);

    }

    for (int i = 0; i < finalHost.size(); i++) {
      nodes[5].add(nodes5[i]);

    }

    
    System.out.println("size of nodes[2]" + nodes[2].getChildCount());
    System.out.println("size of nodes[3]" + nodes[3].getChildCount());
    System.out.println("size of nodes[4]" + nodes[4].getChildCount());
    System.out.println("size of nodes[5]" + nodes[5].getChildCount());
    
    RepoTree tree = new RepoTree(nodes[0]);
    tree.setCellRenderer(new CheckRenderer(checkBoxListener));
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    tree.putClientProperty("JTree.lineStyle", "Angled");
    // treeListener.setIRepoTree(tree);
    CheckBoxTreeListener listener = new CheckBoxTreeListener();
    listener.setJTree(tree);
    listener.setMapController(controller);
    tree.addMouseListener(listener);
    this.setLayout(new BorderLayout());

    JScrollPane sp = new JScrollPane(tree);

    this.add(sp, BorderLayout.CENTER);
  }

}
