/*
 * @lc app=leetcode id=689 lang=java
 *
 * [689] Maximum Sum of 3 Non-Overlapping Subarrays
 *
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 *
 * algorithms
 * Hard (47.87%)
 * Likes:    1418
 * Dislikes: 90
 * Total Accepted:    57.5K
 * Total Submissions: 120.1K
 * Testcase Example:  '[1,2,1,2,6,7,5,1]\n2'
 *
 * Given an integer array nums and an integer k, find three non-overlapping
 * subarrays of length k with maximum sum and return them.
 * 
 * Return the result as a list of indices representing the starting position of
 * each interval (0-indexed). If there are multiple answers, return the
 * lexicographically smallest one.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,1,2,6,7,5,1], k = 2
 * Output: [0,3,5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting
 * indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be
 * lexicographically larger.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
 * Output: [0,2,4]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] < 2^16
 * 1 <= k <= floor(nums.length / 3)
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] kSum = new int[n - k + 1];
        int[][] prefMax = new int[n - k + 1][2];
        int[][] sufMax = new int[n - k + 1][2];
        for(int i = 0; i < k; i++){
            kSum[0] += nums[i];
        }
        prefMax[0][0] = kSum[0];
        prefMax[0][1] = 0;
        for(int i = 1; i < kSum.length; i++){
            kSum[i] += kSum[i - 1];
            kSum[i] -= nums[i - 1];
            kSum[i] += nums[i + k - 1];
            if(prefMax[i - 1][0] >= kSum[i]){
                prefMax[i][0] = prefMax[i - 1][0];
                prefMax[i][1] = prefMax[i - 1][1];
            }
            else{
                prefMax[i][0] = kSum[i];
                prefMax[i][1] = i;
            }
        }
        sufMax[kSum.length - 1][0] = kSum[kSum.length - 1];
        sufMax[kSum.length - 1][1] = kSum.length - 1;
        for(int i = kSum.length - 2; i >= 0; i--){
            if(kSum[i] < sufMax[i + 1][0]){
                sufMax[i][0] = sufMax[i + 1][0];
                sufMax[i][1] = sufMax[i + 1][1];
            }
            else{
                sufMax[i][0] = kSum[i];
                sufMax[i][1] = i;
            }
        }
        // System.out.println(Arrays.toString(kSum));
        // System.out.println(Arrays.deepToString(prefMax));
        // System.out.println(Arrays.deepToString(sufMax));
        int max = 0;
        int[] ret = new int[3];
        Arrays.fill(ret, n);
        for(int i = k; i <= n - 2 * k; i++){
            if(prefMax[i - k][0] + sufMax[i + k][0] + kSum[i] > max){
                max = prefMax[i - k][0] + sufMax[i + k][0] + kSum[i];
                ret = new int[]{prefMax[i - k][1], i, sufMax[i + k][1]};
                System.out.println(Arrays.toString(ret) + ", " + max);
            }
            else if(prefMax[i - k][0] + sufMax[i + k][0] + kSum[i] == max){
                if(ret[0] > prefMax[i - k][1]){
                    ret = new int[]{prefMax[i - k][1], i, sufMax[i + k][1]};
                }
                else if(ret[1] > i){
                    ret = new int[]{prefMax[i - k][1], i, sufMax[i + k][1]};
                }
                else if(ret[2] > sufMax[i + k][1]){
                    ret = new int[]{prefMax[i - k][1], i, sufMax[i + k][1]};
                }
            }
        }
        return ret;
    }
}
// @lc code=end
