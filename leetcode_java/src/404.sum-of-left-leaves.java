/*
 * @lc app=leetcode id=404 lang=java
 *
 * [404] Sum of Left Leaves
 *
 * https://leetcode.com/problems/sum-of-left-leaves/description/
 *
 * algorithms
 * Easy (50.51%)
 * Likes:    1005
 * Dislikes: 115
 * Total Accepted:    165.9K
 * Total Submissions: 328.1K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Find the sum of all left leaves in a given binary tree.
 * 
 * Example:
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * There are two left leaves in the binary tree, with values 9 and 15
 * respectively. Return 24.
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    int dfs(TreeNode root, boolean leftBranch){
    	if(root == null){
    		return 0;
    	}
    	if(root.left == null && root.right == null){
    		if(leftBranch){
    			return root.val;
    		}
    		else{
    			return 0;
    		}
    	}
    	return dfs(root.left, true) + dfs(root.right, false);
    }
}
// @lc code=end
