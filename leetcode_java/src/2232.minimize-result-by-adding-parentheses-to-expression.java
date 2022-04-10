/*
 * @lc app=leetcode id=2232 lang=java
 *
 * [2232] Minimize Result by Adding Parentheses to Expression
 *
 * https://leetcode.com/problems/minimize-result-by-adding-parentheses-to-expression/description/
 *
 * algorithms
 * Medium (59.29%)
 * Likes:    18
 * Dislikes: 68
 * Total Accepted:    4.9K
 * Total Submissions: 8.3K
 * Testcase Example:  '"247+38"'
 *
 * You are given a 0-indexed string expression of the form "<num1>+<num2>"
 * where <num1> and <num2> represent positive integers.
 * 
 * Add a pair of parentheses to expression such that after the addition of
 * parentheses, expression is a valid mathematical expression and evaluates to
 * the smallest possible value. The left parenthesis must be added to the left
 * of '+' and the right parenthesis must be added to the right of '+'.
 * 
 * Return expression after adding a pair of parentheses such that expression
 * evaluates to the smallest possible value. If there are multiple answers that
 * yield the same result, return any of them.
 * 
 * The input has been generated such that the original value of expression, and
 * the value of expression after adding any pair of parentheses that meets the
 * requirements fits within a signed 32-bit integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: expression = "247+38"
 * Output: "2(47+38)"
 * Explanation: The expression evaluates to 2 * (47 + 38) = 2 * 85 = 170.
 * Note that "2(4)7+38" is invalid because the right parenthesis must be to the
 * right of the '+'.
 * It can be shown that 170 is the smallest possible value.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: expression = "12+34"
 * Output: "1(2+3)4"
 * Explanation: The expression evaluates to 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: expression = "999+999"
 * Output: "(999+999)"
 * Explanation: The expression evaluates to 999 + 999 = 1998.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= expression.length <= 10
 * expression consists of digits from '1' to '9' and '+'.
 * expression starts and ends with digits.
 * expression contains exactly one '+'.
 * The original value of expression, and the value of expression after adding
 * any pair of parentheses that meets the requirements fits within a signed
 * 32-bit integer.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String minimizeResult(String expression) {
        int n = expression.length();
        int idxPlus = expression.indexOf('+');
        int min = Integer.MAX_VALUE;
        int[] pos = {0, 0};
        for(int i = 0 ; i < idxPlus; i++){
            for(int j = idxPlus + 1; j < n; j++){
                int a = parse(expression, 0, i - 1);
                int b = parse(expression, i, j);
                int c = parse(expression, j + 1, n - 1);
                if(a * b * c < min){
                    min = a * b * c;    
                    pos = new int[]{i, j};
                }
            }
        }    
        return expression.substring(0, pos[0]) + "(" + expression.substring(pos[0], pos[1] + 1) + ")" + expression.substring(pos[1] + 1);
    }

    int parse(String e, int start, int end){
        if(start > end){
            return 1;
        }
        String s = e.substring(start, end + 1);
        int idxPlus = s.indexOf('+');
        if(idxPlus > 0){
            return Integer.parseInt(s.substring(0, idxPlus)) + Integer.parseInt(s.substring(idxPlus + 1));
        }
        else{
            return Integer.parseInt(s);
        }
    }
}
// @lc code=end
