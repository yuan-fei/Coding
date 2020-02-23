/*
 * @lc app=leetcode id=1362 lang=java
 *
 * [1362] Closest Divisors
 *
 * https://leetcode.com/problems/closest-divisors/description/
 *
 * algorithms
 * Medium (49.82%)
 * Likes:    22
 * Dislikes: 8
 * Total Accepted:    3.7K
 * Total Submissions: 7.4K
 * Testcase Example:  '8'
 *
 * Given an integer num, find the closest two integers in absolute difference
 * whose product equals num + 1 or num + 2.
 * 
 * Return the two integers in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 8
 * Output: [3,3]
 * Explanation: For num + 1 = 9, the closest divisors are 3 & 3, for num + 2 =
 * 10, the closest divisors are 2 & 5, hence 3 & 3 is chosen.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 123
 * Output: [5,25]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: num = 999
 * Output: [40,25]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] closestDivisors(int num) {
        int[] p1 = getClosestDivisors(num + 1);
        int[] p2 = getClosestDivisors(num + 2);
        if(Math.abs(p1[0] - p1[1]) < Math.abs(p2[0] - p2[1])){
        	return p1;
        }
        else{
        	return p2;
        }
    }

    int[] getClosestDivisors(int num){
    	for(int i = (int)Math.sqrt(num); i >= 0; i--){
    		if(num % i == 0){
    			return new int[]{i, num / i};
    		}
    	}
    	return new int[0];
    }
}
// @lc code=end
