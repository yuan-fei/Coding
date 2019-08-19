/*
 * @lc app=leetcode id=83 lang=java
 *
 * [83] Remove Duplicates from Sorted List
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 *
 * algorithms
 * Easy (43.16%)
 * Total Accepted:    352.8K
 * Total Submissions: 817K
 * Testcase Example:  '[1,1,2]'
 *
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * Example 1:
 * 
 * 
 * Input: 1->1->2
 * Output: 1->2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 1->1->2->3->3
 * Output: 1->2->3
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

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while(cur!=null && cur.next!=null){
            if(cur.val==cur.next.val){
                cur.next = cur.next.next;
            }
            else{
                cur = cur.next;    
            }
        }
        return head;
    }

    public ListNode deleteDuplicates1(ListNode head) {
    	if(head == null){
    		return null;
    	}
    	ListNode dummy  =new ListNode(-1);
        dummy.next=head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while(fast!=null){
        	if(fast.val!=slow.next.val){
        		slow = slow.next;
        		slow.next = fast;
        	}
        	fast = fast.next;
        }
        slow = slow.next;
        slow.next = fast;
        return dummy.next;
    }
}
