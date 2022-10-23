/*
 * @lc app=leetcode id=2444 lang=java
 *
 * [2444] Count Subarrays With Fixed Bounds
 *
 * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/
 *
 * algorithms
 * Hard (30.78%)
 * Likes:    196
 * Dislikes: 3
 * Total Accepted:    4.1K
 * Total Submissions: 13.5K
 * Testcase Example:  '[1,3,5,2,7,5]\n1\n5'
 *
 * You are given an integer array nums and two integers minK and maxK.
 * 
 * A fixed-bound subarray of nums is a subarray that satisfies the following
 * conditions:
 * 
 * 
 * The minimum value in the subarray is equal to minK.
 * The maximum value in the subarray is equal to maxK.
 * 
 * 
 * Return the number of fixed-bound subarrays.
 * 
 * A subarray is a contiguous part of an array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
 * Output: 2
 * Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,1,1], minK = 1, maxK = 1
 * Output: 10
 * Explanation: Every subarray of nums is a fixed-bound subarray. There are 10
 * possible subarrays.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i], minK, maxK <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int l = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int lastMin = -1;
        int lastMax = -1;
        long ans = 0;
        for(int r = 0; r < n; r++){
            if(minK == nums[r]){
                lastMin = r;
            }
            if(maxK == nums[r]){
                lastMax = r;
            }
            if(minK <= nums[r] && nums[r] <= maxK){
                if(l == -1){
                    l = r;
                }
                if(lastMax != -1 && lastMin != -1){
                    ans += Math.min(lastMax, lastMin) - l + 1;
                }
            }
            else{
                lastMax = -1;
                lastMin = -1;
                l = -1;
            }
        }
        return ans;
    }
}
// @lc code=end
