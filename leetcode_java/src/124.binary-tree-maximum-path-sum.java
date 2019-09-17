/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 *
 * algorithms
 * Hard (30.91%)
 * Total Accepted:    222.1K
 * Total Submissions: 718.1K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a non-empty binary tree, find the maximum path sum.
 * 
 * For this problem, a path is defined as any sequence of nodes from some
 * starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the
 * root.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,3]
 * 
 * ⁠      1
 * ⁠     / \
 * ⁠    2   3
 * 
 * Output: 6
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [-10,9,20,null,null,15,7]
 * 
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * 
 * Output: 42
 * 
 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
	
    public int maxPathSum(TreeNode root) {
        int[] ans = dfs(root);
        return ans[1];
    }

    int[] dfs(TreeNode root){
    	if(root == null){
    		return null;
    	}
    	int[] ret = {root.val, root.val};
    	int[] left = dfs(root.left);
    	if(left != null){
    		ret[0] = Math.max(left[0] + root.val, ret[0]);
    		ret[1] = Math.max(left[0] + root.val, ret[1]);
    		ret[1] = Math.max(left[1], ret[1]);
    	}
    	int[] right = dfs(root.right);
    	if(right != null){
    		ret[0] = Math.max(right[0] + root.val, ret[0]);
    		ret[1] = Math.max(right[0] + root.val, ret[1]);
    		ret[1] = Math.max(right[1], ret[1]);
    	}
    	if(left != null && right != null){
    		ret[1] = Math.max(left[0] + right[0] + root.val, ret[1]);
    	}
    	return ret;
    }
}
