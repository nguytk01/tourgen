package tourgen.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import tourgen.model.Tournament;

public class TournamentChooserComboBoxCellRenderer implements ListCellRenderer {

  @Override
  public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
    Tournament tournament = (Tournament) arg1;
    JLabel label = null;
    System.out.println("get cell renderer");
    if (tournament == null) return new JLabel();
    if (tournament.isSavingNeeded()) {
      label = new JLabel("*" + tournament.toString());
    } else label = new JLabel(tournament.toString());
    label.setOpaque(false);
    return label;
  }

}
