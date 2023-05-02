/*
 * @lc app=leetcode id=889 lang=java
 *
 * [889] Construct Binary Tree from Preorder and Postorder Traversal
 *
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/description/
 *
 * algorithms
 * Medium (71.04%)
 * Likes:    2476
 * Dislikes: 98
 * Total Accepted:    88.6K
 * Total Submissions: 124.7K
 * Testcase Example:  '[1,2,4,5,3,6,7]\n[4,5,2,6,7,3,1]'
 *
 * Given two integer arrays, preorder and postorder where preorder is the
 * preorder traversal of a binary tree of distinct values and postorder is the
 * postorder traversal of the same tree, reconstruct and return the binary
 * tree.
 * 
 * If there exist multiple answers, you can return any of them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: preorder = [1], postorder = [1]
 * Output: [1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * All the values of preorder are unique.
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * All the values of postorder are unique.
 * It is guaranteed that preorder and postorder are the preorder traversal and
 * postorder traversal of the same binary tree.
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
    Map<Integer, Integer> mPre = new HashMap<>();
    Map<Integer, Integer> mPost = new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for(int i = 0; i < postorder.length; i++){
            mPre.put(preorder[i], i);
            mPost.put(postorder[i], i);
        }
        return build(preorder, postorder, 0, preorder.length, 0, postorder.length);
    }

    TreeNode build(int[] preorder, int[] postorder, int startPre, int endPre, int startPost, int endPost){
        // System.out.println(Arrays.asList(startPre, endPre, startPost, endPost));
        if(startPre < endPre){
            TreeNode root = new TreeNode(preorder[startPre]);
            if(startPre + 1 < endPre){
                int postIdx = mPost.get(preorder[startPre + 1]);
                int preIdx = mPre.get(postorder[endPost - 2]);
                if(preorder[startPre + 1] == postorder[endPost - 2]){
                    root.left = build(preorder, postorder, startPre + 1, endPre, startPost, endPost - 1);
                }
                else{
                    root.left = build(preorder, postorder, startPre + 1, preIdx, startPost, postIdx + 1);
                    root.right = build(preorder, postorder, preIdx, endPre, postIdx + 1, endPost - 1);        
                }
            }
            return root;    
        }
        return null;
    }
}
// @lc code=end
