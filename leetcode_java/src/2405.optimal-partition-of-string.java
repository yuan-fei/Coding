/*
 * @lc app=leetcode id=2405 lang=java
 *
 * [2405] Optimal Partition of String
 *
 * https://leetcode.com/problems/optimal-partition-of-string/description/
 *
 * algorithms
 * Medium (71.61%)
 * Likes:    125
 * Dislikes: 6
 * Total Accepted:    15K
 * Total Submissions: 20.9K
 * Testcase Example:  '"abacaba"'
 *
 * Given a string s, partition the string into one or more substrings such that
 * the characters in each substring are unique. That is, no letter appears in a
 * single substring more than once.
 * 
 * Return the minimum number of substrings in such a partition.
 * 
 * Note that each character should belong to exactly one substring in a
 * partition.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abacaba"
 * Output: 4
 * Explanation:
 * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
 * It can be shown that 4 is the minimum number of substrings needed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "ssssss"
 * Output: 6
 * Explanation:
 * The only valid partition is ("s","s","s","s","s","s").
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of only English lowercase letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int partitionString(String s) {
        Set<Character> seen = new HashSet<>();
        int cnt = 1;
        for(char c : s.toCharArray()){
            if(seen.contains(c)){
                cnt++;
                seen = new HashSet<>();
            }
            seen.add(c);
        }
        return cnt;
    }
}
// @lc code=end
