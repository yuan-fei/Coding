/*
 * @lc app=leetcode id=199 lang=java
 *
 * [199] Binary Tree Right Side View
 *
 * https://leetcode.com/problems/binary-tree-right-side-view/description/
 *
 * algorithms
 * Medium (49.53%)
 * Total Accepted:    194.2K
 * Total Submissions: 391.7K
 * Testcase Example:  '[1,2,3,null,5,null,4]'
 *
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 * 
 * ⁠  1            <---
 * ⁠/   \
 * 2     3         <---
 * ⁠\     \
 * ⁠ 5     4       <---
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
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	Queue<TreeNode> q = new LinkedList<>();
    	if(root != null){
    		q.offer(root);	
    	}
    	
    	while(!q.isEmpty()){
    		for(int i = q.size(); i > 0; i--){
    			TreeNode tn = q.poll();
    			if(i == 1){
    				res.add(tn.val);	
    			}
    			if(tn.left != null){
    				q.offer(tn.left);
    			}
    			if(tn.right != null){
    				q.offer(tn.right);
    			}
    		}
    	}
    	return res;
    }

    
}
