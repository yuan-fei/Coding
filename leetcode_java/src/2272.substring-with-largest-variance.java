/*
 * @lc app=leetcode id=2272 lang=java
 *
 * [2272] Substring With Largest Variance
 *
 * https://leetcode.com/problems/substring-with-largest-variance/description/
 *
 * algorithms
 * Hard (26.15%)
 * Likes:    175
 * Dislikes: 12
 * Total Accepted:    2.3K
 * Total Submissions: 8.9K
 * Testcase Example:  '"aababbb"'
 *
 * The variance of a string is defined as the largest difference between the
 * number of occurrences of any 2 characters present in the string. Note the
 * two characters may or may not be the same.
 * 
 * Given a string s consisting of lowercase English letters only, return the
 * largest variance possible among all substrings of s.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aababbb"
 * Output: 3
 * Explanation:
 * All possible variances along with their respective substrings are listed
 * below:
 * - Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b",
 * "bb", and "bbb".
 * - Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb",
 * "aababbb", and "bab".
 * - Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
 * - Variance 3 for substring "babbb".
 * Since the largest possible variance is 3, we return it.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abcde"
 * Output: 0
 * Explanation:
 * No letter occurs more than once in s, so the variance of every substring is
 * 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int largestVariance(String s) {
        
        int [] freq = new int[26];
        for(int i = 0 ; i < s.length() ; i++)
            freq[(int)(s.charAt(i) - 'a')]++;
        
        int maxVariance = 0;
        for(int a = 0 ; a < 26 ; a++){
            for(int b = 0 ; b < 26 ; b++){
                int remainingA = freq[a];
                int remainingB = freq[b];
                if(a == b || remainingA == 0 || remainingB == 0) continue;
                
                // run kadanes on each possible character pairs (A & B)
                int currBFreq = 0, currAFreq = 0;
                for(int i = 0 ; i < s.length() ; i++){
                    int c =  (int)(s.charAt(i) - 'a');
                    
                    if(c == b) currBFreq++;
                    if(c == a) {
                        currAFreq++;
                        remainingA--;
                    }
                    
                    if(currAFreq > 0)
                        maxVariance = Math.max(maxVariance, currBFreq - currAFreq);
                    
                    if(currBFreq < currAFreq &&  remainingA >= 1){
                        currBFreq = 0;
                        currAFreq = 0;
                    }
                }
            }
        }
        
        return maxVariance;
    }
}
// @lc code=end
