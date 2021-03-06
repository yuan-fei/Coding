/*
 * @lc app=leetcode id=303 lang=java
 *
 * [303] Range Sum Query - Immutable
 *
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 *
 * algorithms
 * Easy (41.19%)
 * Likes:    651
 * Dislikes: 909
 * Total Accepted:    169.6K
 * Total Submissions: 411K
 * Testcase Example:  '["NumArray","sumRange","sumRange","sumRange"]\n[[[-2,0,3,-5,2,-1]],[0,2],[2,5],[0,5]]'
 *
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * Example:
 * 
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * 
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 
 * 
 * 
 * Note:
 * 
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 * 
 * 
 */

// @lc code=start
class NumArray {

	int[] nums;
	int[] pSum;
    public NumArray(int[] nums) {
        this.nums = nums;
        pSum = new int[nums.length + 1];
        for(int i  =1; i < pSum.length; i++){
        	pSum[i] = pSum[i - 1] + nums[i - 1];
        }
    }
    
    public int sumRange(int i, int j) {
        return pSum[j + 1] - pSum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
// @lc code=end
