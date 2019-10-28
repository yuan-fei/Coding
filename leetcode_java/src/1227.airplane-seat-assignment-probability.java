/*
 * @lc app=leetcode id=1227 lang=java
 *
 * [1227] Airplane Seat Assignment Probability
 *
 * https://leetcode.com/problems/airplane-seat-assignment-probability/description/
 *
 * algorithms
 * Medium (69.52%)
 * Total Accepted:    288
 * Total Submissions: 419
 * Testcase Example:  '1'
 *
 * n passengers board an airplane with exactly n seats. The first passenger has
 * lost the ticket and picks a seat randomly. But after that, the rest of
 * passengers will:
 * 
 * 
 * Take their own seat if it is still available, 
 * Pick other seats randomly when they find their seat occupied 
 * 
 * 
 * What is the probability that the n-th person can get his own seat?
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1
 * Output: 1.00000
 * Explanation: The first person can only get the first seat.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: 0.50000
 * Explanation: The second person has a probability of 0.5 to get the second
 * seat (when first person gets the first seat).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * 
 */
class Solution {
    public double nthPersonGetsNthSeat(int n) {
        // case 1: P(p1 seat in seat 1, pn seat in seat n) = 1/n
        // case 2: P(p1 seat in seat x, px seat in sear 1, pn seat in seat n) = (n-2)/n * 1/2
        // P(case 1) + P(case 2) = 0.5
        return n==1?1:0.5;
    }
}
