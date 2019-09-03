/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
 *
 * https://leetcode.com/problems/recover-binary-search-tree/description/
 *
 * algorithms
 * Hard (35.41%)
 * Total Accepted:    125.3K
 * Total Submissions: 353.8K
 * Testcase Example:  '[1,3,null,null,2]'
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,null,null,2]
 * 
 * 1
 * /
 * 3
 * \
 * 2
 * 
 * Output: [3,1,null,null,2]
 * 
 * 3
 * /
 * 1
 * \
 * 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,1,4,null,null,2]
 * 
 * ⁠ 3
 * ⁠/ \
 * 1   4
 * /
 * 2
 * 
 * Output: [2,1,4,null,null,3]
 * 
 * ⁠ 2
 * ⁠/ \
 * 1   4
 * /
 * ⁠ 3
 * 
 * 
 * Follow up:
 * 
 * 
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 * 
 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public void recoverTree(TreeNode root) {
        recoverTreeHelper(root);
        if(wrongNodes.size()==1){
        	wrongNodes.add(last);
        }
        int v = wrongNodes.get(0).val;
		wrongNodes.get(0).val = wrongNodes.get(1).val;
        wrongNodes.get(1).val = v;
    }

    List<TreeNode> wrongNodes = new ArrayList<>();
    TreeNode last = null;
    private void recoverTreeHelper(TreeNode root) {
    	if(wrongNodes.size()==2 || root == null){
    		return;
    	}
    	recoverTreeHelper(root.left);
    	if(last!=null && wrongNodes.size()!=2){
    		if(wrongNodes.isEmpty()){
    			if(last.val > root.val){
    				wrongNodes.add(last);
    			}
    		}
    		else{
    			if(wrongNodes.get(0).val < root.val){
    				wrongNodes.add(last);	
    			}
    		}
    	}
    	last = root;
    	recoverTreeHelper(root.right);
    }
}
