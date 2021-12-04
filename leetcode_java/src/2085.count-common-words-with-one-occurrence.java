/*
 * @lc app=leetcode id=2085 lang=java
 *
 * [2085] Count Common Words With One Occurrence
 *
 * https://leetcode.com/problems/count-common-words-with-one-occurrence/description/
 *
 * algorithms
 * Easy (71.62%)
 * Likes:    59
 * Dislikes: 0
 * Total Accepted:    6.1K
 * Total Submissions: 8.5K
 * Testcase Example:  '["leetcode","is","amazing","as","is"]\n["amazing","leetcode","is"]'
 *
 * Given two string arrays words1 and words2, return the number of strings that
 * appear exactly once in each of the two arrays.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words1 = ["leetcode","is","amazing","as","is"], words2 =
 * ["amazing","leetcode","is"]
 * Output: 2
 * Explanation:
 * - "leetcode" appears exactly once in each of the two arrays. We count this
 * string.
 * - "amazing" appears exactly once in each of the two arrays. We count this
 * string.
 * - "is" appears in each of the two arrays, but there are 2 occurrences of it
 * in words1. We do not count this string.
 * - "as" appears once in words1, but does not appear in words2. We do not
 * count this string.
 * Thus, there are 2 strings that appear exactly once in each of the two
 * arrays.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
 * Output: 0
 * Explanation: There are no strings that appear in each of the two arrays.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
 * Output: 1
 * Explanation: The only string that appears exactly once in each of the two
 * arrays is "ab".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words1.length, words2.length <= 1000
 * 1 <= words1[i].length, words2[j].length <= 30
 * words1[i] and words2[j] consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        for(String s : words1){
            m1.put(s, m1.getOrDefault(s, 0) + 1);
        }
        for(String s : words2){
            m2.put(s, m2.getOrDefault(s, 0) + 1);
        }
        int cnt = 0;
        for(String k : m1.keySet()){
            if(m1.get(k) == 1 && m2.getOrDefault(k, 0) == 1){
                cnt++;
            }
        }
        return cnt;
    }
}
// @lc code=end
