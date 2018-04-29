package tourgen.controller;

import java.util.HashSet;
import java.util.Set;

import tourgen.util.ICheckNode;
import tourgen.util.IRepoTree;

public class RepoTreeMock implements IRepoTree {
	HashSet<ICheckNode> checkNodeSet = new HashSet<ICheckNode>();
	@Override
	public Set<ICheckNode> getMeetList() {
		return checkNodeSet;
	}

}
