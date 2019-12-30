/*
 * @lc app=leetcode id=112 lang=java
 *
 * [112] Path Sum
 *
 * https://leetcode.com/problems/path-sum/description/
 *
 * algorithms
 * Easy (39.42%)
 * Likes:    1310
 * Dislikes: 402
 * Total Accepted:    386.6K
 * Total Submissions: 980.5K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,null,1]\n22'
 *
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
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
 * ⁠/  \      \
 * 7    2      1
 * 
 * 
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * 
 */

// @lc code=start
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
    public boolean hasPathSum(TreeNode root, int sum) {
    	if(root == null){
    		return false;
    	}
        return dfs(root, sum, 0);
    }

    boolean dfs(TreeNode root, int sum, int cur){
    	if(root.left == null && root.right == null){
    		return cur + root.val == sum;
    	}
    	if(root.left != null && dfs(root.left, sum, cur + root.val)){
    		return true;
    	} 
    	if(root.right != null && dfs(root.right, sum, cur + root.val)){
    		return true;
    	}
    	return false;
    }
}
// @lc code=end
