package tourgen.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OperationResultTests {

	private OperationResultEnum resultCode;
	
	@Test
	public void testOperationResultClass() {
		
		OperationResult opTest = new OperationResult(null, resultCode, null, null);
		
		
		opTest.getTicket();
		 resultCode= OperationResultEnum.FAILURE;
		opTest.isFailed();
		resultCode = OperationResultEnum.SUCCESS;
		opTest.isOk();
		opTest.getErrorMessage();
		
		opTest.getAttachedObject();
		
		  
		
	}

}
