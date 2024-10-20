/*
 * @lc app=leetcode id=2903 lang=java
 *
 * [2903] Find Indices With Index and Value Difference I
 *
 * https://leetcode.com/problems/find-indices-with-index-and-value-difference-i/description/
 *
 * algorithms
 * Easy (62.48%)
 * Likes:    142
 * Dislikes: 16
 * Total Accepted:    41.6K
 * Total Submissions: 67.4K
 * Testcase Example:  '[5,1,4,1]\n2\n4'
 *
 * You are given a 0-indexed integer array nums having length n, an integer
 * indexDifference, and an integer valueDifference.
 * 
 * Your task is to find two indices i and j, both in the range [0, n - 1], that
 * satisfy the following conditions:
 * 
 * 
 * abs(i - j) >= indexDifference, and
 * abs(nums[i] - nums[j]) >= valueDifference
 * 
 * 
 * Return an integer array answer, where answer = [i, j] if there are two such
 * indices, and answer = [-1, -1] otherwise. If there are multiple choices for
 * the two indices, return any of them.
 * 
 * Note: i and j may be equal.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [5,1,4,1], indexDifference = 2, valueDifference = 4
 * Output: [0,3]
 * Explanation: In this example, i = 0 and j = 3 can be selected.
 * abs(0 - 3) >= 2 and abs(nums[0] - nums[3]) >= 4.
 * Hence, a valid answer is [0,3].
 * [3,0] is also a valid answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,1], indexDifference = 0, valueDifference = 0
 * Output: [0,0]
 * Explanation: In this example, i = 0 and j = 0 can be selected.
 * abs(0 - 0) >= 0 and abs(nums[0] - nums[0]) >= 0.
 * Hence, a valid answer is [0,0].
 * Other valid answers are [0,1], [1,0], and [1,1].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3], indexDifference = 2, valueDifference = 4
 * Output: [-1,-1]
 * Explanation: In this example, it can be shown that it is impossible to find
 * two indices that satisfy both conditions.
 * Hence, [-1,-1] is returned.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= indexDifference <= 100
 * 0 <= valueDifference <= 50
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int minId = 0;
        int maxId = 0;
        int min = nums[0];
        int max = nums[0];
        int n = nums.length;
        for(int i = 0; i + indexDifference < n ; i++){
            if(min > nums[i]){
                min = nums[i];
                minId = i;
            }
            if(max < nums[i]){
                max = nums[i];
                maxId = i;
            }
            if(Math.abs(nums[i + indexDifference] - min) >= valueDifference){
                return new int[]{minId, i + indexDifference};
            }
            if(Math.abs(nums[i + indexDifference] - max) >= valueDifference){
                return new int[]{maxId, i + indexDifference};
            }
        }
        return new int[]{-1, -1};
    }
}
// @lc code=end