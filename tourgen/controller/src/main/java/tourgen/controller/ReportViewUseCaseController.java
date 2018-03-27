package tourgen.controller;

import tourgen.model.Tournament;
import tourgen.util.IReportTableView;
import tourgen.util.IRepositoryView;

public class ReportViewUseCaseController {
	private IReportTableView reportTable;
	private IRepositoryView repo;
	public ReportViewUseCaseController(IReportTableView reportTableArg, IRepositoryView repoArg ) {
		reportTable = reportTableArg;
		repo = repoArg;
	}
	
	public void changeTournamentView() {
		Object currentTournament = repo.getSelectedTournament();
		reportTable.display(currentTournament);
	}
}
