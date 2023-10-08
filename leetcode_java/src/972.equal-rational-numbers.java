/*
 * @lc app=leetcode id=972 lang=java
 *
 * [972] Equal Rational Numbers
 *
 * https://leetcode.com/problems/equal-rational-numbers/description/
 *
 * algorithms
 * Hard (43.30%)
 * Likes:    90
 * Dislikes: 208
 * Total Accepted:    6.6K
 * Total Submissions: 15.3K
 * Testcase Example:  '"0.(52)"\n"0.5(25)"'
 *
 * Given two strings s and t, each of which represents a non-negative rational
 * number, return true if and only if they represent the same number. The
 * strings may use parentheses to denote the repeating part of the rational
 * number.
 * 
 * A rational number can be represented using up to three parts: <IntegerPart>,
 * <NonRepeatingPart>, and a <RepeatingPart>. The number will be represented in
 * one of the following three ways:
 * 
 * 
 * <IntegerPart>
 * 
 * 
 * For example, 12, 0, and 123.
 * 
 * 
 * <IntegerPart><.><NonRepeatingPart>
 * 
 * For example, 0.5, 1., 2.12, and 123.0001.
 * 
 * 
 * <IntegerPart><.><NonRepeatingPart><(><RepeatingPart><)>
 * 
 * For example, 0.1(6), 1.(9), 123.00(1212).
 * 
 * 
 * 
 * 
 * The repeating portion of a decimal expansion is conventionally denoted
 * within a pair of round brackets. For example:
 * 
 * 
 * 1/6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66).
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "0.(52)", t = "0.5(25)"
 * Output: true
 * Explanation: Because "0.(52)" represents 0.52525252..., and "0.5(25)"
 * represents 0.52525252525..... , the strings represent the same number.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "0.1666(6)", t = "0.166(66)"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "0.9(9)", t = "1."
 * Output: true
 * Explanation: "0.9(9)" represents 0.999999999... repeated forever, which
 * equals 1.  [See this link for an explanation.]
 * "1." represents the number 1, which is formed correctly: (IntegerPart) = "1"
 * and (NonRepeatingPart) = "".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * Each part consists only of digits.
 * The <IntegerPart> does not have leading zeros (except for the zero
 * itself).
 * 1 <= <IntegerPart>.length <= 4
 * 0 <= <NonRepeatingPart>.length <= 4
 * 1 <= <RepeatingPart>.length <= 4
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isRationalEqual(String s, String t) {
        long[] r1 = getFractional(s);
        // System.out.println(Arrays.toString(r1));
        long[] r2 = getFractional(t);
        // System.out.println(Arrays.toString(r2));
        return Arrays.equals(r1, r2);
    }

    long[] getFractional(String s){
        if(s.charAt(s.length() - 1) == '.'){
            s = s.substring(0, s.length() - 1);
        }
        int dot = s.indexOf(".");
        if(dot < 0){
            return new long[]{Long.parseLong(s), 1};
        }
        int l = s.indexOf("(");
        int r = s.indexOf(")");
        s = s.replaceAll("[\\(\\)]", "");
        int digit = s.length() - dot - 1;
        s = s.replaceAll("[\\.]", "");

        long v1 = Long.parseLong(s);
        long base1 = getBase10(digit);
        if(l >= 0){
            long v2 = Long.parseLong(s.substring(0, s.length() - (r - l - 1)));
            long base2 = getBase10(l - dot - 1);
            return reduce(new long[]{v1 - v2, base1 - base2});
        }
        else{
            return reduce(new long[]{v1, base1});
        }
    }

    long getBase10(int digit){
        long n =1;
        for(int i = 0; i < digit; i++){
            n *= 10;
        }
        return n;
    }

    long lcd(long a, long b){
        if(b == 0){
            return a;
        }
        else{
            return lcd(b, a % b);
        }
    }

    long[] reduce(long[] f){
        // System.out.println(Arrays.toString(f));
        long d = lcd(f[0], f[1]);
        return new long[]{f[0] / d, f[1] / d};
    }
}
// @lc code=end
