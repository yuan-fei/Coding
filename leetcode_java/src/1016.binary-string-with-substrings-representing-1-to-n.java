/*
 * @lc app=leetcode id=1016 lang=java
 *
 * [1016] Binary String With Substrings Representing 1 To N
 *
 * https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/description/
 *
 * algorithms
 * Medium (57.34%)
 * Likes:    337
 * Dislikes: 521
 * Total Accepted:    35.7K
 * Total Submissions: 62.2K
 * Testcase Example:  '"0110"\n3'
 *
 * Given a binary string s and a positive integer n, return true if the binary
 * representation of all the integers in the range [1, n] are substrings of s,
 * or false otherwise.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * 
 * Example 1:
 * Input: s = "0110", n = 3
 * Output: true
 * Example 2:
 * Input: s = "0110", n = 4
 * Output: false
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s[i] is either '0' or '1'.
 * 1 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean queryString(String s, int n) {
        Set<Integer> seen = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                int x = 0;
                for(int j = i; j < s.length() && j - i < 32; j++){
                    x <<= 1;
                    x += s.charAt(j) - '0';
                    if(x <= n){
                        seen.add(x);    
                    }
                    else{
                        break;
                    }
                }
            }
        }
        
        return seen.size() == n;
    }
}
// @lc code=end
