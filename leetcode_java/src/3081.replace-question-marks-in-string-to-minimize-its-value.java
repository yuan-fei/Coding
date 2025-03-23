/*
 * @lc app=leetcode id=3081 lang=java
 *
 * [3081] Replace Question Marks in String to Minimize Its Value
 *
 * https://leetcode.com/problems/replace-question-marks-in-string-to-minimize-its-value/description/
 *
 * algorithms
 * Medium (27.76%)
 * Likes:    179
 * Dislikes: 30
 * Total Accepted:    15.1K
 * Total Submissions: 54K
 * Testcase Example:  '"???"'
 *
 * You are given a string s. s[i] is either a lowercase English letter or '?'.
 * 
 * For a string t having length m containing only lowercase English letters, we
 * define the function cost(i) for an index i as the number of characters equal
 * to t[i] that appeared before it, i.e. in the range [0, i - 1].
 * 
 * The value of t is the sum of cost(i) for all indices i.
 * 
 * For example, for the string t = "aab":
 * 
 * 
 * cost(0) = 0
 * cost(1) = 1
 * cost(2) = 0
 * Hence, the value of "aab" is 0 + 1 + 0 = 1.
 * 
 * 
 * Your task is to replace all occurrences of '?' in s with any lowercase
 * English letter so that the value of s is minimized.
 * 
 * Return a string denoting the modified string with replaced occurrences of
 * '?'. If there are multiple strings resulting in the minimum value, return
 * the lexicographically smallest one.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:   s = "???" 
 * 
 * Output:   "abc" 
 * 
 * Explanation:  In this example, we can replace the occurrences of '?' to make
 * s equal to "abc".
 * 
 * For "abc", cost(0) = 0, cost(1) = 0, and cost(2) = 0.
 * 
 * The value of "abc" is 0.
 * 
 * Some other modifications of s that have a value of 0 are "cba", "abz", and,
 * "hey".
 * 
 * Among all of them, we choose the lexicographically smallest.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:  s = "a?a?"
 * 
 * Output:  "abac"
 * 
 * Explanation:  In this example, the occurrences of '?' can be replaced to
 * make s equal to "abac".
 * 
 * For "abac", cost(0) = 0, cost(1) = 0, cost(2) = 1, and cost(3) = 0.
 * 
 * The value of "abac" is 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is either a lowercase English letter or '?'.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
    public String minimizeStringValue(String s) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            if (c != '?') {
                freq[c - 'a']++;
            }
        }
        int[] indexOrderedByFreq = IntStream.range(0, 26).boxed()
                .sorted(Comparator.comparing((Integer i) -> -freq[i]))
                .mapToInt(i -> i)
                .toArray();
        int remaining = n;
        int i = 0;
        for (; i < indexOrderedByFreq.length; i++) {
            if (freq[indexOrderedByFreq[i]] * (26 - i) <= remaining) {
                break;
            } else {
                remaining -= freq[indexOrderedByFreq[i]];
            }
        }
        // System.out.println(Arrays.toString(freq));
        if (i != 26) {
            int base = remaining / (26 - i);
            int overflow = remaining % (26 - i);
            for (int j = 0; j < 26; j++) {
                if (freq[j] <= base) {
                    freq[j] = base;
                    if (overflow > 0) {
                        overflow--;
                        freq[j]++;
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(freq));
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != '?') {
                freq[c - 'a']--;
            }
        }
        int curId = 0;
        for (char c : s.toCharArray()) {
            if (c == '?') {
                while (freq[curId] == 0) {
                    curId++;
                }
                sb.append((char) ('a' + curId));
                freq[curId]--;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
// @lc code=end
