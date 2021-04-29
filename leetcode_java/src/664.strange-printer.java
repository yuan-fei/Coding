/*
 * @lc app=leetcode id=664 lang=java
 *
 * [664] Strange Printer
 *
 * https://leetcode.com/problems/strange-printer/description/
 *
 * algorithms
 * Hard (41.62%)
 * Likes:    570
 * Dislikes: 54
 * Total Accepted:    18K
 * Total Submissions: 43.1K
 * Testcase Example:  '"aaabbb"'
 *
 * 
 * There is a strange printer with the following two special requirements:
 * 
 * 
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending
 * at any places, and will cover the original existing characters.
 * 
 * 
 * 
 * 
 * 
 * Given a string consists of lower English letters only, your job is to count
 * the minimum number of turns the printer needed in order to print it.
 * 
 * 
 * Example 1:
 * 
 * Input: "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "aba"
 * Output: 2
 * Explanation: Print "aaa" first and then print "b" from the second place of
 * the string, which will cover the existing character 'a'.
 * 
 * 
 * 
 * Hint: Length of the given string will not exceed 100.
 */

// @lc code=start
class Solution {
    public int strangePrinter(String s) {
    	int n = s.length();
        dp = new int[n][n];
        return rec(s, 0, n - 1);
    }

    int[][] dp;

    int rec(String s, int start, int end){
    	if(start > end){
    		return 0;
    	}

    	if(dp[start][end] == 0){
	    	while(start < end && s.charAt(start) == s.charAt(start + 1)){
	    		start++;
	    	}

	    	dp[start][end] = 1 + rec(s, start + 1, end);
	    	for(int i = start + 1; i <= end; i++){
	    		if(s.charAt(i) == s.charAt(start)){
	    			dp[start][end] = Math.min(dp[start][end], rec(s, start + 1, i - 1) + rec(s, i, end));
	    		}
	    	}	
    	}
    	
    	// System.out.println(Arrays.asList(start, end, streak, min));
    	return dp[start][end];
    }
}
// @lc code=end
