/*
 * @lc app=leetcode id=2467 lang=java
 *
 * [2467] Most Profitable Path in a Tree
 *
 * https://leetcode.com/problems/most-profitable-path-in-a-tree/description/
 *
 * algorithms
 * Medium (44.35%)
 * Likes:    138
 * Dislikes: 18
 * Total Accepted:    3.2K
 * Total Submissions: 7.1K
 * Testcase Example:  '[[0,1],[1,2],[1,3],[3,4]]\n3\n[-2,4,2,-4,6]'
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at
 * node 0. You are given a 2D integer array edges of length n - 1 where
 * edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi
 * in the tree.
 * 
 * At every node i, there is a gate. You are also given an array of even
 * integers amount, where amount[i] represents:
 * 
 * 
 * the price needed to open the gate at node i, if amount[i] is negative,
 * or,
 * the cash reward obtained on opening the gate at node i, otherwise.
 * 
 * 
 * The game goes on as follows:
 * 
 * 
 * Initially, Alice is at node 0 and Bob is at node bob.
 * At every second, Alice and Bob each move to an adjacent node. Alice moves
 * towards some leaf node, while Bob moves towards node 0.
 * For every node along their path, Alice and Bob either spend money to open
 * the gate at that node, or accept the reward. Note that:
 * 
 * If the gate is already open, no price will be required, nor will there be
 * any cash reward.
 * If Alice and Bob reach the node simultaneously, they share the price/reward
 * for opening the gate there. In other words, if the price to open the gate is
 * c, then both Alice and Bob pay c / 2 each. Similarly, if the reward at the
 * gate is c, both of them receive c / 2 each.
 * 
 * 
 * If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches
 * node 0, he stops moving. Note that these events are independent of each
 * other.
 * 
 * 
 * Return the maximum net income Alice can have if she travels towards the
 * optimal leaf node.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
 * Output: 6
 * Explanation: 
 * The above diagram represents the given tree. The game goes as follows:
 * - Alice is initially on node 0, Bob on node 3. They open the gates of their
 * respective nodes.
 * ⁠ Alice's net income is now -2.
 * - Both Alice and Bob move to node 1. 
 * Since they reach here simultaneously, they open the gate together and share
 * the reward.
 * Alice's net income becomes -2 + (4 / 2) = 0.
 * - Alice moves on to node 3. Since Bob already opened its gate, Alice's
 * income remains unchanged.
 * Bob moves on to node 0, and stops moving.
 * - Alice moves on to node 4 and opens the gate there. Her net income becomes
 * 0 + 6 = 6.
 * Now, neither Alice nor Bob can make any further moves, and the game ends.
 * It is not possible for Alice to get a higher net income.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [[0,1]], bob = 1, amount = [-7280,2350]
 * Output: -7280
 * Explanation: 
 * Alice follows the path 0->1 whereas Bob follows the path 1->0.
 * Thus, Alice opens the gate at node 0 only. Hence, her net income is
 * -7280. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 * 1 <= bob < n
 * amount.length == n
 * amount[i] is an even integer in the range [-10^4, 10^4].
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer> b2a = new ArrayList<>();
    int maxSum = Integer.MIN_VALUE;
    
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < n; i++) graph.put(i, new HashSet<>());
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(v).add(u);
            graph.get(u).add(v);
        }
        
        // 1. find path
        dfs(bob, 0, graph, new ArrayList<Integer>(){{add(bob); }}, new HashSet<Integer>(){{add(bob); }});

        // 2. modify tree
        for (int i = 0; i < b2a.size() / 2; i++) {
            amount[b2a.get(i)] = 0;
        }
        if (b2a.size() % 2 != 0) {
            int m = b2a.get(b2a.size() / 2);
            amount[m] /= 2;
        }
        
        // 3. get result
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        maxPathSum(0, graph, amount, visited, amount[0]);
        return maxSum;
    }
    
    private boolean dfs(int root, int target,  Map<Integer, Set<Integer>> graph, List<Integer> currPath, Set<Integer> visited) {
        if (root == target) {
            b2a = new ArrayList<>(currPath);
            return true;
        }
        
        for (int neighbor : graph.get(root)) {
            if (visited.contains(neighbor)) continue;
            visited.add(neighbor);
            currPath.add(neighbor);
            
            if (dfs(neighbor, target, graph, currPath, visited)) return true;
            
            currPath.remove(currPath.size() - 1);
            visited.remove(neighbor);
        }
        return false;
    }
    
    private void maxPathSum(int root, Map<Integer, Set<Integer>> graph, int[] amount, Set<Integer> visited, int currSum) {
        int cnt = 0;
        for (int child : graph.get(root)) {
            if (visited.contains(child)) continue;
            
            visited.add(child);
            maxPathSum(child, graph, amount, visited, currSum + amount[child]);
            visited.remove(child);
            cnt++;
            
        }
        // leafNode
        if (cnt == 0) maxSum = Math.max(maxSum, currSum);
        return;
    }
}
// @lc code=end
