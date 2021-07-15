/*
 * @lc app=leetcode id=1931 lang=java
 *
 * [1931] Painting a Grid With Three Different Colors
 *
 * https://leetcode.com/problems/painting-a-grid-with-three-different-colors/description/
 *
 * algorithms
 * Hard (53.99%)
 * Likes:    117
 * Dislikes: 6
 * Total Accepted:    2.8K
 * Total Submissions: 5.3K
 * Testcase Example:  '1\n1'
 *
 * You are given two integers m and n. Consider an m x n grid where each cell
 * is initially white. You can paint each cell red, green, or blue. All cells
 * must be painted.
 * 
 * Return the number of ways to color the grid with no two adjacent cells
 * having the same color. Since the answer can be very large, return it modulo
 * 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: m = 1, n = 1
 * Output: 3
 * Explanation: The three possible colorings are shown in the image above.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: m = 1, n = 2
 * Output: 6
 * Explanation: The six possible colorings are shown in the image above.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: m = 5, n = 5
 * Output: 580986
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= m <= 5
 * 1 <= n <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int colorTheGrid(int m, int n) {
        int MOD = (int)1e9 + 7;
        List<int[]> states = new ArrayList<int[]>();
        dfs(m, 0, new int[m], states);
        int l = states.size();
        boolean[][] mat = new boolean[l][l];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < l; j++){
                int[] s1 = states.get(i);
                int[] s2 = states.get(j);
                boolean compatible = true;
                for(int k = 0; k < m; k++){
                    if(s1[k] == s2[k]){
                        compatible = false;
                        break;
                    }
                }
                mat[i][j] = compatible;
            }
        } 
        // for(int i = 0; i < states.size(); i++){
        //     System.out.println(Arrays.toString(states.get(i)));    
        // }
        int[] dp = new int[l];
        Arrays.fill(dp, 1);
        for(int x = 1; x < n; x++){
            int[] newDp = new int[l];
            for(int i = 0; i < l; i++){
                for(int j = 0; j < l; j++){
                    if(mat[i][j]){
                        newDp[j] += dp[i];
                        newDp[j] %= MOD;
                    }
                }
            }
            dp = newDp;
        }
        int sum = 0;
        for(int i = 0; i < dp.length; i++){
            sum += dp[i];
            sum %= MOD;
        }
        return sum;
    }

    void dfs(int m, int pos, int[] cur, List<int[]> states){
        if(m == pos){
            states.add(Arrays.copyOf(cur, cur.length));
            return;
        }
        for(int i = 0; i < 3; i++){
            if(pos == 0 || cur[pos - 1] != i){
                int t = cur[pos];
                cur[pos] = i;
                dfs(m, pos + 1, cur, states);
                cur[pos] = t;
            }
        }
    }
}
// @lc code=end
