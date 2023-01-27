/*
 * @lc app=leetcode id=801 lang=java
 *
 * [801] Minimum Swaps To Make Sequences Increasing
 *
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 *
 * algorithms
 * Hard (39.27%)
 * Likes:    2384
 * Dislikes: 131
 * Total Accepted:    60.7K
 * Total Submissions: 154.7K
 * Testcase Example:  '[1,3,5,4]\n[1,2,3,7]'
 *
 * You are given two integer arrays of the same length nums1 and nums2. In one
 * operation, you are allowed to swap nums1[i] with nums2[i].
 * 
 * 
 * For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can swap the
 * element at i = 3 to obtain nums1 = [1,2,3,4] and nums2 = [5,6,7,8].
 * 
 * 
 * Return the minimum number of needed operations to make nums1 and nums2
 * strictly increasing. The test cases are generated so that the given input
 * always makes it possible.
 * 
 * An array arr is strictly increasing if and only if arr[0] < arr[1] < arr[2]
 * < ... < arr[arr.length - 1].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * Output: 1
 * Explanation: 
 * Swap nums1[3] and nums2[3]. Then the sequences are:
 * nums1 = [1, 3, 5, 7] and nums2 = [1, 2, 3, 4]
 * which are both strictly increasing.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums1.length <= 10^5
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 2 * 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[2][n + 1];
        int MAX = n + 1;
        Arrays.fill(dp[0], MAX);
        Arrays.fill(dp[1], MAX);
        dp[0][1] = 0;
        dp[1][1] = 1;
        for(int i = 2; i <= n; i++){
            if(nums1[i - 1] <= nums1[i - 2] || nums2[i - 1] <= nums2[i - 2]){
                dp[0][i] = Math.min(dp[0][i], dp[1][i - 1]);
                dp[1][i] = Math.min(dp[1][i], dp[0][i - 1] + 1);
            }
            else{
                dp[0][i] = Math.min(dp[0][i], dp[0][i - 1]);
                dp[1][i] = Math.min(dp[1][i], dp[1][i - 1] + 1);
                if(Math.min(nums1[i - 1], nums2[i - 1]) > Math.max(nums1[i - 2], nums2[i - 2])){
                    dp[0][i] = Math.min(dp[0][i], dp[1][i - 1]);
                    dp[1][i] = Math.min(dp[1][i], dp[0][i - 1] + 1);
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        return Math.min(dp[0][n], dp[1][n]);
    }
}
// @lc code=end
