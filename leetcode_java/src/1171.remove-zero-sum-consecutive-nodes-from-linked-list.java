/*
 * @lc app=leetcode id=1171 lang=java
 *
 * [1171] Remove Zero Sum Consecutive Nodes from Linked List
 *
 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/
 *
 * algorithms
 * Medium (44.67%)
 * Total Accepted:    2.7K
 * Total Submissions: 6.1K
 * Testcase Example:  '[1,2,-3,3,1]'
 *
 * Given the head of a linked list, we repeatedly delete consecutive sequences
 * of nodes that sum to 0 until there are no such sequences.
 * 
 * After doing so, return the head of the final linked list.  You may return
 * any such answer.
 * 
 * 
 * (Note that in the examples below, all sequences are serializations of
 * ListNode objects.)
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
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
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        int psumFast = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy.next;
        map.put(0, dummy);
        while(fast!=null){
        	psumFast+=fast.val;
        	if(map.containsKey(psumFast)){
        		// System.out.println(psumFast);
        		ListNode slow = map.get(psumFast);
        		int psumSlow = psumFast;
        		while(slow.next!=fast){
        			psumSlow+=slow.next.val;
        			map.remove(psumSlow);
        			slow.next = slow.next.next;
        		}
        		slow.next = slow.next.next;
        	}
        	else{
        		map.put(psumFast, fast);
        	}
        	fast = fast.next;
        }
        
        return dummy.next;
    }
}
