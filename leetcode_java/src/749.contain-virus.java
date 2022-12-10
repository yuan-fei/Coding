/*
 * @lc app=leetcode id=749 lang=java
 *
 * [749] Contain Virus
 *
 * https://leetcode.com/problems/contain-virus/description/
 *
 * algorithms
 * Hard (50.90%)
 * Likes:    304
 * Dislikes: 418
 * Total Accepted:    9.8K
 * Total Submissions: 19.3K
 * Testcase Example:  '[[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]'
 *
 * A virus is spreading rapidly, and your task is to quarantine the infected
 * area by installing walls.
 * 
 * The world is modeled as an m x n binary grid isInfected, where
 * isInfected[i][j] == 0 represents uninfected cells, and isInfected[i][j] == 1
 * represents cells contaminated with the virus. A wall (and only one wall) can
 * be installed between any two 4-directionally adjacent cells, on the shared
 * boundary.
 * 
 * Every night, the virus spreads to all neighboring cells in all four
 * directions unless blocked by a wall. Resources are limited. Each day, you
 * can install walls around only one region (i.e., the affected area
 * (continuous block of infected cells) that threatens the most uninfected
 * cells the following night). There will never be a tie.
 * 
 * Return the number of walls used to quarantine all the infected regions. If
 * the world will become fully infected, return the number of walls used.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: isInfected =
 * [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
 * Output: 10
 * Explanation: There are 2 contaminated regions.
 * On the first day, add 5 walls to quarantine the viral region on the left.
 * The board after the virus spreads is:
 * 
 * On the second day, add 5 walls to quarantine the viral region on the right.
 * The virus is fully contained.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 4
 * Explanation: Even though there is only one cell saved, there are 4 walls
 * built.
 * Notice that walls are only built on the shared boundary of two different
 * cells.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: isInfected =
 * [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
 * Output: 13
 * Explanation: The region on the left only builds two new walls.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == isInfected.length
 * n == isInfected[i].length
 * 1 <= m, n <= 50
 * isInfected[i][j] is either 0 or 1.
 * There is always a contiguous viral region throughout the described process
 * that will infect strictly more uncontaminated squares in the next round.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] isInfected;
    int m;
    int n;
    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public int containVirus(int[][] isInfected) {
        this.isInfected = isInfected;
        m = isInfected.length;
        n = isInfected[0].length;
        int ans = 0;
        while(true){
            int walls = onePass();
            ans += walls;
            if(walls == 0){
                return ans;
            }
        }
    }

    int onePass(){
        // scan to generate pos -> region
        int[][] pos2Region = new int[m][n];
        int regionId = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(isInfected[i][j] == 1 && pos2Region[i][j] == 0){
                    markRegion(i, j, ++regionId, pos2Region);
                }
            }
        }
        if(regionId == 0){
            return 0;
        }
        // scan to generate region -> infected count
        int[] region2Infected = new int[regionId + 1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(isInfected[i][j] == 0){
                    countInfected(i, j, region2Infected, pos2Region);
                }
            }
        }
        
        int maxRegionId = IntStream.range(0, region2Infected.length).boxed().max(Comparator.comparing(i -> region2Infected[i])).get();
        int walls = 0;
        // System.out.println(Arrays.toString(region2Infected));
        // System.out.println(Arrays.deepToString(isInfected));
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(pos2Region[i][j] == maxRegionId){
                    walls += countWalls(i, j);
                    isInfected[i][j] = -1;
                }
            }
        }
        // mark infected cells
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(isInfected[i][j] == 0){
                    markInfected(i, j, pos2Region);
                }
            }
        }
        // System.out.println(walls);
        return walls;
    }



    void markRegion(int r, int c, int k, int[][] pos2Region){
        pos2Region[r][c] = k;
        for(int[] dir : dirs){
            int newR = r + dir[0];
            int newC = c + dir[1];
            if(0 <= newR && newR < m && 0 <= newC && newC < n && isInfected[newR][newC] == 1 && pos2Region[newR][newC] == 0){
                markRegion(newR, newC, k, pos2Region);
            }
        }
    }

    void countInfected(int r, int c, int[] region2Infected, int[][] pos2Region){
        Set<Integer> s = new HashSet<>();
        for(int[] dir : dirs){
            int newR = r + dir[0];
            int newC = c + dir[1];
            if(0 <= newR && newR < m && 0 <= newC && newC < n && isInfected[newR][newC] == 1){
                s.add(pos2Region[newR][newC]);
            }
        }
        for(int regionId : s){
            region2Infected[regionId]++; 
        }
    }

    int countWalls(int r, int c){
        int walls = 0;
        for(int[] dir : dirs){
            int newR = r + dir[0];
            int newC = c + dir[1];
            if(0 <= newR && newR < m && 0 <= newC && newC < n && isInfected[newR][newC] == 0){
                walls++; 
            }
        }
        return walls;
    }

    void markInfected(int r, int c, int[][] pos2Region){
        for(int[] dir : dirs){
            int newR = r + dir[0];
            int newC = c + dir[1];
            if(0 <= newR && newR < m && 0 <= newC && newC < n && isInfected[newR][newC] == 1 && pos2Region[newR][newC] != 0){
                isInfected[r][c] = 1;
                return; 
            }
        }
    }
}
// @lc code=end
