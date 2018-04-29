package tourgen.controller;

import tourgen.util.INewMain;

public class NewMainMock implements INewMain {

	@Override
	public boolean showOverwriteTournamentConfirmationDialog() {
		return true;
	}

	@Override
	public String showSaveAsTournamentConfirmationDialog() {
		return "abc";
	}

}
