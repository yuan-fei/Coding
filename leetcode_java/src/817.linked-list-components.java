/*
 * @lc app=leetcode id=817 lang=java
 *
 * [817] Linked List Components
 *
 * https://leetcode.com/problems/linked-list-components/description/
 *
 * algorithms
 * Medium (57.90%)
 * Likes:    856
 * Dislikes: 1978
 * Total Accepted:    78.1K
 * Total Submissions: 134.9K
 * Testcase Example:  '[0,1,2,3]\n[0,1,3]'
 *
 * You are given the head of a linked list containing unique integer values and
 * an integer array nums that is a subset of the linked list values.
 * 
 * Return the number of connected components in nums where two values are
 * connected if they appear consecutively in the linked list.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [0,1,2,3], nums = [0,1,3]
 * Output: 2
 * Explanation: 0 and 1 are connected, so [0, 1] and [3] are the two connected
 * components.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [0,1,2,3,4], nums = [0,3,1,4]
 * Output: 2
 * Explanation: 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3,
 * 4] are the two connected components.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the linked list is n.
 * 1 <= n <= 10^4
 * 0 <= Node.val < n
 * All the values Node.val are unique.
 * 1 <= nums.length <= n
 * 0 <= nums[i] < n
 * All the values of nums are unique.
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
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> s = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int ans = 0;
        boolean open = false;
        while(head != null){
            if(s.contains(head.val)){
                if(!open){
                    ans++;                    
                }
                open = true;
            }
            else{
                open = false;
            }
            head = head.next;
        }
        return ans;
    }
}
// @lc code=end
