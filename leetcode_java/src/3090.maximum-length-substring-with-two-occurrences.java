/*
 * @lc app=leetcode id=3090 lang=java
 *
 * [3090] Maximum Length Substring With Two Occurrences
 *
 * https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/description/
 *
 * algorithms
 * Easy (64.09%)
 * Likes:    203
 * Dislikes: 18
 * Total Accepted:    48.8K
 * Total Submissions: 75.9K
 * Testcase Example:  '"bcbbbcba"'
 *
 * Given a string s, return the maximum length of a substring such that it
 * contains at most two occurrences of each character.
 * 
 * Example 1:
 * 
 * 
 * Input: s = "bcbbbcba"
 * 
 * Output: 4
 * 
 * Explanation:
 * The following substring has a length of 4 and contains at most two
 * occurrences of each character: "bcbbbcba".
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aaaa"
 * 
 * Output: 2
 * 
 * Explanation:
 * The following substring has a length of 2 and contains at most two
 * occurrences of each character: "aaaa".
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= s.length <= 100
 * s consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int maximumLengthSubstring(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (checkFreq(s.substring(i, j))) {

                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    boolean checkFreq(String s) {
        Map<Character, Long> frequencyMap = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // System.out.println(Arrays.asList(s, frequencyMap));
        return frequencyMap.values().stream().allMatch(v -> v <= 2);
    }
}
// @lc code=end
