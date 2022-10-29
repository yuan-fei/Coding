/*
 * @lc app=leetcode id=2447 lang=java
 *
 * [2447] Number of Subarrays With GCD Equal to K
 *
 * https://leetcode.com/problems/number-of-subarrays-with-gcd-equal-to-k/description/
 *
 * algorithms
 * Medium (40.73%)
 * Likes:    43
 * Dislikes: 26
 * Total Accepted:    8.5K
 * Total Submissions: 21.1K
 * Testcase Example:  '[9,3,1,2,6,3]\n3'
 *
 * Given an integer array nums and an integer k, return the number of subarrays
 * of nums where the greatest common divisor of the subarray's elements is k.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * 
 * The greatest common divisor of an array is the largest integer that evenly
 * divides all the array elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [9,3,1,2,6,3], k = 3
 * Output: 4
 * Explanation: The subarrays of nums where 3 is the greatest common divisor of
 * all the subarray's elements are:
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4], k = 7
 * Output: 0
 * Explanation: There are no subarrays of nums where 7 is the greatest common
 * divisor of all the subarray's elements.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], k <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] % k == 0){
                int g = nums[i];
                for(int j = i; j < n; j++){
                    g = gcd(g, nums[j]);
                    if(g == k){
                        ans++;
                    }
                }    
            }
        }
        return ans;
    }

    int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        else return gcd(b, a % b);
    }
}
// @lc code=end
