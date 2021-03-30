/*
 * @lc app=leetcode id=1808 lang=java
 *
 * [1808] Maximize Number of Nice Divisors
 *
 * https://leetcode.com/problems/maximize-number-of-nice-divisors/description/
 *
 * algorithms
 * Hard (21.05%)
 * Likes:    31
 * Dislikes: 64
 * Total Accepted:    1.5K
 * Total Submissions: 7.3K
 * Testcase Example:  '5'
 *
 * You are given a positive integer primeFactors. You are asked to construct a
 * positive integer n that satisfies the following conditions:
 * 
 * 
 * ⁠ The number of prime factors of n (not necessarily distinct) is at most
 * primeFactors.
 * ⁠ The number of nice divisors of n is maximized. Note that a divisor of n is
 * nice if it is divisible by every prime factor of n. For example, if n = 12,
 * then its prime factors are [2,2,3], then 6 and 12 are nice divisors, while 3
 * and 4 are not.
 * 
 * 
 * Return the number of nice divisors of n. Since that number can be too large,
 * return it modulo 10^9 + 7.
 * 
 * Note that a prime number is a natural number greater than 1 that is not a
 * product of two smaller natural numbers. The prime factors of a number n is a
 * list of prime numbers such that their product equals n.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: primeFactors = 5
 * Output: 6
 * Explanation: 200 is a valid value of n.
 * It has 5 prime factors: [2,2,2,5,5], and it has 6 nice divisors:
 * [10,20,40,50,100,200].
 * There is not other value of n that has at most 5 prime factors and more nice
 * divisors.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: primeFactors = 8
 * Output: 18
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= primeFactors <= 10^9
 * 
 */

// @lc code=start
class Solution {
    public int maxNiceDivisors(int primeFactors) {
    	if(primeFactors < 4){
    		return primeFactors;
    	}
    	
    	if(primeFactors % 3 == 1){
    		return (int)(pow(3, (primeFactors - 4) / 3) * 4 % MOD);	
    	}
    	else if(primeFactors % 3 == 2){
    		return (int)(pow(3, primeFactors / 3) * 2 % MOD);		
    	}
    	else{
    		return (int)(pow(3, primeFactors / 3) % MOD);
    	}
        
    }

    long MOD=  1000000007;
    long pow(long a, long x){
    	if(x == 0){
    		return 1;
    	}
    	if(x % 2 == 0){
    		long hlf = pow(a, x / 2);
    		return (hlf * hlf) % MOD;
    	}
    	else{
    		return (a * pow(a, x - 1)) % MOD;
    	}
    }

    int ascending(int n, int x){
		long left = getNiceCount(n, x - 1);
		long mid = getNiceCount(n, x);
		long right = getNiceCount(n, x + 1);
		if(left <= mid && mid < right){
			return 1;
		}
		else if(left < mid && mid <= right){
			return 1;
		}
		else if(left > mid && mid >= right){
			return -1;
		}
		else if(left >= mid && mid > right){
			return -1;
		}
		else{
			return 0;
		}
    }

    long getNiceCount(int n, int x){
    	long unit1 = n / x;
    	long unit2 = unit1 + 1;
    	long cnt2 = n - unit1 * x;
    	long cnt1 = x - cnt2;
    	System.out.println(unit1 + ", " + unit2 + ","+ cnt1);
    	long ret = (long)(Math.pow(unit1, cnt1) * Math.pow(unit2, cnt2));
    	System.out.println(n + ", " +x + "="+ ret);
    	return ret;
    }
}
// @lc code=end
