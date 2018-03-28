package tourgen.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXLabel;

public class CollapsibleStagePanel extends JPanel{
	private JCustomizedButton button;
	private JXCollapsiblePane stageCollapsiblePane;
	private String title;
	private JXLabel content;
	
	public CollapsibleStagePanel(String titleArg, String panelContent, ActionListener listener) {
		title = titleArg;
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout());
		
		JPanel sectionalTitlePane = new JPanel();
		button = new JCustomizedButton(this, title);
		
		button.addActionListener(listener);
		sectionalTitlePane.setLayout(new BorderLayout(0, 0));
		
		sectionalTitlePane.add(button);
		
		stageCollapsiblePane = new JXCollapsiblePane();
		stageCollapsiblePane.setLayout(new BorderLayout());
		JPanel childPane = new JPanel();
		childPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		content = new JXLabel(panelContent);
		content.setLineWrap(true);
		
		childPane.add(content);
		stageCollapsiblePane.add(childPane);
		
		containerPanel.add(sectionalTitlePane,BorderLayout.NORTH);
		containerPanel.add(stageCollapsiblePane,BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(containerPanel);
	}
	
	public void setContent(String contentArg) {
		content.setText(contentArg);
	}
	
	public void setCollapsed(boolean flag) {
		stageCollapsiblePane.setCollapsed(flag);
	}
	
	public boolean isCollapsed() {
		return stageCollapsiblePane.isCollapsed();
	}
}
