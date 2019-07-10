/*
 * @lc app=leetcode id=144 lang=java
 *
 * [144] Binary Tree Preorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 *
 * algorithms
 * Medium (51.31%)
 * Total Accepted:    345.8K
 * Total Submissions: 670K
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * Example:
 * 
 * 
 * Input: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * Output: [1,2,3]
 * 
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
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        List<Integer> rt = new ArrayList<>();
        while(!s.isEmpty()||root!=null){
        	while(root!=null){
	        	rt.add(root.val);	
	        	if(root.right!=null){
	        		s.push(root.right);	
	        	}
	        	root=root.left;
        	}
        	if(!s.isEmpty()){
        		root = s.pop();	
        	}
        	
        }
        return rt;
    }
}
