/*
 * @lc app=leetcode id=971 lang=java
 *
 * [971] Flip Binary Tree To Match Preorder Traversal
 *
 * https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/description/
 *
 * algorithms
 * Medium (50.17%)
 * Likes:    912
 * Dislikes: 265
 * Total Accepted:    40.1K
 * Total Submissions: 79.8K
 * Testcase Example:  '[1,2]\n[2,1]'
 *
 * You are given the root of a binary tree with n nodes, where each node is
 * uniquely assigned a value from 1 to n. You are also given a sequence of n
 * values voyage, which is the desired pre-order traversal of the binary tree.
 * 
 * Any node in the binary tree can be flipped by swapping its left and right
 * subtrees. For example, flipping node 1 will have the following effect:
 * 
 * Flip the smallest number of nodes so that the pre-order traversal of the
 * tree matches voyage.
 * 
 * Return a list of the values of all flipped nodes. You may return the answer
 * in any order. If it is impossible to flip the nodes in the tree to make the
 * pre-order traversal match voyage, return the list [-1].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2], voyage = [2,1]
 * Output: [-1]
 * Explanation: It is impossible to flip the nodes such that the pre-order
 * traversal matches voyage.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3], voyage = [1,3,2]
 * Output: [1]
 * Explanation: Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal
 * matches voyage.
 * 
 * Example 3:
 * 
 * 
 * Input: root = [1,2,3], voyage = [1,2,3]
 * Output: []
 * Explanation: The tree's pre-order traversal already matches voyage, so no
 * nodes need to be flipped.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is n.
 * n == voyage.length
 * 1 <= n <= 100
 * 1 <= Node.val, voyage[i] <= n
 * All the values in the tree are unique.
 * All the values in voyage are unique.
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
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        if(root.val == voyage[curPos] && dfs(root, voyage, res)){
            return res;
        }
        else{
            return Arrays.asList(-1);
        }
    }
    
    int curPos = 0;
    
    boolean dfs(TreeNode r, int[] voyage, List<Integer> res){
        curPos++;
        if(r.left != null && r.left.val != voyage[curPos]){
            TreeNode t = r.left;
            r.left = r.right;
            r.right = t;
            res.add(r.val);
        }
        if(r.left != null && r.left.val != voyage[curPos]){
            return false;    
        }
        if(r.left != null && !dfs(r.left, voyage, res)){
            return false;
        }
        if(r.right != null){
            if(r.right.val != voyage[curPos]){
                return false;
            }
            if(!dfs(r.right, voyage, res)){
                return false;
            }    
        }
        return true;
    }
}
// @lc code=end
