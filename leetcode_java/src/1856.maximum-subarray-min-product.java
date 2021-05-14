/*
 * @lc app=leetcode id=1856 lang=java
 *
 * [1856] Maximum Subarray Min-Product
 *
 * https://leetcode.com/problems/maximum-subarray-min-product/description/
 *
 * algorithms
 * Medium (32.27%)
 * Likes:    238
 * Dislikes: 10
 * Total Accepted:    3.9K
 * Total Submissions: 12K
 * Testcase Example:  '[1,2,3,2]'
 *
 * The min-product of an array is equal to the minimum value in the array
 * multiplied by the array's sum.
 * 
 * 
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 *
 * (3+2+5) = 2 * 10 = 20.
 * 
 * 
 * Given an array of integers nums, return the maximum min-product of any
 * non-empty subarray of nums. Since the answer may be large, return it modulo
 * 10^9 + 7.
 * 
 * Note that the min-product should be maximized before performing the modulo
 * operation. Testcases are generated such that the maximum min-product without
 * modulo will fit in a 64-bit signed integer.
 * 
 * A subarray is a contiguous part of an array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,2]
 * Output: 14
 * Explanation: The maximum min-product is achieved with the subarray [2,3,2]
 * (minimum value is 2).
 * 2 * (2+3+2) = 2 * 7 = 14.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,3,3,1,2]
 * Output: 18
 * Explanation: The maximum min-product is achieved with the subarray [3,3]
 * (minimum value is 3).
 * 3 * (3+3) = 3 * 6 = 18.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,1,5,6,4,2]
 * Output: 60
 * Explanation: The maximum min-product is achieved with the subarray [5,6,4]
 * (minimum value is 4).
 * 4 * (5+6+4) = 4 * 15 = 60.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxSumMinProduct(int[] nums) {
    	int n = nums.length;
    	Stack<Integer> s = new Stack<>();
    	long[] prefSum = new long[n + 1];
    	long max = 0;
        for(int i = 1; i <= n; i++){
        	prefSum[i] = prefSum[i - 1] + nums[i - 1];
        	while(!s.isEmpty() && nums[s.peek() - 1] >= nums[i - 1]){
        		int top = s.pop();
        		long lb = s.isEmpty() ? 0 : prefSum[s.peek()];
        		max = Math.max(max, (prefSum[i - 1] - lb) * nums[top - 1]);
        	}
        	s.push(i);
        }
        // System.out.println(s);
        // System.out.println(prefSum[n]);
        while(!s.isEmpty()){
        	int top = s.pop();
    		long lb = s.isEmpty() ? 0 : prefSum[s.peek()];
    		max = Math.max(max, (prefSum[n] - lb) * nums[top - 1]);
        }
        return (int)(max % ((long)1e9 + 7));
    }
}
// @lc code=end
