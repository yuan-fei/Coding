/*
 * @lc app=leetcode id=1655 lang=java
 *
 * [1655] Distribute Repeating Integers
 *
 * https://leetcode.com/problems/distribute-repeating-integers/description/
 *
 * algorithms
 * Hard (24.93%)
 * Likes:    14
 * Dislikes: 0
 * Total Accepted:    453
 * Total Submissions: 1.8K
 * Testcase Example:  '[1,2,3,4]\n[2]'
 *
 * You are given an array of n integers, nums, where there are at most 50
 * unique values in the array. You are also given an array of m customer order
 * quantities, quantity, where quantity[i] is the amount of integers the i^th
 * customer ordered. Determine if it is possible to distribute nums such
 * that:
 * 
 * 
 * The i^th customer gets exactly quantity[i] integers,
 * The integers the i^th customer gets are all equal, and
 * Every customer is satisfied.
 * 
 * 
 * Return true if it is possible to distribute nums according to the above
 * conditions.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4], quantity = [2]
 * Output: false
 * Explanation: The 0th customer cannot be given two different integers.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,3], quantity = [2]
 * Output: true
 * Explanation: The 0th customer is given [3,3]. The integers [1,2] are not
 * used.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,1,2,2], quantity = [2,2]
 * Output: true
 * Explanation: The 0th customer is given [1,1], and the 1st customer is given
 * [2,2].
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [1,1,2,3], quantity = [2,2]
 * Output: false
 * Explanation: Although the 0th customer could be given [1,1], the 1st
 * customer cannot be satisfied.
 * 
 * Example 5:
 * 
 * 
 * Input: nums = [1,1,1,1,1], quantity = [2,3]
 * Output: true
 * Explanation: The 0th customer is given [1,1], and the 1st customer is given
 * [1,1,1].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= 1000
 * m == quantity.length
 * 1 <= m <= 10
 * 1 <= quantity[i] <= 10^5
 * There are at most 50 unique values in nums.
 * 
 */

// @lc code=start
class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
    	Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for(int x : nums){
        	freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        int n = freq.size();
        int m = quantity.length;
        int[] amount = new int[1 << m];
        for(int i = 0; i < 1 << m; i++){
        	for(int j = 0; j < m; j++){
        		if(((i >> j) & 1) != 0){
        			amount[i] = amount[i ^ (1 << j)] + quantity[j];
        			break;
        		}
        	}
        }
        // System.out.println(Arrays.toString(amount));
        boolean[] dp = new boolean[1 << m];
        dp[0] = true;
        for(int v : freq.values()){
        	boolean[] newDp = new boolean[1 << m];
        	for(int i = 0; i < 1 << m; i++){
    			if(amount[i] <= v){
    				for(int j = 0; j < 1 << m; j++){
    					if(dp[j] && (j & i) == 0){
    						newDp[j | i] = true;
    					}
    				}
    			}
    		}
    		dp = newDp;
    		// System.out.println(Arrays.toString(dp));
        }
        return dp[(1 << m) - 1];
    }
}
// @lc code=end
