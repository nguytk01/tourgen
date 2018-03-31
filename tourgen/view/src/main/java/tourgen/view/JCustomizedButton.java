package tourgen.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tourgen.util.ICustomizedButton;

public class JCustomizedButton extends JButton implements ICustomizedButton{
	private Object parent;
	public JCustomizedButton(Object parentArg, String title) {
		super(title);
		setHorizontalAlignment(SwingConstants.LEFT);
		parent = parentArg;
	}
	public Object getSwingParent() {
		return parent;
	}
}
