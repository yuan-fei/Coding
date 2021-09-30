/*
 * @lc app=leetcode id=2018 lang=java
 *
 * [2018] Check if Word Can Be Placed In Crossword
 *
 * https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/description/
 *
 * algorithms
 * Medium (45.95%)
 * Likes:    81
 * Dislikes: 90
 * Total Accepted:    3.8K
 * Total Submissions: 8.4K
 * Testcase Example:  '[["#"," ","#"],[" "," ","#"],["#","c"," "]]\n"abc"'
 *
 * You are given an m x n matrix board, representing the current state of a
 * crossword puzzle. The crossword contains lowercase English letters (from
 * solved words), ' ' to represent any empty cells, and '#' to represent any
 * blocked cells.
 * 
 * A word can be placed horizontally (left to right or right to left) or
 * vertically (top to bottom or bottom to top) in the board if:
 * 
 * 
 * It does not occupy a cell containing the character '#'.
 * The cell each letter is placed in must either be ' ' (empty) or match the
 * letter already on the board.
 * There must not be any empty cells ' ' or other lowercase letters directly
 * left or right of the word if the word was placed horizontally.
 * There must not be any empty cells ' ' or other lowercase letters directly
 * above or below the word if the word was placed vertically.
 * 
 * 
 * Given a string word, return true if word can be placed in board, or false
 * otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word =
 * "abc"
 * Output: true
 * Explanation: The word "abc" can be placed as shown above (top to bottom).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word =
 * "ac"
 * Output: false
 * Explanation: It is impossible to place the word because there will always be
 * a space/letter above or below it.
 * 
 * Example 3:
 * 
 * 
 * Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word =
 * "ca"
 * Output: true
 * Explanation: The word "ca" can be placed as shown above (right to
 * left). 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == board.length
 * n == board[i].length
 * 1 <= m * n <= 2 * 10^5
 * board[i][j] will be ' ', '#', or a lowercase English letter.
 * 1 <= word.length <= max(m, n)
 * word will contain only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    String w;
    char[][] b;
    public boolean placeWordInCrossword(char[][] board, String word) {
        b = board;
        w = word;
        int n = b.length;
        int m = b[0].length;
        for(int i = 0; i < n; i++){
            if(check(i, 0, m, 0) || check(i, m - 1, -1, 0)){
                return true;
            }
        }
        for(int i = 0; i < m; i++){
            if(check(i, 0, n, 1) || check(i, n - 1, -1, 1)){
                return true;
            }
        }
        return false;
    }

    boolean check(int k, int start, int end, int axis){
        int i = start;
        int delta = (int)Math.signum(end - start);
        // System.out.println(k + ", " + start + ", " + i);
        while(i * delta < end * delta){
            while(i * delta < end * delta && get(k, i, axis) == '#'){
                i += delta;
            }
            // System.out.println(k + ", " + start + ", " + i);
            int j = 0;
            while(i * delta < end * delta && j < w.length()){
                // System.out.println(w.charAt(j) + " != " + get(k, i, axis) + (get(k, i, axis) != ' '));
                if(w.charAt(j) != get(k, i, axis) && get(k, i, axis) != ' '){
                    break;
                }
                i += delta;
                j++;
            }
            if(j == w.length() && (i == end || get(k, i, axis) == '#')){
                // System.out.println(k + ", " + start + ", " + i);
                return true;
            }
            while(i * delta < end * delta && get(k, i, axis) != '#'){
                i += delta;
            }
        }
        return false;
    }

    char get(int i, int j, int axis){
        if(axis == 0){
            return b[i][j];
        }
        else{
            return b[j][i];
        }
    }
}
// @lc code=end
