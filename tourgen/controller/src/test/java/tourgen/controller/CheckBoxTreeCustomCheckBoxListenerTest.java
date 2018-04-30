package tourgen.controller;

import org.junit.Test;

public class CheckBoxTreeCustomCheckBoxListenerTest {

	private CheckBoxTreeListener checkBox = new CheckBoxTreeListener();
	MapDriverMock mapDriver = new MapDriverMock();
	private MapController controller = new MapController(null, mapDriver);

	@Test
	public void test() {

		CheckBoxTreeCustomCheckBoxListener customCheck = new CheckBoxTreeCustomCheckBoxListener(controller);
		customCheck.actionPerformed(null);
		
	}

}
