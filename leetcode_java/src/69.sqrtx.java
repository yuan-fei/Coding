/*
 * [69] Sqrt(x)
 *
 * https://leetcode.com/problems/sqrtx/description/
 *
 * algorithms
 * Easy (28.67%)
 * Total Accepted:    212.5K
 * Total Submissions: 739.8K
 * Testcase Example:  '4'
 *
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 * 
 * x is guaranteed to be a non-negative integer.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: 4
 * Output: 2
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we want to return
 * an integer, the decimal part will be truncated.
 * 
 * 
 */
class Solution {
    public int mySqrt(int x) {
        return solve1(x);
    }

    public int solve1(int x) {
        int low = 0;
        int high = x;
        while(low + 1 < high){
        	int mid = low + (high - low) / 2;
        	if(mid == x / mid){
        		return mid;
        	}
        	else if(mid > x / mid){
        		high = mid;
        	}
        	else{
        		low = mid;
        	}
        }
        if(high * high == x){
        	return high;
        }
        return low;
    }
}
