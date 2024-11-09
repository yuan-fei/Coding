/*
 * @lc app=leetcode id=2940 lang=java
 *
 * [2940] Find Building Where Alice and Bob Can Meet
 *
 * https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/description/
 *
 * algorithms
 * Hard (33.72%)
 * Likes:    239
 * Dislikes: 4
 * Total Accepted:    7.4K
 * Total Submissions: 21.3K
 * Testcase Example:  '[6,4,8,5,2,7]\n[[0,1],[0,3],[2,4],[3,4],[2,2]]'
 *
 * You are given a 0-indexed array heights of positive integers, where
 * heights[i] represents the height of the i^th building.
 * 
 * If a person is in building i, they can move to any other building j if and
 * only if i < j and heights[i] < heights[j].
 * 
 * You are also given another array queries where queries[i] = [ai, bi]. On the
 * i^th query, Alice is in building ai while Bob is in building bi.
 * 
 * Return an array ans where ans[i] is the index of the leftmost building where
 * Alice and Bob can meet on the i^th query. If Alice and Bob cannot move to a
 * common building on query i, set ans[i] to -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
 * Output: [2,5,-1,5,2]
 * Explanation: In the first query, Alice and Bob can move to building 2 since
 * heights[0] < heights[2] and heights[1] < heights[2]. 
 * In the second query, Alice and Bob can move to building 5 since heights[0] <
 * heights[5] and heights[3] < heights[5]. 
 * In the third query, Alice cannot meet Bob since Alice cannot move to any
 * other building.
 * In the fourth query, Alice and Bob can move to building 5 since heights[3] <
 * heights[5] and heights[4] < heights[5].
 * In the fifth query, Alice and Bob are already in the same building.  
 * For ans[i] != -1, It can be shown that ans[i] is the leftmost building where
 * Alice and Bob can meet.
 * For ans[i] == -1, It can be shown that there is no building where Alice and
 * Bob can meet.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: heights = [5,3,8,2,6,1,4,6], queries =
 * [[0,7],[3,5],[5,2],[3,0],[1,6]]
 * Output: [7,6,-1,4,6]
 * Explanation: In the first query, Alice can directly move to Bob's building
 * since heights[0] < heights[7].
 * In the second query, Alice and Bob can move to building 6 since heights[3] <
 * heights[6] and heights[5] < heights[6].
 * In the third query, Alice cannot meet Bob since Bob cannot move to any other
 * building.
 * In the fourth query, Alice and Bob can move to building 4 since heights[3] <
 * heights[4] and heights[0] < heights[4].
 * In the fifth query, Alice can directly move to Bob's building since
 * heights[1] < heights[6].
 * For ans[i] != -1, It can be shown that ans[i] is the leftmost building where
 * Alice and Bob can meet.
 * For ans[i] == -1, It can be shown that there is no building where Alice and
 * Bob can meet.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= heights.length <= 5 * 10^4
 * 1 <= heights[i] <= 10^9
 * 1 <= queries.length <= 5 * 10^4
 * queries[i] = [ai, bi]
 * 0 <= ai, bi <= heights.length - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = queries.length;
        int[][] queriesExtended = new int[n][4];
        for(int i = 0; i < n; i++){
            queriesExtended[i][0] = i;
            queriesExtended[i][1] = Math.max(queries[i][0], queries[i][1]);
            queriesExtended[i][2] = Math.min(queries[i][0], queries[i][1]);
            queriesExtended[i][3] = Math.max(heights[queries[i][0]], heights[queries[i][1]]);
        }
        Arrays.sort(queriesExtended, Comparator.comparing(q -> -q[1]));
        TreeMap<Integer, Integer> valueToId = new TreeMap<>();
        int cur = heights.length - 1;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for(int[] q : queriesExtended){
            while(cur >= q[1]){
                while(!valueToId.isEmpty() && heights[cur] >= valueToId.firstKey()){
                    valueToId.remove(valueToId.firstKey());
                }
                valueToId.put(heights[cur], cur);
                cur--;
            }
            
            if(q[1] == q[2] || heights[q[1]] > heights[q[2]]){
                ans[q[0]] = q[1];
            }
            else {
                Integer higherKey = valueToId.higherKey(q[3]);
                if(higherKey != null){
                    ans[q[0]] = valueToId.get(higherKey);
                }
            }
            // System.out.println(Arrays.asList(Arrays.toString(q), ans[q[0]]));
            // System.out.println(valueToId);
            
        }
        return ans;
    }
}
// @lc code=end
