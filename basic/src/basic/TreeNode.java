package basic;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
	public T val;
	public List<TreeNode<T>> children;

	public TreeNode(T v) {
		val = v;
		children = new ArrayList<TreeNode<T>>();
	}
}
