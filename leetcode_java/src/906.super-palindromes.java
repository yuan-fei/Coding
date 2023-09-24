/*
 * @lc app=leetcode id=906 lang=java
 *
 * [906] Super Palindromes
 *
 * https://leetcode.com/problems/super-palindromes/description/
 *
 * algorithms
 * Hard (38.89%)
 * Likes:    329
 * Dislikes: 407
 * Total Accepted:    22.6K
 * Total Submissions: 58.2K
 * Testcase Example:  '"4"\n"1000"'
 *
 * Let's say a positive integer is a super-palindrome if it is a palindrome,
 * and it is also the square of a palindrome.
 * 
 * Given two positive integers left and right represented as strings, return
 * the number of super-palindromes integers in the inclusive range [left,
 * right].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: left = "4", right = "1000"
 * Output: 4
 * Explanation: 4, 9, 121, and 484 are superpalindromes.
 * Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a
 * palindrome.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: left = "1", right = "2"
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= left.length, right.length <= 18
 * left and right consist of only digits.
 * left and right cannot have leading zeros.
 * left and right represent integers in the range [1, 10^18 - 1].
 * left is less than or equal to right.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int superpalindromesInRange(String left, String right) {
        long lb = (long)Math.sqrt(Long.parseLong(left) - 1);
        long ub = (long)Math.sqrt(Long.parseLong(right));
        return superpalindromesUnderPalindrome(ub) - superpalindromesUnderPalindrome(lb);
    }

    int superpalindromesUnderPalindrome(long ub){
        long dCnt = 1;
        long p = 0;
        int cnt = (ub >= 3) ? 4 : (int)ub + 1;

        while(true){
            for(long d = dCnt; d < dCnt * 10; d++){
                String s = "" + d;
                StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.reverse();
                p = Long.parseLong(s + sb.toString());
                if(p > ub){
                    // System.out.println(Arrays.asList(ub, cnt));
                    return cnt;
                }
                if(checkPalindrom("" + (p * p))){
                    cnt++;
                }
            }
            for(long d = dCnt; d < dCnt * 10; d++){
                String s = "" + d;
                StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.reverse();
                for(int m = 0; m < 10; m++){
                    p = Long.parseLong(s + m + sb.toString());
                    if(p > ub){
                        // System.out.println(Arrays.asList(ub, cnt));
                        return cnt;
                    }
                    if(checkPalindrom("" + (p * p))){
                        cnt++;
                    }
                }
            }
            dCnt *= 10;
        }
    }

    boolean checkPalindrom(String s){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != s.charAt(s.length() - i - 1)){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
