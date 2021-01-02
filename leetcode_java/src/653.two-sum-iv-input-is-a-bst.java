/*
 * @lc app=leetcode id=653 lang=java
 *
 * [653] Two Sum IV - Input is a BST
 *
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
 *
 * algorithms
 * Easy (56.01%)
 * Likes:    1790
 * Dislikes: 146
 * Total Accepted:    173.5K
 * Total Submissions: 309.4K
 * Testcase Example:  '[5,3,6,2,4,null,7]\n9'
 *
 * Given the root of a Binary Search Tree and a target number k, return true if
 * there exist two elements in the BST such that their sum is equal to the
 * given target.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [2,1,3], k = 4
 * Output: true
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: root = [2,1,3], k = 1
 * Output: false
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: root = [2,1,3], k = 3
 * Output: true
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^4 <= Node.val <= 10^4
 * root is guaranteed to be a valid binary search tree.
 * -10^5 <= k <= 10^5
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
	public boolean findTarget(TreeNode root, int k) {
		return find(root, root, k);
	}
    public boolean find(TreeNode topRoot, TreeNode root, int k) {
        if(root == null){
        	return false;
        }
        if(find(topRoot, root.left, k)){
        	return true;
        }
        return (k - root.val != root.val && exist(topRoot, k - root.val)) || find(topRoot, root.right, k);
    }

    boolean exist(TreeNode root, int k){
    	if(root == null){
    		return false;
    	}
    	if(root.val == k){
    		return true;
    	}
    	else if(root.val < k){
    		return exist(root.right, k);
    	}
    	else {
    		return exist(root.left, k);
    	}
    }
}
// @lc code=end
