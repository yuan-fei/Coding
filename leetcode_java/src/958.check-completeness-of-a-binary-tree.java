/*
 * @lc app=leetcode id=958 lang=java
 *
 * [958] Check Completeness of a Binary Tree
 *
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (56.28%)
 * Likes:    4080
 * Dislikes: 53
 * Total Accepted:    209.1K
 * Total Submissions: 371.2K
 * Testcase Example:  '[1,2,3,4,5,6]'
 *
 * Given the root of a binary tree, determine if it is a complete binary tree.
 * 
 * In a complete binary tree, every level, except possibly the last, is
 * completely filled, and all nodes in the last level are as far left as
 * possible. It can have between 1 and 2^h nodes inclusive at the last level
 * h.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with
 * node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are
 * as far left as possible.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 100].
 * 1 <= Node.val <= 1000
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
    
    public boolean isCompleteTree(TreeNode root) {
        int cnt = 0;
        int label = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(new TreeNode(0, root.left, root.right));
        while(!q.isEmpty()){
            for(int x = q.size(); x > 0; x--){
                TreeNode t = q.poll();
                label = t.val;
                cnt++;
                if(t.left != null){
                    q.offer(new TreeNode(t.val * 2 + 1, t.left.left, t.left.right));
                }
                if(t.right != null){
                    q.offer(new TreeNode(t.val * 2 + 2, t.right.left, t.right.right));
                }
            }
        }
        // System.out.println(Arrays.asList(label, cnt - 1));
        return label == cnt - 1;
    }
}
// @lc code=end
