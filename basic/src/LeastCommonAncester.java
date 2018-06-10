import java.util.ArrayList;
import java.util.Arrays;

import basic.TreeNode;

public class LeastCommonAncester {

	public static void main(String[] args) {
		TreeNode<Integer>[] nodes = new TreeNode[7];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new TreeNode<Integer>(i);
			nodes[i].children = new ArrayList<TreeNode<Integer>>();
		}
		nodes[0].children = Arrays.asList(nodes[1], nodes[2]);
		nodes[1].children = Arrays.asList(nodes[3], nodes[4]);
		nodes[2].children = Arrays.asList(nodes[5], nodes[6]);
		TreeNode<Integer> rt = lca(nodes[0], nodes[3], nodes[5]);
		System.out.println(rt.val);
		rt = lca(nodes[0], nodes[3], nodes[1]);
		System.out.println(rt.val);

	}

	private static TreeNode<Integer> lca(TreeNode<Integer> root, TreeNode<Integer> n1, TreeNode<Integer> n2) {
		if (root == n1 || root == n2) {
			return root;
		}
		if (root == null) {
			return null;
		}
		TreeNode<Integer> candidate = null;
		for (TreeNode<Integer> child : root.children) {
			TreeNode<Integer> rt = lca(child, n1, n2);
			if (rt != null) {
				if (candidate == null) {
					candidate = rt;
				} else {
					return root;
				}
			}
		}
		return candidate;
	}

}
