/*
 * @lc app=leetcode id=337 lang=java
 *
 * [337] House Robber III
 *
 * https://leetcode.com/problems/house-robber-iii/description/
 *
 * algorithms
 * Medium (49.56%)
 * Likes:    2032
 * Dislikes: 43
 * Total Accepted:    130.9K
 * Total Submissions: 264.1K
 * Testcase Example:  '[3,2,3,null,3,null,1]'
 *
 * The thief has found himself a new place for his thievery again. There is
 * only one entrance to this area, called the "root." Besides the root, each
 * house has one and only one parent house. After a tour, the smart thief
 * realized that "all houses in this place forms a binary tree". It will
 * automatically contact the police if two directly-linked houses were broken
 * into on the same night.
 * 
 * Determine the maximum amount of money the thief can rob tonight without
 * alerting the police.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,2,3,null,3,null,1]
 * 
 * ⁠    3
 * ⁠   / \
 * ⁠  2   3
 * ⁠   \   \ 
 * ⁠    3   1
 * 
 * Output: 7 
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * 
 * Example 2:
 * 
 * 
 * Input: [3,4,5,1,3,null,1]
 * 
 * 3
 * ⁠   / \
 * ⁠  4   5
 * ⁠ / \   \ 
 * ⁠1   3   1
 * 
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
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
	static class Result{
		int incSum, exSum;
		Result(int is, int es){
			incSum = is;
			exSum = es;
		}

		int max(){
			return Math.max(incSum, exSum);
		}
	}

    public int rob(TreeNode root) {
        Result r = dfs(root);
        return r.max();
    }

    Result dfs(TreeNode root){
    	if(root == null){
    		return new Result(0, 0);
    	}
    	Result lRet = dfs(root.left);
    	Result rRet = dfs(root.right);
    	int incSum = root.val + lRet.exSum + rRet.exSum;
    	int exSum = lRet.max() + rRet.max();
    	return new Result(incSum, exSum);
    }
}
// @lc code=end
