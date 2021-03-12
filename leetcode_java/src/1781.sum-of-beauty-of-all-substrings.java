/*
 * @lc app=leetcode id=1781 lang=java
 *
 * [1781] Sum of Beauty of All Substrings
 *
 * https://leetcode.com/problems/sum-of-beauty-of-all-substrings/description/
 *
 * algorithms
 * Medium (38.00%)
 * Likes:    29
 * Dislikes: 17
 * Total Accepted:    2.2K
 * Total Submissions: 6K
 * Testcase Example:  '"aabcb"'
 *
 * The beauty of a string is the difference in frequencies between the most
 * frequent and least frequent characters.
 * 
 * 
 * For example, the beauty of "abaacc" is 3 - 1 = 2.
 * 
 * 
 * Given a string s, return the sum of beauty of all of its substrings.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aabcb"
 * Output: 5
 * Explanation: The substrings with non-zero beauty are
 * ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aabcbaa"
 * Output: 17
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int beautySum(String s) {
    	int n = s.length();
        int[][] pSum = new int[26][n + 1];
        for(int i = 1; i <= n; i++){
        	for(int j = 0; j < 26; j++){
        		pSum[j][i] = pSum[j][i - 1];
        	}
        	pSum[s.charAt(i - 1) - 'a'][i]++;
        }
        int ans = 0;
        for(int i = 1; i <= n; i++){
        	for(int j = i + 1; j <= n; j++){
        		int max = 0;
        		int min = 10000;
        		for(int k = 0; k < 26; k++){
        			max = Math.max(max, pSum[k][j] - pSum[k][i - 1]);
        			if(pSum[k][j] - pSum[k][i - 1] > 0){
        				min = Math.min(min, pSum[k][j] - pSum[k][i - 1]);
        			}
        		}
        		ans += max - min;
        	}
        }
        return ans;
    }
}
// @lc code=end
