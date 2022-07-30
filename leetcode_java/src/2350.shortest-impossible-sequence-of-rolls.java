/*
 * @lc app=leetcode id=2350 lang=java
 *
 * [2350] Shortest Impossible Sequence of Rolls
 *
 * https://leetcode.com/problems/shortest-impossible-sequence-of-rolls/description/
 *
 * algorithms
 * Hard (64.04%)
 * Likes:    320
 * Dislikes: 18
 * Total Accepted:    7K
 * Total Submissions: 11K
 * Testcase Example:  '[4,2,1,2,3,3,2,4,1]\n4'
 *
 * You are given an integer array rolls of length n and an integer k. You roll
 * a k sided dice numbered from 1 to k, n times, where the result of the i^th
 * roll is rolls[i].
 * 
 * Return the length of the shortest sequence of rolls that cannot be taken
 * from rolls.
 * 
 * A sequence of rolls of length len is the result of rolling a k sided dice
 * len times.
 * 
 * Note that the sequence taken does not have to be consecutive as long as it
 * is in order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: rolls = [4,2,1,2,3,3,2,4,1], k = 4
 * Output: 3
 * Explanation: Every sequence of rolls of length 1, [1], [2], [3], [4], can be
 * taken from rolls.
 * Every sequence of rolls of length 2, [1, 1], [1, 2], ..., [4, 4], can be
 * taken from rolls.
 * The sequence [1, 4, 2] cannot be taken from rolls, so we return 3.
 * Note that there are other sequences that cannot be taken from rolls.
 * 
 * Example 2:
 * 
 * 
 * Input: rolls = [1,1,2,2], k = 2
 * Output: 2
 * Explanation: Every sequence of rolls of length 1, [1], [2], can be taken
 * from rolls.
 * The sequence [2, 1] cannot be taken from rolls, so we return 2.
 * Note that there are other sequences that cannot be taken from rolls but [2,
 * 1] is the shortest.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: rolls = [1,1,3,2,2,2,3,3], k = 4
 * Output: 1
 * Explanation: The sequence [4] cannot be taken from rolls, so we return 1.
 * Note that there are other sequences that cannot be taken from rolls but [4]
 * is the shortest.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == rolls.length
 * 1 <= n <= 10^5
 * 1 <= rolls[i] <= k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int shortestSequence(int[] rolls, int k) {
        int n = rolls.length;
        int l = 0;
        Set<Integer> s = new HashSet<>();
        for(int i = n - 1; i >= 0; i--){
            s.add(rolls[i]);
            if(s.size() == k){
                l++;
                s = new HashSet<>();
            }
        }
        return l + 1;
    }
}
// @lc code=end
