/*
 * @lc app=leetcode id=109 lang=java
 *
 * [109] Convert Sorted List to Binary Search Tree
 *
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
 *
 * algorithms
 * Medium (42.67%)
 * Total Accepted:    194.3K
 * Total Submissions: 455.4K
 * Testcase Example:  '[-10,-3,0,5,9]'
 *
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 * Example:
 * 
 * 
 * Given the sorted linked list: [-10,-3,0,5,9],
 * 
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following
 * height balanced BST:
 * 
 * ⁠     0
 * ⁠    / \
 * ⁠  -3   9
 * ⁠  /   /
 * ⁠-10  5
 * 
 * 
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
    public TreeNode sortedListToBST(ListNode head) {
    	if(head == null){
    		return null;
    	}
        return sortedListToBST(head, null);
    }

    TreeNode sortedListToBST(ListNode head, ListNode tail){
    	if(head == tail){
    		return null;
    	}
    	ListNode middle = findMiddle(head, tail);
    	TreeNode root = new TreeNode(middle.val);
    	root.left = sortedListToBST(head, middle);
    	root.right = sortedListToBST(middle.next, tail);
    	return root;
    }

    ListNode findMiddle(ListNode head, ListNode tail){
    	ListNode slow = head;
    	ListNode fast = head;
    	while(fast != tail && fast.next != tail){
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	return slow;
    }
}
