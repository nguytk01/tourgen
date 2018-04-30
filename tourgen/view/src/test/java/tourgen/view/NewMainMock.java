package tourgen.view;

import javax.swing.JPanel;

import tourgen.controller.MapController;
import tourgen.model.SchoolManager;

public class NewMainMock extends NewMain{
	
	public NewMainMock(MapController mapController, JPanel mapPanel, ReportTableView reportTableView,
			NewMainMapSidePane mapSidePane, SchoolManager schoolManager) {
		super(mapController, mapPanel, reportTableView, mapSidePane, schoolManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String showSaveAsTournamentConfirmationDialog() {
		return "abc";
	}
}
