/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
 *
 * https://leetcode.com/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (42.35%)
 * Total Accepted:    259K
 * Total Submissions: 611.6K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * Given the below binary tree and sum = 22,
 * 
 * 
 * ⁠     5
 * ⁠    / \
 * ⁠   4   8
 * ⁠  /   / \
 * ⁠ 11  13  4
 * ⁠/  \    / \
 * 7    2  5   1
 * 
 * 
 * Return:
 * 
 * 
 * [
 * ⁠  [5,4,11,2],
 * ⁠  [5,8,4,5]
 * ]
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
	int sum = 0;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	this.sum = sum;
    	List<List<Integer>> ans = new ArrayList<>();
    	dfs(root, new ArrayList<Integer>(), ans, 0);
    	return ans;
    }

    void dfs(TreeNode root, List<Integer> cur, List<List<Integer>> ans, int pSum){
    	if(root != null){
    		cur.add(root.val);
    		pSum += root.val;
    		if(root.left == null && root.right == null){
    			if(pSum == sum){
    				ans.add(new ArrayList(cur));
    			}
    		}
    		else {
    			if(root.left != null){
	    			dfs(root.left, cur, ans, pSum);
	    		}
	    		if(root.right != null){
	    			dfs(root.right, cur, ans, pSum);
	    		}
    		}
    		cur.remove(cur.size() - 1);
    	}
    }
}
