/*
 * @lc app=leetcode id=103 lang=java
 *
 * [103] Binary Tree Zigzag Level Order Traversal
 *
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * algorithms
 * Medium (41.70%)
 * Total Accepted:    225.2K
 * Total Submissions: 535.6K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
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
 * return its zigzag level order traversal as:
 * 
 * [
 * ⁠ [3],
 * ⁠ [20,9],
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<List<Integer>> r = new ArrayList<>();
        boolean reverse = false;
        while(!q.isEmpty()){
        	int n = q.size();
        	List<Integer> l = new ArrayList<>();
        	for(int i = 0; i< n;i++){
        		TreeNode nd = q.poll();
        		if(nd!=null){
        			l.add(nd.val);	
        			q.offer(nd.left);
        			q.offer(nd.right);
        		}
        		
        	}
        	if(reverse){
        		Collections.reverse(l);
        	}
        	reverse=!reverse;
        	if(!l.isEmpty()){
        		r.add(l);	
        	}
        	
        }
        return r;
    }
}
