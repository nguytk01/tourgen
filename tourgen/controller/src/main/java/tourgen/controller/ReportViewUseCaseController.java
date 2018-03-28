package tourgen.controller;

import tourgen.model.Tournament;
import tourgen.util.IReportTableView;
import tourgen.util.IRepositoryView;

public class ReportViewUseCaseController {
	private IReportTableView reportTable;
	private IRepositoryView repoView;
	public ReportViewUseCaseController(IReportTableView reportTableArg, IRepositoryView repoArg ) {
		reportTable = reportTableArg;
		repoView = repoArg;
	}
	
	public void changeTournamentView() {
		Object currentTournament = repoView.getSelectedTournament();
		reportTable.display(currentTournament);
	}
}
