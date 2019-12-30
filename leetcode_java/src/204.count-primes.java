/*
 * @lc app=leetcode id=204 lang=java
 *
 * [204] Count Primes
 *
 * https://leetcode.com/problems/count-primes/description/
 *
 * algorithms
 * Easy (30.34%)
 * Likes:    1462
 * Dislikes: 489
 * Total Accepted:    293.8K
 * Total Submissions: 968.5K
 * Testcase Example:  '10'
 *
 * Count the number of prime numbers less than a non-negative number, n.
 * 
 * Example:
 * 
 * 
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countPrimes(int n) {
    	boolean[] composite = new boolean[n];
        for(int i = 2; i < n; i++){
        	if(composite[i] == false){
	        	for(int j = 2; j * i < n; j++){
	        		composite[i * j] = true;
	        	}	
        	}
        }
        int cnt = 0;
        for(int i = 2; i < n; i++){
        	if(!composite[i]){
        		cnt++;
        	}
        }
        return cnt;
    }
}
// @lc code=end
