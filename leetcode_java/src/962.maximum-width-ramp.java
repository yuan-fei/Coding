/*
 * @lc app=leetcode id=962 lang=java
 *
 * [962] Maximum Width Ramp
 *
 * https://leetcode.com/problems/maximum-width-ramp/description/
 *
 * algorithms
 * Medium (49.05%)
 * Likes:    1579
 * Dislikes: 42
 * Total Accepted:    42.6K
 * Total Submissions: 86.6K
 * Testcase Example:  '[6,0,8,2,1,5]'
 *
 * A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i]
 * <= nums[j]. The width of such a ramp is j - i.
 * 
 * Given an integer array nums, return the maximum width of a ramp in nums. If
 * there is no ramp in nums, return 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [6,0,8,2,1,5]
 * Output: 4
 * Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1]
 * = 0 and nums[5] = 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2]
 * = 1 and nums[9] = 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5 * 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[][] numsWithIdx = new int[n][2];
        for(int i = 0; i < n; i++){
            numsWithIdx[i][0] = nums[i];
            numsWithIdx[i][1] = i;
        }
        Arrays.sort(numsWithIdx, (a, b) -> Integer.compare(a[0], b[0]));
        int minId = n;
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, numsWithIdx[i][1] - minId);
            minId = Math.min(minId, numsWithIdx[i][1]);
        }
        // System.out.println(Arrays.deepToString(numsWithIdx));
        return ans;
    }
}
// @lc code=end
