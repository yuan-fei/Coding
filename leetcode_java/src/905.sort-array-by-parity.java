/*
 * @lc app=leetcode id=905 lang=java
 *
 * [905] Sort Array By Parity
 *
 * https://leetcode.com/problems/sort-array-by-parity/description/
 *
 * algorithms
 * Easy (75.57%)
 * Likes:    4341
 * Dislikes: 135
 * Total Accepted:    599.1K
 * Total Submissions: 792.7K
 * Testcase Example:  '[3,1,2,4]'
 *
 * Given an integer array nums, move all the even integers at the beginning of
 * the array followed by all the odd integers.
 * 
 * Return any array that satisfies this condition.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,1,2,4]
 * Output: [2,4,3,1]
 * Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be
 * accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0]
 * Output: [0]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 2 == 0){
                swap(nums, i, left);
                left++;
            }
        }
        return nums;
    }

    void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
// @lc code=end
