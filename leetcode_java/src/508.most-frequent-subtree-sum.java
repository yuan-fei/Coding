/*
 * @lc app=leetcode id=508 lang=java
 *
 * [508] Most Frequent Subtree Sum
 *
 * https://leetcode.com/problems/most-frequent-subtree-sum/description/
 *
 * algorithms
 * Medium (57.92%)
 * Likes:    650
 * Dislikes: 121
 * Total Accepted:    72.1K
 * Total Submissions: 124.4K
 * Testcase Example:  '[5,2,-3]'
 *
 * 
 * Given the root of a tree, you are asked to find the most frequent subtree
 * sum. The subtree sum of a node is defined as the sum of all the node values
 * formed by the subtree rooted at that node (including the node itself). So
 * what is the most frequent subtree sum value? If there is a tie, return all
 * the values with the highest frequency in any order.
 * 
 * 
 * Examples 1
 * Input:
 * 
 * ⁠ 5
 * ⁠/  \
 * 2   -3
 * 
 * return [2, -3, 4], since all the values happen only once, return all of them
 * in any order.
 * 
 * 
 * Examples 2
 * Input:
 * 
 * ⁠ 5
 * ⁠/  \
 * 2   -5
 * 
 * return [2], since 2 happens twice, however -5 only occur once.
 * 
 * 
 * Note:
 * You may assume the sum of values in any subtree is in the range of 32-bit
 * signed integer.
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
	Map<Integer, Integer> m = new HashMap<>();
    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> ans = new ArrayList<>();
        int max = 0;
        for(int k : m.keySet()){
        	if(m.get(k) > max){
        		max = m.get(k);
        		ans = new ArrayList<>();
        		ans.add(k);
        	}
        	else if(m.get(k) == max){
        		ans.add(k);
        	}
        }
        int[] ret = new int[ans.size()];
        for(int i = 0; i < ret.length; i++){
        	ret[i] = ans.get(i);
        }
        return ret;
    }

    int dfs(TreeNode root){
    	if(root == null){
    		return 0;
    	}
    	if(root.left == null && root.right == null){
    		int cnt = m.getOrDefault(root.val, 0) + 1;
    		m.put(root.val, cnt);
    		return root.val;
    	}
    	int left = dfs(root.left);
    	int right = dfs(root.right);
    	int sum = left + right + root.val;
    	int cnt = m.getOrDefault(sum, 0) + 1;
    	m.put(sum, cnt);
    	return sum;
    }
}
// @lc code=end
