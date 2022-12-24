/*
 * @lc app=leetcode id=753 lang=java
 *
 * [753] Cracking the Safe
 *
 * https://leetcode.com/problems/cracking-the-safe/description/
 *
 * algorithms
 * Hard (55.55%)
 * Likes:    350
 * Dislikes: 64
 * Total Accepted:    51K
 * Total Submissions: 91.7K
 * Testcase Example:  '1\n2'
 *
 * There is a safe protected by a password. The password is a sequence of n
 * digits where each digit can be in the range [0, k - 1].
 * 
 * The safe has a peculiar way of checking the password. When you enter in a
 * sequence, it checks the most recent n digits that were entered each time you
 * type a digit.
 * 
 * 
 * For example, the correct password is "345" and you enter in
 * "012345":
 * 
 * 
 * After typing 0, the most recent 3 digits is "0", which is incorrect.
 * After typing 1, the most recent 3 digits is "01", which is
 * incorrect.
 * After typing 2, the most recent 3 digits is "012", which is
 * incorrect.
 * After typing 3, the most recent 3 digits is "123", which is
 * incorrect.
 * After typing 4, the most recent 3 digits is "234", which is
 * incorrect.
 * After typing 5, the most recent 3 digits is "345", which is correct and the
 * safe unlocks.
 * 
 * 
 * 
 * 
 * Return any string of minimum length that will unlock the safe at some point
 * of entering it.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1, k = 2
 * Output: "10"
 * Explanation: The password is a single digit, so enter each digit. "01" would
 * also unlock the safe.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, k = 2
 * Output: "01100"
 * Explanation: For each possible password:
 * - "00" is typed in starting from the 4^th digit.
 * - "01" is typed in starting from the 1^st digit.
 * - "10" is typed in starting from the 3^rd digit.
 * - "11" is typed in starting from the 2^nd digit.
 * Thus "01100" will unlock the safe. "01100", "10011", and "11001" would also
 * unlock the safe.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 4
 * 1 <= k <= 10
 * 1 <= k^n <= 4096
 * 
 * 
 */

// @lc code=start
class Solution {
    public String crackSafe(int n, int k) {
        if(k == 1){
            String ans = "";
            for(int i = 0; i < n; i++){
                ans += '0';
            }
            return ans;
        }
        StringBuilder sb = new StringBuilder();
        int base = (int) Math.pow(k, n - 1);
        euler(0, n, k, new boolean[base][k], sb);
        return sb.toString() + sb.substring(0, n - 1);
    }

    void euler(int u, int n, int k, boolean[][] seen, StringBuilder sb) {
        for (int i = 0; i < k; i++) {
            if (!seen[u][i]) {
                seen[u][i] = true;
                int base = (int) Math.pow(k, n - 1);
                int v = (u * k + i) % base;
                euler(v, n, k, seen, sb);
                sb.append((char) ('0' + i));
            }
        }
    }
}
// @lc code=end
