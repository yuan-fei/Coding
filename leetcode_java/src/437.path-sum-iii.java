/*
 * @lc app=leetcode id=437 lang=java
 *
 * [437] Path Sum III
 *
 * https://leetcode.com/problems/path-sum-iii/description/
 *
 * algorithms
 * Easy (45.36%)
 * Likes:    3064
 * Dislikes: 245
 * Total Accepted:    167.8K
 * Total Submissions: 369.9K
 * Testcase Example:  '[10,5,-3,3,2,null,11,3,-2,null,1]\n8'
 *
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards
 * (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000.
 * 
 * Example:
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 * ⁠     10
 * ⁠    /  \
 * ⁠   5   -3
 * ⁠  / \    \
 * ⁠ 3   2   11
 * ⁠/ \   \
 * 3  -2   1
 * 
 * Return 3. The paths that sum to 8 are:
 * 
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
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
	Map<Integer, Integer> pSum2Cnt = new HashMap<>();
    public int pathSum(TreeNode root, int sum) {
        pSum2Cnt.put(0, 1);
        return dfs(root, sum, 0);
    }

    int dfs(TreeNode root, int sum, int cur){
    	if(root == null){
    		return 0;
    	}
    	int cnt = 0;
    	cur += root.val;
    	if(pSum2Cnt.containsKey(cur - sum)){
    		cnt += pSum2Cnt.get(cur - sum);
    	}
    	pSum2Cnt.put(cur, pSum2Cnt.getOrDefault(cur, 0) + 1);
    	cnt += dfs(root.left, sum, cur);
    	cnt += dfs(root.right, sum, cur);
    	pSum2Cnt.put(cur, pSum2Cnt.get(cur) - 1);
    	if(pSum2Cnt.get(cur) == 0){
    		pSum2Cnt.remove(cur);
    	}
    	return cnt;
    }
}
// @lc code=end
