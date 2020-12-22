/*
 * @lc app=leetcode id=1696 lang=java
 *
 * [1696] Jump Game VI
 *
 * https://leetcode.com/problems/jump-game-vi/description/
 *
 * algorithms
 * Medium (37.25%)
 * Likes:    181
 * Dislikes: 12
 * Total Accepted:    4.6K
 * Total Submissions: 12.3K
 * Testcase Example:  '[1,-1,-2,4,-7,3]\n2'
 *
 * You are given a 0-indexed integer array nums and an integer k.
 * 
 * You are initially standing at index 0. In one move, you can jump at most k
 * steps forward without going outside the boundaries of the array. That is,
 * you can jump from index i to any index in the range [i + 1, min(n - 1, i +
 * k)] inclusive.
 * 
 * You want to reach the last index of the array (index n - 1). Your score is
 * the sum of all nums[j] for each index j you visited in the array.
 * 
 * Return the maximum score you can get.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3]
 * (underlined above). The sum is 7.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3]
 * (underlined above). The sum is 17.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length, k <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxResult(int[] nums, int k) {
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
        	while(!q1.isEmpty() && q1.peekFirst() + k < i){
        		q1.pollFirst();
        		q2.pollFirst();
        	}
        	int cur = nums[i];
        	if(!q2.isEmpty()){
        		cur += q2.peekFirst();
        	}
        	while(!q2.isEmpty() && q2.peekLast() < cur){
        		q1.pollLast();
        		q2.pollLast();
        	}
        	q1.offerLast(i);
        	q2.offerLast(cur);
        	// System.out.println(q2);
        }
        return q2.peekLast();
    }
}
// @lc code=end
