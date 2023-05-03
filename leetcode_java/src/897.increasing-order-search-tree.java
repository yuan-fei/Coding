/*
 * @lc app=leetcode id=897 lang=java
 *
 * [897] Increasing Order Search Tree
 *
 * https://leetcode.com/problems/increasing-order-search-tree/description/
 *
 * algorithms
 * Easy (78.36%)
 * Likes:    3907
 * Dislikes: 649
 * Total Accepted:    248.4K
 * Total Submissions: 316.9K
 * Testcase Example:  '[5,3,6,2,4,null,8,1,null,null,null,7,9]'
 *
 * Given the root of a binary search tree, rearrange the tree in in-order so
 * that the leftmost node in the tree is now the root of the tree, and every
 * node has no left child and only one right child.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,1,7]
 * Output: [1,null,5,null,7]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the given tree will be in the range [1, 100].
 * 0 <= Node.val <= 1000
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
    TreeNode dummy = new TreeNode(-1);
    TreeNode cur = dummy;
    public TreeNode increasingBST(TreeNode root) {
        rec(root);
        return dummy.right;
    }

    void rec(TreeNode root){
        if(root != null){
            rec(root.left);
            cur.right = new TreeNode(root.val);
            cur = cur.right;
            rec(root.right);
        }
    }
}
// @lc code=end
