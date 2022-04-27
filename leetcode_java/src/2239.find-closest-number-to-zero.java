/*
 * @lc app=leetcode id=2239 lang=java
 *
 * [2239] Find Closest Number to Zero
 *
 * https://leetcode.com/problems/find-closest-number-to-zero/description/
 *
 * algorithms
 * Easy (46.04%)
 * Likes:    102
 * Dislikes: 5
 * Total Accepted:    14.2K
 * Total Submissions: 30.8K
 * Testcase Example:  '[-4,-2,1,4,8]'
 *
 * Given an integer array nums of size n, return the number with the value
 * closest to 0 in nums. If there are multiple answers, return the number with
 * the largest value.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [-4,-2,1,4,8]
 * Output: 1
 * Explanation:
 * The distance from -4 to 0 is |-4| = 4.
 * The distance from -2 to 0 is |-2| = 2.
 * The distance from 1 to 0 is |1| = 1.
 * The distance from 4 to 0 is |4| = 4.
 * The distance from 8 to 0 is |8| = 8.
 * Thus, the closest number to 0 in the array is 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,-1,1]
 * Output: 1
 * Explanation: 1 and -1 are both the closest numbers to 0, so 1 being larger
 * is returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 1000
 * -10^5 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findClosestNumber(int[] nums) {
        int ans = 100006;
        for(int x : nums){
            if(Math.abs(x) < Math.abs(ans)){
                ans = x;
            }
            else if(Math.abs(x) == Math.abs(ans) && x > ans){
                ans = x;
            }
        }
        return ans;
    }
}
// @lc code=end
