/*
 * @lc app=leetcode id=2455 lang=java
 *
 * [2455] Average Value of Even Numbers That Are Divisible by Three
 *
 * https://leetcode.com/problems/average-value-of-even-numbers-that-are-divisible-by-three/description/
 *
 * algorithms
 * Easy (58.51%)
 * Likes:    93
 * Dislikes: 6
 * Total Accepted:    18.7K
 * Total Submissions: 31.9K
 * Testcase Example:  '[1,3,6,10,12,15]'
 *
 * Given an integer array nums of positive integers, return the average value
 * of all even integers that are divisible by 3.
 * 
 * Note that the average of n elements is the sum of the n elements divided by
 * n and rounded down to the nearest integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,6,10,12,15]
 * Output: 9
 * Explanation: 6 and 12 are even numbers that are divisible by 3. (6 + 12) / 2
 * = 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,4,7,10]
 * Output: 0
 * Explanation: There is no single number that satisfies the requirement, so
 * return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int averageValue(int[] nums) {
        int cnt = 0;
        int sum = 0;
        for(int x : nums){
            if(x % 6 == 0){
                cnt++;
                sum += x;
            }
        }
        return cnt == 0? 0 : sum / cnt;
    }
}
// @lc code=end
