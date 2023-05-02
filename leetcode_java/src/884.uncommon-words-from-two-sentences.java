/*
 * @lc app=leetcode id=884 lang=java
 *
 * [884] Uncommon Words from Two Sentences
 *
 * https://leetcode.com/problems/uncommon-words-from-two-sentences/description/
 *
 * algorithms
 * Easy (66.41%)
 * Likes:    1189
 * Dislikes: 156
 * Total Accepted:    118K
 * Total Submissions: 177.7K
 * Testcase Example:  '"this apple is sweet"\n"this apple is sour"'
 *
 * A sentence is a string of single-space separated words where each word
 * consists only of lowercase letters.
 * 
 * A word is uncommon if it appears exactly once in one of the sentences, and
 * does not appear in the other sentence.
 * 
 * Given two sentences s1 and s2, return a list of all the uncommon words. You
 * may return the answer in any order.
 * 
 * 
 * Example 1:
 * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 * Output: ["sweet","sour"]
 * Example 2:
 * Input: s1 = "apple apple", s2 = "banana"
 * Output: ["banana"]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s1.length, s2.length <= 200
 * s1 and s2 consist of lowercase English letters and spaces.
 * s1 and s2 do not have leading or trailing spaces.
 * All the words in s1 and s2 are separated by a single space.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        String[] parts1 = s1.split(" ");
        Map<String, Long> freq1 = Arrays.stream(parts1)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Set<String> words1 = freq1.keySet().stream().filter(s -> freq1.get(s) == 1).collect(Collectors.toSet());

        String[] parts2 = s2.split(" ");
        Map<String, Long> freq2 = Arrays.stream(parts2)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Set<String> words2 = freq2.keySet().stream().filter(s -> freq2.get(s) == 1).collect(Collectors.toSet());
        return Stream.concat(words1.stream(), words2.stream()).filter(w -> !freq1.containsKey(w) || !freq2.containsKey(w))
                .toArray(String[]::new);
    }
}
// @lc code=end
