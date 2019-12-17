/*
 * @lc app=leetcode id=445 lang=java
 *
 * [445] Add Two Numbers II
 *
 * https://leetcode.com/problems/add-two-numbers-ii/description/
 *
 * algorithms
 * Medium (52.10%)
 * Likes:    970
 * Dislikes: 128
 * Total Accepted:    119.9K
 * Total Submissions: 230K
 * Testcase Example:  '[7,2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The most significant digit comes first and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the
 * lists is not allowed.
 * 
 * 
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * 
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = len(l1);
        int len2 = len(l2);
        Result r = add(l1, l2, len1, len2);
        ListNode head = r.head;
        if(r.carry > 0){
        	head = new ListNode(r.carry);
        	head.next = r.head;
        }
        return head;
    }

    private int len(ListNode l1){
    	int cnt = 0;
    	while(l1 != null){
    		l1 = l1.next;
    		cnt++;
    	}
    	return cnt;
    }

    private Result add(ListNode l1, ListNode l2, int p1, int p2){
    	Result r = new Result(null, 0);
    	ListNode n = new ListNode(0);
    	if(p2 > p1){
    		return add(l2, l1, p2, p1);
    	}
    	if(p1 > p2){
    		r = add(l1.next, l2, p1 - 1, p2);
    	}
    	else{
    		if(l1 == null){
    			return r;
    		}
    		else{
    			r = add(l1.next, l2.next, p1 - 1, p2 - 1);		
    			n = l2;
    		}
    	}
    	int v = (l1.val + n.val + r.carry) % 10;
    	int c = (l1.val + n.val + r.carry) / 10;
    	ListNode head = new ListNode(v);
    	head.next = r.head;
    	return new Result(head, c);
    }

    static class Result{
    	ListNode head;
    	int carry;
    	Result(ListNode h, int c){
    		head = h;
    		carry = c;
    	}
    }
}
// @lc code=end
