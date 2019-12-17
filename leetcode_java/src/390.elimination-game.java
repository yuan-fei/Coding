/*
 * @lc app=leetcode id=390 lang=java
 *
 * [390] Elimination Game
 *
 * https://leetcode.com/problems/elimination-game/description/
 *
 * algorithms
 * Medium (44.06%)
 * Likes:    313
 * Dislikes: 271
 * Total Accepted:    27.3K
 * Total Submissions: 61.9K
 * Testcase Example:  '9'
 *
 * 
 * There is a list of sorted integers from 1 to n. Starting from left to right,
 * remove the first number and every other number afterward until you reach the
 * end of the list.
 * 
 * Repeat the previous step again, but this time from right to left, remove the
 * right most number and every other number from the remaining numbers.
 * 
 * We keep repeating the steps again, alternating left to right and right to
 * left, until a single number remains.
 * 
 * Find the last number that remains starting with a list of length n.
 * 
 * Example:
 * 
 * Input:
 * n = 9,
 * 1 2 3 4 5 6 7 8 9
 * 2 4 6 8
 * 2 6
 * 6
 * 
 * Output:
 * 6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int lastRemaining(int n) {
        return lastRemaining(n, 1);
    }

    private int lastRemaining(int n, int dir){
    	if(n == 1){
    		return n;
    	}
    	if(dir == 1){
    		return 2 * lastRemaining(n / 2, -1);	
    	}
    	else{
    		if(n % 2 == 1){
    			return 2 * lastRemaining(n / 2, 1);	
    		}
    		else{
    			return 2 * lastRemaining(n / 2, 1) - 1;
    		}
    	}
    }
}
// @lc code=end
