/*
 * @lc app=leetcode id=842 lang=java
 *
 * [842] Split Array into Fibonacci Sequence
 *
 * https://leetcode.com/problems/split-array-into-fibonacci-sequence/description/
 *
 * algorithms
 * Medium (38.45%)
 * Likes:    1035
 * Dislikes: 285
 * Total Accepted:    35.2K
 * Total Submissions: 91.6K
 * Testcase Example:  '"1101111"'
 *
 * You are given a string of digits num, such as "123456579". We can split it
 * into a Fibonacci-like sequence [123, 456, 579].
 * 
 * Formally, a Fibonacci-like sequence is a list f of non-negative integers
 * such that:
 * 
 * 
 * 0 <= f[i] < 2^31, (that is, each integer fits in a 32-bit signed integer
 * type),
 * f.length >= 3, and
 * f[i] + f[i + 1] == f[i + 2] for all 0 <= i < f.length - 2.
 * 
 * 
 * Note that when splitting the string into pieces, each piece must not have
 * extra leading zeroes, except if the piece is the number 0 itself.
 * 
 * Return any Fibonacci-like sequence split from num, or return [] if it cannot
 * be done.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = "1101111"
 * Output: [11,0,11,11]
 * Explanation: The output [110, 1, 111] would also be accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "112358130"
 * Output: []
 * Explanation: The task is impossible.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: num = "0123"
 * Output: []
 * Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not
 * valid.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num.length <= 200
 * num contains only digits.
 * 
 * 
 */

// @lc code=start
import java.math.BigInteger;
class Solution {
    public List<Integer> splitIntoFibonacci(String num) {
        int n = num.length();
        int maxL = ("" + Integer.MAX_VALUE).length();
        for(int r = 1; r <= n - 1; r++){
            for(int l = 1; l < r; l++){
                // System.out.println(l + ", " + r);
                if(l <= maxL && r - l <= maxL && Long.parseLong(num.substring(0, l)) <= Integer.MAX_VALUE && Long.parseLong(num.substring(l, r)) <= Integer.MAX_VALUE){
                    List<Integer> ret = getSeq(num, l, r);
                    if(!ret.isEmpty()){
                        return ret;
                    }    
                }
            }
        }
        return new ArrayList<>();
    }

    List<Integer> getSeq(String num, int first, int second){
        // System.out.println(first + ", " + second);
        List<Integer> ret = new ArrayList<>();
        long n1 = Long.parseLong(num.substring(0, first));
        long n2 = Long.parseLong(num.substring(first, second));
        if(n1 != 0 && num.charAt(0) == '0'){
            return ret;
        }
        if(n2 != 0 && num.charAt(first) == '0'){
            return ret;
        }
        ret.add((int)n1);
        ret.add((int)n2);
        int l = second;
        while(l < num.length()){
            long n3 = n1 + n2;
            if(n3 > Integer.MAX_VALUE){
                ret = new ArrayList<>();
                break;
            }
            String n3s = "" + n3;
            if(!n3s.equals(num.substring(l, Math.min(num.length(), l + n3s.length())))){
                ret = new ArrayList<>();
                break;
            }
            ret.add((int)n3);
            n1 = n2;
            n2 = n3;
            l += n3s.length();
        }
        return ret;
    }
}
// @lc code=end
