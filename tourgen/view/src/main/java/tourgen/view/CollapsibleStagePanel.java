package tourgen.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXCollapsiblePane;

public class CollapsibleStagePanel extends JXCollapsiblePane{
	private JButton button;
	private JXCollapsiblePane stageCollapsiblePane;
	private String title;
	private JLabel content;
	
	public CollapsibleStagePanel(String titleArg, ActionListener listener) {
		title = titleArg;
		
		JPanel sectionalContainer = new JPanel();
		sectionalContainer.setLayout(new BorderLayout());
		
		JPanel sectionalTitlePane = new JPanel();
		button = new JButton(title);
		
		button.addActionListener(listener);
		sectionalTitlePane.setLayout(new BorderLayout(0, 0));
		
		sectionalTitlePane.add(button);
		
		stageCollapsiblePane = new JXCollapsiblePane();
		stageCollapsiblePane.setLayout(new BorderLayout());
		JPanel sectionalChildPane = new JPanel();
		content = new JLabel();
		
		stageCollapsiblePane.add(sectionalChildPane);
		
		sectionalContainer.add(sectionalTitlePane,BorderLayout.NORTH);
		sectionalContainer.add(stageCollapsiblePane,BorderLayout.CENTER);
	}
	
	public void setContent(String contentArg) {
		content.setText(contentArg);
	}
	
	public void setCollapsed(boolean flag) {
		stageCollapsiblePane.setCollapsed(flag);
	}
}
