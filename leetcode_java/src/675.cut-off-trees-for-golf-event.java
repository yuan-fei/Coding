/*
 * @lc app=leetcode id=675 lang=java
 *
 * [675] Cut Off Trees for Golf Event
 *
 * https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
 *
 * algorithms
 * Hard (35.58%)
 * Likes:    680
 * Dislikes: 405
 * Total Accepted:    43.7K
 * Total Submissions: 122.9K
 * Testcase Example:  '[[1,2,3],[0,0,4],[7,6,5]]'
 *
 * You are asked to cut off all the trees in a forest for a golf event. The
 * forest is represented as an m x n matrix. In this matrix:
 * 
 * 
 * 0 means the cell cannot be walked through.
 * 1 represents an empty cell that can be walked through.
 * A number greater than 1 represents a tree in a cell that can be walked
 * through, and this number is the tree's height.
 * 
 * 
 * In one step, you can walk in any of the four directions: north, east, south,
 * and west. If you are standing in a cell with a tree, you can choose whether
 * to cut it off.
 * 
 * You must cut off the trees in order from shortest to tallest. When you cut
 * off a tree, the value at its cell becomes 1 (an empty cell).
 * 
 * Starting from the point (0, 0), return the minimum steps you need to walk to
 * cut off all the trees. If you cannot cut off all the trees, return -1.
 * 
 * You are guaranteed that no two trees have the same height, and there is at
 * least one tree needs to be cut off.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
 * Output: 6
 * Explanation: Following the path above allows you to cut off the trees from
 * shortest to tallest in 6 steps.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
 * Output: -1
 * Explanation: The trees in the bottom row cannot be accessed as the middle
 * row is blocked.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
 * Output: 6
 * Explanation: You can follow the same path as Example 1 to cut off all the
 * trees.
 * Note that you can cut off the first tree at (0, 0) before making any
 * steps.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == forest.length
 * n == forest[i].length
 * 1 <= m, n <= 50
 * 0 <= forest[i][j] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
	List<List<Integer>> g;
	int n;
	int m;
	List<int[]> pos;
    public int cutOffTree(List<List<Integer>> forest) {
    	g = forest;
    	n = forest.size();
    	m = forest.get(0).size();
    	if(forest.get(0).get(0) == 0){
    		return -1;
    	}
        pos = new ArrayList<>();
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		if(forest.get(i).get(j) > 1){
        			pos.add(new int[]{i, j});
        		}
        	}
        }
        Collections.sort(pos, (a, b) -> Integer.compare(forest.get(a[0]).get(a[1]), forest.get(b[0]).get(b[1])));
        int[] pre = {0, 0};
        int ans = 0;
        for(int[] cur : pos){
        	int d = bfs(pre, cur);
        	if(d == -1){
        		return -1;
        	}
        	else{
        		ans += d;
        		forest.get(cur[0]).set(cur[1], 1);
        	}
        	pre = cur;
        }
        return ans;
    }

    int bfs(int[] src, int[] des){
    	Queue<int[]> q = new LinkedList<>();
    	boolean[][] seen = new boolean[n][m];
    	int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    	q.offer(src);
    	int step = 0;
    	while(!q.isEmpty()){
    		for(int x = q.size(); x > 0; x--){
    			int[] cur = q.poll();
    			if(cur[0] == des[0] && cur[1] == des[1]){
    				return step;
    			}
    			for(int[] dir : dirs){
    				int[] next = {cur[0] + dir[0], cur[1] + dir[1]};
    				if(0 <= next[0] && next[0] < n && 0 <= next[1] && next[1] < m && !seen[next[0]][next[1]] && g.get(next[0]).get(next[1]) > 0){
    					q.offer(next);
    					seen[next[0]][next[1]] = true;
    				}
    			}
    		}
    		step++;
    	}
    	return -1;
    }
}
// @lc code=end
