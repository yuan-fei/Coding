/*
 * @lc app=leetcode id=86 lang=java
 *
 * [86] Partition List
 *
 * https://leetcode.com/problems/partition-list/description/
 *
 * algorithms
 * Medium (37.20%)
 * Total Accepted:    172.1K
 * Total Submissions: 453.8K
 * Testcase Example:  '[1,4,3,2,5,2]\n3'
 *
 * Given a linked list and a value x, partition it such that all nodes less
 * than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * Example:
 * 
 * 
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
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
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode left = dummy;
        ListNode cur = dummy;
        while(cur.next!=null){
        	if (cur.next.val < x) {
        		if(cur==left){
        			cur=cur.next;
        			left = left.next;
        		}
        		else{
	        		ListNode t1 = cur.next;
	        		ListNode t2 = left.next;
	        		cur.next = t1.next;
					t1.next = t2;
	        		left.next = t1;
	        		System.out.println(left.next.val);
	        		left = left.next;	
        		}
        	}
        	else{
        		cur= cur.next;	
        	}
        	// System.out.println(left.val);
        }
        return dummy.next;
    }
}
