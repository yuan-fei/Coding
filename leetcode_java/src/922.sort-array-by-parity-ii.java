/*
 * @lc app=leetcode id=922 lang=java
 *
 * [922] Sort Array By Parity II
 *
 * https://leetcode.com/problems/sort-array-by-parity-ii/description/
 *
 * algorithms
 * Easy (70.58%)
 * Likes:    2422
 * Dislikes: 87
 * Total Accepted:    225.4K
 * Total Submissions: 319.5K
 * Testcase Example:  '[4,2,5,7]'
 *
 * Given an array of integers nums, half of the integers in nums are odd, and
 * the other half are even.
 * 
 * Sort the array so that whenever nums[i] is odd, i is odd, and whenever
 * nums[i] is even, i is even.
 * 
 * Return any answer array that satisfies this condition.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been
 * accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,3]
 * Output: [2,3]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 2 * 10^4
 * nums.length is even.
 * Half of the integers in nums are even.
 * 0 <= nums[i] <= 1000
 * 
 * 
 * 
 * Follow Up: Could you solve it in-place?
 * 
 */

// @lc code=start
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int even = 0;
        int odd = 1;
        while(even < nums.length && odd < nums.length){
            if(nums[even] % 2 != 0){
                if(nums[odd] % 2 == 0){
                    int t = nums[even];
                    nums[even] = nums[odd];
                    nums[odd] = t;
                    even += 2;    
                }
                else{
                    odd += 2;
                }
            }
            else{
                even += 2;
            }
        }
        return nums;
    }
}
// @lc code=end
