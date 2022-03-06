/*
 * @lc app=leetcode id=2188 lang=java
 *
 * [2188] Minimum Time to Finish the Race
 *
 * https://leetcode.com/problems/minimum-time-to-finish-the-race/description/
 *
 * algorithms
 * Hard (38.61%)
 * Likes:    255
 * Dislikes: 6
 * Total Accepted:    4.4K
 * Total Submissions: 11.4K
 * Testcase Example:  '[[2,3],[3,4]]\n5\n4'
 *
 * You are given a 0-indexed 2D integer array tires where tires[i] = [fi, ri]
 * indicates that the i^th tire can finish its x^th successive lap in fi *
 * ri^(x-1) seconds.
 * 
 * 
 * For example, if fi = 3 and ri = 2, then the tire would finish its 1^st lap
 * in 3 seconds, its 2^nd lap in 3 * 2 = 6 seconds, its 3^rd lap in 3 * 2^2 =
 * 12 seconds, etc.
 * 
 * 
 * You are also given an integer changeTime and an integer numLaps.
 * 
 * The race consists of numLaps laps and you may start the race with any tire.
 * You have an unlimited supply of each tire and after every lap, you may
 * change to any given tire (including the current tire type) if you wait
 * changeTime seconds.
 * 
 * Return the minimum time to finish the race.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
 * Output: 21
 * Explanation: 
 * Lap 1: Start with tire 0 and finish the lap in 2 seconds.
 * Lap 2: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
 * Lap 3: Change tires to a new tire 0 for 5 seconds and then finish the lap in
 * another 2 seconds.
 * Lap 4: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
 * Total time = 2 + 6 + 5 + 2 + 6 = 21 seconds.
 * The minimum time to complete the race is 21 seconds.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
 * Output: 25
 * Explanation: 
 * Lap 1: Start with tire 1 and finish the lap in 2 seconds.
 * Lap 2: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
 * Lap 3: Change tires to a new tire 1 for 6 seconds and then finish the lap in
 * another 2 seconds.
 * Lap 4: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
 * Lap 5: Change tires to tire 0 for 6 seconds then finish the lap in another 1
 * second.
 * Total time = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 seconds.
 * The minimum time to complete the race is 25 seconds. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= tires.length <= 10^5
 * tires[i].length == 2
 * 1 <= fi, changeTime <= 10^5
 * 2 <= ri <= 10^5
 * 1 <= numLaps <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int[] straight = new int[19];
        Arrays.fill(straight, Integer.MAX_VALUE);
        for (int[] tire: tires) {
            int f = tire[0];
            int r = tire[1];
            int timeSum = 0;
            for (int i = 1; i <= numLaps; i++) {
                int t =  f * (int) Math.pow(r, i - 1); 
                timeSum += t;
                if (timeSum > Math.pow(2, 18)) {
                    break; //more than required time, impossible to reach by going straight
                }
                straight[i] = Math.min(straight[i], timeSum);
            }
        }
        int[] dp = new int[numLaps + 1];
        for (int i = 1; i <= numLaps; i++) {
            dp[i] = i < 19? straight[i] : Integer.MAX_VALUE;
            for (int j = 1; j < Math.min(19, i/2 + 1); j++) {
                dp[i] = Math.min(dp[i], dp[j] + changeTime + dp[i - j]);
            }
        }
        return dp[numLaps];
    }
}
// @lc code=end
