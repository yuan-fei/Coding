/*
 * @lc app=leetcode id=2470 lang=java
 *
 * [2470] Number of Subarrays With LCM Equal to K
 *
 * https://leetcode.com/problems/number-of-subarrays-with-lcm-equal-to-k/description/
 *
 * algorithms
 * Medium (38.02%)
 * Likes:    147
 * Dislikes: 12
 * Total Accepted:    12.5K
 * Total Submissions: 32.8K
 * Testcase Example:  '[3,6,2,7,1]\n6'
 *
 * Given an integer array nums and an integer k, return the number of subarrays
 * of nums where the least common multiple of the subarray's elements is k.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * 
 * The least common multiple of an array is the smallest positive integer that
 * is divisible by all the array elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,6,2,7,1], k = 6
 * Output: 4
 * Explanation: The subarrays of nums where 6 is the least common multiple of
 * all the subarray's elements are:
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3], k = 2
 * Output: 0
 * Explanation: There are no subarrays of nums where 2 is the least common
 * multiple of all the subarray's elements.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], k <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for(int i = 0; i < n; i++){
            int lcm = nums[i];
            for(int j = i; j < n; j++){
                int gcd = gcd(lcm, nums[j]);
                lcm = lcm * nums[j] / gcd;
                if(lcm == k){
                    ans++;
                }
                else if(lcm > k){
                    break;
                }
            }
        }
        return ans;
    }

    int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        else{
            return gcd(b, a % b);
        }
    }
}
// @lc code=end
