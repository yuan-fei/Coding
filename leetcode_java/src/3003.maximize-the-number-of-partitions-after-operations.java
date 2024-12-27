/*
 * @lc app=leetcode id=3003 lang=java
 *
 * [3003] Maximize the Number of Partitions After Operations
 *
 * https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations/description/
 *
 * algorithms
 * Hard (26.37%)
 * Likes:    137
 * Dislikes: 29
 * Total Accepted:    3.7K
 * Total Submissions: 13.8K
 * Testcase Example:  '"accca"\n2'
 *
 * You are given a string s and an integer k.
 * 
 * First, you are allowed to change at most one index in s to another lowercase
 * English letter.
 * 
 * After that, do the following partitioning operation until s is empty:
 * 
 * 
 * Choose the longest prefix of s containing at most k distinct characters.
 * Delete the prefix from s and increase the number of partitions by one. The
 * remaining characters (if any) in s maintain their initial order.
 * 
 * 
 * Return an integer denoting the maximum number of resulting partitions after
 * the operations by optimally choosing at most one index to change.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "accca", k = 2
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * The optimal way is to change s[2] to something other than a and c, for
 * example, b. then it becomes "acbca".
 * 
 * Then we perform the operations:
 * 
 * 
 * The longest prefix containing at most 2 distinct characters is "ac", we
 * remove it and s becomes "bca".
 * Now The longest prefix containing at most 2 distinct characters is "bc", so
 * we remove it and s becomes "a".
 * Finally, we remove "a" and s becomes empty, so the procedure ends.
 * 
 * 
 * Doing the operations, the string is divided into 3 partitions, so the answer
 * is 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aabaab", k = 3
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * Initially s contains 2 distinct characters, so whichever character we
 * change, it will contain at most 3 distinct characters, so the longest prefix
 * with at most 3 distinct characters would always be all of it, therefore the
 * answer is 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "xxyz", k = 1
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * The optimal way is to change s[0] or s[1] to something other than characters
 * in s, for example, to change s[0] to w.
 * 
 * Then s becomes "wxyz", which consists of 4 distinct characters, so as k is
 * 1, it will divide into 4 partitions.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s consists only of lowercase English letters.
 * 1 <= k <= 26
 * 
 * 
 */

// @lc code=start
class Solution {
    private HashMap<Long, Integer> cache;
    private String s;
    private int k;

    public int maxPartitionsAfterOperations(String s, int k) {
        this.cache = new HashMap<>();
        this.s = s;
        this.k = k;
        return dp(0, 0, true) + 1;
    }

    private int dp(int index, int currentSet, boolean canChange) {
        long key = ((long) index << 27)
                | ((long) currentSet << 1)
                | (canChange ? 1 : 0);

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (index == s.length()) {
            return 0;
        }

        int characterIndex = s.charAt(index) - 'a';
        int currentSetUpdated = currentSet | (1 << characterIndex);
        int distinctCount = Integer.bitCount(currentSetUpdated);

        int res;
        if (distinctCount > k) {
            res = 1 + dp(index + 1, 1 << characterIndex, canChange);
        } else {
            res = dp(index + 1, currentSetUpdated, canChange);
        }

        if (canChange) {
            for (int newCharIndex = 0; newCharIndex < 26; newCharIndex++) {
                int newSet = currentSet | (1 << newCharIndex);
                int newDistinctCount = Integer.bitCount(newSet);

                if (newDistinctCount > k) {
                    res = Math.max(res, 1 + dp(index + 1, 1 << newCharIndex, false));
                } else {
                    res = Math.max(res, dp(index + 1, newSet, false));
                }
            }
        }

        cache.put(key, res);
        return res;
    }
}
// @lc code=end
