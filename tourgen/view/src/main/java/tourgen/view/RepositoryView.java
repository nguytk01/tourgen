package tourgen.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import tourgen.model.Repository;
import tourgen.model.Tournament;
import tourgen.util.IRepositoryView;

public class RepositoryView extends JPanel implements IRepositoryView {
  private JList<Tournament> tournamentList;
  private Vector<Tournament> tournamentVector;
  private JScrollPane scrollPane;
  private Repository repositoryModel;

  /**
   * Build a Repository View (a list of tournament).
   * @param listener an ActionListener to listen to selection event in the list.
   * @param repositoryModelArg a repository object.
   */
  public RepositoryView(ListSelectionListener listener, Repository repositoryModelArg) {
    setSize(200, 500);
    setLayout(new BorderLayout());
    tournamentVector = new Vector<Tournament>();
    tournamentList = new JList<Tournament>(tournamentVector);
    this.add(tournamentList, BorderLayout.CENTER);
    tournamentList.addListSelectionListener(listener);
    repositoryModel = repositoryModelArg;
  }

  void populateView(List<Tournament> list) {
    tournamentVector.clear();
    tournamentVector.addAll(list);
  }

  void populatePrivate(List<Object> list) {
    for (Object i : list) {
      try {
        Tournament type = (Tournament) i;
      } catch (ClassCastException exception) {
        exception.printStackTrace();
        return;
      }
    }
    tournamentVector.clear();
    for (Object i : list) {
      Tournament tour = (Tournament) i;
      tournamentVector.add(tour);
    }

  }

  @Override
  public Object getSelectedTournament() {
    if (tournamentList.getSelectedIndex() != -1) {
      return tournamentVector.get(tournamentList.getSelectedIndex());
    }
    return null;
  }

  @Override
  public void populate() {
    List<Tournament> list = repositoryModel.getGirlList();
    for (Tournament i : list) {
      try {
        Tournament type = (Tournament) i;
      } catch (ClassCastException exception) {
        exception.printStackTrace();
        return;
      }
    }
    populateView(list);
  }

}
