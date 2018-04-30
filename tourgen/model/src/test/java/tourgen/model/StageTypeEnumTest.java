package tourgen.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StageTypeEnumTest {

	@Test
	public void test() {
		assertEquals(true, StageType.SECTIONAL.equals(StageType.REGIONAL.getLowerStageType()));
		assertEquals(true, StageType.REGIONAL.equals(StageType.SEMISTATE.getLowerStageType()));
		assertEquals(true, StageType.SEMISTATE.equals(StageType.STATEFINAL.getLowerStageType()));
		
		StageType stage = null;
	
	
	
	}
	

}
