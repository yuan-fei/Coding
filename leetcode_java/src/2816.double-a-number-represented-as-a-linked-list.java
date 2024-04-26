/*
 * @lc app=leetcode id=2816 lang=java
 *
 * [2816] Double a Number Represented as a Linked List
 *
 * https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/description/
 *
 * algorithms
 * Medium (48.78%)
 * Likes:    486
 * Dislikes: 8
 * Total Accepted:    42.3K
 * Total Submissions: 84.9K
 * Testcase Example:  '[1,8,9]'
 *
 * You are given the head of a non-empty linked list representing a
 * non-negative integer without leading zeroes.
 * 
 * Return the head of the linked list after doubling it.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,8,9]
 * Output: [3,7,8]
 * Explanation: The figure above corresponds to the given linked list which
 * represents the number 189. Hence, the returned linked list represents the
 * number 189 * 2 = 378.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [9,9,9]
 * Output: [1,9,9,8]
 * Explanation: The figure above corresponds to the given linked list which
 * represents the number 999. Hence, the returned linked list reprersents the
 * number 999 * 2 = 1998. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the list is in the range [1, 10^4]
 * 0 <= Node.val <= 9
 * The input is generated such that the list represents a number that does not
 * have leading zeros, except the number 0 itself.
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
    public ListNode doubleIt(ListNode head) {
        ListNode reverseHead = reverse(head);
        doubleReversed(reverseHead);
        return reverse(reverseHead);
    }

    ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode newHead = null;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }

    void doubleReversed(ListNode reverseHead){
        ListNode cur = reverseHead;
        int adder = 0;
        while(true){
            cur.val *= 2;
            cur.val += adder;
            adder = cur.val / 10;
            cur.val %= 10;
            if(cur.next != null){
                cur = cur.next;
            }
            else{
                break;
            }
        }
        if(adder > 0){
            cur.next = new ListNode(adder);
        }
    }
}
// @lc code=end
