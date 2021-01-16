/*
 * @lc app=leetcode id=1721 lang=java
 *
 * [1721] Swapping Nodes in a Linked List
 *
 * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/description/
 *
 * algorithms
 * Medium (65.82%)
 * Likes:    85
 * Dislikes: 11
 * Total Accepted:    8.7K
 * Total Submissions: 13.2K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * You are given the head of a linked list, and an integer k.
 * 
 * Return the head of the linked list after swapping the values of the k^th
 * node from the beginning and the k^th node from the end (the list is
 * 1-indexed).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: head = [1], k = 1
 * Output: [1]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: head = [1,2], k = 1
 * Output: [2,1]
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: head = [1,2,3], k = 2
 * Output: [1,2,3]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 10^5
 * 0 <= Node.val <= 100
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
    	ListNode dummy = new ListNode(-1);
    	dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for(int i = 0; i < k - 1; i++){
        	fast = fast.next;
        }
        ListNode beginningPre = fast;
        fast = fast.next;
        while(fast.next != null){
        	fast = fast.next;
        	slow = slow.next;
        }
        ListNode endPre = slow;

        if(beginningPre != endPre){
        	ListNode beginning = beginningPre.next;
        	ListNode end = endPre.next;
        	System.out.println(beginning.val + ", " + end.val);
        	if(beginning == endPre){
        		ListNode t = end.next;
        		beginningPre.next = end;
        		end.next = beginning;
        		beginning.next = t;
        	}
        	else if(end == beginningPre){
        		ListNode t = beginning.next;
        		endPre.next = beginning;
        		beginning.next = end;
        		end.next = t;
        	}
        	else{
        		ListNode t = beginning.next;
	        	beginning.next = end.next;
	        	endPre.next = beginning;
	        	beginningPre.next = end;
	        	end.next = t;
        	}
        }
        return dummy.next;
    }
}
// @lc code=end
