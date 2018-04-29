package tourgen.controller;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import tourgen.model.Meet;
import tourgen.util.ICheckNode;

public class CheckNodeMock implements ICheckNode {

	private Object meet;
	public CheckNodeMock(Meet meet){
		this.meet = meet;
	}
	
	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectionMode(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSelectionMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSelected(boolean isSelected) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValue() {
		return meet;
	}

}
