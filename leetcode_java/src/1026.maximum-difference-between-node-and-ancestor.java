/*
 * @lc app=leetcode id=1026 lang=java
 *
 * [1026] Maximum Difference Between Node and Ancestor
 *
 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/description/
 *
 * algorithms
 * Medium (75.60%)
 * Likes:    4175
 * Dislikes: 100
 * Total Accepted:    219.3K
 * Total Submissions: 290K
 * Testcase Example:  '[8,3,10,1,6,null,14,null,null,4,7,13]'
 *
 * Given the root of a binary tree, find the maximum value v for which there
 * exist different nodes a and b where v = |a.val - b.val| and a is an ancestor
 * of b.
 * 
 * A node a is an ancestor of b if either: any child of a is equal to b or any
 * child of a is an ancestor of b.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are
 * given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 -
 * 1| = 7.
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,null,2,null,0,3]
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [2, 5000].
 * 0 <= Node.val <= 10^5
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
    public int maxAncestorDiff(TreeNode root) {
        maxAncestorDiff(root.left, root.val, root.val);
        maxAncestorDiff(root.right, root.val, root.val);
        return ans;
    }

    int ans = 0;

    void maxAncestorDiff(TreeNode root, int curMin, int curMax){
        if(root != null){
            ans = Math.max(ans, Math.abs(root.val - curMax));
            ans = Math.max(ans, Math.abs(root.val - curMin));
            maxAncestorDiff(root.left, Math.min(curMin, root.val), Math.max(curMax, root.val));
            maxAncestorDiff(root.right, Math.min(curMin, root.val), Math.max(curMax, root.val));
        }
    }
}
// @lc code=end
