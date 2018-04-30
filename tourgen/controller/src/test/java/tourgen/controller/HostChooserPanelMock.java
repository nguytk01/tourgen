package tourgen.controller;

import javax.swing.JList;

import tourgen.model.Repository;
import tourgen.util.IHostChooserPanel;

public class HostChooserPanelMock implements IHostChooserPanel{

	private Object selectedMeet;
	private JList list;
	
	@Override
	public Object getSelectedMeet() {
		return Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(2);
	}

	@Override
	public Object getNewHost() {
		return Repository.getInstance1().getGirlList().get(0).getStageList().get(0).getMeetList().get(2).getParticipatingSchool().get(1);
	}

}
