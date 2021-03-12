/*
 * @lc app=leetcode id=1780 lang=java
 *
 * [1780] Check if Number is a Sum of Powers of Three
 *
 * https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/description/
 *
 * algorithms
 * Medium (56.78%)
 * Likes:    36
 * Dislikes: 0
 * Total Accepted:    3.3K
 * Total Submissions: 5.8K
 * Testcase Example:  '12'
 *
 * Given an integer n, return true if it is possible to represent n as the sum
 * of distinct powers of three. Otherwise, return false.
 * 
 * An integer y is a power of three if there exists an integer x such that y ==
 * 3^x.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 12
 * Output: true
 * Explanation: 12 = 3^1 + 3^2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 91
 * Output: true
 * Explanation: 91 = 3^0 + 3^2 + 3^4
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 21
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkPowersOfThree(int n) {
        int[] power3 = new int[15];
        power3[0] = 1;
        for(int i = 1; i < power3.length; i++){
        	power3[i] = power3[i - 1] * 3;
        }
        int id = 16;
        while(n != 0){
        	int newId = Arrays.binarySearch(power3, n);
        	if(newId < 0){
        		newId = -newId - 2;
        	}
        	if(newId == id){
        		return false;
        	}
        	id = newId;
        	n -= power3[id];
        }
        return true;
    }
}
// @lc code=end
