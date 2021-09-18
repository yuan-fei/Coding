/*
 * @lc app=leetcode id=2002 lang=java
 *
 * [2002] Maximum Product of the Length of Two Palindromic Subsequences
 *
 * https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/description/
 *
 * algorithms
 * Medium (48.83%)
 * Likes:    186
 * Dislikes: 15
 * Total Accepted:    6.3K
 * Total Submissions: 12.8K
 * Testcase Example:  '"leetcodecom"'
 *
 * Given a string s, find two disjoint palindromic subsequences of s such that
 * the product of their lengths is maximized. The two subsequences are disjoint
 * if they do not both pick a character at the same index.
 * 
 * Return the maximum possible product of the lengths of the two palindromic
 * subsequences.
 * 
 * A subsequence is a string that can be derived from another string by
 * deleting some or no characters without changing the order of the remaining
 * characters. A string is palindromic if it reads the same forward and
 * backward.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "leetcodecom"
 * Output: 9
 * Explanation: An optimal solution is to choose "ete" for the 1^st subsequence
 * and "cdc" for the 2^nd subsequence.
 * The product of their lengths is: 3 * 3 = 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "bb"
 * Output: 1
 * Explanation: An optimal solution is to choose "b" (the first character) for
 * the 1^st subsequence and "b" (the second character) for the 2^nd
 * subsequence.
 * The product of their lengths is: 1 * 1 = 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "accbcaxxcxx"
 * Output: 25
 * Explanation: An optimal solution is to choose "accca" for the 1^st
 * subsequence and "xxcxx" for the 2^nd subsequence.
 * The product of their lengths is: 5 * 5 = 25.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= s.length <= 12
 * s consists of lowercase English letters only.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxProduct(String s) {
        int n = s.length();
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for(int i = 0; i < (1 << n); i++){
            int h1 = -1;
            int l1 = n;
            for(int j = 0; j < n; j++){
                if(((i >> j) & 1) != 0){
                    h1 = j;
                    l1 = Math.min(l1, j);
                }
            }
            if(h1 != -1){
                int p = 1 << h1;
                p |= 1 << l1;
                if(set.contains(i ^ p) && s.charAt(h1) == s.charAt(l1)){
                    set.add(i);
                }
            }
        }
        int ret = 0;
        for(int x : set){
            for(int y : set){
                if((x & y) == 0){
                    ret = Math.max(ret, Integer.bitCount(x) * Integer.bitCount(y));
                }
            }
        }
        return ret;
    }

}
// @lc code=end
