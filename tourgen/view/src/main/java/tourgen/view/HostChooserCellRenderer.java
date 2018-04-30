package tourgen.view;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import tourgen.model.School;
import tourgen.model.Tournament;

public class HostChooserCellRenderer extends javax.swing.JLabel implements ListCellRenderer {
  private static final JLabel label =  new JLabel("ha");
  private java.beans.PropertyChangeSupport propertyChangeSupport;
  private java.awt.event.MouseListener mouseListener;
  
  /*public HostChooserCellRenderer(java.awt.event.MouseListener mouseListenerArg) {
    //propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    //mouseListener = new HostChooserCellListener();
    
  }*/
  @Override
  public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
   //School school = (School)arg1;
   //System.out.println("get cell renderer");
   //this.setEditable(false);
   //this.setText(school.toString());
   //if (school != null) label.setText(school.toString());
   //else label.setText("");
   //return new JLabel("ha");
	  return label;
  }
  
  /*private class HostChooserCellListener implements java.awt.event.MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	  
  }*/
}
