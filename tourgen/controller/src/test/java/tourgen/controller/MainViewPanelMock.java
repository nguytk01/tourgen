package tourgen.controller;

import tourgen.util.INewMainViewPane;

public class MainViewPanelMock implements INewMainViewPane {

  private Object tournament;
  @Override
  public void setActiveTournament(Object tournament) {
    this.tournament = tournament;
  }
  
  Object getTournamentJustSet(){
	  return tournament;
  }
}
