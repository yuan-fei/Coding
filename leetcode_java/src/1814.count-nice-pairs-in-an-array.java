/*
 * @lc app=leetcode id=1814 lang=java
 *
 * [1814] Count Nice Pairs in an Array
 *
 * https://leetcode.com/problems/count-nice-pairs-in-an-array/description/
 *
 * algorithms
 * Medium (32.68%)
 * Likes:    81
 * Dislikes: 11
 * Total Accepted:    2.9K
 * Total Submissions: 9K
 * Testcase Example:  '[42,11,1,97]'
 *
 * You are given an array nums that consists of non-negative integers. Let us
 * define rev(x) as the reverse of the non-negative integer x. For example,
 * rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it
 * satisfies all of the following conditions:
 * 
 * 
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * 
 * 
 * Return the number of nice pairs of indices. Since that number can be too
 * large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [42,11,1,97]
 * Output: 2
 * Explanation: The two pairs are:
 * ⁠- (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 * ⁠- (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [13,10,35,24,76]
 * Output: 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        int MOD = (int)1e9 + 7;
        long ans = 0;
        for(int x : nums){
        	int k = x - getReverse(x);
        	int v = m.getOrDefault(k, 0);
        	ans += v;
        	ans %= MOD;
        	m.put(k, v + 1);
        }
        
        return (int)ans;
    }

    int getReverse(int x){
    	StringBuilder sb = new StringBuilder();
    	sb.append(x);
    	sb.reverse();
    	return Integer.parseInt(sb.toString());
    }
}
// @lc code=end
