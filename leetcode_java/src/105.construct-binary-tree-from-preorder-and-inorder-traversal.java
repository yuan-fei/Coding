/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 *
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 * algorithms
 * Medium (43.36%)
 * Total Accepted:    258.9K
 * Total Submissions: 597.1K
 * Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * For example, given
 * 
 * 
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * 
 * Return the following binary tree:
 * 
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
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
	int[] po;
	int[] io;
	Map<Integer, Integer> ioMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if(preorder.length == 0){
    		return null;
    	}
    	po = preorder;
    	io = inorder;
        ioMap = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
        	ioMap.put(inorder[i], i);
        }
        return buildTree(0, 0, preorder.length);
    }

    TreeNode buildTree(int pl, int ol, int len){
    	System.out.println(""+pl  + ","+ol + ","+len);
    	TreeNode root = new TreeNode(po[pl]);
    	int rIdx = ioMap.get(po[pl]);
    	if(rIdx > ol){
			root.left = buildTree(pl + 1, ol, rIdx - ol);
    	}
    	if(rIdx + 1 <= ol + len - 1){
			root.right = buildTree(pl - ol + rIdx + 1, rIdx + 1, len + ol - rIdx - 1);
    	}
    	return root;
    }
}
