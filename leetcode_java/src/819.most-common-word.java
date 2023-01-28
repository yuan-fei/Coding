/*
 * @lc app=leetcode id=819 lang=java
 *
 * [819] Most Common Word
 *
 * https://leetcode.com/problems/most-common-word/description/
 *
 * algorithms
 * Easy (44.85%)
 * Likes:    1471
 * Dislikes: 2840
 * Total Accepted:    319K
 * Total Submissions: 711.5K
 * Testcase Example:  '"Bob hit a ball, the hit BALL flew far after it was hit."\n["hit"]'
 *
 * Given a string paragraph and a string array of the banned words banned,
 * return the most frequent word that is not banned. It is guaranteed there is
 * at least one word that is not banned, and that the answer is unique.
 * 
 * The words in paragraph are case-insensitive and the answer should be
 * returned in lowercase.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was
 * hit.", banned = ["hit"]
 * Output: "ball"
 * Explanation: 
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent
 * non-banned word in the paragraph. 
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"), 
 * and that "hit" isn't the answer even though it occurs more because it is
 * banned.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: paragraph = "a.", banned = []
 * Output: "a"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= paragraph.length <= 1000
 * paragraph consists of English letters, space ' ', or one of the symbols:
 * "!?',;.".
 * 0 <= banned.length <= 100
 * 1 <= banned[i].length <= 10
 * banned[i] consists of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] parts = paragraph.replaceAll("[\\!\\?',;\\.]", " ").split("\\W+");
        // System.out.println(Arrays.toString(parts));
        Set<String> bSet = Arrays.stream(banned).collect(Collectors.toSet());
        Map<String, Integer> m = Arrays.stream(parts).filter((String p) -> !bSet.contains(p))
                .map(String::toLowerCase)
                .map((String p) -> new Pair(p, 1))
                .collect(Collectors.toMap(p -> p.k(), p -> p.v(), (v1, v2) -> v1 + v2));
        // System.out.println(m);
        return m.entrySet().stream()
                .max(Comparator.comparing((Map.Entry<String, Integer> e) -> e.getValue()))
                .get()
                .getKey();
    }
}
record Pair(String k, int v){}
// @lc code=end
