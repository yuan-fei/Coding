/*
 * @lc app=leetcode id=2552 lang=java
 *
 * [2552] Count Increasing Quadruplets
 *
 * https://leetcode.com/problems/count-increasing-quadruplets/description/
 *
 * algorithms
 * Hard (18.32%)
 * Likes:    47
 * Dislikes: 23
 * Total Accepted:    1.2K
 * Total Submissions: 6.6K
 * Testcase Example:  '[1,3,2,4,5]'
 *
 * Given a 0-indexed integer array nums of size n containing all numbers from 1
 * to n, return the number of increasing quadruplets.
 * 
 * A quadruplet (i, j, k, l) is increasing if:
 * 
 * 
 * 0 <= i < j < k < l < n, and
 * nums[i] < nums[k] < nums[j] < nums[l].
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,2,4,5]
 * Output: 2
 * Explanation: 
 * - When i = 0, j = 1, k = 2, and l = 3, nums[i] < nums[k] < nums[j] <
 * nums[l].
 * - When i = 0, j = 1, k = 2, and l = 4, nums[i] < nums[k] < nums[j] <
 * nums[l]. 
 * There are no other quadruplets, so we return 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Explanation: There exists only one quadruplet with i = 0, j = 1, k = 2, l =
 * 3, but since nums[j] < nums[k], we return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 4 <= nums.length <= 4000
 * 1 <= nums[i] <= nums.length
 * All the integers of nums are unique. nums is a permutation.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][] leTable = buildLETable(nums);
        // System.out.println(Arrays.deepToString(leTable));
        long ret = 0;
        for(int i = 1; i < n; i++){
            for(int j = i + 1; j < n - 1; j++){
                if(nums[i] > nums[j]){
                    ret += 1L * leTable[i - 1][nums[j] - 1] * (leTable[n - 1][n] - leTable[n - 1][nums[i]] - (leTable[j][n] - leTable[j][nums[i]]));
                }
            }
        }
        return ret;
    }

    int[][] buildLETable(int[] nums){
        int n = nums.length;
        int[][] ret = new int[n][n + 1];
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= n; j++){
                if(i > 0){
                    ret[i][j] = ret[i - 1][j];    
                }
                if(nums[i] <= j){
                    ret[i][j]++;
                }
            }
        }
        return ret;
    }
}
// @lc code=end
