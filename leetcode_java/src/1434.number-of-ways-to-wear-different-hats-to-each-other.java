/*
 * @lc app=leetcode id=1434 lang=java
 *
 * [1434] Number of Ways to Wear Different Hats to Each Other
 *
 * https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/description/
 *
 * algorithms
 * Hard (28.47%)
 * Likes:    98
 * Dislikes: 1
 * Total Accepted:    1.4K
 * Total Submissions: 5.1K
 * Testcase Example:  '[[3,4],[4,5],[5]]'
 *
 * There are n people and 40 types of hats labeled from 1 to 40.
 * 
 * Given a list of list of integers hats, where hats[i] is a list of all hats
 * preferred by the i-th person.
 * 
 * Return the number of ways that the n people wear different hats to each
 * other.
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: hats = [[3,4],[4,5],[5]]
 * Output: 1
 * Explanation: There is only one way to choose hats given the conditions. 
 * First person choose hat 3, Second person choose hat 4 and last one hat 5.
 * 
 * Example 2:
 * 
 * 
 * Input: hats = [[3,5,1],[3,5]]
 * Output: 4
 * Explanation: There are 4 ways to choose hats
 * (3,5), (5,3), (1,3) and (1,5)
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
 * Output: 24
 * Explanation: Each person can choose hats labeled from 1 to 4.
 * Number of Permutations of (1,2,3,4) = 24.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
 * Output: 111
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == hats.length
 * 1 <= n <= 10
 * 1 <= hats[i].length <= 40
 * 1 <= hats[i][j] <= 40
 * hats[i] contains a list of unique integers.
 * 
 */

// @lc code=start
class Solution {
	int mod = 1000000007;
    public int numberWays(List<List<Integer>> hats) {
    	int n = hats.size();
        int[] personMap = getPersonMap(hats);
        int[] dp = new int[1 << n];
        dp[0] = 1;
        for(int h = 1; h <= 40; h++){
        	int[] newDp = new int[1 << n];
        	for(int j = 0; j < (1 << n); j++){
        		newDp[j] = dp[j];
        		int both = j & personMap[h];
        		for(int i = 0; i < n; i++){
        			if(((both >> i) & 1) != 0){
        				newDp[j] = (newDp[j] + dp[j ^ (1 << i)]) % mod;
        			}
        		}
        	}
        	dp = newDp;
        }
        return dp[(1 << n) - 1];
    }

    private int[] getPersonMap(List<List<Integer>> hats){
    	int n = hats.size();
    	int[] map = new int[41];
    	for(int i = 0; i < hats.size(); i++){
    		for(int j = 0; j < hats.get(i).size(); j++){
    			int m = hats.get(i).get(j);
    			map[m] |= 1 << i;
    		}
    	}
    	return map;
    }
    
}
// @lc code=end
