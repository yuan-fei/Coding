/*
 * @lc app=leetcode id=82 lang=java
 *
 * [82] Remove Duplicates from Sorted List II
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * algorithms
 * Medium (33.76%)
 * Total Accepted:    194.7K
 * Total Submissions: 575.9K
 * Testcase Example:  '[1,2,3,3,4,4,5]'
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * 
 * Example 1:
 * 
 * 
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 1->1->1->2->3
 * Output: 2->3
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
    	if(head==null){
    		return null;
    	}
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while(fast != null){
        	if(slow.next.val!=fast.val){
        		if(slow.next.next!=fast){
	        		slow.next=fast;
        		}
        		else{
        			slow=slow.next;	
        		}
        	}
        	fast=fast.next;
        }
        if(slow.next.next!=fast){
    		slow.next=fast;
		}
        return dummy.next;
    }
}
