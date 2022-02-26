/*
 * @lc app=leetcode id=2177 lang=java
 *
 * [2177] Find Three Consecutive Integers That Sum to a Given Number
 *
 * https://leetcode.com/problems/find-three-consecutive-integers-that-sum-to-a-given-number/description/
 *
 * algorithms
 * Medium (56.05%)
 * Likes:    100
 * Dislikes: 16
 * Total Accepted:    11.9K
 * Total Submissions: 21.3K
 * Testcase Example:  '33'
 *
 * Given an integer num, return three consecutive integers (as a sorted array)
 * that sum to num. If num cannot be expressed as the sum of three consecutive
 * integers, return an empty array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 33
 * Output: [10,11,12]
 * Explanation: 33 can be expressed as 10 + 11 + 12 = 33.
 * 10, 11, 12 are 3 consecutive integers, so we return [10, 11, 12].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 4
 * Output: []
 * Explanation: There is no way to express 4 as the sum of 3 consecutive
 * integers.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= num <= 10^15
 * 
 * 
 */

// @lc code=start
class Solution {
    public long[] sumOfThree(long num) {
        if(num % 3 == 0){
            return new long[]{num / 3 - 1, num / 3, num / 3 + 1};
        }
        return new long[0];
    }
}
// @lc code=end
