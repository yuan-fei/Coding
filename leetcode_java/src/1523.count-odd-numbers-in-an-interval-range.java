/*
 * @lc app=leetcode id=1523 lang=java
 *
 * [1523] Count Odd Numbers in an Interval Range
 *
 * https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/description/
 *
 * algorithms
 * Easy (52.29%)
 * Likes:    17
 * Dislikes: 0
 * Total Accepted:    5.3K
 * Total Submissions: 10.2K
 * Testcase Example:  '3\n7'
 *
 * Given two non-negative integers low and high. Return the count of odd
 * numbers between low and high (inclusive).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: low = 3, high = 7
 * Output: 3
 * Explanation: The odd numbers between 3 and 7 are [3,5,7].
 * 
 * Example 2:
 * 
 * 
 * Input: low = 8, high = 10
 * Output: 1
 * Explanation: The odd numbers between 8 and 10 are [9].
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= low <= high <= 10^9
 * 
 */

// @lc code=start
class Solution {
    public int countOdds(int low, int high) {
        if(low % 2 == 1){
        	return countOdds(low + 1, high) + 1;
        }
        if(high < low){
        	return 0;
        }
        return (high - low + 1) / 2;
    }
}
// @lc code=end
