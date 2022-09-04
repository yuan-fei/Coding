/*
 * @lc app=leetcode id=2392 lang=java
 *
 * [2392] Build a Matrix With Conditions
 *
 * https://leetcode.com/problems/build-a-matrix-with-conditions/description/
 *
 * algorithms
 * Hard (57.58%)
 * Likes:    462
 * Dislikes: 2
 * Total Accepted:    8.1K
 * Total Submissions: 14.1K
 * Testcase Example:  '3\n[[1,2],[3,2]]\n[[2,1],[3,2]]'
 *
 * You are given a positive integer k. You are also given:
 * 
 * 
 * a 2D integer array rowConditions of size n where rowConditions[i] = [abovei,
 * belowi], and
 * a 2D integer array colConditions of size m where colConditions[i] = [lefti,
 * righti].
 * 
 * 
 * The two arrays contain integers from 1 to k.
 * 
 * You have to build a k x k matrix that contains each of the numbers from 1 to
 * k exactly once. The remaining cells should have the value 0.
 * 
 * The matrix should also satisfy the following conditions:
 * 
 * 
 * The number abovei should appear in a row that is strictly above the row at
 * which the number belowi appears for all i from 0 to n - 1.
 * The number lefti should appear in a column that is strictly left of the
 * column at which the number righti appears for all i from 0 to m - 1.
 * 
 * 
 * Return any matrix that satisfies the conditions. If no answer exists, return
 * an empty matrix.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
 * Output: [[3,0,0],[0,0,1],[0,2,0]]
 * Explanation: The diagram above shows a valid example of a matrix that
 * satisfies all the conditions.
 * The row conditions are the following:
 * - Number 1 is in row 1, and number 2 is in row 2, so 1 is above 2 in the
 * matrix.
 * - Number 3 is in row 0, and number 2 is in row 2, so 3 is above 2 in the
 * matrix.
 * The column conditions are the following:
 * - Number 2 is in column 1, and number 1 is in column 2, so 2 is left of 1 in
 * the matrix.
 * - Number 3 is in column 0, and number 2 is in column 1, so 3 is left of 2 in
 * the matrix.
 * Note that there may be multiple correct answers.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions =
 * [[2,1]]
 * Output: []
 * Explanation: From the first two conditions, 3 has to be below 1 but the
 * third conditions needs 3 to be above 1 to be satisfied.
 * No matrix can satisfy all the conditions, so we return the empty matrix.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= k <= 400
 * 1 <= rowConditions.length, colConditions.length <= 10^4
 * rowConditions[i].length == colConditions[i].length == 2
 * 1 <= abovei, belowi, lefti, righti <= k
 * abovei != belowi
 * lefti != righti
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<Integer>[] adjRow = buildAdj(rowConditions, k);
        List<Integer>[] adjCol = buildAdj(colConditions, k);
        List<Integer> rowOrder = topoSort(adjRow, k);
        List<Integer> colOrder = topoSort(adjCol, k);
        if(rowOrder == null || colOrder == null){
            return new int[0][0];
        }
        Map<Integer, Integer> m = new HashMap();
        for (int i = 0; i < colOrder.size(); i++) {
            m.put(colOrder.get(i), i);
        }
        int[][] ans = new int[k][k];
        for(int i = 0; i < k; i++){
            ans[i][m.get(rowOrder.get(i))] = rowOrder.get(i);
        }
        return ans;
    }

    List<Integer>[] buildAdj(int[][] a, int k){
        List<Integer>[] adj = new List[k + 1];
        for(int i = 0; i <= k; i++){
            adj[i] = new ArrayList<Integer>();
        }
        for(int[] e : a){
            adj[e[1]].add(e[0]);
        }
        return adj;
    }

    List<Integer> topoSort(List<Integer>[] adj, int k){
        int[] state = new int[k + 1];
        List<Integer> output = new ArrayList<>();
        for(int i = 1; i <= k; i++){
            if(state[i] != 2){
                if(dfs(i, adj, state, output)){
                    return null;
                }
            }
        }
        return output;
    }

    boolean dfs(int u, List<Integer>[] adj, int[] state, List<Integer> output){
        state[u] = 1;
        for(int v : adj[u]){
            if(state[v] == 0){
                if(dfs(v, adj, state, output)){
                    return true;
                }
            }
            else if(state[v] == 1){
                return true;
            }
            
        }
        output.add(u);
        state[u] = 2;
        return false;
    }
}
// @lc code=end
