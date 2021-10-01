/*
 * @lc app=leetcode id=687 lang=java
 *
 * [687] Longest Univalue Path
 *
 * https://leetcode.com/problems/longest-univalue-path/description/
 *
 * algorithms
 * Medium (38.47%)
 * Likes:    2649
 * Dislikes: 585
 * Total Accepted:    126.2K
 * Total Submissions: 328K
 * Testcase Example:  '[5,4,5,1,1,5]'
 *
 * Given the root of a binary tree, return the length of the longest path,
 * where each node in the path has the same value. This path may or may not
 * pass through the root.
 * 
 * The length of the path between two nodes is represented by the number of
 * edges between them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,4,5,1,1,5]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,4,5,4,4,5]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 10^4].
 * -1000 <= Node.val <= 1000
 * The depth of the tree will not exceed 1000.
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
    public int longestUnivaluePath(TreeNode root) {
        dfs(root, -1);
        return max - 1;
    }
    int MAXV = 2000;
    int max = 1;

    int dfs(TreeNode root, int parentV){
        if(root == null){
            return 0;
        }
        int l = dfs(root.left, root.val);
        int r = dfs(root.right, root.val);
        max = Math.max(max, l + r + 1);
        return parentV == root.val? Math.max(l, r) + 1 : 0;
    }
}
// @lc code=end
