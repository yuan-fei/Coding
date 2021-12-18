/*
 * @lc app=leetcode id=2099 lang=java
 *
 * [2099] Find Subsequence of Length K With the Largest Sum
 *
 * https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/description/
 *
 * algorithms
 * Easy (39.97%)
 * Likes:    81
 * Dislikes: 5
 * Total Accepted:    4.6K
 * Total Submissions: 11.4K
 * Testcase Example:  '[2,1,3,3]\n2'
 *
 * You are given an integer array nums and an integer k. You want to find a
 * subsequence of nums of length k that has the largest sum.
 * 
 * Return any such subsequence as an integer array of length k.
 * 
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,1,3,3], k = 2
 * Output: [3,3]
 * Explanation:
 * The subsequence has the largest sum of 3 + 3 = 6.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-1,-2,3,4], k = 3
 * Output: [-1,3,4]
 * Explanation: 
 * The subsequence has the largest sum of -1 + 3 + 4 = 6.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,4,3,3], k = 2
 * Output: [3,4]
 * Explanation:
 * The subsequence has the largest sum of 3 + 4 = 7. 
 * Another possible subsequence is [4, 3].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++){
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> -Integer.compare(a[0], b[0]));
        int[][] kLargest = Arrays.copyOf(arr, k);
        Arrays.sort(kLargest, (a, b) -> Integer.compare(a[1], b[1]));
        int[] ret = new int[k];
        for(int i = 0; i < k; i++){
            ret[i] = kLargest[i][0];
        }
        return ret;
    }
}
// @lc code=end
