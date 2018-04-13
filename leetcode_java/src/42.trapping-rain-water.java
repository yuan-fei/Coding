/*
 * [42] Trapping Rain Water
 *
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (37.46%)
 * Total Accepted:    161K
 * Total Submissions: 429.3K
 * Testcase Example:  '[]'
 *
 * 
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after
 * raining. 
 * 
 * 
 * 
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * 
 * 
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 */
class Solution {
    public int trap(int[] height) {
    	int left = 0;
        int right = height.length - 1;
        int leftFast = left + 1;
        int rightFast = right - 1;
        int total = 0;
        while(leftFast <= right){
        	if(height[leftFast] >= height[left]){
        		int waterMark = height[left];
        		while(left < leftFast){
        			total += (waterMark - height[left]);
        			left++;
        		}
        	}
        	leftFast++;
        }

        while(rightFast >= left){
        	if(height[rightFast] >= height[right]){
        		int waterMark = height[right];
        		while(right > rightFast){
        			total += (waterMark - height[right]);
        			right--;
        		}
        	}
        	rightFast--;
        }
        return total;
    }
}
