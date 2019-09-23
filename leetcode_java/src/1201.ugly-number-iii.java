/*
 * @lc app=leetcode id=1201 lang=java
 *
 * [1201] Ugly Number III
 *
 * https://leetcode.com/problems/ugly-number-iii/description/
 *
 * algorithms
 * Medium (17.23%)
 * Total Accepted:    1.5K
 * Total Submissions: 8K
 * Testcase Example:  '3\n2\n3\n5'
 *
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive integers which are divisible by a or b or c.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, a = 2, b = 3, c = 5
 * Output: 4
 * Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, a = 2, b = 3, c = 4
 * Output: 6
 * Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 12... The 4th is 6.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 5, a = 2, b = 11, c = 13
 * Output: 10
 * Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is
 * 10.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
 * Output: 1999999984
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * It's guaranteed that the result will be in range [1, 2 * 10^9]
 * 
 * 
 */
class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        long lb = 1;
        long ub = 2000000000;
        while(lb + 1 < ub){
        	long mid = lb + (ub - lb) / 2;
        	// System.out.println(mid + "="+ countUglyNumbers(n, a, b, c, mid));
        	if(countUglyNumbers(n, a, b, c, mid) >= n){
        		ub = mid;
        	}
        	else{
        		lb = mid;
        	}
        }
        if(countUglyNumbers(n, a, b, c, lb) >= n){
        	return (int)lb;
        }
        else{
        	return (int)ub;	
        }
        
    }

    long countUglyNumbers(int n, long a, long b, long c, long max){
    	long lcmAB = (a * b) / gcd(a, b);
    	long lcmBC = (c * b) / gcd(c, b);
    	long lcmAC = (a * c) / gcd(a, c);
    	long lcmABC = (lcmAB * c) / gcd(lcmAB, c);
    	return max / a + max / b + max / c 
    			- max / lcmAB  
    			- max / lcmBC
    			- max / lcmAC
    			+ max / lcmABC;
    }

    long gcd(long a, long b){
    	if(b == 0){
    		return a;
    	}
    	else{
    		return gcd(b, a % b);
    	}
    }
}
