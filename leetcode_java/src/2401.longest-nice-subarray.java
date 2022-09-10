/*
 * @lc app=leetcode id=2401 lang=java
 *
 * [2401] Longest Nice Subarray
 *
 * https://leetcode.com/problems/longest-nice-subarray/description/
 *
 * algorithms
 * Medium (34.26%)
 * Likes:    112
 * Dislikes: 6
 * Total Accepted:    5.9K
 * Total Submissions: 15.7K
 * Testcase Example:  '[1,3,8,48,10]'
 *
 * You are given an array nums consisting of positive integers.
 * 
 * We call a subarray of nums nice if the bitwise AND of every pair of elements
 * that are in different positions in the subarray is equal to 0.
 * 
 * Return the length of the longest nice subarray.
 * 
 * A subarray is a contiguous part of an array.
 * 
 * Note that subarrays of length 1 are always considered nice.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,8,48,10]
 * Output: 3
 * Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies
 * the conditions:
 * - 3 AND 8 = 0.
 * - 3 AND 48 = 0.
 * - 8 AND 48 = 0.
 * It can be proven that no longer nice subarray can be obtained, so we return
 * 3.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,1,5,11,13]
 * Output: 1
 * Explanation: The length of the longest nice subarray is 1. Any subarray of
 * length 1 can be chosen.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int l = 0;
        int ans = 0;
        int[] bCnt = new int[32];
        for(int r = 0; r < nums.length; r++){
            op(bCnt, nums[r], 1);
            while(l <= r && !isGood(bCnt)){
                op(bCnt, nums[l], -1);
                l++;
            }
            ans = Math.max(r - l + 1, ans);
        }
        return ans;
    }

    void op(int[] bCnt, int x, int delta){
        for(int i = 0; i < 32; i++){
            if(((x >> i) & 1) == 1){
                bCnt[i] += delta;
            }
        }
    }

    boolean isGood(int[] bCnt){
        for(int i = 0; i < 32; i++){
            if(bCnt[i] > 1){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
