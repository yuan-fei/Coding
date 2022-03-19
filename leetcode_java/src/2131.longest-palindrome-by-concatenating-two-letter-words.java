/*
 * @lc app=leetcode id=2131 lang=java
 *
 * [2131] Longest Palindrome by Concatenating Two Letter Words
 *
 * https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/description/
 *
 * algorithms
 * Medium (37.49%)
 * Likes:    340
 * Dislikes: 7
 * Total Accepted:    11.5K
 * Total Submissions: 30.7K
 * Testcase Example:  '["lc","cl","gg"]'
 *
 * You are given an array of strings words. Each element of words consists of
 * two lowercase English letters.
 * 
 * Create the longest possible palindrome by selecting some elements from words
 * and concatenating them in any order. Each element can be selected at most
 * once.
 * 
 * Return the length of the longest palindrome that you can create. If it is
 * impossible to create any palindrome, return 0.
 * 
 * A palindrome is a string that reads the same forward and backward.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of
 * length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" =
 * "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is
 * "xx".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 10^5
 * words[i].length == 2
 * words[i] consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> m = new HashMap<>();
        int len = 0;
        for(String x : words){
            StringBuilder sb = new StringBuilder(x);
            sb.reverse();
            String rev = sb.toString();
            if(m.containsKey(rev)){
                int cnt = m.getOrDefault(rev, 0) - 1;
                if(cnt > 0){
                    m.put(rev, cnt);    
                }
                else{
                    m.remove(rev);
                }
                len += 4;
            }
            else{
                m.put(x, m.getOrDefault(x, 0) + 1);    
            }
        }
        // System.out.println(m);
        int maxPair = 0;
        for(String k : m.keySet()){
            if(k.charAt(0) == k.charAt(1)){
                maxPair = Math.max(maxPair, m.get(k));
            }
        }
        return len + maxPair * 2;
    }
}
// @lc code=end
