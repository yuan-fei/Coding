/*
 * @lc app=leetcode id=988 lang=java
 *
 * [988] Smallest String Starting From Leaf
 *
 * https://leetcode.com/problems/smallest-string-starting-from-leaf/description/
 *
 * algorithms
 * Medium (50.61%)
 * Likes:    1623
 * Dislikes: 223
 * Total Accepted:    71.1K
 * Total Submissions: 140.1K
 * Testcase Example:  '[0,1,2,3,4,3,4]'
 *
 * You are given the root of a binary tree where each node has a value in the
 * range [0, 25] representing the letters 'a' to 'z'.
 * 
 * Return the lexicographically smallest string that starts at a leaf of this
 * tree and ends at the root.
 * 
 * As a reminder, any shorter prefix of a string is lexicographically
 * smaller.
 * 
 * 
 * For example, "ab" is lexicographically smaller than "aba".
 * 
 * 
 * A leaf of a node is a node that has no children.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [0,1,2,3,4,3,4]
 * Output: "dba"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [25,1,3,1,3,0,2]
 * Output: "adz"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [2,2,1,null,1,0,null,0]
 * Output: "abc"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 8500].
 * 0 <= Node.val <= 25
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
    public String smallestFromLeaf(TreeNode root) {
        List<StringBuilder> all = rec(root);
        // for(StringBuilder sb : all){
        //     sb.reverse();
        // }
        Collections.sort(all);
        return all.get(0).toString();
    }

    List<StringBuilder> rec(TreeNode root){
        if(root == null){
            return new ArrayList<>();
        }
        String rVal = "" + (char)('a' + root.val);
        if(root.left == null && root.right == null){
            List<StringBuilder> l = new ArrayList();
            l.add(new StringBuilder(rVal));
            return l;
        }
        List<StringBuilder> all = rec(root.left);
        all.addAll(rec(root.right));
        for(StringBuilder sb : all){
            sb.append(rVal);
        }
        return all;
    }
}
// @lc code=end
