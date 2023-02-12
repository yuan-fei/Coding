/*
 * @lc app=leetcode id=2563 lang=java
 *
 * [2563] Count the Number of Fair Pairs
 *
 * https://leetcode.com/problems/count-the-number-of-fair-pairs/description/
 *
 * algorithms
 * Medium (24.20%)
 * Likes:    124
 * Dislikes: 6
 * Total Accepted:    7.1K
 * Total Submissions: 29.8K
 * Testcase Example:  '[0,1,7,4,4,5]\n3\n6'
 *
 * Given a 0-indexed integer array nums of size n and two integers lower and
 * upper, return the number of fair pairs.
 * 
 * A pair (i, j) is fair if:
 * 
 * 
 * 0 <= i < j < n, and
 * lower <= nums[i] + nums[j] <= upper
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
 * Output: 6
 * Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and
 * (1,5).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,7,9,2,5], lower = 11, upper = 11
 * Output: 1
 * Explanation: There is a single fair pair: (2,3).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * nums.length == n
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= lower <= upper <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));
        return countLE(nums, upper) - countLE(nums, lower - 1);
    }

    long countLE(int[] nums, int ceiling){
        int i = 0;
        int j = nums.length - 1;
        
        long ans = 0;
        while(i < j){
            if(nums[i] + nums[j] > ceiling){
                j--;
            }
            else{
                ans += j - i;
                // System.out.println(ceiling + ", " + Arrays.asList(i, j));
                i++;
            }
        }
        return ans;
    }
}
// @lc code=end
