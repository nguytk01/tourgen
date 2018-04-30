package tourgen.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tourgen.controller.CheckBoxTreeCustomCheckBoxListener;
import tourgen.controller.MapController;


public class NewMainLeftTournamentPane extends javax.swing.JPanel implements tourgen.util.INewMainViewPane {
  private CheckBoxTreePanel checkBoxTreePanel;
  private TournamentChooserComboBox repositoryComboBox;
  public NewMainLeftTournamentPane(tourgen.controller.MapController controller, 
      java.awt.event.ActionListener tournamentChooserListener) {
    setLayout(new java.awt.BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setLayout(new java.awt.BorderLayout());
    //FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    //flowLayout.setAlignment(FlowLayout.LEFT);
    add(panel, java.awt.BorderLayout.NORTH);

    JLabel lblNewLabel = new JLabel("Current Tournament");
    panel.add(lblNewLabel, java.awt.BorderLayout.NORTH);

    repositoryComboBox = new TournamentChooserComboBox(tournamentChooserListener);
    repositoryComboBox.setPreferredSize(new Dimension(200, 25));
    panel.add(repositoryComboBox, java.awt.BorderLayout.SOUTH);

    checkBoxTreePanel = new CheckBoxTreePanel(controller);
    add(checkBoxTreePanel, java.awt.BorderLayout.CENTER);
    revalidate();
  }

  @Override
  public void setActiveTournament(Object tournament) {
    checkBoxTreePanel.setActiveTournament(tournament);
  }

  public void initUserInterface() {
    repositoryComboBox.initUserInterface();
  }



}
