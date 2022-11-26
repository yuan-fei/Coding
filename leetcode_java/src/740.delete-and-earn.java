/*
 * @lc app=leetcode id=740 lang=java
 *
 * [740] Delete and Earn
 *
 * https://leetcode.com/problems/delete-and-earn/description/
 *
 * algorithms
 * Medium (57.37%)
 * Likes:    5799
 * Dislikes: 308
 * Total Accepted:    238K
 * Total Submissions: 414.9K
 * Testcase Example:  '[3,4,2]'
 *
 * You are given an integer array nums. You want to maximize the number of
 * points you get by performing the following operation any number of
 * times:
 * 
 * 
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must
 * delete every element equal to nums[i] - 1 and every element equal to nums[i]
 * + 1.
 * 
 * 
 * Return the maximum number of points you can earn by applying the above
 * operation some number of times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums =
 * [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int deleteAndEarn(int[] nums) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for(int x : nums){
            m.put(x, m.getOrDefault(x, 0) + x);
        }
        int[] dp = new int[2];
        int lastKey = -1;
        for(int k : m.keySet()){
            int[] newDp = new int[2];
            newDp[0] = Math.max(dp[0], dp[1]);
            if(k == lastKey + 1){
                newDp[1] = dp[0] + m.get(k);
            }
            else{
                newDp[1] = Math.max(dp[0], dp[1]) + m.get(k);
            }
            lastKey = k;
            dp = newDp;
        }
        return Math.max(dp[0], dp[1]);
    }
}
// @lc code=end
