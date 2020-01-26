/*
 * @lc app=leetcode id=1330 lang=java
 *
 * [1330] Reverse Subarray To Maximize Array Value
 *
 * https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/description/
 *
 * algorithms
 * Hard (19.33%)
 * Likes:    30
 * Dislikes: 4
 * Total Accepted:    416
 * Total Submissions: 2.2K
 * Testcase Example:  '[2,3,1,5,4]'
 *
 * You are given an integer array nums. The value of this array is defined as
 * the sum of |nums[i]-nums[i+1]| for all 0 <= i < nums.length-1.
 * 
 * You are allowed to select any subarray of the given array and reverse it.
 * You can perform this operation only once.
 * 
 * Find maximum possible value of the final array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,3,1,5,4]
 * Output: 10
 * Explanation: By reversing the subarray [3,1,5] the array becomes [2,5,1,3,4]
 * whose value is 10.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,4,9,24,2,1,10]
 * Output: 68
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 3*10^4
 * -10^5 <= nums[i] <= 10^5
 * 
 */

// @lc code=start
class Solution {
    public int maxValueAfterReverse(int[] nums) {
    	// for pair (i, i+1), (j, j+1) value increases only when intervals [min(nums[i], nums[i + 1]), max(nums[i], nums[i + 1])] and [min(nums[j], nums[j + 1]), max(nums[j], nums[j + 1])] doesn't intersect
    	int n = nums.length;
    	int sum = 0;
    	int maxDiffWithFirst = 0;
    	int maxDiffWithLast = 0;
    	int maxMin = Integer.MIN_VALUE;
    	int minMax = Integer.MAX_VALUE;
    	int maxDiff = 0;
    	for(int i = 0; i < n - 1; i++){
    		sum += Math.abs(nums[i] - nums[i + 1]);
    		maxDiffWithFirst = Math.max(maxDiffWithFirst, Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]));
    		maxDiffWithLast = Math.max(maxDiffWithLast, Math.abs(nums[n - 1] - nums[i]) - Math.abs(nums[i] - nums[i + 1]));
    		maxMin = Math.max(maxMin, Math.min(nums[i], nums[i + 1]));
    		minMax = Math.min(minMax, Math.max(nums[i], nums[i + 1]));
    		maxDiff = Math.max(maxDiff, maxDiffWithFirst);
    		maxDiff = Math.max(maxDiff, maxDiffWithLast);
    		maxDiff = Math.max(maxDiff, (maxMin - minMax) * 2);
    	}
    	// System.out.println(sum);
    	// System.out.println(Arrays.toString(new int[]{maxDiff, minMax, maxMin, maxDiffWithFirst, maxDiffWithLast}));
    	return sum + maxDiff;
    }

}
// @lc code=end
