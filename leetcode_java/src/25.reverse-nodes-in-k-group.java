/*
 * [25] Reverse Nodes in k-Group
 *
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (31.26%)
 * Total Accepted:    120.9K
 * Total Submissions: 384.3K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the
 * linked list. If the number of nodes is not a multiple of k then left-out
 * nodes in the end should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be
 * changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example,
 * Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode fast = head;
        int count = 0;
        while(fast != null){
        	count++;
        	if(count == k){
        		ListNode nexHead = reverseKGroup(fast.next, k);
        		fast.next = null;
        		ListNode tail = head;
        		head = reverse(head);
        		tail.next = nexHead;
        		return head;
        	}
        	else{
        		fast = fast.next;
        	}
        }
        return head;

    }

    public ListNode reverse(ListNode head){
    	ListNode cur = head.next;
    	while(cur != null){
    		ListNode next = cur.next;
    		cur.next = head;
    		head = cur;
    		cur = next;
    	}
    	return head;
    }
}
