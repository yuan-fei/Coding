/*
 * @lc app=leetcode id=1703 lang=java
 *
 * [1703] Minimum Adjacent Swaps for K Consecutive Ones
 *
 * https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/description/
 *
 * algorithms
 * Hard (34.41%)
 * Likes:    102
 * Dislikes: 3
 * Total Accepted:    1.3K
 * Total Submissions: 3.9K
 * Testcase Example:  '[1,0,0,1,0,1]\n2'
 *
 * You are given an integer array, nums, and an integer k. nums comprises of
 * only 0's and 1's. In one move, you can choose two adjacent indices and swap
 * their values.
 * 
 * Return the minimum number of moves required so that nums has k consecutive
 * 1's.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: 1
 * Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive
 * 1's.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,0,0,0,0,0,1,1], k = 3
 * Output: 5
 * Explanation: In 5 moves, the leftmost 1 can be shifted right until nums =
 * [0,0,0,0,0,1,1,1].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,1,0,1], k = 2
 * Output: 0
 * Explanation: nums already has 2 consecutive 1's.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * nums[i] is 0 or 1.
 * 1 <= k <= sum(nums)
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minMoves(int[] nums, int k) {
        List<Integer> pos1 = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
        	if(nums[i] == 1){
        		pos1.add(i);	
        	}
        }

        // trick: calibration for consecutive placement
        for(int i = 0; i < pos1.size(); i++){
        	pos1.set(i, pos1.get(i) - i);
        }

        long[] pSum = new long[pos1.size() + 1];
        for(int i = 1; i < pSum.length; i++){
        	pSum[i] = pSum[i - 1] + pos1.get(i - 1);
        }
        long min = Integer.MAX_VALUE;
		for(int i = 0; i + k <= pos1.size(); i++){
			// the minimum of k points is the sum of distances to the median
			long distToMedian = (pSum[i + k] - pSum[i + k / 2]) - (pSum[i + (k + 1) / 2] - pSum[i]);
        	min = Math.min(min, distToMedian);
        }        
        return (int)min;
    }

}
// @lc code=end
