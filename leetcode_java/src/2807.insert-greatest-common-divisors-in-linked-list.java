/*
 * @lc app=leetcode id=2807 lang=java
 *
 * [2807] Insert Greatest Common Divisors in Linked List
 *
 * https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/description/
 *
 * algorithms
 * Medium (88.14%)
 * Likes:    483
 * Dislikes: 16
 * Total Accepted:    61.6K
 * Total Submissions: 69.8K
 * Testcase Example:  '[18,6,10,3]'
 *
 * Given the head of a linked list head, in which each node contains an integer
 * value.
 * 
 * Between every pair of adjacent nodes, insert a new node with a value equal
 * to the greatest common divisor of them.
 * 
 * Return the linked list after insertion.
 * 
 * The greatest common divisor of two numbers is the largest positive integer
 * that evenly divides both numbers.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [18,6,10,3]
 * Output: [18,6,6,2,10,1,3]
 * Explanation: The 1^st diagram denotes the initial linked list and the 2^nd
 * diagram denotes the linked list after inserting the new nodes (nodes in blue
 * are the inserted nodes).
 * - We insert the greatest common divisor of 18 and 6 = 6 between the 1^st and
 * the 2^nd nodes.
 * - We insert the greatest common divisor of 6 and 10 = 2 between the 2^nd and
 * the 3^rd nodes.
 * - We insert the greatest common divisor of 10 and 3 = 1 between the 3^rd and
 * the 4^th nodes.
 * There are no more adjacent nodes, so we return the linked list.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [7]
 * Output: [7]
 * Explanation: The 1^st diagram denotes the initial linked list and the 2^nd
 * diagram denotes the linked list after inserting the new nodes.
 * There are no pairs of adjacent nodes, so we return the initial linked
 * list.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the list is in the range [1, 5000].
 * 1 <= Node.val <= 1000
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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode cur = head;
        while(cur.next != null){
            ListNode n = new ListNode(gcd(cur.val, cur.next.val));
            n.next = cur.next;
            cur.next = n;
            cur = n.next;
        }
        return head;
    }

    int gcd(int a, int b){
        return (b == 0) ? a : gcd(b, a % b);
    }
}
// @lc code=end
