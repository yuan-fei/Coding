/*
 * @lc app=leetcode id=3147 lang=java
 *
 * [3147] Taking Maximum Energy From the Mystic Dungeon
 *
 * https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon/description/
 *
 * algorithms
 * Medium (40.92%)
 * Likes:    156
 * Dislikes: 15
 * Total Accepted:    31.9K
 * Total Submissions: 77.8K
 * Testcase Example:  '[5,2,-10,-5,1]\n3'
 *
 * In a mystic dungeon, n magicians are standing in a line. Each magician has
 * an attribute that gives you energy. Some magicians can give you negative
 * energy, which means taking energy from you.
 * 
 * You have been cursed in such a way that after absorbing energy from magician
 * i, you will be instantly transported to magician (i + k). This process will
 * be repeated until you reach the magician where (i + k) does not exist.
 * 
 * In other words, you will choose a starting point and then teleport with k
 * jumps until you reach the end of the magicians' sequence, absorbing all the
 * energy during the journey.
 * 
 * You are given an array energy and an integer k. Return the maximum possible
 * energy you can gain.
 * 
 * Note that when you are reach a magician, you must take energy from them,
 * whether it is negative or positive energy.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:  energy = [5,2,-10,-5,1], k = 3
 * 
 * Output: 3
 * 
 * Explanation: We can gain a total energy of 3 by starting from magician 1
 * absorbing 2 + 1 = 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: energy = [-2,-3,-1], k = 2
 * 
 * Output: -1
 * 
 * Explanation: We can gain a total energy of -1 by starting from magician
 * 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= energy.length <= 10^5
 * -1000 <= energy[i] <= 1000
 * 1 <= k <= energy.length - 1
 * 
 * 
 * 
 * ​​​​​​
 */

// @lc code=start
class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp[n - i - 1] = energy[n - i - 1] + ((n - i - 1 + k < n) ? dp[n - i - 1 + k] : 0);
            ans = Math.max(ans, dp[n - i - 1]);
        }
        return ans;
    }
}
// @lc code=end
