/*
 * @lc app=leetcode id=102 lang=java
 *
 * [102] Binary Tree Level Order Traversal
 *
 * https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 *
 * algorithms
 * Medium (49.68%)
 * Total Accepted:    416.1K
 * Total Submissions: 837.5K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * 
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 
 * 
 * return its level order traversal as:
 * 
 * [
 * ⁠ [3],
 * ⁠ [9,20],
 * ⁠ [15,7]
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
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> ans = new ArrayList<>();
    	if(root != null){
	    	Queue<TreeNode> q = new LinkedList<>();
	        q.offer(root);
	        while(!q.isEmpty()){
				int n = q.size();
				List<Integer> cur = new ArrayList<>();
		        for (int i = 0; i < n; i++) {
		        	TreeNode tn = q.poll();
		        	cur.add(tn.val);
		        	if(tn.left !=null){
		        		q.offer(tn.left);	
		        	}
		        	if(tn.right !=null){
		        		q.offer(tn.right);	
		        	}
		        }
		        ans.add(cur);
	        }	
    	}
        
        return ans;
    }
}
