/*
 * @lc app=leetcode id=2575 lang=java
 *
 * [2575] Find the Divisibility Array of a String
 *
 * https://leetcode.com/problems/find-the-divisibility-array-of-a-string/description/
 *
 * algorithms
 * Medium (26.18%)
 * Likes:    95
 * Dislikes: 3
 * Total Accepted:    9.1K
 * Total Submissions: 35.2K
 * Testcase Example:  '"998244353"\n3'
 *
 * You are given a 0-indexed string word of length n consisting of digits, and
 * a positive integer m.
 * 
 * The divisibility array div of word is an integer array of length n such
 * that:
 * 
 * 
 * div[i] = 1 if the numeric value of word[0,...,i] is divisible by m, or
 * div[i] = 0 otherwise.
 * 
 * 
 * Return the divisibility array of word.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "998244353", m = 3
 * Output: [1,1,0,0,0,1,1,0,0]
 * Explanation: There are only 4 prefixes that are divisible by 3: "9", "99",
 * "998244", and "9982443".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "1010", m = 10
 * Output: [0,1,0,1]
 * Explanation: There are only 2 prefixes that are divisible by 10: "10", and
 * "1010".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * word.length == n
 * word consists of digits from 0 to 9
 * 1 <= m <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] divisibilityArray(String word, int m) {
        int[] ans = new int[word.length()];
        long rem = 0;
        for(int i = 0; i < word.length(); i++){
            rem = rem * 10 + word.charAt(i) - '0';
            rem %= m;
            // System.out.println(rem);
            ans[i] = (rem == 0 ? 1 : 0);
        }
        return ans;
    }
}
// @lc code=end
