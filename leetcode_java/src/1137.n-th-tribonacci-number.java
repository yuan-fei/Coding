/*
 * @lc app=leetcode id=1137 lang=java
 *
 * [1137] N-th Tribonacci Number
 *
 * https://leetcode.com/problems/n-th-tribonacci-number/description/
 *
 * algorithms
 * Easy (63.47%)
 * Likes:    4000
 * Dislikes: 175
 * Total Accepted:    595K
 * Total Submissions: 937.7K
 * Testcase Example:  '4'
 *
 * The Tribonacci sequence Tn is defined as follows: 
 * 
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * 
 * Given n, return the value of Tn.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 25
 * Output: 1389537
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31
 * - 1.
 * 
 */

// @lc code=start
class Solution {
    public int tribonacci(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 1;
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        q.offer(0);
        q.offer(1);
        q.offer(1);
        int last = 1;
        for(int i = 3; i <= n; i++){
            last = last * 2 - q.poll();
            q.offer(last);
        }
        return last;
    }
}
// @lc code=end
