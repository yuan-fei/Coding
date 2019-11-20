/*
 * @lc app=leetcode id=1263 lang=java
 *
 * [1263] Minimum Moves to Move a Box to Their Target Location
 *
 * https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/description/
 *
 * algorithms
 * Hard (28.89%)
 * Likes:    25
 * Dislikes: 1
 * Total Accepted:    643
 * Total Submissions: 2.2K
 * Testcase Example:  '[["#","#","#","#","#","#"],["#","T","#","#","#","#"],["#",".",".","B",".","#"],["#",".","#","#",".","#"],["#",".",".",".","S","#"],["#","#","#","#","#","#"]]'
 *
 * Storekeeper is a game, in which the player pushes boxes around in a
 * warehouse, trying to get them to target locations.
 * 
 * The game is represented by a grid of size n*m, where each element is a wall,
 * floor or a box.
 * 
 * Your task is move the box 'B' to the target position 'T' under the following
 * rules:
 * 
 * 
 * Player is represented by character 'S' and can move up, down, left, right in
 * the grid if its a floor (empy cell).
 * Floor is represented by character '.' that means free cell to walk.
 * Wall is represented by character '#' that means obstacle  (impossible to
 * walk there). 
 * There is only one box 'B' and one target cell 'T' in the grid.
 * The box can be moved to an adjacent free cell by standing next to the box
 * and then moving in the direction of the box. This is a push.
 * The player cannot walk through the box.
 * 
 * 
 * Return the minimum number of pushes to move the box to the target. If there
 * is no way to reach the target, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: grid = [["#","#","#","#","#","#"],
 * ⁠              ["#","T","#","#","#","#"],
 * ["#",".",".","B",".","#"],
 * ["#",".","#","#",".","#"],
 * ["#",".",".",".","S","#"],
 * ["#","#","#","#","#","#"]]
 * Output: 3
 * Explanation: We return only the number of times the box is pushed.
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [["#","#","#","#","#","#"],
 * ⁠              ["#","T","#","#","#","#"],
 * ["#",".",".","B",".","#"],
 * ["#","#","#","#",".","#"],
 * ["#",".",".",".","S","#"],
 * ["#","#","#","#","#","#"]]
 * Output: -1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [["#","#","#","#","#","#"],
 * ["#","T",".",".","#","#"],
 * ["#",".","#","B",".","#"],
 * ["#",".",".",".",".","#"],
 * ["#",".",".",".","S","#"],
 * ["#","#","#","#","#","#"]]
 * Output: 5
 * Explanation:  push the box down, left, left, up and up.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: grid = [["#","#","#","#","#","#","#"],
 * ["#","S","#",".","B","T","#"],
 * ["#","#","#","#","#","#","#"]]
 * Output: -1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length <= 20
 * 1 <= grid[i].length <= 20
 * grid contains only characters '.', '#',  'S' , 'T', or 'B'.
 * There is only one character 'S', 'B' and 'T' in the grid.
 * 
 * 
 */

// @lc code=start
class Solution {

	static class State{
		int[] box;
		int[] player;
		State(int[] box, int[] player){
			this.box = box;
			this.player = player;
		}

		public String toString(){
			return String.format("<{%d}, {%d}, {%d}, {%d}>", box[0], box[1], player[0], player[1]);
		}
	}

	int[] target = new int[2];
    public int minPushBox(char[][] grid) {
        final int[][][][] state = new int[20][20][20][20];
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        
		int m = grid.length;
    	int n = grid[0].length;
    	int[] box = new int[2];
    	int[] player = new int[2];
    	
        for(int i = 0; i < m; i++){
        	for(int j = 0; j < n; j++){
        		if(grid[i][j] == 'B'){
        			box = new int[]{i, j};
        		}
        		if(grid[i][j] == 'S'){
        			player = new int[]{i, j};
        		}
        		if(grid[i][j] == 'T'){
        			target = new int[]{i, j};
        		}
        	}
        }
        PriorityQueue<State> q = new PriorityQueue<>((a, b) -> Integer.compare(state[a.box[0]][a.box[1]][a.player[0]][a.player[1]] + dist(a.box), state[b.box[0]][b.box[1]][b.player[0]][b.player[1]] + dist(b.box)));
        state[box[0]][box[1]][player[0]][player[1]] = 1;
        q.offer(new State(box, player));
        while(!q.isEmpty()){
        	// System.out.println(q);
        	State cur = q.poll();
        	if(arrayEquals(cur.box, target)){
        		return state[cur.box[0]][cur.box[1]][cur.player[0]][cur.player[1]] - 1;
        	}
        	List<State> targetCandidates = new ArrayList<>();
			for(int i = 0; i < 4; i++){
    			int bx = cur.box[0] + dir[i][0];
    			int by = cur.box[1] + dir[i][1];
    			int px = cur.box[0] - dir[i][0];
    			int py = cur.box[1] - dir[i][1];
    			if(bx >= 0 && bx < m && by >= 0 && by < n && grid[bx][by] != '#'){
    				if(px >= 0 && px < m && py >= 0 && py < n && grid[px][py] != '#'){
    					if(state[bx][by][cur.box[0]][cur.box[1]] == 0){
							targetCandidates.add(new State(new int[]{bx, by}, new int[]{px, py}));
    					}
    				}
    				
    			}
    		}
    		List<State> reachableTargets = filterReachableTarget(grid, cur.box, cur.player, targetCandidates);
			for(State t : reachableTargets){
				state[t.box[0]][t.box[1]][cur.box[0]][cur.box[1]] = state[cur.box[0]][cur.box[1]][cur.player[0]][cur.player[1]] + 1;
				q.offer(new State(t.box, cur.box));
			}
        }
        return -1;
    }

    List<State> filterReachableTarget(char[][] grid, int[] box, int[] player, List<State> targets){
    	int m = grid.length;
    	int n = grid[0].length;
    	boolean[][] reacheable = new boolean[m][n];
    	int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(player);
    	reacheable[player[0]][player[1]] = true;
    	List<State> ret = new ArrayList<>();
    	while(!q.isEmpty()){
    		int[] cur = q.poll();
    		State targetFound = null;
    		for(State t : targets){
    			if(arrayEquals(cur, t.player)){
    				targetFound = t;
    				break;
    			}
    		}
    		if(targetFound != null){
    			ret.add(targetFound);
    		}
    		for(int i = 0; i < 4; i++){
    			int x = cur[0] + dir[i][0];
    			int y = cur[1] + dir[i][1];
    			int[] nxt = new int[]{x, y};
    			if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#' && !arrayEquals(nxt, box) && !reacheable[x][y]){
    				reacheable[x][y] = true;
    				q.offer(nxt);
    			}
    		}
    	}
    	return ret;
    }

    boolean arrayEquals(int[] a, int[] b){
    	for(int i = 0; i < a.length; i++){
    		if(a[i] != b[i]){
    			return false;
    		}
    	}
    	return true;
    }

    int dist(int[] a){
    	return Math.abs(a[0] - target[0]) + Math.abs(a[1] - target[1]);
    }
}
// @lc code=end
