/*
 * @lc app=leetcode id=805 lang=java
 *
 * [805] Split Array With Same Average
 *
 * https://leetcode.com/problems/split-array-with-same-average/description/
 *
 * algorithms
 * Hard (25.74%)
 * Likes:    1059
 * Dislikes: 126
 * Total Accepted:    29.4K
 * Total Submissions: 114.2K
 * Testcase Example:  '[1,2,3,4,5,6,7,8]'
 *
 * You are given an integer array nums.
 * 
 * You should move each element of nums into one of the two arrays A and B such
 * that A and B are non-empty, and average(A) == average(B).
 * 
 * Return true if it is possible to achieve that and false otherwise.
 * 
 * Note that for an array arr, average(arr) is the sum of all the elements of
 * arr over the length of arr.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4,5,6,7,8]
 * Output: true
 * Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both
 * of them have an average of 4.5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,1]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 30
 * 0 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return false;
        }
        int MAX = n * 10000;
        int[] dp = new int[MAX + 5];
        dp[0] = 1;
        int sum = 0;
        for(int i = 1; i <= n; i++){
            sum += nums[i - 1];
            for(int x = MAX; x >= 0; x--){
                if(x + nums[i - 1] <= MAX){
                    dp[x + nums[i - 1]] |= dp[x] << 1;
                }
            }
        }
        
        for(int i = 1; i < n; i++){
            if((sum * i) % n == 0){
                int groupSum = sum * i / n;
                if(((dp[groupSum] >> i) & 1) == 1){
                    return true;
                }    
            }
        }
        // System.out.println(Arrays.toString(Arrays.copyOf(dp[n], sum + 1)));
        return false;
    }
}
// @lc code=end
