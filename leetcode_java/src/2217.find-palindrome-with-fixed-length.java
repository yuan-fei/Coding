/*
 * @lc app=leetcode id=2217 lang=java
 *
 * [2217] Find Palindrome With Fixed Length
 *
 * https://leetcode.com/problems/find-palindrome-with-fixed-length/description/
 *
 * algorithms
 * Medium (27.16%)
 * Likes:    115
 * Dislikes: 111
 * Total Accepted:    4.9K
 * Total Submissions: 17.9K
 * Testcase Example:  '[1,2,3,4,5,90]\n3'
 *
 * Given an integer array queries and a positive integer intLength, return an
 * array answer where answer[i] is either the queries[i]^th smallest positive
 * palindrome of length intLength or -1 if no such palindrome exists.
 * 
 * A palindrome is a number that reads the same backwards and forwards.
 * Palindromes cannot have leading zeros.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: queries = [1,2,3,4,5,90], intLength = 3
 * Output: [101,111,121,131,141,999]
 * Explanation:
 * The first few palindromes of length 3 are:
 * 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 201, ...
 * The 90^th palindrome of length 3 is 999.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: queries = [2,4,6], intLength = 4
 * Output: [1111,1331,1551]
 * Explanation:
 * The first six palindromes of length 4 are:
 * 1001, 1111, 1221, 1331, 1441, and 1551.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= queries.length <= 5 * 10^4
 * 1 <= queries[i] <= 10^9
 * 1 <= intLength <= 15
 * 
 * 
 */

// @lc code=start
class Solution {
    public long[] kthPalindrome(int[] queries, int intLength) {
        long base = 1;
        for(int i = 0; i < (intLength - 1) / 2; i++){
            base *= 10;
        }
        long[] ans = new long[queries.length];
        for(int i = 0; i < queries.length; i++){
            long prefix = base + queries[i] - 1;
            if(("" + prefix).length() > ("" + base).length()){
                ans[i] = -1;
            }
            else{
                // System.out.println(prefix);
                StringBuilder sb = new StringBuilder();
                sb.append(("" + prefix).substring(0, intLength / 2));
                sb.reverse();
                ans[i] = Long.parseLong(prefix + sb.toString());    
            }
            
        }
        return ans;
    }

}
// @lc code=end
