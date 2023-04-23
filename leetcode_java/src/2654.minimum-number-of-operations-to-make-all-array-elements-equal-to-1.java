/*
 * @lc app=leetcode id=2654 lang=java
 *
 * [2654] Minimum Number of Operations to Make All Array Elements Equal to 1
 *
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/description/
 *
 * algorithms
 * Medium (29.62%)
 * Likes:    91
 * Dislikes: 5
 * Total Accepted:    4.4K
 * Total Submissions: 14.9K
 * Testcase Example:  '[2,6,3,4]'
 *
 * You are given a 0-indexed array nums consisiting of positive integers. You
 * can do the following operation on the array any number of times:
 * 
 * 
 * Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or
 * nums[i+1] with their gcd value.
 * 
 * 
 * Return the minimum number of operations to make all elements of nums equal
 * to 1. If it is impossible, return -1.
 * 
 * The gcd of two integers is the greatest common divisor of the two
 * integers.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,6,3,4]
 * Output: 4
 * Explanation: We can do the following operations:
 * - Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums
 * = [2,6,1,4].
 * - Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums
 * = [2,1,1,4].
 * - Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums
 * = [1,1,1,4].
 * - Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums
 * = [1,1,1,1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,10,6,14]
 * Output: -1
 * Explanation: It can be shown that it is impossible to make all the elements
 * equal to 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 50
 * 1 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int cnt1 = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == 1){
                cnt1++;
            }
        }
        if(cnt1 > 0){
            return n - cnt1;
        }
        int min = n;
        for(int i = 0; i < n; i++){
            int g = nums[i];
            for(int j = i; j < n; j++){
                g = gcd(g, nums[j]);
                if(g == 1){
                    min = Math.min(min, j - i);
                }
            }
        }

        return (min == n) ? -1 : min + n - 1;
    }

    int gcd(int a, int b){
        return (b == 0) ? a : gcd(b, a % b);
    }
}
// @lc code=end
