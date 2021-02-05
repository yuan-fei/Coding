/*
 * @lc app=leetcode id=1744 lang=java
 *
 * [1744] Can You Eat Your Favorite Candy on Your Favorite Day?
 *
 * https://leetcode.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/description/
 *
 * algorithms
 * Medium (30.23%)
 * Likes:    46
 * Dislikes: 154
 * Total Accepted:    4.8K
 * Total Submissions: 16K
 * Testcase Example:  '[7,4,5,3,8]\n[[0,2,2],[4,2,4],[2,13,1000000000]]'
 *
 * You are given a (0-indexed) array of positive integers candiesCount where
 * candiesCount[i] represents the number of candies of the i^th type you have.
 * You are also given a 2D array queries where queries[i] = [favoriteTypei,
 * favoriteDayi, dailyCapi].
 * 
 * You play a game with the following rules:
 * 
 * 
 * You start eating candies on day 0.
 * You cannot eat any candy of type i unless you have eaten all candies of type
 * i - 1.
 * You must eat at least one candy per day until you have eaten all the
 * candies.
 * 
 * 
 * Construct a boolean array answer such that answer.length == queries.length
 * and answer[i] is true if you can eat a candy of type favoriteTypei on day
 * favoriteDayi without eating more than dailyCapi candies on any day, and
 * false otherwise. Note that you can eat different types of candy on the same
 * day, provided that you follow rule 2.
 * 
 * Return the constructed array answer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: candiesCount = [7,4,5,3,8], queries =
 * [[0,2,2],[4,2,4],[2,13,1000000000]]
 * Output: [true,false,true]
 * Explanation:
 * 1- If you eat 2 candies (type 0) on day 0 and 2 candies (type 0) on day 1,
 * you will eat a candy of type 0 on day 2.
 * 2- You can eat at most 4 candies each day.
 * ⁠  If you eat 4 candies every day, you will eat 4 candies (type 0) on day 0
 * and 4 candies (type 0 and type 1) on day 1.
 * ⁠  On day 2, you can only eat 4 candies (type 1 and type 2), so you cannot
 * eat a candy of type 4 on day 2.
 * 3- If you eat 1 candy each day, you will eat a candy of type 2 on day 13.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: candiesCount = [5,2,6,4,1], queries =
 * [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
 * Output: [false,true,true,false,false]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= candiesCount.length <= 10^5
 * 1 <= candiesCount[i] <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 3
 * 0 <= favoriteTypei < candiesCount.length
 * 0 <= favoriteDayi <= 10^9
 * 1 <= dailyCapi <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
    	int n = candiesCount.length;
        long[] pSum = new long[n + 1];
        for(int i = 1; i <= n; i++){
        	pSum[i] = pSum[i - 1] + candiesCount[i - 1];
        }
        boolean[] ans = new boolean[queries.length];
        for(int i = 0; i < queries.length; i++){
        	int type = queries[i][0];
        	long min = queries[i][1];
        	long max = 1L * queries[i][2] * (queries[i][1] + 1);
        	// System.out.println(Arrays.asList(min,pSum[type + 1],pSum[type], max));
        	ans[i] = min < pSum[type + 1] && pSum[type] < max;
        }
        return ans;
    }
}
// @lc code=end
