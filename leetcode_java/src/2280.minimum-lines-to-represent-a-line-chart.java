/*
 * @lc app=leetcode id=2280 lang=java
 *
 * [2280] Minimum Lines to Represent a Line Chart
 *
 * https://leetcode.com/problems/minimum-lines-to-represent-a-line-chart/description/
 *
 * algorithms
 * Medium (18.35%)
 * Likes:    94
 * Dislikes: 259
 * Total Accepted:    8.8K
 * Total Submissions: 48K
 * Testcase Example:  '[[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]'
 *
 * You are given a 2D integer array stockPrices where stockPrices[i] = [dayi,
 * pricei] indicates the price of the stock on day dayi is pricei. A line chart
 * is created from the array by plotting the points on an XY plane with the
 * X-axis representing the day and the Y-axis representing the price and
 * connecting adjacent points. One such example is shown below:
 * 
 * Return the minimum number of lines needed to represent the line chart.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stockPrices = [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
 * Output: 3
 * Explanation:
 * The diagram above represents the input, with the X-axis representing the day
 * and Y-axis representing the price.
 * The following 3 lines can be drawn to represent the line chart:
 * - Line 1 (in red) from (1,7) to (4,4) passing through (1,7), (2,6), (3,5),
 * and (4,4).
 * - Line 2 (in blue) from (4,4) to (5,4).
 * - Line 3 (in green) from (5,4) to (8,1) passing through (5,4), (6,3), (7,2),
 * and (8,1).
 * It can be shown that it is not possible to represent the line chart using
 * less than 3 lines.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stockPrices = [[3,4],[1,2],[7,8],[2,3]]
 * Output: 1
 * Explanation:
 * As shown in the diagram above, the line chart can be represented with a
 * single line.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= stockPrices.length <= 10^5
 * stockPrices[i].length == 2
 * 1 <= dayi, pricei <= 10^9
 * All dayi are distinct.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumLines(int[][] stockPrices) {
        if(stockPrices.length == 1){
            return 0;
        }
        if(stockPrices.length < 3){
            return 1;
        }
        Arrays.sort(stockPrices, (a, b) -> Integer.compare(a[0], b[0]));
        int left = 0;
        int cnt = 1;
        int[] lastSlope = {stockPrices[1][1] - stockPrices[0][1], stockPrices[1][0] - stockPrices[0][0]};
        for(int i = 2; i < stockPrices.length; i++){
            int[] slope = {stockPrices[i][1] - stockPrices[i - 1][1], stockPrices[i][0] - stockPrices[i - 1][0]};
            if(slope[0] * lastSlope[1] != slope[1] * lastSlope[0]){
                lastSlope = slope;
                cnt++;
            }
        }
        return cnt;
    }
}
// @lc code=end
