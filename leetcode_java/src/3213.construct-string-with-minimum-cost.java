/*
 * @lc app=leetcode id=3213 lang=java
 *
 * [3213] Construct String with Minimum Cost
 *
 * https://leetcode.com/problems/construct-string-with-minimum-cost/description/
 *
 * algorithms
 * Hard (19.67%)
 * Likes:    172
 * Dislikes: 29
 * Total Accepted:    12.9K
 * Total Submissions: 67.9K
 * Testcase Example:  '"abcdef"\n["abdef","abc","d","def","ef"]\n[100,1,1,10,5]'
 *
 * You are given a string target, an array of strings words, and an integer
 * array costs, both arrays of the same length.
 * 
 * Imagine an empty string s.
 * 
 * You can perform the following operation any number of times (including
 * zero):
 * 
 * 
 * Choose an index i in the range [0, words.length - 1].
 * Append words[i] to s.
 * The cost of operation is costs[i].
 * 
 * 
 * Return the minimum cost to make s equal to target. If it's not possible,
 * return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: target = "abcdef", words = ["abdef","abc","d","def","ef"], costs =
 * [100,1,1,10,5]
 * 
 * Output: 7
 * 
 * Explanation:
 * 
 * The minimum cost can be achieved by performing the following
 * operations:
 * 
 * 
 * Select index 1 and append "abc" to s at a cost of 1, resulting in s =
 * "abc".
 * Select index 2 and append "d" to s at a cost of 1, resulting in s =
 * "abcd".
 * Select index 4 and append "ef" to s at a cost of 5, resulting in s =
 * "abcdef".
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: target = "aaaa", words = ["z","zz","zzz"], costs = [1,10,100]
 * 
 * Output: -1
 * 
 * Explanation:
 * 
 * It is impossible to make s equal to target, so we return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= target.length <= 5 * 10^4
 * 1 <= words.length == costs.length <= 5 * 10^4
 * 1 <= words[i].length <= target.length
 * The total sum of words[i].length is less than or equal to 5 * 10^4.
 * target and words[i] consist only of lowercase English letters.
 * 1 <= costs[i] <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    long BASE = (long) 37;
    long MOD = (long) 1e9 + 7;

    public int minimumCost(String target, String[] words, int[] costs) {
        int n = target.length();
        int m = words.length;

        long[] prefHash = new long[n + 1];
        long[] baseExp = new long[n + 1];
        baseExp[0] = 1;
        for (int i = 1; i <= n; i++) {
            prefHash[i] = Math.floorMod(prefHash[i - 1] * BASE + target.charAt(i - 1), MOD);
            baseExp[i] = Math.floorMod(baseExp[i - 1] * BASE, MOD);
        }

        Map<Long, Integer> word2Cost = new HashMap<>();
        Set<Integer> allPossibleLengths = new HashSet<>();
        for (int i = 0; i < m; i++) {
            allPossibleLengths.add(words[i].length());
            long hash = 0;
            for (char c : words[i].toCharArray()) {
                hash = Math.floorMod(hash * BASE + c, MOD);
            }
            word2Cost.put(hash, Math.min(word2Cost.getOrDefault(hash, Integer.MAX_VALUE), costs[i]));
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int l : allPossibleLengths) {
                if (i >= l) {
                    long hash = Math.floorMod(prefHash[i] - prefHash[i - l] * baseExp[l], MOD);
                    if (word2Cost.containsKey(hash) && dp[i - l] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[i - l] + word2Cost.get(hash));
                    }
                }
            }
        }
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }
}
// @lc code=end
