/*
 * @lc app=leetcode id=145 lang=java
 *
 * [145] Binary Tree Postorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 *
 * algorithms
 * Hard (48.41%)
 * Total Accepted:    265.6K
 * Total Submissions: 543.7K
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
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
 * Output: [3,2,1]
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
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        List<Integer> rt = new ArrayList<>();
        TreeNode last = null;
        while(!s.isEmpty()||root != null){
        	if(root == null){
        		if(last == s.peek().right||null==s.peek().right){
	        		last = s.pop();
	        		rt.add(last.val);	
        		}
        		else{
        			root = s.peek().right;
        		}
        	}
        	
        	while(root != null){
        		s.push(root);
        		root = root.left;
        	}
        }
        return rt;
    }
}
