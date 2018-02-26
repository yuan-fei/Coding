/*
 * [50] Pow(x, n)
 *
 * https://leetcode.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (26.04%)
 * Total Accepted:    199K
 * Total Submissions: 764.2K
 * Testcase Example:  '2.00000\n10'
 *
 * Implement pow(x, n).
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: 2.00000, 10
 * Output: 1024.00000
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 2.10000, 3
 * Output: 9.26100
 * 
 * 
 */
class Solution {
    public double myPow(double x, int n) {
    	if(n == Integer.MIN_VALUE){
    		return Math.abs(x) == 1? 1 : 0;
    	}
        if(n < 0){
        	return 1 / positiveExpPower(x, -n);
        }
        return positiveExpPower(x, n);
    }

    public double positiveExpPower(double x, int n){
    	if(n == 0){
        	return 1;
        }
        double r = 1;
        if((n & 1) == 1){
			r = x;
			n -= 1;
		}
		double p = myPow(x, n>>1);
		return r*p*p;
    }
}
