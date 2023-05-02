/*
 * @lc app=leetcode id=890 lang=java
 *
 * [890] Find and Replace Pattern
 *
 * https://leetcode.com/problems/find-and-replace-pattern/description/
 *
 * algorithms
 * Medium (77.50%)
 * Likes:    3665
 * Dislikes: 161
 * Total Accepted:    169.4K
 * Total Submissions: 218.6K
 * Testcase Example:  '["abc","deq","mee","aqq","dkd","ccc"]\n"abb"'
 *
 * Given a list of strings words and a string pattern, return a list of
 * words[i] that match pattern. You may return the answer in any order.
 * 
 * A word matches the pattern if there exists a permutation of letters p so
 * that after replacing every letter x in the pattern with p(x), we get the
 * desired word.
 * 
 * Recall that a permutation of letters is a bijection from letters to letters:
 * every letter maps to another letter, and no two letters map to the same
 * letter.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a ->
 * m, b -> e, ...}. 
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a
 * permutation, since a and b map to the same letter.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= pattern.length <= 20
 * 1 <= words.length <= 50
 * words[i].length == pattern.length
 * pattern and words[i] are lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        String p = encode(pattern);
        return Arrays.stream(words).filter(w -> encode(w).equals(p)).collect(Collectors.toList());
    }

    String encode(String s){
        int[] code = new int[26];
        int ctr = 1;
        String ans = "";
        for(char c : s.toCharArray()){
            if(code[c - 'a'] == 0){
                code[c - 'a'] = ctr++;
            }
            ans += "|"+code[c - 'a'];
        }
        return ans;
    }
}
// @lc code=end
