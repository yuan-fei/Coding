/*
 * [172] Factorial Trailing Zeroes
 *
 * https://leetcode.com/problems/factorial-trailing-zeroes/description/
 *
 * algorithms
 * Easy (36.78%)
 * Total Accepted:    110.8K
 * Total Submissions: 301.2K
 * Testcase Example:  '0'
 *
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * Note: Your solution should be in logarithmic time complexity.
 * 
 * Credits:Special thanks to @ts for adding this problem and creating all test
 * cases.
 */
class Solution {
    public int trailingZeroes(int n) {
    	int cnt = 0;
    	while(n != 0){
    		n /= 5;
    		cnt += n;
    	}
        return cnt;
    }
}
