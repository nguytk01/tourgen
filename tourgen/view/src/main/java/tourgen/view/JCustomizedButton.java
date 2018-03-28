package tourgen.view;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JCustomizedButton extends JButton {
	private JPanel parent;
	public JCustomizedButton(JPanel parentArg, String title) {
		super(title);
		parent = parentArg;
	}
	public JPanel getParentPanel() {
		return parent;
	}
}
