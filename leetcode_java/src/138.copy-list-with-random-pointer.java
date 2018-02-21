/*
 * [138] Copy List with Random Pointer
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 *
 * algorithms
 * Medium (25.95%)
 * Total Accepted:    146.1K
 * Total Submissions: 563.6K
 * Testcase Example:  '{}'
 *
 * 
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * 
 * 
 * Return a deep copy of the list.
 * 
 */
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        return copyRandomList(head, map);
    }

    public RandomListNode copyRandomList(RandomListNode head, Map<RandomListNode, RandomListNode> map) {
    	if(head == null){
    		return null;
    	}
    	if(map.containsKey(head)){
    		return map.get(head);
    	}
    	else{
    		RandomListNode cloneHead = new RandomListNode(head.label);
    		map.put(head, cloneHead);

    		cloneHead.next = copyRandomList(head.next, map);
    		cloneHead.random = copyRandomList(head.random, map);

    		return cloneHead;
    	}
    }
}
