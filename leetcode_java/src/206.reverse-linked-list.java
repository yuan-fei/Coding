/*
 * @lc app=leetcode id=206 lang=java
 *
 * [206] Reverse Linked List
 *
 * https://leetcode.com/problems/reverse-linked-list/description/
 *
 * algorithms
 * Easy (58.56%)
 * Likes:    3251
 * Dislikes: 78
 * Total Accepted:    773.1K
 * Total Submissions: 1.3M
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * Reverse a singly linked list.
 * 
 * Example:
 * 
 * 
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * 
 * 
 * Follow up:
 * 
 * A linked list can be reversed either iteratively or recursively. Could you
 * implement both?
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	public ListNode reverseList(ListNode head) {
		if(head == null || head.next == null){
			return head;
		}
        ListNode nxt = head.next;
        ListNode newHead = reverseList(nxt);
        nxt.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseListIter(ListNode head) {
        if(head == null){
        	return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
        	ListNode t = cur.next;
        	cur.next = pre;
        	pre = cur;
        	cur = t;
        }
        return pre;
    }
}
// @lc code=end
