/*
 * @lc app=leetcode id=913 lang=java
 *
 * [913] Cat and Mouse
 *
 * https://leetcode.com/problems/cat-and-mouse/description/
 *
 * algorithms
 * Hard (34.95%)
 * Likes:    772
 * Dislikes: 132
 * Total Accepted:    16.7K
 * Total Submissions: 47.9K
 * Testcase Example:  '[[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]'
 *
 * A game on an undirected graph is played by two players, Mouse and Cat, who
 * alternate turns.
 * 
 * The graph is given as follows: graph[a] is a list of all nodes b such that
 * ab is an edge of the graph.
 * 
 * The mouse starts at node 1 and goes first, the cat starts at node 2 and goes
 * second, and there is a hole at node 0.
 * 
 * During each player's turn, they must travel along one edge of the graph that
 * meets where they are.  For example, if the Mouse is at node 1, it must
 * travel to any node in graph[1].
 * 
 * Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
 * 
 * Then, the game can end in three ways:
 * 
 * 
 * If ever the Cat occupies the same node as the Mouse, the Cat wins.
 * If ever the Mouse reaches the Hole, the Mouse wins.
 * If ever a position is repeated (i.e., the players are in the same position
 * as a previous turn, and it is the same player's turn to move), the game is a
 * draw.
 * 
 * 
 * Given a graph, and assuming both players play optimally, return
 * 
 * 
 * 1 if the mouse wins the game,
 * 2 if the cat wins the game, or
 * 0 if the game is a draw.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * Output: 0
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: graph = [[1,3],[0],[3],[0,2]]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= graph.length <= 50
 * 1 <= graph[i].length < graph.length
 * 0 <= graph[i][j] < graph.length
 * graph[i][j] != i
 * graph[i] is unique.
 * The mouse and the cat can always move. 
 * 
 * 
 */

// @lc code=start
class Solution {
    int N;
    public int catMouseGame(int[][] graph) {
        N = graph.length;
        int total = N * N * 2;
        int[] state = new int[total];
        Arrays.fill(state, -1);
        Set<Integer>[] adjUpstream = new Set[total];
        Set<Integer>[] adjDownstream = new Set[total];
        for(int i = 0; i < total; i++){
            adjUpstream[i] = new HashSet<>();
            adjDownstream[i] = new HashSet<>();
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int c1 = encode(new int[]{i, j, 0});
                if(i == 0){
                    state[c1] = 1;
                    q.offer(c1);
                }
                else if(i == j){
                    state[c1] = 0;
                    q.offer(c1);
                }
                for(int v : graph[i]){
                    int c = encode(new int[]{v, j, 1});
                    adjDownstream[c1].add(c);
                    adjUpstream[c].add(c1);
                }
                
                int c2 = encode(new int[]{i, j, 1});
                if(i == 0){
                    state[c2] = 0;
                    q.offer(c2);
                }
                else if(i == j){
                    state[c2] = 1;
                    q.offer(c2);
                }
                for(int v : graph[j]){
                    if(v != 0){
                        int c = encode(new int[]{i, v, 0});
                        adjDownstream[c2].add(c); 
                        adjUpstream[c].add(c2);
                    }
                }
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int v : adjUpstream[cur]){
                if(state[v] == -1){
                    if(state[cur] == 0){
                        q.offer(v);
                        state[v] = 1;
                    }
                    else{
                        adjDownstream[v].remove(cur);
                        if(adjDownstream[v].isEmpty()){
                            q.offer(v);
                            state[v] = 0;
                        }
                    }
                }
            }
        }
        int initial = encode(new int[]{1, 2, 0});
        if(state[initial] == 0){
            return 2;
        }
        else if(state[initial] == 1){
            return 1;
        }
        return 0;
    }

    int encode(int[] state){
        return state[2] * N * N + state[0] * N + state[1];
    }

    
}
// @lc code=end
