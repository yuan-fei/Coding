/*
 * @lc app=leetcode id=1769 lang=java
 *
 * [1769] Minimum Number of Operations to Move All Balls to Each Box
 *
 * https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/description/
 *
 * algorithms
 * Medium (90.15%)
 * Likes:    114
 * Dislikes: 12
 * Total Accepted:    10.9K
 * Total Submissions: 12.2K
 * Testcase Example:  '"110"'
 *
 * You have n boxes. You are given a binary string boxes of length n, where
 * boxes[i] is '0' if the i^th box is empty, and '1' if it contains one ball.
 * 
 * In one operation, you can move one ball from a box to an adjacent box. Box i
 * is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may
 * be more than one ball in some boxes.
 * 
 * Return an array answer of size n, where answer[i] is the minimum number of
 * operations needed to move all the balls to the i^th box.
 * 
 * Each answer[i] is calculated considering the initial state of the boxes.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: boxes = "110"
 * Output: [1,1,3]
 * Explanation: The answer for each box is as follows:
 * 1) First box: you will have to move one ball from the second box to the
 * first box in one operation.
 * 2) Second box: you will have to move one ball from the first box to the
 * second box in one operation.
 * 3) Third box: you will have to move one ball from the first box to the third
 * box in two operations, and move one ball from the second box to the third
 * box in one operation.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: boxes = "001011"
 * Output: [11,8,5,4,3,4]
 * 
 * 
 * Constraints:
 * 
 * 
 * n == boxes.length
 * 1 <= n <= 2000
 * boxes[i] is either '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] minOperations(String boxes) {
    	int n = boxes.length();
        int[] prefSum = new int[n + 1];
        int[] prefCnt = new int[n + 1];
        for(int i = 1; i <= n; i++){

        	if(boxes.charAt(i - 1) == '1'){
        		prefSum[i] = prefSum[i - 1] + i;
        		prefCnt[i] = prefCnt[i - 1] + 1;
        	}
        	else{
        		prefSum[i] = prefSum[i - 1];	
        		prefCnt[i] = prefCnt[i - 1];
        	}
        }
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
        	ans[i] = prefCnt[i] * (i + 1) - prefSum[i] + prefSum[n] - prefSum[i + 1] - (prefCnt[n] - prefCnt[i + 1]) * (i + 1);
        }
        return ans;
    }
}
// @lc code=end
