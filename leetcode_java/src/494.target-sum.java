/*
 * @lc app=leetcode id=494 lang=java
 *
 * [494] Target Sum
 *
 * https://leetcode.com/problems/target-sum/description/
 *
 * algorithms
 * Medium (46.34%)
 * Likes:    2756
 * Dislikes: 110
 * Total Accepted:    172.6K
 * Total Submissions: 372.4K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * You are given a list of non-negative integers, a1, a2, ..., an, and a
 * target, S. Now you have 2 symbols + and -. For each integer, you should
 * choose one from + and - as its new symbol.
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to
 * target S.
 * 
 * Example 1:
 * 
 * 
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * Explanation: 
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
    	int n = nums.length;
    	Map<Integer, Integer> cnt = new HashMap<>();
    	cnt.put(0, 1);
    	for(int i = 0; i < n; i++){
    		Map<Integer, Integer> cntTmp = new HashMap<>();
    		for(int k : cnt.keySet()){
    			cntTmp.put(k + nums[i], cnt.get(k) + cntTmp.getOrDefault(k + nums[i], 0));
    			cntTmp.put(k - nums[i], cnt.get(k) + cntTmp.getOrDefault(k - nums[i], 0));
			}
			cnt = cntTmp;
			// System.out.println(cnt);
    	}
    	return cnt.getOrDefault(S, 0);
    }


}
// @lc code=end
