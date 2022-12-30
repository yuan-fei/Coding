/*
 * @lc app=leetcode id=791 lang=java
 *
 * [791] Custom Sort String
 *
 * https://leetcode.com/problems/custom-sort-string/description/
 *
 * algorithms
 * Medium (69.30%)
 * Likes:    2365
 * Dislikes: 307
 * Total Accepted:    204.9K
 * Total Submissions: 295.7K
 * Testcase Example:  '"cba"\n"abcd"'
 *
 * You are given two strings order and s. All the characters of order are
 * unique and were sorted in some custom order previously.
 * 
 * Permute the characters of s so that they match the order that order was
 * sorted. More specifically, if a character x occurs before a character y in
 * order, then x should occur before y in the permuted string.
 * 
 * Return any permutation of s that satisfies this property.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: order = "cba", s = "abcd"
 * Output: "cbad"
 * Explanation: 
 * "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c",
 * "b", and "a". 
 * Since "d" does not appear in order, it can be at any position in the
 * returned string. "dcba", "cdba", "cbda" are also valid outputs.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: order = "cbafg", s = "abcd"
 * Output: "cbad"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= order.length <= 26
 * 1 <= s.length <= 200
 * order and s consist of lowercase English letters.
 * All the characters of order are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String customSortString(String order, String s) {
        int[] ord = new int[26];
        for(int i = 0; i < order.length(); i++){
            ord[order.charAt(i) - 'a'] = i + 1;
        }
        char[] chars = s.toCharArray();
        return IntStream.range(0, s.length()).mapToObj(i -> s.charAt(i))
                .sorted(Comparator.comparing(c -> ord[c - 'a'])).map(c -> "" + c).collect(Collectors.joining());
        
    }
}
// @lc code=end
