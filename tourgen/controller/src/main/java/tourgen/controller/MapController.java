package tourgen.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import tourgen.model.Meet;
import tourgen.util.ICheckNode;
import tourgen.util.IMapDriver;
import tourgen.util.IRepoTree;

//import tourgen.view.TreeCheckBoxPT2.CheckNode;

public class MapController {

	private CheckBoxTreeListener checkBoxListener;
	private IMapDriver mapDriver;
	//private List<Meet> meetList = new ArrayList<Meet>();
	private IRepoTree tree;
	public MapController(CheckBoxTreeListener checkBoxListenerArg, IMapDriver mapDriver)
	{
		//checkBoxListener = checkBoxListenerArg; checkBoxListener.setMapController(this);
		this.mapDriver = mapDriver;
		//checkBoxListener.setMapController(this);
		
		//tree = treeArg;
		
	}
	public void showMeetsOnTheMap() {
		
			
			mapDriver.showMeetList(mapDriver.getMeetList());
	}
	
	
	public void treeCheckBoxClicked() {
		Set<ICheckNode> meetSet = tree.getMeetList();
		Object[] arr = meetSet.toArray();
		List<Meet> meetList = new ArrayList<Meet>();
		
		for (int i = 0; i < arr.length; i++) {
			meetList.add((Meet)((ICheckNode)arr[i]).getValue());
		}
		
		
		mapDriver.showMeetList(meetList);
		
		
		
		
		
	}
	
	public void setTree(IRepoTree treeArg) {
		tree = treeArg;
	}

	//public void add
	
	
	
	
	
	
}


