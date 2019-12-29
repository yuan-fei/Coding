/*
 * @lc app=leetcode id=1295 lang=java
 *
 * [1295] Find Numbers with Even Number of Digits
 *
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/description/
 *
 * algorithms
 * Easy (85.29%)
 * Likes:    63
 * Dislikes: 11
 * Total Accepted:    14.6K
 * Total Submissions: 17.2K
 * Testcase Example:  '[12,345,2,6,7896]'
 *
 * Given an array nums of integers, return how many of them contain an even
 * number of digits.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [12,345,2,6,7896]
 * Output: 2
 * Explanation: 
 * 12 contains 2 digits (even number of digits). 
 * 345 contains 3 digits (odd number of digits). 
 * 2 contains 1 digit (odd number of digits). 
 * 6 contains 1 digit (odd number of digits). 
 * 7896 contains 4 digits (even number of digits). 
 * Therefore only 12 and 7896 contain an even number of digits.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [555,901,482,1771]
 * Output: 1 
 * Explanation: 
 * Only 1771 contains an even number of digits.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 10^5
 * 
 */

// @lc code=start
class Solution {
    public int findNumbers(int[] nums) {
        int cnt = 0;
        for (int n : nums) {
        	if(99 >= n && n >= 10){
        		cnt++;
        	}
        	else if(9999 >= n && n >= 1000){
        		cnt++;
        	}
        }
        return cnt;
    }
}
// @lc code=end
