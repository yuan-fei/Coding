/*
 * @lc app=leetcode id=1575 lang=java
 *
 * [1575] Count All Possible Routes
 *
 * https://leetcode.com/problems/count-all-possible-routes/description/
 *
 * algorithms
 * Hard (57.19%)
 * Likes:    14
 * Dislikes: 1
 * Total Accepted:    922
 * Total Submissions: 1.6K
 * Testcase Example:  '[2,3,6,8,4]\n1\n3\n5'
 *
 * You are given an array of distinct positive integers locations where
 * locations[i] represents the position of city i. You are also given integers
 * start, finish and fuel representing the starting city, ending city, and the
 * initial amount of fuel you have, respectively.
 * 
 * At each step, if you are at city i, you can pick any city j such that j != i
 * and 0 <= j < locations.length and move to city j. Moving from city i to city
 * j reduces the amount of fuel you have by |locations[i] - locations[j]|.
 * Please notice that |x| denotes the absolute value of x.
 * 
 * Notice that fuel cannot become negative at any point in time, and that you
 * are allowed to visit any city more than once (including start and finish).
 * 
 * Return the count of all possible routes from start to finish.
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
 * Output: 4
 * Explanation: The following are all possible routes, each uses 5 units of
 * fuel:
 * 1 -> 3
 * 1 -> 2 -> 3
 * 1 -> 4 -> 3
 * 1 -> 4 -> 2 -> 3
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: locations = [4,3,1], start = 1, finish = 0, fuel = 6
 * Output: 5
 * Explanation: The following are all possible routes:
 * 1 -> 0, used fuel = 1
 * 1 -> 2 -> 0, used fuel = 5
 * 1 -> 2 -> 1 -> 0, used fuel = 5
 * 1 -> 0 -> 1 -> 0, used fuel = 3
 * 1 -> 0 -> 1 -> 0 -> 1 -> 0, used fuel = 5
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: locations = [5,2,1], start = 0, finish = 2, fuel = 3
 * Output: 0
 * Explanation: It's impossible to get from 0 to 2 using only 3 units of fuel
 * since the shortest route needs 4 units of fuel.
 * 
 * Example 4:
 * 
 * 
 * Input: locations = [2,1,5], start = 0, finish = 0, fuel = 3
 * Output: 2
 * Explanation: There are two possible routes, 0 and 0 -> 1 -> 0.
 * 
 * Example 5:
 * 
 * 
 * Input: locations = [1,2,3], start = 0, finish = 2, fuel = 40
 * Output: 615088286
 * Explanation: The total number of possible routes is 2615088300. Taking this
 * number modulo 10^9 + 7 gives us 615088286.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= locations.length <= 100
 * 1 <= locations[i] <= 10^9
 * All integers in locations are distinct.
 * 0 <= start, finish < locations.length
 * 1 <= fuel <= 200
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
    	int mod = 1000000007;
    	int n = locations.length;
        int[][] dp = new int[n][fuel + 1];
        dp[start][fuel] = 1;
        for(int f = fuel; f >=0; f--){
        	for(int i = 0; i < n; i++){
	    		for(int j = 0; j < n; j++){
	    			int remaining = f - Math.abs(locations[i] - locations[j]);
	    			if(i != j && remaining >= 0){
	    				dp[j][remaining] = (dp[j][remaining] + dp[i][f]) % mod;
	    			}
	    		}
        	}
        }
        int total = 0;
        for(int i = 0; i <= fuel; i++){
        	total = (total + dp[finish][i]) % mod;
        }
        return total;
    }

    public int countRoutes1(int[] locations, int start, int finish, int fuel) {
    	int mod = 1000000007;
    	int n = locations.length;
        int[][] dp = new int[n][fuel + 1];
        boolean[][] visited = new boolean[n][fuel + 1];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> -Integer.compare(a[1], b[1]));
        dp[start][fuel] = 1;
        q.offer(new int[]{ start, fuel});
        visited[start][fuel] = true;
        while(!q.isEmpty()){
    		int[] cur = q.poll();
    		for(int j = 0; j < n; j++){
    			int remaining = cur[1] - Math.abs(locations[cur[0]] - locations[j]);
    			if(cur[0] != j && remaining >= 0){
    				dp[j][remaining] = (dp[j][remaining] + dp[cur[0]][cur[1]]) % mod;
    				// System.out.println(j + ", " + remaining + "=" + dp[j][remaining]+"< "+Arrays.toString(cur));
    				if(!visited[j][remaining]){
    					visited[j][remaining] = true;
    					q.offer(new int[]{j, remaining});
    				}
    			}
    		}
        }
        int total = 0;
        for(int i = 0; i <= fuel; i++){
        	total = (total + dp[finish][i]) % mod;
        }
        return total;
    }
}
// @lc code=end
