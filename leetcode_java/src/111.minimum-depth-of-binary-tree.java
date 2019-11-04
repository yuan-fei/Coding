/*
 * @lc app=leetcode id=111 lang=java
 *
 * [111] Minimum Depth of Binary Tree
 *
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (36.08%)
 * Likes:    887
 * Dislikes: 502
 * Total Accepted:    337.1K
 * Total Submissions: 933.8K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * return its minimum depth = 2.
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
    public int minDepth(TreeNode root) {
    	if(root == null){
    		return 0;
    	}
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        while(!q.isEmpty()){
        	level++;
        	for(int i = q.size(); i > 0; i--){
        		TreeNode cur = q.poll();
        		if(cur.left == null && cur.right == null){
        			return level;
        		}
        		else{
        			if(cur.left != null){
        				q.offer(cur.left);
        			}
        			if(cur.right != null){
        				q.offer(cur.right);
        			}
        		}
        	}
        }
        return -1;
    }
}
// @lc code=end
