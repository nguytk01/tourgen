package tourgen.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JCustomizedButton extends JButton {
	private JPanel parent;
	public JCustomizedButton(JPanel parentArg, String title) {
		super(title);
		setHorizontalAlignment(SwingConstants.LEFT);
		parent = parentArg;
	}
	public JPanel getParentPanel() {
		return parent;
	}
}
