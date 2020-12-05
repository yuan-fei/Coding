/*
 * @lc app=leetcode id=1669 lang=java
 *
 * [1669] Merge In Between Linked Lists
 *
 * https://leetcode.com/problems/merge-in-between-linked-lists/description/
 *
 * algorithms
 * Medium (81.94%)
 * Likes:    30
 * Dislikes: 1
 * Total Accepted:    3.4K
 * Total Submissions: 4.1K
 * Testcase Example:  '[0,1,2,3,4,5]\n3\n4\n[1000000,1000001,1000002]'
 *
 * You are given two linked lists: list1 and list2 of sizes n and m
 * respectively.
 * 
 * Remove list1's nodes from the a^th node to the b^th node, and put list2 in
 * their place.
 * 
 * The blue edges and nodes in the following figure incidate the result:
 * 
 * Build the result list and return its head.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 =
 * [1000000,1000001,1000002]
 * Output: [0,1,2,1000000,1000001,1000002,5]
 * Explanation: We remove the nodes 3 and 4 and put the entire list2 in their
 * place. The blue edges and nodes in the above figure indicate the result.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 =
 * [1000000,1000001,1000002,1000003,1000004]
 * Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
 * Explanation: The blue edges and nodes in the above figure indicate the
 * result.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= list1.length <= 10^4
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 10^4
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    	ListNode begin = list1;
    	int i = 0;
    	for(; i < a - 1; i++){
    		begin = begin.next;
    	}    
    	ListNode end = begin;
    	for(; i <= b; i++){
    		end = end.next;
    	}
    	ListNode l2end = list2;
    	while(l2end.next != null){
    		l2end = l2end.next;
    	}
    	begin.next = list2;
    	l2end.next = end;
    	return list1;
    }
}
// @lc code=end
