/*
 * @lc app=leetcode id=214 lang=java
 *
 * [214] Shortest Palindrome
 *
 * https://leetcode.com/problems/shortest-palindrome/description/
 *
 * algorithms
 * Hard (28.33%)
 * Likes:    786
 * Dislikes: 93
 * Total Accepted:    82.8K
 * Total Submissions: 292.1K
 * Testcase Example:  '"aacecaaa"'
 *
 * Given a string s, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * 
 * Example 1:
 * 
 * 
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "abcd"
 * Output: "dcbabcd"
 */

// @lc code=start
class Solution {
    public String shortestPalindrome(String s) {
        long p = 1000000007;
        int n = s.length();
        long head = 0;
        long tail = 0;
        int maxPalindromeLength = 1;
        long pExp = 1;

        for(int l = 1; l <= n; l++){
    		head = head * p + s.charAt(l - 1);
    		tail += s.charAt(l - 1) * pExp;
    		pExp *= p;
    		if(head == tail){
    			maxPalindromeLength = l;
    		}
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i >= maxPalindromeLength; i--){
        	sb.append(s.charAt(i));
        }
        sb.append(s);

        return sb.toString();
    }

}
// @lc code=end
