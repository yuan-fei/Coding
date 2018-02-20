/*
 * [84] Largest Rectangle in Histogram
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (27.44%)
 * Total Accepted:    111.5K
 * Total Submissions: 406.2K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * 
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * 
 * 
 * 
 * Above is a histogram where width of each bar is 1, given height =
 * [2,1,5,6,2,3].
 * 
 * 
 * 
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10
 * unit.
 * 
 * 
 * 
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 * 
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
    	if(heights == null || heights.length == 0){
    		return 0;
    	}
        Stack<Integer> s = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        
        while(i < heights.length){
        	if(s.isEmpty()){
        		s.push(i);
        		i++;
        	}
        	else if(heights[i] < heights[s.peek()]){
        		int top = s.pop();
        		int lowerLeft = -1;
        		if(!s.isEmpty()){
        			lowerLeft = s.peek();
        		}
        		int area = heights[top] * (i - lowerLeft - 1);
        		maxArea = Math.max(area, maxArea);
        	}
        	else{
        		s.push(i);
        		i++;
        	}
        }
        while(!s.isEmpty()){
        	int top = s.pop();
    		int lowerLeft = -1;
    		if(!s.isEmpty()){
    			lowerLeft = s.peek();
    		}
    		int area = heights[top] * (i - lowerLeft - 1);
    		maxArea = Math.max(area, maxArea);
        	
        }
        return maxArea;
    }
}
