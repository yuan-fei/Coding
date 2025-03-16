/*
 * @lc app=leetcode id=3077 lang=java
 *
 * [3077] Maximum Strength of K Disjoint Subarrays
 *
 * https://leetcode.com/problems/maximum-strength-of-k-disjoint-subarrays/description/
 *
 * algorithms
 * Hard (27.03%)
 * Likes:    147
 * Dislikes: 73
 * Total Accepted:    7K
 * Total Submissions: 25.8K
 * Testcase Example:  '[1,2,3,-1,2]\n3'
 *
 * You are given an array of integers nums with length n, and a positive odd
 * integer k.
 * 
 * Select exactly k disjoint subarrays sub1, sub2, ..., subk from nums such
 * that the last element of subi appears before the first element of sub{i+1}
 * for all 1 <= i <= k-1. The goal is to maximize their combined strength.
 * 
 * The strength of the selected subarrays is defined as:
 * 
 * strength = k * sum(sub1)- (k - 1) * sum(sub2) + (k - 2) * sum(sub3) - ... -
 * 2 * sum(sub{k-1}) + sum(subk)
 * 
 * where sum(subi) is the sum of the elements in the i-th subarray.
 * 
 * Return the maximum possible strength that can be obtained from selecting
 * exactly k disjoint subarrays from nums.
 * 
 * Note that the chosen subarrays don't need to cover the entire array.
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,-1,2], k = 3
 * 
 * Output: 22
 * 
 * Explanation:
 * 
 * The best possible way to select 3 subarrays is: nums[0..2], nums[3..3], and
 * nums[4..4]. The strength is calculated as follows:
 * 
 * strength = 3 * (1 + 2 + 3) - 2 * (-1) + 2 = 22
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: nums = [12,-2,-2,-2,-2], k = 5
 * 
 * Output: 64
 * 
 * Explanation:
 * 
 * The only possible way to select 5 disjoint subarrays is: nums[0..0],
 * nums[1..1], nums[2..2], nums[3..3], and nums[4..4]. The strength is
 * calculated as follows:
 * 
 * strength = 5 * 12 - 4 * (-2) + 3 * (-2) - 2 * (-2) + (-2) = 64
 * 
 * Example 3:
 * 
 * Input: nums = [-1,-2,-3], k = 1
 * 
 * Output: -1
 * 
 * Explanation:
 * 
 * The best possible way to select 1 subarray is: nums[0..0]. The strength is
 * -1.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * 1 <= k <= n
 * 1 <= n * k <= 10^6
 * k is odd.
 * 
 * 
 */

// @lc code=start
class Solution {
    // fmax(k, i) = k * a[i] + Max(fmax(k, i - 1), - min(fmin(k - 1, j)) where i > j >= 0)
    // fmin(k, i) = k * a[i] + Max(fmin(k, i - 1), - max(fmax(k - 1, j)) where i > j >= 0)
    long MAX = (long)1e15 + 7;
    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        reverse(nums);
        long[][] dpMax = new long[k + 1][n + 1];
        long[][] dpMin = new long[k + 1][n + 1];
        for(int i = 1; i <= k; i++){
            Arrays.fill(dpMax[i], -MAX);
            Arrays.fill(dpMin[i], MAX);
        }
        
        
        for(int i = 1; i <= k; i++){
            long min = dpMin[i - 1][i - 1];
            long max = dpMax[i - 1][i - 1];
            for(int j = i; j <= n; j++){
                dpMax[i][j] = 1L * i * nums[j - 1] + Math.max(dpMax[i][j - 1], -min);
                dpMin[i][j] = 1L * i * nums[j - 1] + Math.min(dpMin[i][j - 1], -max);
                min = Math.min(min, dpMin[i - 1][j]);
                max = Math.max(max, dpMax[i - 1][j]);
            }
        }
        // System.out.println(Arrays.deepToString(dpMax));
        // System.out.println(Arrays.deepToString(dpMin));
        return Arrays.stream(dpMax[k]).max().getAsLong();
    }

    void reverse(int[] nums){
        for(int i = 0, j = nums.length - 1; i < j; i++,j--){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
// @lc code=end
