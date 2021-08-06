/*
 * @lc app=leetcode id=1952 lang=java
 *
 * [1952] Three Divisors
 *
 * https://leetcode.com/problems/three-divisors/description/
 *
 * algorithms
 * Easy (55.86%)
 * Likes:    66
 * Dislikes: 9
 * Total Accepted:    12.8K
 * Total Submissions: 23K
 * Testcase Example:  '2'
 *
 * Given an integer n, return true if n has exactly three positive divisors.
 * Otherwise, return false.
 * 
 * An integer m is a divisor of n if there exists an integer k such that n = k
 * * m.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2
 * Output: false
 * Explantion: 2 has only two divisors: 1 and 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4
 * Output: true
 * Explantion: 4 has three divisors: 1, 2, and 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isThree(int n) {
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                return i * i == n;
            }
        }
        return false;
    }
}
// @lc code=end
