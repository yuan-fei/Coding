/*
 * [230] Kth Smallest Element in a BST
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 *
 * algorithms
 * Medium (45.02%)
 * Total Accepted:    134K
 * Total Submissions: 296.8K
 * Testcase Example:  '[1]\n1'
 *
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it.
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to
 * find the kth smallest frequently? How would you optimize the kthSmallest
 * routine?
 * 
 * Credits:Special thanks to @ts for adding this problem and creating all test
 * cases.
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
	private int counter = 0;
	private int result = 0;
    public int kthSmallest(TreeNode root, int k) {
        kthSmallestHelper(root, k);
        return result;
    }

    private void kthSmallestHelper(TreeNode root, int k) {
    	if(root == null){
    		return;
        }
        kthSmallestHelper(root.left, k);
        if(counter == k){
        	return;
        }
        counter++;
        if(counter == k){
        	result = root.val;
        	return;
        }
    	kthSmallestHelper(root.right, k);
    }
}
