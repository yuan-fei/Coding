/*
 * @lc app=leetcode id=3038 lang=java
 *
 * [3038] Maximum Number of Operations With the Same Score I
 *
 * https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-i/description/
 *
 * algorithms
 * Easy (52.30%)
 * Likes:    81
 * Dislikes: 23
 * Total Accepted:    36.6K
 * Total Submissions: 70K
 * Testcase Example:  '[3,2,1,4,5]'
 *
 * You are given an array of integers nums. Consider the following
 * operation:
 * 
 * 
 * Delete the first two elements nums and define the score of the operation as
 * the sum of these two elements.
 * 
 * 
 * You can perform this operation until nums contains fewer than two elements.
 * Additionally, the same score must be achieved in all operations.
 * 
 * Return the maximum number of operations you can perform.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,2,1,4,5]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 
 * We can perform the first operation with the score 3 + 2 = 5. After this
 * operation, nums = [1,4,5].
 * We can perform the second operation as its score is 4 + 1 = 5, the same as
 * the previous operation. After this operation, nums = [5].
 * As there are fewer than two elements, we can't perform more operations.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,5,3,3,4,1,3,2,2,3]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 
 * We can perform the first operation with the score 1 + 5 = 6. After this
 * operation, nums = [3,3,4,1,3,2,2,3].
 * We can perform the second operation as its score is 3 + 3 = 6, the same as
 * the previous operation. After this operation, nums = [4,1,3,2,2,3].
 * We cannot perform the next operation as its score is 4 + 1 = 5, which is
 * different from the previous scores.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [5,3]
 * 
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxOperations(int[] nums) {
        int ans = 0;
        for(int i = 0; i + 1 < nums.length; i += 2){
            if(nums[i] + nums[i + 1] == nums[0] + nums[1]){
                ans++;
            }
            else{
                break;
            }
        }
        return ans;
    }
}
// @lc code=end
