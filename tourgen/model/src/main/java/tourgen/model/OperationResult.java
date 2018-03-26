public class OperationResult implements IOperationResult{
    private Object observerTicket;
    private OperationResultCodeEnum resultCode;
    private String errorMessage;
    private Object attachedObject;

    public OperationResult(Object observerTicketArg, OperationResultCodeEnum resultCodeArg, String errorMessageArg, Object attachedObject){
        this.observerTicket = observerTicketArg;
        this.resultCode = resultCodeArg;
        this.errorMessage = errorMessageArg;
        this.attachedObject = attachedObjectArg;
    }

    public Object getTicket(){
        return ticket;
    }
    public boolean isFailed(){
        return resultCode == OperationResult.FAILURE;
    }
    public boolean isOK(){
        return resultCode == OperationResult.SUCCESS;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Object getAttachedObject() {
        return attachedObject;
    }
}