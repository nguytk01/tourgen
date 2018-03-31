package tourgen.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.commons.text.WordUtils;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXLabel;

public class CollapsibleStagePanel extends JPanel{
	private JCustomizedButton button;
	private JXCollapsiblePane stageCollapsiblePane;
	private String title;
	private JTextPane content;
	private String contentStorage;
	private JPanel childPane;
	
	public CollapsibleStagePanel(String titleArg, String panelContent, ActionListener listener) {
		title = titleArg;
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout());
		
		JPanel sectionalTitlePane = new JPanel();
		button = new JCustomizedButton(this, title);
		
		button.addActionListener(listener);
		button.setFont(new Font ("Tahoma",Font.BOLD,24));
		sectionalTitlePane.setLayout(new BorderLayout());
		
		sectionalTitlePane.add(button);
		
		stageCollapsiblePane = new JXCollapsiblePane();
		stageCollapsiblePane.setLayout(new BorderLayout());
		stageCollapsiblePane.setCollapsed(true);
		childPane = new JPanel();
		childPane.setLayout(new BorderLayout());
		content = new JTextPane();
		Font font = new Font("Serif", Font.ITALIC,20);
		
		//StyledDocument d = content.getStyledDocument();
		//Style a = content.addStyle("Test", null);
		content.setContentType("text/html");
		HTMLEditorKit kit = new HTMLEditorKit();
		HTMLDocument doc = new HTMLDocument();
		content.setEditorKit(kit);
		content.setDocument(doc);
		//content.setText(WordUtils.wrap(panelContent, 60, "<br/>", true));
		try {
			kit.insertHTML(doc, doc.getLength(), "<font size=\"5\">" + panelContent+"</font>", 0, 0, null);
		} catch (BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		content.setEditable(false);
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
		if (flag == true) {
			contentStorage = content.getText();;
			//content.setText("");
			//childPane.remove(content);
			revalidate();
			repaint();

		} else {
			content.setText(contentStorage);
			//childPane.add(content);
			//stageCollapsiblePane.setCollapsed(true);
			//stageCollapsiblePane.setCollapsed(false);
			revalidate();
			repaint();
		}
		stageCollapsiblePane.setCollapsed(flag);
		revalidate();
	}
	
	public boolean isCollapsed() {
		return stageCollapsiblePane.isCollapsed();
	}
}
