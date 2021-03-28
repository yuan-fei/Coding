/*
 * @lc app=leetcode id=1799 lang=java
 *
 * [1799] Maximize Score After N Operations
 *
 * https://leetcode.com/problems/maximize-score-after-n-operations/description/
 *
 * algorithms
 * Hard (39.92%)
 * Likes:    66
 * Dislikes: 3
 * Total Accepted:    1.7K
 * Total Submissions: 4.4K
 * Testcase Example:  '[1,2]'
 *
 * You are given nums, an array of positive integers of size 2 * n. You must
 * perform n operations on this array.
 * 
 * In the i^th operation (1-indexed), you will:
 * 
 * 
 * Choose two elements, x and y.
 * Receive a score of i * gcd(x, y).
 * Remove x and y from nums.
 * 
 * 
 * Return the maximum score you can receive after performing n operations.
 * 
 * The function gcd(x, y) is the greatest common divisor of x and y.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2]
 * Output: 1
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 2)) = 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,4,6,8]
 * Output: 11
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3,4,5,6]
 * Output: 14
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 7
 * nums.length == 2 * n
 * 1 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxScore(int[] nums) {
    	int n = nums.length / 2;
        int[] dp = new int[1 << 14];
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i < 2 * n; i++){
        	for(int j = i + 1; j < 2 * n; j++){
        		int mask = (1 << i) | (1 << j);
        		dp[mask] = gcd(nums[i], nums[j]);
        		l.add(mask);
        	}
        }
        List<Integer> l1 = new ArrayList<>(l);
        for(int i = 1; i < n; i++){
        	List<Integer> l2 = new ArrayList<>();
        	for(int mask1 : l){
        		for(int mask2: l1){
        			if((mask1 & mask2) == 0){
        				if(dp[mask1 | mask2] == 0){
        					l2.add(mask1 | mask2);
        				}
        				dp[mask1 | mask2] = Math.max(dp[mask1 | mask2], dp[mask2] + (i + 1) * dp[mask1]);
        			}
        		}
        	}
        	l1 = l2;
        }
        return dp[(1 << (2*n)) - 1];
    }
    int gcd(int x, int y){
    	if(y == 0){
    		return x;
    	}
    	else{
    		return gcd(y, x % y);
    	}
    }
}
// @lc code=end
