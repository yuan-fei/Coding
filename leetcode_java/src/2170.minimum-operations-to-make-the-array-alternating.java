/*
 * @lc app=leetcode id=2170 lang=java
 *
 * [2170] Minimum Operations to Make the Array Alternating
 *
 * https://leetcode.com/problems/minimum-operations-to-make-the-array-alternating/description/
 *
 * algorithms
 * Medium (26.93%)
 * Likes:    136
 * Dislikes: 165
 * Total Accepted:    7.4K
 * Total Submissions: 27.7K
 * Testcase Example:  '[3,1,3,2,4,3]'
 *
 * You are given a 0-indexed array nums consisting of n positive integers.
 * 
 * The array nums is called alternating if:
 * 
 * 
 * nums[i - 2] == nums[i], where 2 <= i <= n - 1.
 * nums[i - 1] != nums[i], where 1 <= i <= n - 1.
 * 
 * 
 * In one operation, you can choose an index i and change nums[i] into any
 * positive integer.
 * 
 * Return the minimum number of operations required to make the array
 * alternating.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,1,3,2,4,3]
 * Output: 3
 * Explanation:
 * One way to make the array alternating is by converting it to [3,1,3,1,3,1].
 * The number of operations required in this case is 3.
 * It can be proven that it is not possible to make the array alternating in
 * less than 3 operations. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,2,2,2]
 * Output: 2
 * Explanation:
 * One way to make the array alternating is by converting it to [1,2,1,2,1].
 * The number of operations required in this case is 2.
 * Note that the array cannot be converted to [2,2,2,2,2] because in this case
 * nums[0] == nums[1] which violates the conditions of an alternating
 * array.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumOperations(int[] nums) {
        int freq[][] = new int[100005][2];
        int i, j, k, ans=0;
        for(i = 0; i < nums.length; i++) {
                freq[nums[i]][i&1]++;
        }
            
        for(i = 1, j=k=0; i <= 100000; i++) {
            // Add the maximum frequency of odd indexes to maximum frequency even indexes 
            //and vice versa, it will give us how many elements we don't need to change. 
            ans = Math.max(ans, Math.max(freq[i][0] + k, freq[i][1] + j));
            j = Math.max(j, freq[i][0]);
            k = Math.max(k, freq[i][1]);
        }
        return nums.length - ans;
    }
}
// @lc code=end
