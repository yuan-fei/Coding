/*
 * @lc app=leetcode id=3138 lang=java
 *
 * [3138] Minimum Length of Anagram Concatenation
 *
 * https://leetcode.com/problems/minimum-length-of-anagram-concatenation/description/
 *
 * algorithms
 * Medium (41.37%)
 * Likes:    182
 * Dislikes: 98
 * Total Accepted:    30.4K
 * Total Submissions: 74.9K
 * Testcase Example:  '"abba"'
 *
 * You are given a string s, which is known to be a concatenation of anagrams
 * of some string t.
 * 
 * Return the minimum possible length of the string t.
 * 
 * An anagram is formed by rearranging the letters of a string. For example,
 * "aab", "aba", and, "baa" are anagrams of "aab".
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abba"
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * One possible string t could be "ba".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "cdef"
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * One possible string t could be "cdef", notice that t can be equal to s.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abcbcacabbaccba"
 * 
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consist only of lowercase English letters.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int minAnagramLength(String s) {
        int n = s.length();
        for (int f = 1; f <= n; f++) {
            if (n % f == 0 && checkAnagramOfLength(s, f)) {
                return f;
            }
        }
        return -1;
    }

    boolean checkAnagramOfLength(String s, int l) {
        int[] freq1 = getFreq(s, 0, l);
        for (int i = l; i < s.length(); i += l) {
            int[] freq2 = getFreq(s, i, l);
            if (!Arrays.equals(freq1, freq2)) {
                return false;
            }
        }
        return true;
    }

    int[] getFreq(String s, int start, int l) {
        int[] freq = new int[26];
        for (int i = 0; i < l; i++) {
            freq[s.charAt(i + start) - 'a']++;
        }
        return freq;
    }
}
// @lc code=end
