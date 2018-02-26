/*
 * [326] Power of Three
 *
 * https://leetcode.com/problems/power-of-three/description/
 *
 * algorithms
 * Easy (40.59%)
 * Total Accepted:    118.8K
 * Total Submissions: 292.5K
 * Testcase Example:  '27'
 *
 * 
 * ⁠   Given an integer, write a function to determine if it is a power of
 * three.
 * 
 * 
 * ⁠   Follow up:
 * ⁠   Could you do it without using any loop / recursion?
 * 
 * 
 * Credits:Special thanks to @dietpepsi for adding this problem and creating
 * all test cases.
 */
class Solution {
    public boolean isPowerOfThree(int n) {
    	int maxPowerOfThree = 1162261467;
        return n > 0 && (maxPowerOfThree % n == 0);
    }
}
