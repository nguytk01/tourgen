package tourgen.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Shell;

import tourgen.util.IReportTableView;

import javax.swing.JComboBox;
/*import org.jdesktop.swingx.JXCollapsiblePane;*/

public class ReportTableView extends JFrame implements IReportTableView{
	public void showReport() {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());
		shell.setText("Report");
		ExpandBar bar = new ExpandBar (shell, SWT.V_SCROLL);
		org.eclipse.swt.graphics.Image image = display.getSystemImage(SWT.ICON_QUESTION);
			
		// First item
		Composite composite = new Composite (bar, SWT.NONE);
		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		//layout.verticalSpacing = 10;
		composite.setLayout(layout);
		
		ExpandItem item0 = new ExpandItem (bar, SWT.NONE, 0);
		item0.setText("Secstional");
		item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item0.setControl(composite);
		item0.setImage(image);
	
		// Second item
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout (2, false);
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		
		ExpandItem item1 = new ExpandItem (bar, SWT.NONE, 1);
		item1.setText("Regional");
		item1.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item1.setControl(composite);
		item1.setImage(image);
	
		// Third item
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout (2, true);
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		
		ExpandItem item2 = new ExpandItem (bar, SWT.NONE, 2);
		item2.setText("Semi-state");
		item2.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item2.setControl(composite);
		item2.setImage(image);
		
		ExpandItem item3 = new ExpandItem (bar, SWT.NONE, 2);
		item3.setText("Final");
		item3.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item3.setControl(composite);
		item3.setImage(image);
	
		item1.setExpanded(true);
		bar.setSpacing(8);
		shell.setSize(400, 350);
		shell.open();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}
		image.dispose();
		display.dispose();
			}
	}	
	