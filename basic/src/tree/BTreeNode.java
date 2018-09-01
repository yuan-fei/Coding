package tree;

import java.util.ArrayList;
import java.util.List;

import utils.Util;

public class BTreeNode<K extends Comparable<K>> {
	int minDegree;
	public List<K> keys;
	public List<BTreeNode<K>> children;
	public boolean isLeaf;

	public BTreeNode(int minDegree, boolean isLeaf) {
		this.minDegree = minDegree;
		this.isLeaf = isLeaf;
		keys = new ArrayList<K>(2 * minDegree - 1);
		children = new ArrayList<BTreeNode<K>>(2 * minDegree);
	}

	public boolean isFull() {
		return keys.size() == 2 * minDegree - 1;
	}

	@Override
	public String toString() {
		return Util.toString(keys);
	}
}
