/*
 * @lc app=leetcode id=2012 lang=java
 *
 * [2012] Sum of Beauty in the Array
 *
 * https://leetcode.com/problems/sum-of-beauty-in-the-array/description/
 *
 * algorithms
 * Medium (41.36%)
 * Likes:    129
 * Dislikes: 12
 * Total Accepted:    7.3K
 * Total Submissions: 17.5K
 * Testcase Example:  '[1,2,3]'
 *
 * You are given a 0-indexed integer array nums. For each index i (1 <= i <=
 * nums.length - 2) the beauty of nums[i] equals:
 * 
 * 
 * 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <=
 * nums.length - 1.
 * 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not
 * satisfied.
 * 0, if none of the previous conditions holds.
 * 
 * 
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,4,6,4]
 * Output: 1
 * Explanation: For each index i in the range 1 <= i <= 2:
 * - The beauty of nums[1] equals 1.
 * - The beauty of nums[2] equals 0.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        min[n - 1] = 100006;
        for(int i = 1; i < n - 1; i++){
            max[i] = Math.max(max[i - 1], nums[i - 1]);
        }
        for(int i = n - 2; i >= 1; i--){
            min[i] = Math.min(min[i + 1], nums[i + 1]);
        }
        int ret = 0;
        for(int i = 1; i < n - 1; i++){
            if(min[i] > nums[i] && nums[i] > max[i]){
                ret += 2;
            }
            else if(nums[i + 1] > nums[i] && nums[i] > nums[i - 1]){
                ret += 1;
            }
        }
        return ret;
    }
}
// @lc code=end
