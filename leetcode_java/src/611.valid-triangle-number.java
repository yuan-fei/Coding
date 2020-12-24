/*
 * @lc app=leetcode id=611 lang=java
 *
 * [611] Valid Triangle Number
 *
 * https://leetcode.com/problems/valid-triangle-number/description/
 *
 * algorithms
 * Medium (48.94%)
 * Likes:    1229
 * Dislikes: 97
 * Total Accepted:    73.6K
 * Total Submissions: 150.2K
 * Testcase Example:  '[2,2,3,4]'
 *
 * Given an array consists of non-negative integers,  your task is to count the
 * number of triplets chosen from the array that can make triangles if we take
 * them as side lengths of a triangle.
 * 
 * Example 1:
 * 
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are: 
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int triangleNumber(int[] nums) {
    	int n = nums.length;
    	Arrays.sort(nums);
    	int cnt = 0;
        for(int i = 0; i < n; i++){
        	int right = 0;
        	for(int j = i + 1; j < n; j++){
        		right = Math.max(right, j + 1);
        		while(right < n && nums[right] < nums[i] + nums[j]){
        			right++;
        		}
        		// System.out.println(right + ", " + j + ", " + cnt);
        		cnt += right - j - 1;

        	}
			// System.out.println(i + ", " + cnt);
        }
        return cnt;
    }
}
// @lc code=end
