package tourgen.util;

public interface IRepositoryView extends java.util.Observer {
  public Object getSelectedTournament();

  public void populate();
}
