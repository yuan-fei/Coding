/*
 * @lc app=leetcode id=1595 lang=java
 *
 * [1595] Minimum Cost to Connect Two Groups of Points
 *
 * https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/description/
 *
 * algorithms
 * Hard (25.41%)
 * Likes:    36
 * Dislikes: 4
 * Total Accepted:    733
 * Total Submissions: 2.9K
 * Testcase Example:  '[[15,96],[36,2]]'
 *
 * You are given two groups of points where the first group has size1 points,
 * the second group has size2 points, and size1 >= size2.
 * 
 * The cost of the connection between any two points are given in an size1 x
 * size2 matrix where cost[i][j] is the cost of connecting point i of the first
 * group and point j of the second group. The groups are connected if each
 * point in both groups is connected to one or more points in the opposite
 * group. In other words, each point in the first group must be connected to at
 * least one point in the second group, and each point in the second group must
 * be connected to at least one point in the first group.
 * 
 * Return the minimum cost it takes to connect the two groups.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: cost = [[15, 96], [36, 2]]
 * Output: 17
 * Explanation: The optimal way of connecting the groups is:
 * 1--A
 * 2--B
 * This results in a total cost of 17.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
 * Output: 4
 * Explanation: The optimal way of connecting the groups is:
 * 1--A
 * 2--B
 * 2--C
 * 3--A
 * This results in a total cost of 4.
 * Note that there are multiple points connected to point 2 in the first group
 * and point A in the second group. This does not matter as there is no limit
 * to the number of points that can be connected. We only care about the
 * minimum total cost.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
 * Output: 10
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * size1 == cost.length
 * size2 == cost[i].length
 * 1 <= size1, size2 <= 12
 * size1 >= size2
 * 0 <= cost[i][j] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
    	int n = cost.size();
    	int m = cost.get(0).size();
        int[][] dp = new int[n][1 << m];
        int MAX = 12 * 12 * 100 + 5;
        for(int i = 1; i < n; i++){
        	Arrays.fill(dp[i], MAX);	
        }
        
        for(int j = 0; j < (1 << m); j++){
    		for(int k = 0; k < m; k++){
    			if(((j >> k) & 1) != 0){
    				dp[0][j] += cost.get(0).get(k);
    			}
    		}
    	}

        for(int i = 1; i < n; i++){
        	for(int j = 0; j < (1 << m); j++){
        		int minCosti = MAX;
        		for(int k = 0; k < m; k++){
        			if(((j >> k) & 1) != 0){
        				int p = j ^ (1 << k);
        				if(p != 0){
	        				dp[i][j] = Math.min(dp[i][j], dp[i][p] + cost.get(i).get(k));
	        				dp[i][j] = Math.min(dp[i][j], dp[i - 1][p] + cost.get(i).get(k));	
        				}
        				minCosti = Math.min(minCosti, cost.get(i).get(k));
        			}
        		}
        		dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + minCosti);
        	}
        }
        // System.out.println(Arrays.deepToString(dp));
        return dp[n - 1][(1 << m) - 1];
    }
}
// @lc code=end
