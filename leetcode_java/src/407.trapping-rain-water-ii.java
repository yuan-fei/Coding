/*
 * @lc app=leetcode id=407 lang=java
 *
 * [407] Trapping Rain Water II
 *
 * https://leetcode.com/problems/trapping-rain-water-ii/description/
 *
 * algorithms
 * Hard (39.75%)
 * Total Accepted:    29.6K
 * Total Submissions: 74.5K
 * Testcase Example:  '[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]'
 *
 * Given an m x n matrix of positive integers representing the height of each
 * unit cell in a 2D elevation map, compute the volume of water it is able to
 * trap after raining.
 * 
 * 
 * 
 * Note:
 * 
 * Both m and n are less than 110. The height of each unit cell is greater than
 * 0 and is less than 20,000.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Given the following 3x6 height map:
 * [
 * ⁠ [1,4,3,1,3,2],
 * ⁠ [3,2,1,3,2,4],
 * ⁠ [2,3,3,2,3,1]
 * ]
 * 
 * Return 4.
 * 
 * 
 * 
 * 
 * The above image represents the elevation map
 * [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
 * 
 * 
 * 
 * 
 * 
 * After the rain, water is trapped between the blocks. The total volume of
 * water trapped is 4.
 * 
 */
class Solution {
    public int trapRainWater(int[][] heightMap) {
    	if(heightMap.length==0){
    		return 0;
    	}
    	int N = heightMap.length;
    	int M = heightMap[0].length;
    	PriorityQueue<Cell> q = new PriorityQueue<>((a, b)->Integer.compare(a.h, b.h));
    	boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
        	q.offer(new Cell(i, 0, heightMap[i][0]));
        	q.offer(new Cell(i, M-1, heightMap[i][M-1]));
        	visited[i][0] = true;
        	visited[i][M-1] = true;
        }
        for (int i = 1; i < M-1; i++) {
        	q.offer(new Cell(0, i, heightMap[0][i]));
        	q.offer(new Cell(N-1, i, heightMap[N-1][i]));
        	visited[0][i] = true;
        	visited[N-1][i] = true;	
        }
        int[] dx = new int[]{0,0,-1,1};
        int[] dy = new int[]{-1,1,0,0};
        int ans = 0;
        while(!q.isEmpty()){
        	Cell cur = q.poll();
        	for(int i = 0; i < 4; i++){
        		int x = cur.x+dx[i];
        		int y = cur.y+dy[i];
        		if(x>=0 && y>=0 && x<N && y<M && !visited[x][y]){
        			if(cur.h > heightMap[x][y]){
        				ans += cur.h - heightMap[x][y];
        			}
        			q.offer(new Cell(x, y, Math.max(cur.h, heightMap[x][y])));
        			visited[x][y] = true;
        		}
        	}
        }
        return ans;
    }

    
    static class Cell{
    	int x;
    	int y;
    	int h;
    	Cell(int xx, int yy, int hh){
    		x=xx;
    		y=yy;
    		h=hh;
    	}
    }
    
}
