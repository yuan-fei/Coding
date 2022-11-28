/*
 * @lc app=leetcode id=2487 lang=java
 *
 * [2487] Remove Nodes From Linked List
 *
 * https://leetcode.com/problems/remove-nodes-from-linked-list/description/
 *
 * algorithms
 * Medium (68.65%)
 * Likes:    75
 * Dislikes: 0
 * Total Accepted:    8.1K
 * Total Submissions: 11.8K
 * Testcase Example:  '[5,2,13,3,8]'
 *
 * You are given the head of a linked list.
 * 
 * Remove every node which has a node with a strictly greater value anywhere to
 * the right side of it.
 * 
 * Return the head of the modified linked list.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of the nodes in the given list is in the range [1, 10^5].
 * 1 <= Node.val <= 10^5
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
    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> q = new ArrayDeque<>();
        ListNode cur = head;
        while(cur != null){
            while(!q.isEmpty() && q.peekLast().val < cur.val){
                q.pollLast();
            }
            q.offerLast(cur);
            cur = cur.next;
        }
        ListNode dummy = new ListNode();
        cur = dummy;
        while(!q.isEmpty()){
            cur.next = q.pollFirst();
            cur = cur.next;
            cur.next = null;
        }
        return dummy.next;
    }
}
// @lc code=end
