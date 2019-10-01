/*
 * @lc app=leetcode id=114 lang=java
 *
 * [114] Flatten Binary Tree to Linked List
 *
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 *
 * algorithms
 * Medium (44.35%)
 * Total Accepted:    266.9K
 * Total Submissions: 601.9K
 * Testcase Example:  '[1,2,5,3,4,null,6]'
 *
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example, given the following tree:
 * 
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   5
 * ⁠/ \   \
 * 3   4   6
 * 
 * 
 * The flattened tree should look like:
 * 
 * 
 * 1
 * ⁠\
 * ⁠ 2
 * ⁠  \
 * ⁠   3
 * ⁠    \
 * ⁠     4
 * ⁠      \
 * ⁠       5
 * ⁠        \
 * ⁠         6
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
    public void flatten(TreeNode root) {
        dfs(root);
    }

    TreeNode dfs(TreeNode root){
    	if(root == null){
    		return null;
    	}
    	
    	TreeNode dummy = new TreeNode(0);
    	TreeNode tail = dummy;
    	if(root.left != null){
    		tail.right = root.left;
    		tail = dfs(root.left);
    	}
    	if(root.right != null){
    		tail.right = root.right;
    		tail = dfs(root.right);
    	}
    	root.left = null;
    	root.right = dummy.right;

    	return (tail == dummy)? root : tail;
    }
}
