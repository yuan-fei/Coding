/*
 * @lc app=leetcode id=85 lang=java
 *
 * [85] Maximal Rectangle
 *
 * https://leetcode.com/problems/maximal-rectangle/description/
 *
 * algorithms
 * Hard (33.29%)
 * Total Accepted:    123.8K
 * Total Submissions: 368.9K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * ⁠ ["1","0","1","0","0"],
 * ⁠ ["1","0","1","1","1"],
 * ⁠ ["1","1","1","1","1"],
 * ⁠ ["1","0","0","1","0"]
 * ]
 * Output: 6
 * 
 * 
 */
class Solution {
	public int maximalRectangle(char[][] matrix) {
		int r = matrix.length;
    	if(r==0){
    		return 0;
    	}
    	int c = matrix[0].length;

    	int[] h = new int[c];
    	int max = 0;
    	for(int i = 0; i < r; i++){
    		//update h
    		for(int j = 0; j < c; j++){
    			if(matrix[i][j]=='1'){
    				h[j]++;
    			}
    			else{
    				h[j]=0;
    			}
    		}
    		max = Math.max(max, maxRectangleInHistgram(h));
    		//System.out.println(max);
    	}
    	return max;
	}

	int maxRectangleInHistgram(int[] h){
		Stack<Integer> s = new Stack<>();
		int max = 0;

		for(int i = 0; i<=h.length; i++){
			while(!s.isEmpty() && (i==h.length||h[s.peek()]>=h[i])){
				int hi = s.pop();
				int left = s.isEmpty()?-1:s.peek();
				max = Math.max(max, h[hi]*(i-1-left));
			}
			s.push(i);
		}
		return max;
	}

	/* Get maxRight of each point, and try all [left,right]*/
    public int maximalRectangle1(char[][] matrix) {
    	int r = matrix.length;
    	if(r==0){
    		return 0;
    	}
    	int c = matrix[0].length;

    	int[][] maxRight = new int[r][c];
        for(int i = 0 ; i< r; i++){
        	int left = -1;
        	for(int j = 0; j< c; j++){
        		if(matrix[i][j]=='0'){
        			if(left ==-1){
        				maxRight[i][j]=-1;
        			}
        			else{
        				if(j-1>=0){
        					for(int k = left; k < j; k++){
	        					maxRight[i][k] = j-1;
	        				}	
        				}
        				left=-1;
        			}
        		}
        		else{
        			if(left==-1){
        				left = j;
        			}
        		}	
        	}
        	if(left!=-1){
				for(int k = left; k < c; k++){
					maxRight[i][k] = c-1;
				}	
			}
        }
        int max = 0;
        for(int i = 0; i < c; i++){
        	for(int j = i; j < c; j++){
        		int left = -1;
        		for(int k = 0;k < r; k++){
        			if(maxRight[k][i]>=j){
        				if(left==-1){
        					left=k;
        				}
        				max = Math.max(max, (k-left+1)*(j-i+1));
        			}
        			else{
        				left=-1;
        			}
        		}
        	}
        }
        return max;
    }
}
