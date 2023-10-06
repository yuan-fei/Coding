/*
 * @lc app=leetcode id=965 lang=java
 *
 * [965] Univalued Binary Tree
 *
 * https://leetcode.com/problems/univalued-binary-tree/description/
 *
 * algorithms
 * Easy (70.12%)
 * Likes:    1805
 * Dislikes: 63
 * Total Accepted:    202.7K
 * Total Submissions: 288.6K
 * Testcase Example:  '[1,1,1,1,1,null,1]'
 *
 * A binary tree is uni-valued if every node in the tree has the same value.
 * 
 * Given the root of a binary tree, return true if the given tree is
 * uni-valued, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,1,1,1,1,null,1]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [2,2,2,5,2]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 100].
 * 0 <= Node.val < 100
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
    int v = -1;
    public boolean isUnivalTree(TreeNode root) {
        if(root == null){
            return true;
        }
        if(v != -1 && root.val != v){
            return false;
        }
        else{
            v = root.val;
            return isUnivalTree(root.left) && isUnivalTree(root.right);
        }
    }
}
// @lc code=end
