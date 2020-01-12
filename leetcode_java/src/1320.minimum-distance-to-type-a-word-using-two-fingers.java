/*
 * @lc app=leetcode id=1320 lang=java
 *
 * [1320] Minimum Distance to Type a Word Using Two Fingers
 *
 * https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/description/
 *
 * algorithms
 * Hard (39.00%)
 * Likes:    49
 * Dislikes: 3
 * Total Accepted:    1.3K
 * Total Submissions: 3.3K
 * Testcase Example:  '"CAKE"'
 *
 * 
 * 
 * You have a keyboard layout as shown above in the XY plane, where each
 * English uppercase letter is located at some coordinate, for example, the
 * letter A is located at coordinate (0,0), the letter B is located at
 * coordinate (0,1), the letter P is located at coordinate (2,3) and the letter
 * Z is located at coordinate (4,1).
 * 
 * Given the string word, return the minimum total distance to type such string
 * using only two fingers. The distance between coordinates (x1,y1) and (x2,y2)
 * is |x1 - x2| + |y1 - y2|. 
 * 
 * Note that the initial positions of your two fingers are considered free so
 * don't count towards your total distance, also your two fingers do not have
 * to start at the first letter or the first two letters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "CAKE"
 * Output: 3
 * Explanation: 
 * Using two fingers, one optimal way to type "CAKE" is: 
 * Finger 1 on letter 'C' -> cost = 0 
 * Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2 
 * Finger 2 on letter 'K' -> cost = 0 
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1 
 * Total distance = 3
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "HAPPY"
 * Output: 6
 * Explanation: 
 * Using two fingers, one optimal way to type "HAPPY" is:
 * Finger 1 on letter 'H' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
 * Finger 2 on letter 'P' -> cost = 0
 * Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
 * Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
 * Total distance = 6
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word = "NEW"
 * Output: 3
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: word = "YEAR"
 * Output: 7
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= word.length <= 300
 * Each word[i] is an English uppercase letter.
 * 
 */

// @lc code=start
class Solution {
    public int minimumDistance(String word) {
    	int n = word.length();
    	int MAX = 300 * 100 + 5;
    	int[][] dp = new int[n + 1][n + 1];    

    	for(int i = 0; i <= n; i++){
    		if(i + 1 <= n){
    			dp[i][i + 1] = dp[0][i];
    			for(int j = 1; j < i; j++){
    				dp[i][i + 1] = Math.min(dp[i][i + 1], dp[j][i] + dist(word.charAt(j - 1), word.charAt(i)));
    			}
    		}
    		for(int j = i + 2; j <= n; j++){
    			dp[i][j] = dp[i][j - 1] + dist(word.charAt(j - 2), word.charAt(j - 1));
    		}
    	}
    	// System.out.println(Arrays.deepToString(dp));
    	int minDis = MAX;
    	for (int i = 0; i < n; i++) {
    		minDis = Math.min(minDis, dp[i][n]);
    	}
    	return minDis;
    }

    int dist(char a, char b){
    	int[] fi = getCoordinate(a);
    	int[] se = getCoordinate(b);
    	return Math.abs(fi[0] - se[0]) + Math.abs(fi[1] - se[1]);
    }

    int[] getCoordinate(char a){
    	int offset = a - 'A';
    	return new int[]{offset / 6, offset % 6};
    }
}
// @lc code=end
