/*
 * @lc app=leetcode id=1674 lang=java
 *
 * [1674] Minimum Moves to Make Array Complementary
 *
 * https://leetcode.com/problems/minimum-moves-to-make-array-complementary/description/
 *
 * algorithms
 * Medium (19.40%)
 * Likes:    43
 * Dislikes: 14
 * Total Accepted:    872
 * Total Submissions: 4.5K
 * Testcase Example:  '[1,2,4,3]\n4'
 *
 * You are given an integer array nums of even length n and an integer limit.
 * In one move, you can replace any integer from nums with another integer
 * between 1 and limit, inclusive.
 * 
 * The array nums is complementary if for all indices i (0-indexed), nums[i] +
 * nums[n - 1 - i] equals the same number. For example, the array [1,2,3,4] is
 * complementary because for all indices i, nums[i] + nums[n - 1 - i] = 5.
 * 
 * Return the minimum number of moves required to make nums complementary.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,4,3], limit = 4
 * Output: 1
 * Explanation: In 1 move, you can change nums to [1,2,2,3] (underlined
 * elements are changed).
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is
 * complementary.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,2,1], limit = 2
 * Output: 2
 * Explanation: In 2 moves, you can change nums to [2,2,2,2]. You cannot change
 * any number to 3 since 3 > limit.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,1,2], limit = 2
 * Output: 0
 * Explanation: nums is already complementary.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 2 <= n <= 10^5
 * 1 <= nums[i] <= limit <= 10^5
 * n is even.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minMoves(int[] nums, int limit) {
    	int n = nums.length;
    	int MAX = 100000;
    	int[] cnt = new int[2 * MAX + 1];

    	Map<Integer, Integer> freq = new HashMap<>();
    	for(int i = 0; i < n / 2; i++){
    		int sum = nums[i] + nums[n - 1 - i];
    		freq.put(sum, freq.getOrDefault(sum, 0) + 1);
    		int lb = 1 + Math.min(nums[i], nums[n - 1 - i]);
    		int ub = limit + Math.max(nums[i], nums[n - 1 - i]) + 1;
    		// System.out.println(sum + ", " + lb + "," + ub);
    		cnt[lb]++;
    		cnt[ub]--;
    	}
    	for(int i = 1; i < cnt.length; i++){
    		cnt[i] += cnt[i - 1];
    	}
    	System.out.println(freq);
    	int min = n;
    	for(int f = 0; f < cnt.length; f++){
    		// System.out.println(f + "," + cnt[f] + "," + freq.get(f));
    		min = Math.min(min, n - cnt[f] - freq.getOrDefault(f, 0));
    	}
    	return min;
    }

}
// @lc code=end
