/*
 * @lc app=leetcode id=3170 lang=java
 *
 * [3170] Lexicographically Minimum String After Removing Stars
 *
 * https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/description/
 *
 * algorithms
 * Medium (35.82%)
 * Likes:    546
 * Dislikes: 75
 * Total Accepted:    109.2K
 * Total Submissions: 214.5K
 * Testcase Example:  '"aaba*"'
 *
 * You are given a string s. It may contain any number of '*' characters. Your
 * task is to remove all '*' characters.
 * 
 * While there is a '*', do the following operation:
 * 
 * 
 * Delete the leftmost '*' and the smallest non-'*' character to its left. If
 * there are several smallest characters, you can delete any of them.
 * 
 * 
 * Return the lexicographically smallest resulting string after removing all
 * '*' characters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aaba*"
 * 
 * Output: "aab"
 * 
 * Explanation:
 * 
 * We should delete one of the 'a' characters with '*'. If we choose s[3], s
 * becomes the lexicographically smallest.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abc"
 * 
 * Output: "abc"
 * 
 * Explanation:
 * 
 * There is no '*' in the string.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists only of lowercase English letters and '*'.
 * The input is generated such that it is possible to delete all '*'
 * characters.
 * 
 * 
 */

// @lc code=start

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    public String clearStars(String s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                Comparator.comparing((Integer i) -> s.charAt(i)).thenComparing(i -> -i));
        Set<Integer> removedIdx = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                removedIdx.add(i);
                removedIdx.add(pq.poll());
            } else {
                pq.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removedIdx.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
// @lc code=end
