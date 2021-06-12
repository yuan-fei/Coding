/*
 * @lc app=leetcode id=1888 lang=java
 *
 * [1888] Minimum Number of Flips to Make the Binary String Alternating
 *
 * https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/description/
 *
 * algorithms
 * Medium (32.28%)
 * Likes:    271
 * Dislikes: 13
 * Total Accepted:    5.5K
 * Total Submissions: 17K
 * Testcase Example:  '"111000"'
 *
 * You are given a binary string s. You are allowed to perform two types of
 * operations on the string in any sequence:
 * 
 * 
 * Type-1: Remove the character at the start of the string s and append it to
 * the end of the string.
 * Type-2: Pick any character in s and flip its value, i.e., if its value is
 * '0' it becomes '1' and vice-versa.
 * 
 * 
 * Return the minimum number of type-2 operations you need to perform such that
 * s becomes alternating.
 * 
 * The string is called alternating if no two adjacent characters are
 * equal.
 * 
 * 
 * For example, the strings "010" and "1010" are alternating, while the string
 * "0100" is not.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "111000"
 * Output: 2
 * Explanation: Use the first operation two times to make s = "100011".
 * Then, use the second operation on the third and sixth elements to make s =
 * "101010".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "1110"
 * Output: 1
 * Explanation: Use the second operation on the second element to make s =
 * "1010".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minFlips(String s) {
        /*
        * Sliding Window Approach
        */
        
        
        int n = s.length();
        
        int mininumFlip = Integer.MAX_VALUE;
        
        int misMatchCount = 0;
        for(int i = 0; i < (2 * n); i++){
            
            int r = i % n;
            
            //add mis watch count in current window
            if((s.charAt(r) - '0') != (i % 2 == 0 ? 1 : 0)) misMatchCount++;
            
            //remove mismatch count which are not relvent for current window
            if(i >= n && (s.charAt(r) - '0') != (r % 2 == 0 ? 1 : 0)) misMatchCount--;
            
            
            //misMatchCount : when valid binary string start from 1
            //n - misMatchCount : when valid binary string start from 0
            if(i >= n - 1) mininumFlip = Math.min(mininumFlip, Math.min(misMatchCount, n - misMatchCount));
        }
        
        return mininumFlip;
    }
}
// @lc code=end
