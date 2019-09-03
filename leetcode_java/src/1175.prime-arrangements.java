/*
 * @lc app=leetcode id=1175 lang=java
 *
 * [1175] Prime Arrangements
 *
 * https://leetcode.com/problems/prime-arrangements/description/
 *
 * algorithms
 * Easy (49.79%)
 * Total Accepted:    3K
 * Total Submissions: 5.8K
 * Testcase Example:  '5'
 *
 * Return the number of permutations of 1 to n so that prime numbers are at
 * prime indices (1-indexed.)
 * 
 * (Recall that an integer is prime if and only if it is greater than 1, and
 * cannot be written as a product of two positive integers both smaller than
 * it.)
 * 
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5
 * Output: 12
 * Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1]
 * is not because the prime number 5 is at index 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 100
 * Output: 682289015
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 100
 * 
 * 
 */
class Solution {
    public int numPrimeArrangements(int n) {
    	int mod = 1000000007;
        Set<Integer> primes = new HashSet<>(Arrays.asList(2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97));
        long ans = 1;
        int primeCnt = 0;
        for (int i = 1; i<=n; i++) {
        	if(primes.contains(i)){
        		primeCnt++;
        		ans = (ans*primeCnt)%mod;
        	}
        	else{
        		ans = (ans*(i-primeCnt))%mod;	
        	}
        }
        return (int)ans;
    }
}
