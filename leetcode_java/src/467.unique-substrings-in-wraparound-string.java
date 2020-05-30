/*
 * @lc app=leetcode id=467 lang=java
 *
 * [467] Unique Substrings in Wraparound String
 *
 * https://leetcode.com/problems/unique-substrings-in-wraparound-string/description/
 *
 * algorithms
 * Medium (35.20%)
 * Likes:    530
 * Dislikes: 82
 * Total Accepted:    23.9K
 * Total Submissions: 67.8K
 * Testcase Example:  '"a"'
 *
 * Consider the string s to be the infinite wraparound string of
 * "abcdefghijklmnopqrstuvwxyz", so s will look like this:
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 
 * Now we have another string p. Your job is to find out how many unique
 * non-empty substrings of p are present in s. In particular, your input is the
 * string p and you need to output the number of different non-empty substrings
 * of p in the string s.
 * 
 * Note: p consists of only lowercase English letters and the size of p might
 * be over 10000.
 * 
 * Example 1:
 * 
 * Input: "a"
 * Output: 1
 * 
 * Explanation: Only the substring "a" of string "a" is in the string s.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "cac"
 * Output: 2
 * Explanation: There are two substrings "a", "c" of string "cac" in the string
 * s.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of
 * string "zab" in the string s.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findSubstringInWraproundString(String p) {
        int[] cnt = new int[26];
        int maxLen = 0;
        for(int i = 0 ; i < p.length(); i++){
        	if(i > 0 && (p.charAt(i) == p.charAt(i - 1) + 1 || p.charAt(i) == p.charAt(i - 1) - 25)){
        		maxLen++;
        	}
        	else{
        		maxLen = 1;
        	}
        	cnt[p.charAt(i) - 'a'] = Math.max(cnt[p.charAt(i) - 'a'], maxLen);
        }

        int ans = 0;
        for(int x : cnt){
        	ans += x;
        }
        return ans;
    }
}
// @lc code=end
