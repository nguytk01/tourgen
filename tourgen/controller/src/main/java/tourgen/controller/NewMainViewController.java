package tourgen.controller;

import tourgen.model.Repository;
import tourgen.model.SchoolManager;
import tourgen.model.Tournament;
import tourgen.util.INewMain;
import tourgen.util.INewMainViewPane;
import tourgen.util.IReportTableView;

public class NewMainViewController {
  INewMainViewPane mainViewLeftPane;
  INewMainViewPane mainViewRightPane;
  tourgen.model.Tournament currentTournament;
  
  private IReportTableView reportTableView;
  private SchoolManager schoolManager;
  private INewMain newMain;
  
  public NewMainViewController(INewMainViewPane leftPaneArg, 
      INewMainViewPane rightPaneArg,
      SchoolManager schoolManagerArg) {
    mainViewLeftPane = leftPaneArg;
    mainViewRightPane = rightPaneArg;
    schoolManager= schoolManagerArg;
  }
  
  public void setActiveTournament(Object tournament) {
    if (tournament != currentTournament) {
      mainViewLeftPane.setActiveTournament(tournament);
      mainViewRightPane.setActiveTournament(tournament);
    }
    
    currentTournament = (tourgen.model.Tournament) tournament;
  }
  
  public java.util.List<tourgen.model.Meet> getAvailableSectionalMeets(Object meet, Object school){
    try {
      return currentTournament.getSectionalMeetSuggestions(school);
    } catch (NullPointerException e) {
      //e.printStackTrace();
      System.out.println("currentTournament is null. setActiveTournament() has not been called. It needs that to get suggestions on sectional meets.");
    }
    return null;
  }
  
  /*public void setReportTableView(tourgen.util.IReportTableView reportTableViewArg) {
    reportTableView = reportTableViewArg;
  }*/
  
  /*public void refreshReportView() {
    reportTableView.display();
  }*/

  public Tournament getCurrentlyDisplayTournament() {
    return currentTournament;
  }

  public SchoolManager getSchoolManager() {
    return schoolManager;
  }

  public void saveCurrentTournamentClicked() {
    if (currentTournament.isSavingNeeded()) {
      Repository.getInstance1().saveTournamentAsNewTournament(currentTournament, null);
    }
  }

  public void saveAsTournamentClicked() {
    String newName = newMain.showSaveAsTournamentConfirmationDialog();
    Repository.getInstance1().saveTournamentAsNewTournament(currentTournament, newName);
  }
  
  /**
   * This method is currently unused. 
   * @param newMainArg
   */
  public void setMainView(tourgen.util.INewMain newMainArg) {
    newMain = newMainArg;
  }
  
  public void changeCompetitionSite(Object school, Object oldMeet, Object newMeet) {
    Repository.getInstance1().changeCompetitionSiteForSchool(null, 
        currentTournament, 
        (tourgen.model.Meet)newMeet, 
        (tourgen.model.Meet)oldMeet, 
        (tourgen.model.School)school,
        schoolManager);
  }
  
  public void changeHostForMeet(Object meet, Object newHost) {
    Repository.getInstance1().changeHostOfMeet(null, 
        schoolManager, 
        currentTournament, 
        (tourgen.model.Meet)meet, 
        (tourgen.model.School) newHost);
  }

  public void removeTournamentClicked() {
	  Repository.getInstance1().removeTournament(currentTournament);
  }
}
