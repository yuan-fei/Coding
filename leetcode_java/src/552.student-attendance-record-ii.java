/*
 * @lc app=leetcode id=552 lang=java
 *
 * [552] Student Attendance Record II
 *
 * https://leetcode.com/problems/student-attendance-record-ii/description/
 *
 * algorithms
 * Hard (36.88%)
 * Likes:    589
 * Dislikes: 116
 * Total Accepted:    25.2K
 * Total Submissions: 68.3K
 * Testcase Example:  '1'
 *
 * Given a positive integer n, return the number of all possible attendance
 * records with length n, which will be regarded as rewardable. The answer may
 * be very large, return it after mod 10^9 + 7.
 * 
 * A student attendance record is a string that only contains the following
 * three characters:
 * 
 * 
 * 
 * 'A' : Absent. 
 * 'L' : Late.
 * ⁠'P' : Present. 
 * 
 * 
 * 
 * 
 * A record is regarded as rewardable if it doesn't contain more than one 'A'
 * (absent) or more than two continuous 'L' (late).
 * 
 * Example 1:
 * 
 * Input: n = 2
 * Output: 8 
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" won't be regarded as rewardable owing to more than one absent
 * times. 
 * 
 * 
 * 
 * Note:
 * The value of n won't exceed 100,000.
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int checkRecord(int n) {
    	int[][][] dp = new int[n + 1][2][3];
    	dp[1][0][0] = 1;
    	dp[1][1][0] = 1;
    	dp[1][0][1] = 1;
    	for(int i = 2; i <= n; i++){
    		dp[i][0][0] = add(add(dp[i - 1][0][0], dp[i - 1][0][1]), dp[i - 1][0][2]);

    		// P
			dp[i][1][0] = add(add(dp[i - 1][1][0], dp[i - 1][1][1]), dp[i - 1][1][2]);
			// A
			dp[i][1][0] = add(dp[i][1][0], add(add(dp[i - 1][0][0], dp[i - 1][0][1]), dp[i - 1][0][2]));

			// L
			dp[i][0][1] = dp[i - 1][0][0];
			// L
			dp[i][1][1] = dp[i - 1][1][0];

			// L
			dp[i][0][2] = dp[i - 1][0][1];
			// L
			dp[i][1][2] = dp[i - 1][1][1];
    	}

    	return add(add(add(dp[n][0][0], dp[n][0][1]), dp[n][0][2]), add(add(dp[n][1][0], dp[n][1][1]), dp[n][1][2]));
    }

    int add(int a, int b){
    	return (a + b) % 1000000007;
    }
}
// @lc code=end
