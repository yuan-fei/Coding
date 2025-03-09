/*
 * @lc app=leetcode id=3040 lang=java
 *
 * [3040] Maximum Number of Operations With the Same Score II
 *
 * https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-ii/description/
 *
 * algorithms
 * Medium (33.38%)
 * Likes:    168
 * Dislikes: 16
 * Total Accepted:    21.7K
 * Total Submissions: 64.9K
 * Testcase Example:  '[3,2,1,2,3,4]'
 *
 * Given an array of integers called nums, you can perform any of the following
 * operation while nums contains at least 2 elements:
 * 
 * 
 * Choose the first two elements of nums and delete them.
 * Choose the last two elements of nums and delete them.
 * Choose the first and the last elements of nums and delete them.
 * 
 * 
 * The score of the operation is the sum of the deleted elements.
 * 
 * Your task is to find the maximum number of operations that can be performed,
 * such that all operations have the same score.
 * 
 * Return the maximum number of operations possible that satisfy the condition
 * mentioned above.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,2,1,2,3,4]
 * Output: 3
 * Explanation: We perform the following operations:
 * - Delete the first two elements, with score 3 + 2 = 5, nums = [1,2,3,4].
 * - Delete the first and the last elements, with score 1 + 4 = 5, nums =
 * [2,3].
 * - Delete the first and the last elements, with score 2 + 3 = 5, nums = [].
 * We are unable to perform any more operations as nums is empty.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,2,6,1,4]
 * Output: 2
 * Explanation: We perform the following operations:
 * - Delete the first two elements, with score 3 + 2 = 5, nums = [6,1,4].
 * - Delete the last two elements, with score 1 + 4 = 5, nums = [6].
 * It can be proven that we can perform at most 2 operations.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 2000
 * 1 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {

    // f(score, 1, n) = max(f(score, 3, n), f(score, 1, n - 2), f(score, 2, n - 1)) + 1
    int[][] cache;
    public int maxOperations(int[] nums) {
        int ans = 0;
        int n = nums.length;
        cache = new int[n][n];
        resetCache();
        ans = Math.max(ans, maxOperations(nums, nums[0] + nums[1], 0, n - 1));
        resetCache();
        ans = Math.max(ans, maxOperations(nums, nums[n - 2] + nums[n - 1], 0, n - 1));
        resetCache();
        ans = Math.max(ans, maxOperations(nums, nums[0] + nums[n - 1], 0, n - 1));
        return ans;
    }

    void resetCache(){
        for(int i = 0; i < cache.length ; i++){
            Arrays.fill(cache[i], -1);
        }    
    }

    int maxOperations(int[] nums, int score, int start, int end){
        if(end <= start){
            return 0;
        }
        if(cache[start][end] == -1){
            int count = 0;
            if(nums[start] + nums[start + 1] == score){
                count = Math.max(count, maxOperations(nums, score, start + 2, end) + 1);
            }
            if(nums[end - 1] + nums[end] == score){
                count = Math.max(count, maxOperations(nums, score, start, end - 2) + 1);
            }
            if(nums[start] + nums[end] == score){
                count = Math.max(count, maxOperations(nums, score, start + 1, end - 1) + 1);
            }  
            cache[start][end] = count;  
        }
        
        return cache[start][end];
    }
}
// @lc code=end
