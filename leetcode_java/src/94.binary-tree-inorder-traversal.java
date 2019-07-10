/*
 * @lc app=leetcode id=94 lang=java
 *
 * [94] Binary Tree Inorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Medium (56.59%)
 * Total Accepted:    473.5K
 * Total Submissions: 830.3K
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * Example:
 * 
 * 
 * Input: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * Output: [1,3,2]
 * 
 * Follow up: Recursive solution is trivial, could you do it iteratively?
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
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> rt = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();

        while(!s.isEmpty()||root!=null){
        	if(root==null){
        		root = s.pop();
        		rt.add(root.val);	
        		root = root.right;
        	}
        	
	        while(root!=null){
	        	s.push(root);
	        	root = root.left;
	        }
        }
        return rt;
    }
}
