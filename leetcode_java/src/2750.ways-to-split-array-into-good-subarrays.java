/*
 * @lc app=leetcode id=2750 lang=java
 *
 * [2750] Ways to Split Array Into Good Subarrays
 *
 * https://leetcode.com/problems/ways-to-split-array-into-good-subarrays/description/
 *
 * algorithms
 * Medium (33.20%)
 * Likes:    392
 * Dislikes: 11
 * Total Accepted:    18.6K
 * Total Submissions: 56.2K
 * Testcase Example:  '[0,1,0,0,1]'
 *
 * You are given a binary array nums.
 * 
 * A subarray of an array is good if it contains exactly one element with the
 * value 1.
 * 
 * Return an integer denoting the number of ways to split the array nums into
 * good subarrays. As the number may be too large, return it modulo 10^9 + 7.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [0,1,0,0,1]
 * Output: 3
 * Explanation: There are 3 ways to split nums into good subarrays:
 * - [0,1] [0,0,1]
 * - [0,1,0] [0,1]
 * - [0,1,0,0] [1]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,1,0]
 * Output: 1
 * Explanation: There is 1 way to split nums into good subarrays:
 * - [0,1,0]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 1
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    public int numberOfGoodSubarraySplits(int[] nums) {
        int n = nums.length;
        int lastOne = -1;
        long ans = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == 1){
                if(lastOne == -1){
                    ans = 1;
                }
                else{
                    ans *= i - lastOne;
                    ans %= MOD;
                }
                lastOne = i;
            }
        }
        return (int)ans;
    }
}
// @lc code=end
