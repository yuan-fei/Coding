package tree.lca;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tree.TreeNode;
import tree.UnionFindSet;

/**
 * Tarjan's offline Least common ancester algorithm with union find set
 * (https://en.wikipedia.org/wiki/Tarjan%27s_off-line_lowest_common_ancestors_algorithm)
 * 
 * Offline means: the m query is fixed, and queries can be processed during the
 * dfs in O(n+m)
 */
public class OfflineAllPairLeastCommonAncester {

	public static void main(String[] args) {
		TreeNode<Integer>[] nodes = new TreeNode[7];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new TreeNode<Integer>(i);
		}
		nodes[0].children.addAll(Arrays.asList(nodes[1], nodes[2]));
		nodes[1].children.addAll(Arrays.asList(nodes[3], nodes[4]));
		nodes[2].children.addAll(Arrays.asList(nodes[5], nodes[6]));
		lca(nodes[0]);
	}

	static UnionFindSet<TreeNode<Integer>> ufs = new UnionFindSet<TreeNode<Integer>>();;
	static Map<TreeNode<Integer>, TreeNode<Integer>> ancester = new HashMap<TreeNode<Integer>, TreeNode<Integer>>();
	static Set<TreeNode<Integer>> visited = new HashSet<TreeNode<Integer>>();

	public static void lca(TreeNode<Integer> root) {
		ufs.makeSet(root);
		ancester.put(root, root);
		for (TreeNode<Integer> child : root.children) {
			lca(child);
			ufs.union(root, child);
			ancester.put(ufs.findSet(root), root);
		}
		visited.add(root);
		for (TreeNode<Integer> node : visited) {
			System.out.println(root.val + ", " + node.val + "=" + ancester.get(ufs.findSet(node)).val);
		}
	}
}
