/*
 * [23] Merge k Sorted Lists
 *
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (28.01%)
 * Total Accepted:    208.7K
 * Total Submissions: 744.1K
 * Testcase Example:  '[]'
 *
 * 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
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
    public ListNode mergeKLists(ListNode[] lists) {
    	return mergeKLists2(lists);
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        boolean remaining = true;
        while(remaining){
        	int min = Integer.MAX_VALUE;
        	int minIndex = -1;
        	remaining = false;
        	for (int i = 0; i < lists.length; i++) {
        		if(lists[i] != null){
        			remaining = true;
        			if(lists[i].val < min){
        				min = lists[i].val;
        				minIndex = i;
        			}
        		}
        	}

        	if(remaining){
        		tail.next = lists[minIndex];
        		tail = tail.next;
        		lists[minIndex] = lists[minIndex].next;
        	}	
        }
        return head.next;
    }


    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        Queue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
        	public int compare(ListNode n1, ListNode n2){
        		return n1.val - n2.val;
        	}
        });
        for (int i = 0; i < lists.length; i++) {
    		if(lists[i] != null){
    			queue.offer(lists[i]);
    		}
        }
        while(!queue.isEmpty()){
        	ListNode min = queue.poll();
        	if(min.next != null){
        		queue.offer(min.next);
        	}	
    		tail.next = min;
    		tail = tail.next;
        }
        return head.next;
    }
}
