/*
 * @lc app=leetcode id=148 lang=java
 *
 * [148] Sort List
 *
 * https://leetcode.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (37.32%)
 * Total Accepted:    207.6K
 * Total Submissions: 555.3K
 * Testcase Example:  '[4,2,1,3]'
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * Example 1:
 * 
 * 
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
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
    public ListNode sortList(ListNode head) {
    	if(head == null || head.next == null){
    		return head;
    	}
    	ListNode[] lists = split(head);
    	// System.out.println(str(lists[0])+str(lists[1]));
    	ListNode l1 = sortList(lists[0]);
    	ListNode l2 = sortList(lists[1]);
    	ListNode ret = merge(l1, l2);
    	return ret;
    }


    ListNode merge(ListNode l1, ListNode l2){
    	if(l1 == null){
    		return l2;
    	}
    	if(l2 == null){
    		return l1;
    	}
    	ListNode dummy = new ListNode(0);
    	ListNode tail = dummy;
    	while(l1 != null && l2 != null){
    		if(l1.val < l2.val){
    			tail.next = l1;
    			l1 = l1.next;
    		}
    		else{
    			tail.next = l2;	
    			l2 = l2.next;
    		}
    		tail = tail.next;
    	}
    	if(l1 != null){
    		tail.next = l1;
    	}
    	if(l2 != null){
    		tail.next = l2;
    	}
    	return dummy.next;
    }

    ListNode[] split(ListNode head){
    	ListNode fast = head.next;
    	ListNode slow = head;
    	while(fast != null && fast.next != null){
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	ListNode mid = slow.next;
    	slow.next = null;
    	return new ListNode[]{head, mid};
    }

    String str(ListNode n){
    	return (n == null)?"null" : ""+n.val;
    }
}
