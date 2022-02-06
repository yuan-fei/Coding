/*
 * @lc app=leetcode id=2160 lang=java
 *
 * [2160] Minimum Sum of Four Digit Number After Splitting Digits
 *
 * https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/description/
 *
 * algorithms
 * Easy (85.44%)
 * Likes:    75
 * Dislikes: 9
 * Total Accepted:    11K
 * Total Submissions: 12.9K
 * Testcase Example:  '2932'
 *
 * You are given a positive integer num consisting of exactly four digits.
 * Split num into two new integers new1 and new2 by using the digits found in
 * num. Leading zeros are allowed in new1 and new2, and all the digits found in
 * num must be used.
 * 
 * 
 * For example, given num = 2932, you have the following digits: two 2's, one 9
 * and one 3. Some of the possible pairs [new1, new2] are [22, 93], [23, 92],
 * [223, 9] and [2, 329].
 * 
 * 
 * Return the minimum possible sum of new1 and new2.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 2932
 * Output: 52
 * Explanation: Some possible pairs [new1, new2] are [29, 23], [223, 9], etc.
 * The minimum sum can be obtained by the pair [29, 23]: 29 + 23 = 52.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 4009
 * Output: 13
 * Explanation: Some possible pairs [new1, new2] are [0, 49], [490, 0], etc. 
 * The minimum sum can be obtained by the pair [4, 9]: 4 + 9 = 13.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1000 <= num <= 9999
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumSum(int num) {
        char[] nChars = ("" + num).toCharArray();
        Arrays.sort(nChars);
        int ans = 10000;
        for(int i = 0; i < (1 << 4); i++){
            int a = 0;
            int b = 0;
            for(int j = 0; j < 4; j++){
                if(((i >> j) & 1) != 0){
                    a *= 10;
                    a += nChars[j] - '0';
                }
                else{
                    b *= 10;
                    b += nChars[j] - '0';
                }
            }
            ans = Math.min(ans, a + b);
        }
        return ans;
    }
}
// @lc code=end
