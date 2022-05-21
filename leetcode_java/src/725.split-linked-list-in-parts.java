/*
 * @lc app=leetcode id=725 lang=java
 *
 * [725] Split Linked List in Parts
 *
 * https://leetcode.com/problems/split-linked-list-in-parts/description/
 *
 * algorithms
 * Medium (56.84%)
 * Likes:    1796
 * Dislikes: 197
 * Total Accepted:    95.8K
 * Total Submissions: 168.5K
 * Testcase Example:  '[1,2,3]\n5'
 *
 * Given the head of a singly linked list and an integer k, split the linked
 * list into k consecutive linked list parts.
 * 
 * The length of each part should be as equal as possible: no two parts should
 * have a size differing by more than one. This may lead to some parts being
 * null.
 * 
 * The parts should be in the order of occurrence in the input list, and parts
 * occurring earlier should always have a size greater than or equal to parts
 * occurring later.
 * 
 * Return an array of the k parts.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,2,3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but its string representation as a
 * ListNode is [].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * Output: [[1,2,3,4],[5,6,7],[8,9,10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most
 * 1, and earlier parts are a larger size than the later parts.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the list is in the range [0, 1000].
 * 0 <= Node.val <= 1000
 * 1 <= k <= 50
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
    public ListNode[] splitListToParts(ListNode head, int k) {
        int cnt = 0;
        ListNode cur = head;
        while(cur != null){
            cnt++;
            cur = cur.next;
        }
        int cntBig = cnt % k;
        int valBig = (cnt + k - 1) / k;
        int cntSmall = k - cntBig;
        int valSmall = cnt / k;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode[] ret = new ListNode[k];
        int i = 0;
        while(cntBig > 0){
            cur = dummy;
            cnt = 0;
            while(cnt < valBig){
                cur = cur.next;
                cnt++;
            }
            ret[i++] = dummy.next;
            dummy.next = cur.next;
            cur.next = null;
            cntBig--;
        }
        while(cntSmall > 0){
            cur = dummy;
            cnt = 0;
            while(cnt < valSmall){
                cur = cur.next;
                cnt++;
            }
            ret[i++] = dummy.next;
            dummy.next = cur.next;
            cur.next = null;
            cntSmall--;
        }
        return ret;
    }
}
// @lc code=end
