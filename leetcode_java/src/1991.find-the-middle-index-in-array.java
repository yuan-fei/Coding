/*
 * @lc app=leetcode id=1991 lang=java
 *
 * [1991] Find the Middle Index in Array
 *
 * https://leetcode.com/problems/find-the-middle-index-in-array/description/
 *
 * algorithms
 * Easy (60.49%)
 * Likes:    63
 * Dislikes: 2
 * Total Accepted:    7.2K
 * Total Submissions: 11.9K
 * Testcase Example:  '[2,3,-1,8,4]'
 *
 * Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e.,
 * the smallest amongst all the possible ones).
 * 
 * A middleIndex is an index where nums[0] + nums[1] + ... +
 * nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... +
 * nums[nums.length-1].
 * 
 * If middleIndex == 0, the left side sum is considered to be 0. Similarly, if
 * middleIndex == nums.length - 1, the right side sum is considered to be 0.
 * 
 * Return the leftmost middleIndex that satisfies the condition, or -1 if there
 * is no such index.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,3,-1,8,4]
 * Output: 3
 * Explanation:
 * The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
 * The sum of the numbers after index 3 is: 4 = 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,-1,4]
 * Output: 2
 * Explanation:
 * The sum of the numbers before index 2 is: 1 + -1 = 0
 * The sum of the numbers after index 2 is: 0
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [2,5]
 * Output: -1
 * Explanation:
 * There is no valid middleIndex.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [1]
 * Output: 0
 * Explantion:
 * The sum of the numbers before index 0 is: 0
 * The sum of the numbers after index 0 is: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 100
 * -1000 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findMiddleIndex(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        int leftSum = 0;
        for(int i = 0; i < nums.length; i++){
            if(leftSum == sum - leftSum - nums[i]){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}
// @lc code=end
