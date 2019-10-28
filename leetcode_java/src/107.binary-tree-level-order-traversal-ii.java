/*
 * @lc app=leetcode id=107 lang=java
 *
 * [107] Binary Tree Level Order Traversal II
 *
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
 *
 * algorithms
 * Easy (48.63%)
 * Likes:    881
 * Dislikes: 167
 * Total Accepted:    255.4K
 * Total Submissions: 524.9K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the bottom-up level order traversal of its
 * nodes' values. (ie, from left to right, level by level from leaf to root).
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
 * return its bottom-up level order traversal as:
 * 
 * [
 * ⁠ [15,7],
 * ⁠ [9,20],
 * ⁠ [3]
 * ]
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root != null){
        	q.offer(root);	
        }
        
        while(!q.isEmpty()){
        	List<Integer> level = new ArrayList<>();
        	for (int i = q.size(); i > 0; i--) {
        		TreeNode cur = q.poll();
        		level.add(cur.val);
        		if(cur.left != null){
					q.offer(cur.left);        			
        		}
        		if(cur.right != null){
        			q.offer(cur.right);	
        		}
        	}
        	ans.add(level);
        }
        Collections.reverse(ans);
        return ans;
    }
}
// @lc code=end
