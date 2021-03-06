/*
 * @lc app=leetcode id=238 lang=java
 *
 * [238] Product of Array Except Self
 *
 * https://leetcode.com/problems/product-of-array-except-self/description/
 *
 * algorithms
 * Medium (57.85%)
 * Likes:    3290
 * Dislikes: 278
 * Total Accepted:    358.2K
 * Total Submissions: 618.5K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given an array nums of n integers where n > 1,  return an array output such
 * that output[i] is equal to the product of all the elements of nums except
 * nums[i].
 * 
 * Example:
 * 
 * 
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * 
 * Note: Please solve it without division and in O(n).
 * 
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does
 * not count as extra space for the purpose of space complexity analysis.)
 * 
 */

// @lc code=start
class Solution {
    public int[] productExceptSelf(int[] nums) {
    	int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for(int i = 1; i < n; i++){
        	ans[i] = ans[i - 1] * nums[i - 1];
        }
        // System.out.println(Arrays.toString(ans));
        int prod = 1;
        for(int i = n - 2; i >= 0; i--){
        	prod *= nums[i + 1];
        	ans[i] = ans[i] * prod;
        }
        return ans;
    }
}
// @lc code=end
