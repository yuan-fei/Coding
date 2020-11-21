/*
 * @lc app=leetcode id=1659 lang=java
 *
 * [1659] Maximize Grid Happiness
 *
 * https://leetcode.com/problems/maximize-grid-happiness/description/
 *
 * algorithms
 * Hard (17.96%)
 * Likes:    27
 * Dislikes: 24
 * Total Accepted:    487
 * Total Submissions: 2.7K
 * Testcase Example:  '2\n3\n1\n2'
 *
 * You are given four integers, m, n, introvertsCount, and extrovertsCount. You
 * have an m x n grid, and there are two types of people: introverts and
 * extroverts. There are introvertsCount introverts and extrovertsCount
 * extroverts.
 * 
 * You should decide how many people you want to live in the grid and assign
 * each of them one grid cell. Note that you do not have to have all the people
 * living in the grid.
 * 
 * The happiness of each person is calculated as follows:
 * 
 * 
 * Introverts start with 120 happiness and lose 30 happiness for each neighbor
 * (introvert or extrovert).
 * Extroverts start with 40 happiness and gain 20 happiness for each neighbor
 * (introvert or extrovert).
 * 
 * 
 * Neighbors live in the directly adjacent cells north, east, south, and west
 * of a person's cell.
 * 
 * The grid happiness is the sum of each person's happiness. Return the maximum
 * possible grid happiness.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
 * Output: 240
 * Explanation: Assume the grid is 1-indexed with coordinates (row, column).
 * We can put the introvert in cell (1,1) and put the extroverts in cells (1,3)
 * and (2,3).
 * - Introvert at (1,1) happiness: 120 (starting happiness) - (0 * 30) (0
 * neighbors) = 120
 * - Extrovert at (1,3) happiness: 40 (starting happiness) + (1 * 20) (1
 * neighbor) = 60
 * - Extrovert at (2,3) happiness: 40 (starting happiness) + (1 * 20) (1
 * neighbor) = 60
 * The grid happiness is 120 + 60 + 60 = 240.
 * The above figure shows the grid in this example with each person's
 * happiness. The introvert stays in the light green cell while the extroverts
 * live on the light purple cells.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
 * Output: 260
 * Explanation: Place the two introverts in (1,1) and (3,1) and the extrovert
 * at (2,1).
 * - Introvert at (1,1) happiness: 120 (starting happiness) - (1 * 30) (1
 * neighbor) = 90
 * - Extrovert at (2,1) happiness: 40 (starting happiness) + (2 * 20) (2
 * neighbors) = 80
 * - Introvert at (3,1) happiness: 120 (starting happiness) - (1 * 30) (1
 * neighbor) = 90
 * The grid happiness is 90 + 80 + 90 = 260.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
 * Output: 240
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= m, n <= 5
 * 0 <= introvertsCount, extrovertsCount <= min(m * n, 6)
 * 
 * 
 */

// @lc code=start
class Solution {
	int[][] decoded;
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
    	int total = (int)Math.pow(3, n);
    	decoded = new int[total][n];
    	decode(n);
    	int MIN = -120 * 100 - 5;
        int[][][][] dp = new int[m + 2][introvertsCount + 1][extrovertsCount + 1][total];
        for(int i = 0; i <= m + 1; i++){
        	for(int cntIntro = 0; cntIntro <= introvertsCount; cntIntro++){
        		for(int cntExtro = 0; cntExtro <= extrovertsCount; cntExtro++){
        			for(int j = 0; j < total; j++){
        				dp[i][cntIntro][cntExtro][j] = MIN;
        			}
        		}
        	}
        }
        dp[0][introvertsCount][extrovertsCount][0] = 0;
        for(int i = 1; i <= m + 1; i++){
        	for(int cntIntro = 0; cntIntro <= introvertsCount; cntIntro++){
        		for(int cntExtro = 0; cntExtro <= extrovertsCount; cntExtro++){
        			for(int j = 0; j < total; j++){
        				int[] bmCur = decoded[j];
        				int[] cntCur = countNonEmpty(bmCur);
        				int prevCntIntro = cntIntro + cntCur[0];
        				int prevCntExtro = cntExtro + cntCur[1];
        				int incrementCur = 0;
        				for(int l = 0; l < n; l++){
							if(bmCur[l] == 1){
								incrementCur += 120;
							}
							else if(bmCur[l] == 2){
								incrementCur += 40;	
							}
							if(l > 0){
								incrementCur += adjustScore(bmCur[l], bmCur[l - 1]);	
							}
						}
        				if(prevCntIntro <= introvertsCount && prevCntExtro <= extrovertsCount){
	        				for(int k = 0; k < total; k++){
	        					int[] bmPrev = decoded[k];
	        					int[] cntPrev = countNonEmpty(bmPrev);
	        					if(prevCntIntro + cntPrev[0] <= introvertsCount && prevCntExtro + cntPrev[1] <= extrovertsCount){
	        						int incrementPrev = 0;
	        						for(int l = 0; l < n; l++){
	        							incrementPrev += adjustScore(bmCur[l], bmPrev[l]);
	        						}
	        						dp[i][cntIntro][cntExtro][j] = Math.max(dp[i][cntIntro][cntExtro][j], dp[i - 1][prevCntIntro][prevCntExtro][k] + incrementCur + incrementPrev);
	        					}
	        				}	
        				}
        				
        			}
        		}
        	}
        }
        // System.out.println(Arrays.deepToString(dp));
        int max = 0;
        for(int cntIntro = 0; cntIntro <= introvertsCount; cntIntro++){
    		for(int cntExtro = 0; cntExtro <= extrovertsCount; cntExtro++){
    			max = Math.max(max, dp[m + 1][cntIntro][cntExtro][0]);
    		}
    	}
        return max;
    }

    void decode(int n){
    	for(int k = 0; k < decoded.length; k++){
    		int x = k;
	    	for(int i = 0; i < n; i++){
	    		decoded[k][i] = x % 3;
	    		x /= 3;
	    	}	
    	}
    }


    int[] countNonEmpty(int[] c){
    	int[] cnt = new int[2];
    	for(int x : c){
    		if(x >= 1){
    			cnt[x - 1]++;	
    		}
    	}
    	return cnt;
    }

    int adjustScore(int cur, int pre){
    	int adjust = 0;
    	if(cur == 1 && pre == 1){
    		adjust = -60;
    	}
    	else if(cur == 1 && pre == 2){
    		adjust = -10;
    	}
    	else if(cur == 2 && pre == 1){
    		adjust = -10;
    	}
    	else if(cur == 2 && pre == 2){
    		adjust = 40;
    	}
    	return adjust;
    }
}
// @lc code=end
