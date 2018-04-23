package tourgen.view;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Observable;

import javax.swing.SwingUtilities;

import tourgen.model.Repository;
import tourgen.model.Tournament;

public class TournamentChooserComboBox extends javax.swing.JComboBox 
implements tourgen.util.ITournamentChooserComboBox, java.util.Observer, java.beans.PropertyChangeListener {
  
	private tourgen.controller.NewMainViewController newMainViewController;
	private java.awt.event.ActionListener tournamentChooserActionListener;
	private int proposedSelectedIndex;
	
	public TournamentChooserComboBox(java.awt.event.ActionListener tournamentChooserActionListenerArg) {
		//this.addActionListener(tournamentChooserActionListener);
    //this.addItemListener( (java.awt.event.ItemListener)tournamentChooserActionListener);
		tournamentChooserActionListener = tournamentChooserActionListenerArg;
		Repository.getInstance1().addPropertyChangeListener(this);
		this.setRenderer(new TournamentChooserComboBoxCellRenderer());
		proposedSelectedIndex = 0;
	}

	@Deprecated
	@Override
	public int getSelectedTournamentIndex() {
		return this.getSelectedIndex();
	}
	
	public void initUserInterface() {
		repopulate();
	}
	
	public void resetUserInterface() {
		this.removeAllItems();
		repopulate();
	}
	
	public void repopulate() {
	    //this.removeActionListener(tournamentChooserActionListener);
	    SwingUtilities.invokeLater( new Runnable() {
	      

	      @Override
	      public void run() {
		for (tourgen.model.Tournament tour: Repository.getInstance1().getGirlList()) {
			addItem(tour);
		}
		//this.addItemListener( (java.awt.event.ItemListener)tournamentChooserActionListener);
		System.out.println("repopulate combobox/");
		//somehow populating the combobox will fire actionListener
	    addActionListener(tournamentChooserActionListener);

		setSelectedIndex(proposedSelectedIndex);
		proposedSelectedIndex = 0;
	      }});
	    }
	
  @Override
  public void update(Observable arg0, Object arg1) {
    this.getSelectedIndex();
    List<Tournament> tourList = Repository.getInstance1().getGirlList();
    for (tourgen.model.Tournament tour: Repository.getInstance1().getGirlList()) {
      this.addItem(tour);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    System.out.println("hey property Change combobox?");
    Tournament currentTournament = (Tournament)this.getSelectedItem();
    int currentTournamentPosition = this.getSelectedIndex();
    if (evt.getPropertyName() == Repository.TOURNAMENT_MODIFIED 
    		|| evt.getPropertyName() == Repository.TOURNAMENT_OVERWRITTEN) {}
    else if (evt.getPropertyName() == Repository.TOURNAMENT_REMOVED) {
    	Tournament tournamentRemoved = (Tournament)evt.getNewValue();
    	//if (tournamentRemoved == currentTournament) {
    		if ( currentTournamentPosition == this.getItemCount() - 1 ) {
    			proposedSelectedIndex = this.getItemCount() - 2;
    		} else { 
    			proposedSelectedIndex = currentTournamentPosition;
    		}
    		resetUserInterface();
    	//}
    } else if ( evt.getPropertyName() == Repository.TOURNAMENT_SAVEAS ) {
    	resetUserInterface();
    	System.out.println("currentTournamentPosition " + currentTournamentPosition );
    	System.out.println("itemCount " + this.getItemCount());
    	proposedSelectedIndex = currentTournamentPosition + 1;
    	//this.setSelectedIndex(currentTournamentPosition + 1);
    }
    
  }
}
