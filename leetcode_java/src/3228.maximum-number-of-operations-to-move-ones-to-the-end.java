/*
 * @lc app=leetcode id=3228 lang=java
 *
 * [3228] Maximum Number of Operations to Move Ones to the End
 *
 * https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/description/
 *
 * algorithms
 * Medium (53.86%)
 * Likes:    531
 * Dislikes: 38
 * Total Accepted:    123.7K
 * Total Submissions: 184.2K
 * Testcase Example:  '"1001101"'
 *
 * You are given a binary string s.
 * 
 * You can perform the following operation on the string any number of
 * times:
 * 
 * 
 * Choose any index i from the string where i + 1 < s.length such that s[i] ==
 * '1' and s[i + 1] == '0'.
 * Move the character s[i] to the right until it reaches the end of the string
 * or another '1'. For example, for s = "010010", if we choose i = 1, the
 * resulting string will be s = "000110".
 * 
 * 
 * Return the maximum number of operations that you can perform.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "1001101"
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * We can perform the following operations:
 * 
 * 
 * Choose index i = 0. The resulting string is s = "0011101".
 * Choose index i = 4. The resulting string is s = "0011011".
 * Choose index i = 3. The resulting string is s = "0010111".
 * Choose index i = 2. The resulting string is s = "0001111".
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "00111"
 * 
 * Output: 0
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
    public int maxOperations(String s) {
        int countOnes = (s.charAt(0) == '1' ? 1 : 0);
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                countOnes++;
            } else if (s.charAt(i) != s.charAt(i - 1)) {
                ans += countOnes;
            }
        }
        return ans;
    }
}
// @lc code=end
