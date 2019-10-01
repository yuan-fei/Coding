/*
 * @lc app=leetcode id=143 lang=java
 *
 * [143] Reorder List
 *
 * https://leetcode.com/problems/reorder-list/description/
 *
 * algorithms
 * Medium (32.37%)
 * Total Accepted:    173.7K
 * Total Submissions: 535.1K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You may not modify the values in the list's nodes, only nodes itself may be
 * changed.
 * 
 * Example 1:
 * 
 * 
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * 
 * Example 2:
 * 
 * 
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
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
class Solution {
    public void reorderList(ListNode head) {
    	if(head == null){
    		return ;
    	}
    	ListNode fast = head;
    	int l = 0;
    	while(fast != null){
    		l++;
    		fast = fast.next;
    	}
    	int half = (l - 1) / 2;
    	fast = head;
    	while(half > 0){
    		half--;
    		fast = fast.next;
    	}
    	ListNode halfHead = fast.next;
    	fast.next = null;
    	halfHead = reverse(halfHead);
    	head = merge(head, halfHead);
    }

    ListNode reverse(ListNode head){
    	ListNode slow = null;
    	ListNode fast = head;
    	while(fast != null){
    		ListNode tmp = fast.next;
    		fast.next = slow;
    		slow = fast;
    		fast = tmp;
    	}
    	return slow;
    }

    ListNode merge(ListNode l1, ListNode l2){
    	ListNode head = l1;
    	ListNode tail = head;
    	while(l2 != null){
    		ListNode l2Nxt = l2.next;
    		l2.next = tail.next;
    		tail.next = l2;
    		tail = l2.next;
    		l2 = l2Nxt;
    	}
    	return head;
    }
}
