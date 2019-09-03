/*
 * @lc app=leetcode id=864 lang=java
 *
 * [864] Shortest Path to Get All Keys
 *
 * https://leetcode.com/problems/shortest-path-to-get-all-keys/description/
 *
 * algorithms
 * Hard (36.97%)
 * Total Accepted:    5.6K
 * Total Submissions: 15.1K
 * Testcase Example:  '["@.a.#","###.#","b.A.B"]'
 *
 * We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@"
 * is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are
 * locks.
 * 
 * We start at the starting point, and one move consists of walking one space
 * in one of the 4 cardinal directions.  We cannot walk outside the grid, or
 * walk into a wall.  If we walk over a key, we pick it up.  We can't walk over
 * a lock unless we have the corresponding key.
 * 
 * For some 1 <= K <= 6, there is exactly one lowercase and one uppercase
 * letter of the first K letters of the English alphabet in the grid.  This
 * means that there is exactly one key for each lock, and one lock for each
 * key; and also that the letters used to represent the keys and locks were
 * chosen in the same order as the English alphabet.
 * 
 * Return the lowest number of moves to acquire all keys.  If it's impossible,
 * return -1.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: ["@.a.#","###.#","b.A.B"]
 * Output: 8
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ["@..aA","..B#.","....b"]
 * Output: 6
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= grid.length <= 30
 * 1 <= grid[0].length <= 30
 * grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
 * The number of keys is in [1, 6].  Each key has a different letter and opens
 * exactly one lock.
 * 
 * 
 * 
 */
class Solution {
    public int shortestPathAllKeys(String[] grid) {
    	int N = grid.length;
    	int M = grid[0].length();
    	boolean[][][] visited = new boolean[30][30][64]; 
    	Queue<State> q = new LinkedList<>();
    	int K = 0;
    	for (int i = 0; i<N; i++) {
    		for(int j = 0; j < M; j++){
    			if(grid[i].charAt(j) == '@'){
    				q.offer(new State(i, j, 0));
    				visited[i][j][0] = true;
    			}
    			if(grid[i].charAt(j) >= 'a' && grid[i].charAt(j)<='f'){
    				K++;
    			}
    		}
    	}

    	int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    	int step=0;
    	while(!q.isEmpty()){
    		// System.out.println(q);
    		for(int c = q.size(); c>0; c--){
	    		State cur = q.poll();
	    		// System.out.println(cur);
	    		if(cur.s==(1<<K)-1){
	    			return step;
	    		}
	    		for(int i = 0; i < dir.length; i++){
	    			int x=cur.x+dir[i][0];
	    			int y=cur.y+dir[i][1];
	    			// System.out.println(i);
	    			if(x>=0 && y>=0 && x<N && y<M && grid[x].charAt(y)!='#'){

	    				int newS = cur.s;
	    				if(grid[x].charAt(y) >= 'a' && grid[x].charAt(y)<='f' ){
	    					newS |= (1<<(grid[x].charAt(y) - 'a'));	
	    				}
	    				if(grid[x].charAt(y) < 'A' || grid[x].charAt(y)>'F' || (cur.s&(1<<(grid[x].charAt(y) - 'a')))!=0){
	    					if(!visited[x][y][newS]){
	    						State s = new State(x, y, newS);
	    						// System.out.println(s+"-"+step);
	    						q.offer(s);
	    						visited[x][y][newS] = true;
    						}	
	    				}
	    			}
	    		}	
    		}
    		step++;
    	}
    	return -1;
    }

    class State{
    	int x;
    	int y;
    	int s;
    	State(int xx, int yy, int ss){
    		x =xx;
    		y=yy;
    		s=ss;
    	}

    	public String toString(){
    		return "["+x+","+y+"="+s+"]";
    	}
    }
}
