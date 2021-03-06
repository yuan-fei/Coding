/*
 * @lc app=leetcode id=1390 lang=java
 *
 * [1390] Four Divisors
 *
 * https://leetcode.com/problems/four-divisors/description/
 *
 * algorithms
 * Medium (31.78%)
 * Likes:    14
 * Dislikes: 22
 * Total Accepted:    4.7K
 * Total Submissions: 14.5K
 * Testcase Example:  '[21,4,7]'
 *
 * Given an integer array nums, return the sum of divisors of the integers in
 * that array that have exactly four divisors.
 * 
 * If there is no such integer in the array, return 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [21,4,7]
 * Output: 32
 * Explanation:
 * 21 has 4 divisors: 1, 3, 7, 21
 * 4 has 3 divisors: 1, 2, 4
 * 7 has 2 divisors: 1, 7
 * The answer is the sum of divisors of 21 only.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int sumFourDivisors(int[] nums) {
    	int sum = 0;
        for(int x : nums){
        	sum += sumDivisors(x);
        	// System.out.println(sum);
        }
        return sum;
    }

    int sumDivisors(int n){
    	int cnt = 0;
    	int sum = 0;
    	for(int i = 1; i * i <= n; i++){
    		if(n % i == 0){
    			if(n / i == i){
    				return 0;
    			}
    			else{
    				cnt += 2;
    				sum += i;
    				sum += n / i;
    			}
    		}
    	}
    	return (cnt == 4) ? sum : 0;
    }
}
// @lc code=end
