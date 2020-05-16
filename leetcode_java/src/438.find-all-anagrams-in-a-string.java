/*
 * @lc app=leetcode id=438 lang=java
 *
 * [438] Find All Anagrams in a String
 *
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 *
 * algorithms
 * Medium (41.51%)
 * Likes:    2515
 * Dislikes: 165
 * Total Accepted:    205.2K
 * Total Submissions: 493.5K
 * Testcase Example:  '"cbaebabacd"\n"abc"'
 *
 * Given a string s and a non-empty string p, find all the start indices of p's
 * anagrams in s.
 * 
 * Strings consists of lowercase English letters only and the length of both
 * strings s and p will not be larger than 20,100.
 * 
 * The order of output does not matter.
 * 
 * Example 1:
 * 
 * Input:
 * s: "cbaebabacd" p: "abc"
 * 
 * Output:
 * [0, 6]
 * 
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of
 * "abc".
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s: "abab" p: "ab"
 * 
 * Output:
 * [0, 1, 2]
 * 
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
    	if(s.length() < p.length()){
    		return new ArrayList<>();
    	}
        int[] pattern = new int[26];
        for(char c : p.toCharArray()){
        	pattern[c - 'a']++;
        }
        List<Integer> ret = new ArrayList<>();
        int[] cnt = new int[26];
        for(int i = 0; i < p.length(); i++){
        	cnt[s.charAt(i) - 'a']++;
        }
        if(Arrays.equals(cnt, pattern)){
        	ret.add(0);
        }
        for(int i = p.length(); i < s.length(); i++){
        	cnt[s.charAt(i) - 'a']++;
        	cnt[s.charAt(i - p.length()) - 'a']--;
        	if(Arrays.equals(cnt, pattern)){
        		ret.add(i - p.length() + 1);
        	}
        }
        return ret;
    }
}
// @lc code=end
