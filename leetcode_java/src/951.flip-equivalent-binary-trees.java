/*
 * @lc app=leetcode id=951 lang=java
 *
 * [951] Flip Equivalent Binary Trees
 *
 * https://leetcode.com/problems/flip-equivalent-binary-trees/description/
 *
 * algorithms
 * Medium (66.86%)
 * Likes:    2187
 * Dislikes: 91
 * Total Accepted:    136.7K
 * Total Submissions: 204.3K
 * Testcase Example:  '[1,2,3,4,5,6,null,null,null,7,8]\n[1,3,2,null,6,4,5,null,null,null,null,8,7]'
 *
 * For a binary tree T, we can define a flip operation as follows: choose any
 * node, and swap the left and right child subtrees.
 * 
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can
 * make X equal to Y after some number of flip operations.
 * 
 * Given the roots of two binary trees root1 and root2, return true if the two
 * trees are flip equivalent or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 =
 * [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root1 = [], root2 = []
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root1 = [], root2 = [1]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in each tree is in the range [0, 100].
 * Each tree will have unique node values in the range [0, 99].
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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){
            return true;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        if(root1.val == root2.val){
            List<TreeNode> children1 = new ArrayList<>();
            List<TreeNode> children2 = new ArrayList<>();
            for(TreeNode c : new TreeNode[]{root1.left, root1.right}){
                if(c != null){
                    children1.add(c);
                }    
            }
            for(TreeNode c : new TreeNode[]{root2.left, root2.right}){
                if(c != null){
                    children2.add(c);
                }    
            }
            if(children1.size() != children2.size()){
                return false;
            }
            if(children1.size() == 0){
                return true;
            }
            if(children1.size() == 1){
                return flipEquiv(children1.get(0), children2.get(0));
            }
            if(children1.get(0).val != children2.get(0).val){
                children2.add(children2.remove(0));
            }
            if(children1.get(0).val == children2.get(0).val && children1.get(1).val == children2.get(1).val){
                return flipEquiv(children1.get(0), children2.get(0)) && flipEquiv(children1.get(1), children2.get(1));
            }
        }
        return false;
    }
}
// @lc code=end
