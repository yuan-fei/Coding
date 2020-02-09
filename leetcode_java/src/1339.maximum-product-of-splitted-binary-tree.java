/*
 * @lc app=leetcode id=1339 lang=java
 *
 * [1339] Maximum Product of Splitted Binary Tree
 *
 * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/
 *
 * algorithms
 * Medium (31.25%)
 * Likes:    47
 * Dislikes: 6
 * Total Accepted:    3K
 * Total Submissions: 9.4K
 * Testcase Example:  '[1,2,3,4,5,6]'
 *
 * Given a binary tree root. Split the binary tree into two subtrees by
 * removing 1 edge such that the product of the sums of the subtrees are
 * maximized.
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10.
 * Their product is 110 (11*10)
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation:  Remove the red edge and get 2 binary trees with sum 15 and
 * 6.Their product is 90 (15*6)
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [2,3,9,10,7,8,6,5,4,11,1]
 * Output: 1025
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: root = [1,1]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * Each tree has at most 50000 nodes and at least 2 nodes.
 * Each node's value is between [1, 10000].
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
	long max = 0;
	long total;
    public int maxProduct(TreeNode root) {
        total = dfs(root);
        max(root);
        return (int)(max % 1000000007);
    }

    long dfs(TreeNode root){
    	if(root == null){
    		return 0;
    	}
    	return root.val + dfs(root.left) + dfs(root.right);
    }

    long max(TreeNode root){
    	if(root == null){
    		return 0;
    	}
    	long sum = root.val + max(root.left) + max(root.right);
    	max = Math.max(sum * (total - sum), max);
    	// System.out.println(Arrays.asList(sum, sum * (total - sum)));
    	return sum;
    }
}
// @lc code=end
