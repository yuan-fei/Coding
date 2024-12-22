/*
 * @lc app=leetcode id=2966 lang=java
 *
 * [2966] Divide Array Into Arrays With Max Difference
 *
 * https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/description/
 *
 * algorithms
 * Medium (57.79%)
 * Likes:    843
 * Dislikes: 194
 * Total Accepted:    155.2K
 * Total Submissions: 216.1K
 * Testcase Example:  '[1,3,4,8,7,9,3,5,1]\n2'
 *
 * You are given an integer array nums of size n where n is a multiple of 3 and
 * a positive integer k.
 * 
 * Divide the array nums into n / 3 arrays of size 3 satisfying the following
 * condition:
 * 
 * 
 * The difference between any two elements in one array is less than or equal
 * to k.
 * 
 * 
 * Return a 2D array containing the arrays. If it is impossible to satisfy the
 * conditions, return an empty array. And if there are multiple answers, return
 * any of them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
 * 
 * Output: [[1,1,3],[3,4,5],[7,8,9]]
 * 
 * Explanation:
 * 
 * The difference between any two elements in each array is less than or equal
 * to 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,4,2,2,5,2], k = 2
 * 
 * Output: []
 * 
 * Explanation:
 * 
 * Different ways to divide nums into 2 arrays of size 3 are:
 * 
 * 
 * [[2,2,2],[2,4,5]] (and its permutations)
 * [[2,2,4],[2,2,5]] (and its permutations)
 * 
 * 
 * Because there are four 2s there will be an array with the elements 2 and 5
 * no matter how we divide it. since 5 - 2 = 3 > k, the condition is not
 * satisfied and so there is no valid division.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11], k = 14
 * 
 * Output: [[2,2,12],[4,8,5],[5,9,7],[7,8,5],[5,9,10],[11,12,2]]
 * 
 * Explanation:
 * 
 * The difference between any two elements in each array is less than or equal
 * to 14.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 10^5
 * n is a multiple of 3
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] ans = new int[n / 3][3];
        for(int i = 0; i < n / 3; i++){
            if(Math.abs(nums[i * 3] - nums[i * 3 + 2]) > k){
                return new int[0][0];
            }
            for(int j = 0; j < 3; j++){
                ans[i][j] = nums[i * 3 + j];
            }
        }
        return ans;
    }
}
// @lc code=end
