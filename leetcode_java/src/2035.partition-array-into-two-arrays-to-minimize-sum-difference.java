/*
 * @lc app=leetcode id=2035 lang=java
 *
 * [2035] Partition Array Into Two Arrays to Minimize Sum Difference
 *
 * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/
 *
 * algorithms
 * Hard (21.02%)
 * Likes:    114
 * Dislikes: 5
 * Total Accepted:    1.7K
 * Total Submissions: 8K
 * Testcase Example:  '[3,9,7,3]'
 *
 * You are given an integer array nums of 2 * n integers. You need to partition
 * nums into two arrays of length n to minimize the absolute difference of the
 * sums of the arrays. To partition nums, put each element of nums into one of
 * the two arrays.
 * 
 * Return the minimum possible absolute difference.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,9,7,3]
 * Output: 2
 * Explanation: One optimal partition is: [3,9] and [7,3].
 * The absolute difference between the sums of the arrays is abs((3 + 9) - (7 +
 * 3)) = 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-36,36]
 * Output: 72
 * Explanation: One optimal partition is: [-36] and [36].
 * The absolute difference between the sums of the arrays is abs((-36) - (36))
 * = 72.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [2,-1,0,4,-2,-9]
 * Output: 0
 * Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
 * The absolute difference between the sums of the arrays is abs((2 + 4 + -9) -
 * (-1 + 0 + -2)) = 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 15
 * nums.length == 2 * n
 * -10^7 <= nums[i] <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int[] h1 = Arrays.copyOfRange(nums, 0, n);
        int[] h2 = Arrays.copyOfRange(nums, n, 2 * n);
        int sum = 0;
        for(int x : nums){
            sum += x;
        }
        TreeSet<Integer>[] ts1 = getAllCompositions(h1);
        TreeSet<Integer>[] ts2 = getAllCompositions(h2);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= n; i++){
            for(int x : ts1[i]){
                int target = sum - x;
                Integer floor = ts2[n - i].floor(target);
                if(floor != null){
                    min = Math.min(min, target - floor);
                }
                Integer ceiling = ts2[n - i].ceiling(target);
                if(ceiling != null){
                    min = Math.min(min, ceiling - target);
                }
            }
        }
        return min;
    }

    TreeSet<Integer>[] getAllCompositions(int[] arr){
        int n = arr.length;
        int[] dp = new int[1 << n];
        TreeSet<Integer>[] ts = new TreeSet[n + 1];
        for(int i = 0; i < ts.length; i++){
            ts[i] = new TreeSet<>();
        }
        ts[0].add(0);
        for(int i = 1; i < (1 << n); i++){
            int bc = Integer.bitCount(i);
            int j = 0;
            for(; j < n; j++){
                if(((i >> j) & 1) != 0){
                    break;
                }
            }
            dp[i] = dp[i ^ (1 << j)] + arr[j];
            ts[bc].add(2 * dp[i]);
        }
        return ts;
    }
}
// @lc code=end
