/*
 * @lc app=leetcode id=964 lang=java
 *
 * [964] Least Operators to Express Number
 *
 * https://leetcode.com/problems/least-operators-to-express-number/description/
 *
 * algorithms
 * Hard (48.04%)
 * Likes:    307
 * Dislikes: 67
 * Total Accepted:    9.3K
 * Total Submissions: 19.3K
 * Testcase Example:  '3\n19'
 *
 * Given a single positive integer x, we will write an expression of the form x
 * (op1) x (op2) x (op3) x ... where each operator op1, op2, etc. is either
 * addition, subtraction, multiplication, or division (+, -, *, or /). For
 * example, with x = 3, we might write 3 * 3 / 3 + 3 - 3 which is a value of
 * 3.
 * 
 * When writing such an expression, we adhere to the following
 * conventions:
 * 
 * 
 * The division operator (/) returns rational numbers.
 * There are no parentheses placed anywhere.
 * We use the usual order of operations: multiplication and division happen
 * before addition and subtraction.
 * It is not allowed to use the unary negation operator (-). For example, "x -
 * x" is a valid expression as it only uses subtraction, but "-x + x" is not
 * because it uses negation.
 * 
 * 
 * We would like to write an expression with the least number of operators such
 * that the expression equals the given target. Return the least number of
 * operators used.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: x = 3, target = 19
 * Output: 5
 * Explanation: 3 * 3 + 3 * 3 + 3 / 3.
 * The expression contains 5 operations.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = 5, target = 501
 * Output: 8
 * Explanation: 5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.
 * The expression contains 8 operations.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: x = 100, target = 100000000
 * Output: 3
 * Explanation: 100 * 100 * 100 * 100.
 * The expression contains 3 operations.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= x <= 100
 * 1 <= target <= 2 * 10^8
 * 
 * 
 */

// @lc code=start
class Solution {
    public int leastOpsExpressTarget(int x, int target) {
        List<Integer> r = convertToBase(target, x);
        // System.out.println(r);
        int MAX = 5000;
        int[] dp = new int[2];
        dp[1] = MAX;
        for(int i = 0; i <= r.size(); i++){
            int d = 0;
            if(i < r.size()){
                d = r.get(i);
            }
            
            int[] newDp = new int[2];
            Arrays.fill(newDp, MAX);
            if(d == 0){
                newDp[0] = Math.min(dp[0], newDp[0]);
                newDp[0] = Math.min(dp[1] + (d + 1) * getOpCount(i) + d + 1, newDp[0]);
                newDp[1] = Math.min(dp[1] + (x - d - 1) * getOpCount(i) + (x - d - 2) + 1, newDp[1]);
            }
            else{
                newDp[0] = Math.min(dp[0] + d * getOpCount(i) + (d - 1) + 1, newDp[0]);
                newDp[1] = Math.min(dp[0] + (x - d) * getOpCount(i) + (x - d - 1) + 1, newDp[1]);
                // System.out.println(d + "[1]" + Arrays.toString(newDp));
                if(d + 1 == x){
                    newDp[1] = Math.min(newDp[1], dp[1]);
                    // System.out.println(d + "[2]" + Arrays.toString(newDp));
                }
                else{
                    newDp[0] = Math.min(dp[1] + (d + 1) * getOpCount(i) + d + 1, newDp[0]);
                    newDp[1] = Math.min(dp[1] + (x - d - 1) * getOpCount(i) + (x - d - 2) + 1, newDp[1]);
                    // System.out.println(d + "[3]" + Arrays.toString(newDp));
                }
            }
            dp = newDp;
            // System.out.println(Arrays.toString(dp));
        }
        return dp[0] - 1;
    }

    int getOpCount(int digit){
        if(digit == 0){
            return 1;
        }
        else {
            return digit - 1;
        }
    }

    List<Integer> convertToBase(int n, int base){
        List<Integer> ret = new ArrayList<>();
        while(n != 0){
            ret.add(n % base);
            n /= base;
        }
        // ret.reverse();
        return ret;
    }
}
// @lc code=end
