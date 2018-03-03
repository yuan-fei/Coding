/*
 * [19] Remove Nth Node From End of List
 *
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 *
 * algorithms
 * Medium (34.15%)
 * Total Accepted:    230.8K
 * Total Submissions: 677.1K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given a linked list, remove the nth node from the end of list and return its
 * head.
 * 
 * For example,
 * 
 * 
 * ⁠  Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * ⁠  After removing the second node from the end, the linked list becomes
 * 1->2->3->5.
 * 
 * 
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
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
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        int i = 0;
        while(i < n){
        	if(fast == null){
        		return head;
        	}
        	else{
        		fast = fast.next;
        		i++;
        	}
        }

        while(fast != null){
        	slow = slow.next;
        	fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
