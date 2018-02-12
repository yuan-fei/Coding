/*
 * [2] Add Two Numbers
 *
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (28.43%)
 * Total Accepted:    438K
 * Total Submissions: 1.5M
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * 
 * Example
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * 
 * 
 */
/*
Analysis:
Dummy node for code simplification
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
        	return l2;
        }
        if(l2 == null){
        	return l1;
        }
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode head = new ListNode(-1);
        ListNode previous = head;
        int carry = 0;
        while(n1 != null && n2 != null){
        	int val = (n1.val + n2.val + carry) % 10;
        	carry = (n1.val + n2.val + carry) / 10;
        	previous.next = new ListNode(val);
        	previous = previous.next; 
        	n1 = n1.next;
        	n2 = n2.next;
        }
        ListNode remaining = (n1 != null)? n1 : n2;
        while(remaining != null){
        	int val = (remaining.val + carry) % 10;
        	carry = (remaining.val + carry) / 10;
        	previous.next = new ListNode(val);
        	previous = previous.next;
        	remaining = remaining.next;
        }
        if(carry != 0){
        	previous.next = new ListNode(carry);
        }
        return head.next;
    }
}
