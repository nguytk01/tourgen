package tourgen.controller;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JTree;

import tourgen.util.ICheckNode;
import tourgen.util.IRepoTree;

public class RepoTreeMock extends JTree implements IRepoTree { 
	HashSet<ICheckNode> checkNodeSet = new HashSet<ICheckNode>();
	@Override
	public Set<ICheckNode> getMeetList() {
		return checkNodeSet;
	}

}
