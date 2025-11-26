/*
 * @lc app=leetcode id=3186 lang=java
 *
 * [3186] Maximum Total Damage With Spell Casting
 *
 * https://leetcode.com/problems/maximum-total-damage-with-spell-casting/description/
 *
 * algorithms
 * Medium (27.02%)
 * Likes:    730
 * Dislikes: 64
 * Total Accepted:    110.2K
 * Total Submissions: 244.8K
 * Testcase Example:  '[1,1,3,4]'
 *
 * A magician has various spells.
 * 
 * You are given an array power, where each element represents the damage of a
 * spell. Multiple spells can have the same damage value.
 * 
 * It is a known fact that if a magician decides to cast a spell with a damage
 * of power[i], they cannot cast any spell with a damage of power[i] - 2,
 * power[i] - 1, power[i] + 1, or power[i] + 2.
 * 
 * Each spell can be cast only once.
 * 
 * Return the maximum possible total damage that a magician can cast.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: power = [1,1,3,4]
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with
 * damage 1, 1, 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: power = [7,1,6,6]
 * 
 * Output: 13
 * 
 * Explanation:
 * 
 * The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with
 * damage 1, 6, 6.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= power.length <= 10^5
 * 1 <= power[i] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public long maximumTotalDamage(int[] power) {
        TreeMap<Integer, Long> damageMap = new TreeMap<>();
        long maxPower = 0;
        for (int p : power) {
            damageMap.put(p, damageMap.getOrDefault(p, 0L) + 1);
        }
        Map<Integer, Long> dp = new HashMap<>();
        for (int p : damageMap.keySet()) {
            long count = damageMap.get(p);
            long prev3 = damageMap.lowerKey(p - 2) == null ? 0 : dp.get(damageMap.lowerKey(p - 2));
            long prev2 = dp.getOrDefault(p - 2, 0L);
            long prev1 = dp.getOrDefault(p - 1, 0L);
            long cur = Math.max(Math.max(prev3 + count * p, prev2), prev1);
            dp.put(p, cur);
            maxPower = Math.max(maxPower, cur);
        }
        return maxPower;
    }
}
// @lc code=end
