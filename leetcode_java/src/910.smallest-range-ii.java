/*
 * @lc app=leetcode id=910 lang=java
 *
 * [910] Smallest Range II
 *
 * https://leetcode.com/problems/smallest-range-ii/description/
 *
 * algorithms
 * Medium (35.27%)
 * Likes:    1484
 * Dislikes: 416
 * Total Accepted:    46.9K
 * Total Submissions: 133.1K
 * Testcase Example:  '[1]\n0'
 *
 * You are given an integer array nums and an integer k.
 * 
 * For each index i where 0 <= i < nums.length, change nums[i] to be either
 * nums[i] + k or nums[i] - k.
 * 
 * The score of nums is the difference between the maximum and minimum elements
 * in nums.
 * 
 * Return the minimum score of nums after changing the values at each index.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1], k = 0
 * Output: 0
 * Explanation: The score is max(nums) - min(nums) = 1 - 1 = 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,10], k = 2
 * Output: 6
 * Explanation: Change nums to be [2, 8]. The score is max(nums) - min(nums) =
 * 8 - 2 = 6.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,3,6], k = 3
 * Output: 3
 * Explanation: Change nums to be [4, 6, 3]. The score is max(nums) - min(nums)
 * = 6 - 3 = 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^4
 * 0 <= k <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int smallestRangeII(int[] A, int K) {
        int N = A.length;
        Arrays.sort(A);
        int ans = A[N-1] - A[0];

        for (int i = 0; i < A.length - 1; ++i) {
            int a = A[i], b = A[i+1];
            int high = Math.max(A[N-1] - K, a + K);
            int low = Math.min(A[0] + K, b - K);
            ans = Math.min(ans, high - low);
        }
        return ans;
    }
}
// @lc code=end
