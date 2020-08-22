/*
 * @lc app=leetcode id=515 lang=java
 *
 * [515] Find Largest Value in Each Tree Row
 *
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
 *
 * algorithms
 * Medium (61.16%)
 * Likes:    946
 * Dislikes: 63
 * Total Accepted:    105.9K
 * Total Submissions: 173.2K
 * Testcase Example:  '[1,3,2,5,3,null,9]'
 *
 * You need to find the largest value in each row of a binary tree.
 * 
 * Example:
 * 
 * Input: 
 * 
 * ⁠         1
 * ⁠        / \
 * ⁠       3   2
 * ⁠      / \   \  
 * ⁠     5   3   9 
 * 
 * Output: [1, 3, 9]
 * 
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
    public List<Integer> largestValues(TreeNode root) {
    	List<Integer> ans = new ArrayList<>();
    	if(root == null){
    		return ans;
    	}
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode cur = null;
        while(!q.isEmpty()){
        	int max = Integer.MIN_VALUE;
        	for(int i = q.size(); i > 0; i--){
        		cur = q.poll();
        		max = Math.max(max, cur.val);
        		if(cur.right != null){
        			q.offer(cur.right);
        		}
        		if(cur.left != null){
        			q.offer(cur.left);
        		}
        	}
        	ans.add(max);
        }
        return ans;
    }
}
// @lc code=end
