/*
 * @lc app=leetcode id=92 lang=java
 *
 * [92] Reverse Linked List II
 *
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
 * algorithms
 * Medium (35.75%)
 * Total Accepted:    208.9K
 * Total Submissions: 584.2K
 * Testcase Example:  '[1,2,3,4,5]\n2\n4'
 *
 * Reverse a linked list from position m to n. Do it in one-pass.
 * 
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * 
 * Example:
 * 
 * 
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        
        for(int i = 0 ; i < m-1; i++){
        	slow=slow.next;
        }
        ListNode firstTail = slow;
        ListNode midHead = slow.next;
        ListNode fast = slow.next;
        ListNode fastNext = fast.next;
        for(int i = m; i <= n; i++){
        	// System.out.println(fast.val+", "+slow.val);
        	fastNext = fast.next;
        	fast.next = slow;
        	slow = fast;
        	fast = fastNext;
        }
        ListNode midTail = slow;
        // System.out.println(midTail.val);
        ListNode lastHead = fast;
        // System.out.println(lastHead.val);
        firstTail.next = midTail;
        // System.out.println(firstTail.val);
        midHead.next = lastHead;
        // System.out.println(midHead.val);
        return dummy.next;
    }
}
