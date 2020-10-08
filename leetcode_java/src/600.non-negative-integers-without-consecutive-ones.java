/*
 * @lc app=leetcode id=600 lang=java
 *
 * [600] Non-negative Integers without Consecutive Ones
 *
 * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/description/
 *
 * algorithms
 * Hard (34.30%)
 * Likes:    432
 * Dislikes: 73
 * Total Accepted:    12.9K
 * Total Submissions: 37.7K
 * Testcase Example:  '1'
 *
 * Given a positive integer n, find the number of non-negative integers less
 * than or equal to n, whose binary representations do NOT contain consecutive
 * ones.
 * 
 * Example 1:
 * 
 * Input: 5
 * Output: 5
 * Explanation: 
 * Here are the non-negative integers 
 * 
 * 
 * Note:
 * 1 
 * 
 */

// @lc code=start
class Solution {
    public int findIntegers(int num) {
        int l = Integer.toBinaryString(num).length();
        return dfs(num, l, 1, 0);
    }
    int[][][] cache = new int[32][2][2];
    int dfs(int n, int cur, int isTight, int isLastBitSet){
    	if(cur == 0){
    		return 1;
    	}
    	if(cache[cur][isTight][isLastBitSet] == 0){
    		int cnt = 0;
	    	int curBit = ((n >> (cur - 1)) & 1);
	    	if(1 == isTight){
	    		if(1 == isLastBitSet){
	    			// 0
	    			cnt += dfs(n, cur - 1, 1 - curBit, 0);
	    		}
	    		else{
	    			// n[cur] = 0
	    			if(0 == curBit){
	    				cnt += dfs(n, cur - 1, 1, 0);	
	    			}
	    			else{ // n[cur] = 1
	    				// 0
	    				cnt += dfs(n, cur - 1, 0, 0);
	    				// 1
		    			cnt += dfs(n, cur - 1, 1, 1);
	    			}
	    		}
	    	}
	    	else{
	    		if(1 == isLastBitSet){
	    			// 0
	    			cnt += dfs(n, cur - 1, 0, 0);
	    		}
	    		else{
	    			cnt += dfs(n, cur - 1, 0, 0);	
	    			cnt += dfs(n, cur - 1, 0, 1);
	    		}
	    	}
	    	cache[cur][isTight][isLastBitSet] = cnt;
    	}
    	
    	// System.out.println(Arrays.asList(cur, isTight, isLastBitSet, cnt));
    	return cache[cur][isTight][isLastBitSet];
    }
}
// @lc code=end
