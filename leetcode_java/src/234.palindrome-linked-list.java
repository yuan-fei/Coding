/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
 *
 * https://leetcode.com/problems/palindrome-linked-list/description/
 *
 * algorithms
 * Easy (37.57%)
 * Likes:    2234
 * Dislikes: 307
 * Total Accepted:    332.5K
 * Total Submissions: 884.1K
 * Testcase Example:  '[1,2]'
 *
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Example 1:
 * 
 * 
 * Input: 1->2
 * Output: false
 * 
 * Example 2:
 * 
 * 
 * Input: 1->2->2->1
 * Output: true
 * 
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
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
    public boolean isPalindrome(ListNode head) {
        List<Integer> l = new ArrayList<>();
        while(head != null){
        	l.add(head.val);
        	head = head.next;
        }
        for(int i = 0; i < l.size(); i++){
        	if(!l.get(i).equals(l.get(l.size() - 1 - i))){
        		return false;
        	}
        }
        return true;
    }
}
// @lc code=end
