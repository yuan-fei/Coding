/*
 * @lc app=leetcode id=1014 lang=java
 *
 * [1014] Best Sightseeing Pair
 *
 * https://leetcode.com/problems/best-sightseeing-pair/description/
 *
 * algorithms
 * Medium (59.37%)
 * Likes:    2436
 * Dislikes: 58
 * Total Accepted:    89K
 * Total Submissions: 149.9K
 * Testcase Example:  '[8,1,5,2,6]'
 *
 * You are given an integer array values where values[i] represents the value
 * of the i^th sightseeing spot. Two sightseeing spots i and j have a distance
 * j - i between them.
 * 
 * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] +
 * i - j: the sum of the values of the sightseeing spots, minus the distance
 * between them.
 * 
 * Return the maximum score of a pair of sightseeing spots.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: values = [8,1,5,2,6]
 * Output: 11
 * Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 =
 * 11
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: values = [1,2]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= values.length <= 5 * 10^4
 * 1 <= values[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    int MIN = -50005;
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int maxMinusPart = MIN;
        int ans = Integer.MIN_VALUE;
        for(int i = n - 1; i >= 0; i--){
            ans = Math.max(ans, values[i] + i + maxMinusPart);
            maxMinusPart = Math.max(maxMinusPart, values[i] - i);
        }
        return ans;
    }
}
// @lc code=end
