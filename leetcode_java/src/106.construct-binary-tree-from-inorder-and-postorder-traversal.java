/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
 *
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 *
 * algorithms
 * Medium (41.19%)
 * Total Accepted:    171.2K
 * Total Submissions: 415.5K
 * Testcase Example:  '[9,3,15,20,7]\n[9,15,7,20,3]'
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * For example, given
 * 
 * 
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
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
	int[] io;
	int[] po;
	Map<Integer, Integer> ioMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	if(inorder.length == 0){
    		return null;
    	}
        io = inorder;
        po = postorder;
		ioMap = new HashMap<>();
        for (int i = 0; i < io.length; i++) {
        	ioMap.put(io[i], i);
        }
        return buildTree(0, 0, po.length);
    }

    TreeNode buildTree(int pl, int il, int len){
    	// System.out.println(""+pl+"," +il+","+len);
    	TreeNode root = new TreeNode(po[pl + len - 1]);
    	int rid = ioMap.get(po[pl + len - 1]);
    	if(rid - 1 >= il){
    		root.left = buildTree(pl, il, rid - il);
    	}
    	if(rid + 1 <= il + len - 1){
    		root.right = buildTree(pl + rid - il, rid + 1, il + len - 1 - rid);
    	}
    	return root;
    }
}
