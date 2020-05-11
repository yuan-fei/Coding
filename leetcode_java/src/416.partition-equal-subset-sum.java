/*
 * @lc app=leetcode id=416 lang=java
 *
 * [416] Partition Equal Subset Sum
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (42.91%)
 * Likes:    2185
 * Dislikes: 63
 * Total Accepted:    156.6K
 * Total Submissions: 364.9K
 * Testcase Example:  '[1,5,11,5]'
 *
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both
 * subsets is equal.
 * 
 * Note:
 * 
 * 
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1, 5, 11, 5]
 * 
 * Output: true
 * 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1, 2, 3, 5]
 * 
 * Output: false
 * 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) {
        	sum += x;
        }
        if(sum % 2 != 0){
        	return false;
        }

        int target = sum / 2;
        boolean[] reachable = new boolean[sum + 1];
        reachable[0] = true;
        for(int n : nums){
        	for(int t = target - n; t >= 0; t--){
        		reachable[t + n] |= reachable[t];
        	}
        	// System.out.println(Arrays.toString(reachable));
        }

        return reachable[target];
    }
}
// @lc code=end
