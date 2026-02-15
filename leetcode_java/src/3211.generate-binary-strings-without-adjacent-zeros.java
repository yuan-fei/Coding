/*
 * @lc app=leetcode id=3211 lang=java
 *
 * [3211] Generate Binary Strings Without Adjacent Zeros
 *
 * https://leetcode.com/problems/generate-binary-strings-without-adjacent-zeros/description/
 *
 * algorithms
 * Medium (86.80%)
 * Likes:    271
 * Dislikes: 46
 * Total Accepted:    73.9K
 * Total Submissions: 84K
 * Testcase Example:  '3'
 *
 * You are given a positive integer n.
 * 
 * A binary string x is valid if all substrings of x of length 2 contain at
 * least one "1".
 * 
 * Return all valid strings with length n, in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3
 * 
 * Output: ["010","011","101","110","111"]
 * 
 * Explanation:
 * 
 * The valid strings of length 3 are: "010", "011", "101", "110", and "111".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1
 * 
 * Output: ["0","1"]
 * 
 * Explanation:
 * 
 * The valid strings of length 1 are: "0" and "1".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 18
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> validStrings(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            if (checkConsecutiveZeros(i, n)) {
                ans.add(padding(Integer.toBinaryString(i), n));
            }
        }
        return ans;
    }

    boolean checkConsecutiveZeros(int x, int n) {
        for (int i = 1; i < n; i++) {
            if (((x >> i) & 1) == 0 && ((x >> (i - 1)) & 1) == 0) {
                return false;
            }
        }
        return true;
    }

    String padding(String s, int l) {
        int paddingLength = l - s.length();
        String padding = "";
        for (int i = 0; i < paddingLength; i++) {
            padding += "0";
        }
        return padding + s;
    }
}
// @lc code=end
