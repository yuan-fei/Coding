/*
 * [231] Power of Two
 *
 * https://leetcode.com/problems/power-of-two/description/
 *
 * algorithms
 * Easy (40.60%)
 * Total Accepted:    162.7K
 * Total Submissions: 400.5K
 * Testcase Example:  '1'
 *
 * 
 * Given an integer, write a function to determine if it is a power of two.
 * 
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 */
class Solution {
    public boolean isPowerOfTwo(int n) {
    	if(n <= 0){
    		return false;
    	}
        return (n & (n - 1)) == 0;
    }
}
