/*
 * @lc app=leetcode id=1802 lang=java
 *
 * [1802] Maximum Value at a Given Index in a Bounded Array
 *
 * https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/description/
 *
 * algorithms
 * Medium (23.30%)
 * Likes:    78
 * Dislikes: 20
 * Total Accepted:    2.5K
 * Total Submissions: 10.6K
 * Testcase Example:  '4\n2\n6'
 *
 * You are given three positive integers n, index and maxSum. You want to
 * construct an array nums (0-indexed) that satisfies the following
 * conditions:
 * 
 * 
 * nums.length == n
 * nums[i] is a positive integer where 0 <= i < n.
 * abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * The sum of all the elements of nums does not exceed maxSum.
 * nums[index] is maximized.
 * 
 * 
 * Return nums[index] of the constructed array.
 * 
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, index = 2,  maxSum = 6
 * Output: 2
 * Explanation: The arrays [1,1,2,1] and [1,2,2,1] satisfy all the conditions.
 * There are no other valid arrays with a larger value at the given index.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 6, index = 1,  maxSum = 10
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= maxSum <= 10^9
 * 0 <= index < n
 * 
 */

// @lc code=start
class Solution {
    public int maxValue(int n, int index, int maxSum) {
        long low = 1;
        long high = maxSum;
        while(low + 1 < high){
        	long mid = low + (high - low) / 2;
        	if(feasible(n, index, maxSum, mid)){
        		low = mid;
        	}
        	else{
        		high = mid;
        	}
        }
        if(feasible(n, index, maxSum, high)){
        	return (int)high;
        }
        else{
        	return (int)low;
        }
    }

    boolean feasible(int n, int index, long maxSum, long v){
    	maxSum -= n;
    	v--;
    	long min = Math.max(v - index, 0);
    	maxSum -= (min + v) * (v - min + 1) / 2;
    	min = Math.max(v - (n - index - 1), 0);
    	maxSum -= (min + v) * (v - min + 1) / 2;
    	maxSum += v;
    	// System.out.println(v + "" + maxSum);
    	return maxSum >= 0;
    }
}
// @lc code=end
