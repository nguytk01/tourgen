package tourgen.controller;

import javax.swing.JComboBox;

import tourgen.util.ITournamentChooserComboBox;

public class TournamentComboBoxMock extends JComboBox implements ITournamentChooserComboBox{
	public int getSelectedIndex() {
		return 0;
	}


	@Override
	public int getSelectedTournamentIndex() {
		return 0;
	}
}
