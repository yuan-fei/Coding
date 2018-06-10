package basic;

import java.util.List;

public class TreeNode<T> {
	public T val;
	public List<TreeNode<T>> children;

	public TreeNode(T v) {
		val = v;
	}
}
