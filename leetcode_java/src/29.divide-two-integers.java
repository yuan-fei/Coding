/*
 * [29] Divide Two Integers
 *
 * https://leetcode.com/problems/divide-two-integers/description/
 *
 * algorithms
 * Medium (15.83%)
 * Total Accepted:    126.6K
 * Total Submissions: 801.3K
 * Testcase Example:  '0\n1'
 *
 * 
 * Divide two integers without using multiplication, division and mod
 * operator.
 * 
 * 
 * If it is overflow, return MAX_INT.
 * 
 */
class Solution {
    public int divide(int dividend, int divisor) {
    	if(divisor == 0){
    		return Integer.MAX_VALUE;
    	}
        long i = 0;
        long long_dividend = dividend;
        long long_divisor = divisor;
        long_dividend = (long_dividend > 0)? long_dividend: -long_dividend;
        long_divisor = (long_divisor > 0)? long_divisor: -long_divisor;

        if(long_divisor == 1){
        	i = long_dividend;
        }
        else{
        	i = fast_devide(long_dividend, long_divisor);	
        }      

        if((dividend > 0 && divisor < 0)||(dividend < 0 && divisor > 0)){
        	i = -i;
        }

        if(i > Integer.MAX_VALUE || i < Integer.MIN_VALUE){
        	return Integer.MAX_VALUE;
        }

        return (int)i;
    }

    private long fast_devide(long dividend, long divisor){
    	if(divisor > dividend){
    		return 0;
    	}
    	long r = 1;
    	long sum = divisor;
    	while(sum + sum < dividend){
    		r += r;
    		sum += sum;
    	}
    	return r + fast_devide(dividend - sum, divisor);
    }
}
