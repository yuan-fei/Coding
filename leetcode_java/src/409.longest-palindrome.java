/*
 * @lc app=leetcode id=409 lang=java
 *
 * [409] Longest Palindrome
 *
 * https://leetcode.com/problems/longest-palindrome/description/
 *
 * algorithms
 * Easy (49.86%)
 * Likes:    846
 * Dislikes: 78
 * Total Accepted:    133.9K
 * Total Submissions: 268.4K
 * Testcase Example:  '"abccccdd"'
 *
 * Given a string which consists of lowercase or uppercase letters, find the
 * length of the longest palindromes that can be built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome
 * here.
 * 
 * Note:
 * Assume the length of given string will not exceed 1,010.
 * 
 * 
 * Example: 
 * 
 * Input:
 * "abccccdd"
 * 
 * Output:
 * 7
 * 
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : s.toCharArray()) {
        	cnt.put(c, cnt.getOrDefault(c, 0) + 1);
        }
        int ret = 0;
        boolean odd = false;
        for (int v : cnt.values()) {
        	if(v % 2 == 0){
        		ret += v;
        	}
        	else{
        		odd = true;
        		ret += v - 1;
        	}
        }
        return ret + (odd ? 1 : 0);
    }
}
// @lc code=end
