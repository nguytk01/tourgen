package tourgen.model;

public class OperationResult implements IOperationResult {
  private Object observerTicket;
  private OperationResultEnum resultCode;
  private String errorMessage;
  private Object attachedObject;
  
  /**
   * this is to construct an instance of operation result.
   * @param observerTicketArg is the ticket for the observer
   * @param resultCodeArg is the result code of the operation
   * @param errorMessageArg is the error message sent from the model
   * @param attachedObjectArg is the object sent by model
   */
  public OperationResult(Object observerTicketArg, OperationResultEnum resultCodeArg,
      String errorMessageArg, Object attachedObjectArg) {
    this.observerTicket = observerTicketArg;
    this.resultCode = resultCodeArg;
    this.errorMessage = errorMessageArg;
    this.attachedObject = attachedObjectArg;
  }

  public Object getTicket() {
    return observerTicket;
  }

  public boolean isFailed() {
    return resultCode == OperationResultEnum.FAILURE;
  }

  public boolean isOk() {
    return resultCode == OperationResultEnum.SUCCESS;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Object getAttachedObject() {
    return attachedObject;
  }
}