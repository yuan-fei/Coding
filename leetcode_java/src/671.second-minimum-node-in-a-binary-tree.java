/*
 * @lc app=leetcode id=671 lang=java
 *
 * [671] Second Minimum Node In a Binary Tree
 *
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
 *
 * algorithms
 * Easy (42.84%)
 * Likes:    820
 * Dislikes: 1076
 * Total Accepted:    100.6K
 * Total Submissions: 234.8K
 * Testcase Example:  '[2,2,5,null,null,5,7]'
 *
 * Given a non-empty special binary tree consisting of nodes with the
 * non-negative value, where each node in this tree has exactly two or zero
 * sub-node. If the node has two sub-nodes, then this node's value is the
 * smaller value among its two sub-nodes. More formally, the property root.val
 * = min(root.left.val, root.right.val) always holds.
 * 
 * Given such a binary tree, you need to output the second minimum value in the
 * set made of all the nodes' value in the whole tree.
 * 
 * If no such second minimum value exists, output -1 instead.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [2,2,5,null,null,5,7]
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [2,2,2]
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest
 * value.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 25].
 * 1 <= Node.val <= 2^31 - 1
 * root.val == min(root.left.val, root.right.val) for each internal node of the
 * tree.
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
    public int findSecondMinimumValue(TreeNode root) {
		if(root == null || root.left == null){
			return -1;
		}        
		if(root.left.val < root.right.val){
			int l = findSecondMinimumValue(root.left);
			if(l == -1){
				l = root.right.val;
			}
			return Math.min(l, root.right.val);
		}
		else if(root.left.val > root.right.val){
			int r = findSecondMinimumValue(root.right);
			if(r == -1){
				r = root.left.val;
			}
			return Math.min(r, root.left.val);
		}
		else {
			int l = findSecondMinimumValue(root.left);
			int r = findSecondMinimumValue(root.right);
			if(l == -1 && r == -1){
				return -1;
			}
			else if(l == -1){
				return r;
			}
			else if(r == -1){
				return l;
			}
			else{
				return Math.min(l, r);
			}
		}
    }

}
// @lc code=end
