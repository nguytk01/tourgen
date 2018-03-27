package tourgen.view;

import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import tourgen.model.Tournament;
import tourgen.util.IRepositoryView;

public class RepositoryView extends JPanel implements IRepositoryView{
	private JList<Tournament> tournamentList;
	private Vector<Tournament> tournamentVector;
	private JScrollPane scrollPane;
	public RepositoryView(ListSelectionListener listener) {
		tournamentVector = new Vector<Tournament>();
		tournamentList = new JList<Tournament>(tournamentVector);
		this.add(tournamentList);
		tournamentList.addListSelectionListener(listener);
	}
	
	public void populate(List<Tournament> list) {
		tournamentVector.addAll(list);
	}

	@Override
	public Object getSelectedTournament() {
		if (tournamentList.isSelectionEmpty() != false) {
			return tournamentVector.get(tournamentList.getSelectedIndex());
		}
		return null;
	}
	
}
