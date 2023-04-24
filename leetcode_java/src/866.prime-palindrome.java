/*
 * @lc app=leetcode id=866 lang=java
 *
 * [866] Prime Palindrome
 *
 * https://leetcode.com/problems/prime-palindrome/description/
 *
 * algorithms
 * Medium (25.72%)
 * Likes:    372
 * Dislikes: 761
 * Total Accepted:    27.9K
 * Total Submissions: 108.5K
 * Testcase Example:  '6'
 *
 * Given an integer n, return the smallest prime palindrome greater than or
 * equal to n.
 * 
 * An integer is prime if it has exactly two divisors: 1 and itself. Note that
 * 1 is not a prime number.
 * 
 * 
 * For example, 2, 3, 5, 7, 11, and 13 are all primes.
 * 
 * 
 * An integer is a palindrome if it reads the same from left to right as it
 * does from right to left.
 * 
 * 
 * For example, 101 and 12321 are palindromes.
 * 
 * 
 * The test cases are generated so that the answer always exists and is in the
 * range [2, 2 * 10^8].
 * 
 * 
 * Example 1:
 * Input: n = 6
 * Output: 7
 * Example 2:
 * Input: n = 8
 * Output: 11
 * Example 3:
 * Input: n = 13
 * Output: 101
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^8
 * 
 * 
 */

// @lc code=start
class Solution {
    public int primePalindrome(int n) {
        for(int lb = 1, ub = 10; ; lb *= 10, ub *= 10){
            for(int j = 1; j >= 0; j--){
                for(int i = lb; i < ub; i++){
                    String s = "" + i;
                    StringBuilder sb = new StringBuilder(s.substring(0, s.length() - j));
                    String palindromeStr = s + sb.reverse().toString();
                    int p = Integer.parseInt(palindromeStr);
                    if(p >= n && isPrime(p)){
                        return p;
                    }
                }    
            }
        }
    }

    boolean isPrime(int x){
        if(x == 1){
            return false;
        }
        for(int i = 2; i * i <= x; i++){
            if(x % i == 0){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
