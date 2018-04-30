package tourgen.controller;

import javax.swing.JList;

import tourgen.model.Repository;
import tourgen.util.IAvailableMeetsPanel;

public class IAvailableMeetsPanelMock implements IAvailableMeetsPanel{

	  private Object selectedSchool;
	  private  javax.swing.DefaultListModel 
	  availableMeetListModel = new javax.swing.DefaultListModel();
	  
	  private javax.swing.JList<tourgen.model.Meet> availableMeetGraphicalList = 
		        new JList<tourgen.model.Meet>(availableMeetListModel);
	  private Object oldMeet;
	@Override
	public Object getSelectedSchool() {
		return Repository.getInstance1().getGirlList().get(0).getStageList()
				.get(0).getMeetList().get(0).getParticipatingSchool().get(0);
	}

	@Override
	public Object getNewMeet() {
		return Repository.getInstance1().getGirlList().get(0).getStageList()
				.get(0).getMeetList().get(1);
	}

	@Override
	public Object getOldMeet() {
		return Repository.getInstance1().getGirlList().get(0).getStageList()
				.get(0).getMeetList().get(0);
	}

}
