/*
 * @lc app=leetcode id=775 lang=java
 *
 * [775] Global and Local Inversions
 *
 * https://leetcode.com/problems/global-and-local-inversions/description/
 *
 * algorithms
 * Medium (43.55%)
 * Likes:    1485
 * Dislikes: 344
 * Total Accepted:    62.3K
 * Total Submissions: 143K
 * Testcase Example:  '[1,0,2]'
 *
 * You are given an integer array nums of length n which represents a
 * permutation of all the integers in the range [0, n - 1].
 * 
 * The number of global inversions is the number of the different pairs (i, j)
 * where:
 * 
 * 
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * 
 * 
 * The number of local inversions is the number of indices i where:
 * 
 * 
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * 
 * 
 * Return true if the number of global inversions is equal to the number of
 * local inversions.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion and 1 local inversion.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions and 1 local inversion.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] < n
 * All the integers of nums are unique.
 * nums is a permutation of all the numbers in the range [0, n - 1].
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isIdealPermutation(int[] nums) {
        if(nums.length < 3){
            return true;
        }
        int max1 = Math.max(nums[0], nums[1]);
        int max2 = Math.min(nums[0], nums[1]);
        int max1Id = 0;
        int max2Id = 1;
        if(nums[0] < nums[1]){
            max1Id = 1;
            max2Id = 0;
        }
        
        for(int i = 2; i < nums.length; i++){
            if(nums[i] < max1 && i - max1Id > 1){
                return false;
            }
            if(nums[i] < max2 && i - max2Id > 1){
                return false;
            }
            if(max1 < nums[i]){
                max2 = max1;
                max2Id = max1Id;
                max1 = nums[i];
                max1Id = i;
            }
            else if(max2 < nums[i]){
                max2 = nums[i];
                max2Id = i;
            }
        }
        return true;
    }
}
// @lc code=end
