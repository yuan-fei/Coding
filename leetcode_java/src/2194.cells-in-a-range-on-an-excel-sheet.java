/*
 * @lc app=leetcode id=2194 lang=java
 *
 * [2194] Cells in a Range on an Excel Sheet
 *
 * https://leetcode.com/problems/cells-in-a-range-on-an-excel-sheet/description/
 *
 * algorithms
 * Easy (84.71%)
 * Likes:    71
 * Dislikes: 24
 * Total Accepted:    14K
 * Total Submissions: 16.5K
 * Testcase Example:  '"K1:L2"'
 *
 * A cell (r, c) of an excel sheet is represented as a string "<col><row>"
 * where:
 * 
 * 
 * <col> denotes the column number c of the cell. It is represented by
 * alphabetical letters.
 * 
 * 
 * For example, the 1^st column is denoted by 'A', the 2^nd by 'B', the 3^rd by
 * 'C', and so on.
 * 
 * 
 * <row> is the row number r of the cell. The r^th row is represented by the
 * integer r.
 * 
 * 
 * You are given a string s in the format "<col1><row1>:<col2><row2>", where
 * <col1> represents the column c1, <row1> represents the row r1, <col2>
 * represents the column c2, and <row2> represents the row r2, such that r1 <=
 * r2 and c1 <= c2.
 * 
 * Return the list of cells (x, y) such that r1 <= x <= r2 and c1 <= y <= c2.
 * The cells should be represented as strings in the format mentioned above and
 * be sorted in non-decreasing order first by columns and then by rows.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "K1:L2"
 * Output: ["K1","K2","L1","L2"]
 * Explanation:
 * The above diagram shows the cells which should be present in the list.
 * The red arrows denote the order in which the cells should be presented.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "A1:F1"
 * Output: ["A1","B1","C1","D1","E1","F1"]
 * Explanation:
 * The above diagram shows the cells which should be present in the list.
 * The red arrow denotes the order in which the cells should be presented.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * s.length == 5
 * 'A' <= s[0] <= s[3] <= 'Z'
 * '1' <= s[1] <= s[4] <= '9'
 * s consists of uppercase English letters, digits and ':'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> cellsInRange(String s) {
        String[] parts = s.split(":");
        char rStart = parts[0].charAt(0);
        char rEnd = parts[1].charAt(0);
        int cStart = parts[0].charAt(1) - '0';
        int cEnd = parts[1].charAt(1) - '0';
        List<String> l = new ArrayList<>();
        for(char c = rStart; c <= rEnd; c++){
            for(int i = cStart; i <= cEnd; i++){
                l.add("" + c + i);
            }
        }
        return l;
    }
}
// @lc code=end
