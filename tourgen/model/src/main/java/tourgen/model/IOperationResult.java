package tourgen.model;

public interface IOperationResult {
  public Object getTicket();

  public boolean isFailed();

  public boolean isOk();

  public String getErrorMessage();

  public Object getAttachedObject();
}