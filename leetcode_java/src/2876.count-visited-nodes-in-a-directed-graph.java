/*
 * @lc app=leetcode id=2876 lang=java
 *
 * [2876] Count Visited Nodes in a Directed Graph
 *
 * https://leetcode.com/problems/count-visited-nodes-in-a-directed-graph/description/
 *
 * algorithms
 * Hard (32.15%)
 * Likes:    322
 * Dislikes: 6
 * Total Accepted:    10.8K
 * Total Submissions: 32.6K
 * Testcase Example:  '[1,2,0,0]'
 *
 * There is a directed graph consisting of n nodes numbered from 0 to n - 1 and
 * n directed edges.
 * 
 * You are given a 0-indexed array edges where edges[i] indicates that there is
 * an edge from node i to node edges[i].
 * 
 * Consider the following process on the graph:
 * 
 * 
 * You start from a node x and keep visiting other nodes through edges until
 * you reach a node that you have already visited before on this same
 * process.
 * 
 * 
 * Return an array answer where answer[i] is the number of different nodes that
 * you will visit if you perform the process starting from node i.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [1,2,0,0]
 * Output: [3,3,3,4]
 * Explanation: We perform the process starting from each node in the following
 * way:
 * - Starting from node 0, we visit the nodes 0 -> 1 -> 2 -> 0. The number of
 * different nodes we visit is 3.
 * - Starting from node 1, we visit the nodes 1 -> 2 -> 0 -> 1. The number of
 * different nodes we visit is 3.
 * - Starting from node 2, we visit the nodes 2 -> 0 -> 1 -> 2. The number of
 * different nodes we visit is 3.
 * - Starting from node 3, we visit the nodes 3 -> 0 -> 1 -> 2 -> 0. The number
 * of different nodes we visit is 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [1,2,3,4,0]
 * Output: [5,5,5,5,5]
 * Explanation: Starting from any node we can visit every node in the graph in
 * the process.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == edges.length
 * 2 <= n <= 10^5
 * 0 <= edges[i] <= n - 1
 * edges[i] != i
 * 
 * 
 */

// @lc code=start
class Solution {
    int[] ans;
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        ans = new int[n];
        Arrays.fill(ans, -1);
        boolean[] seen = new boolean[n];
        for(int i = 0; i < n; i++){
            if(ans[i] == -1){
                int entry = findCycleEntry(i, edges, seen);
                if(ans[entry] == -1){
                    fillCycle(entry, edges);
                }
                dfs(i, edges);   
            }
        }
        return ans;
    }

    int findCycleEntry(int start, List<Integer> edges, boolean[] seen){
        int u = start;
        while(!seen[u]){
            seen[u] = true;
            u = edges.get(u);
        }
        return u;
    }

    void fillCycle(int entry, List<Integer> edges){
        int u = edges.get(entry);
        int depth = 1;
        while(u != entry){
            depth++;
            u = edges.get(u);
        }
        ans[entry] = depth;
        u = edges.get(entry);
        while(u != entry){
            ans[u] = depth;
            u = edges.get(u);
        }
    }

    int dfs(int u, List<Integer> edges){
        if(ans[u] == -1){
            ans[u] = dfs(edges.get(u), edges) + 1;
        }
        return ans[u];
    }

    // void fillChain(int u, List<Integer> edges){
    //     Deque<Integer> stk = new ArrayDeque<>();
    //     while(ans[u] == -1){
    //         stk.push(u);
    //         u = edges.get(u);
    //     }
    //     int depth = ans[u];
    //     while(!stk.isEmpty()){
    //         ans[stk.pop()] = ++depth;
    //     }
    // }
}
// @lc code=end
