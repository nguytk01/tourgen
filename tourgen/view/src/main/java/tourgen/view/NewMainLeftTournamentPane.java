package tourgen.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.controller.MapController;


public class NewMainLeftTournamentPane extends javax.swing.JPanel {
  public NewMainLeftTournamentPane(tourgen.controller.MapController controller) {
    setLayout(new java.awt.BorderLayout(0, 0));

    JPanel panel = new JPanel();
    FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    add(panel, java.awt.BorderLayout.NORTH);

    JLabel lblNewLabel = new JLabel("Tournament");
    panel.add(lblNewLabel);

    JComboBox repositoryComboBox = new JComboBox();
    repositoryComboBox.setPreferredSize(new Dimension(200, 25));
    panel.add(repositoryComboBox);

    CheckBoxTreePanel checkBoxTreePanel = new CheckBoxTreePanel(null, controller);
    add(checkBoxTreePanel, java.awt.BorderLayout.CENTER);
    revalidate();
  }

}
