/*
 * [11] Container With Most Water
 *
 * https://leetcode.com/problems/container-with-most-water
 *
 * algorithms
 * Medium (36.84%)
 * Total Accepted:    166.6K
 * Total Submissions: 452.2K
 * Testcase Example:  '[1,1]'
 *
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two
 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most
 * water.
 * 
 * Note: You may not slant the container and n is at least 2.
 * 
 */

/*
 * 2 pointer solution: if checked in width decreasing order, then only change the short side could possibly increase the area.
 * 
 * 
 * 
 */

class Solution {
    public int maxArea(int[] height) {
		int left = 0; 
		int right = height.length-1;
		int maxArea = 0;
		int width = height.length-1;
		while(left < right){
			maxArea = Math.max(Math.min(height[left], height[right]) * width, maxArea);
			if(height[left] > height[right]){
				right--;
			}
			else{
				left++;
			}
			width--;
		}
		return maxArea;
    }
}
