/*
 * @lc app=leetcode id=546 lang=java
 *
 * [546] Remove Boxes
 *
 * https://leetcode.com/problems/remove-boxes/description/
 *
 * algorithms
 * Hard (43.37%)
 * Likes:    703
 * Dislikes: 50
 * Total Accepted:    16.2K
 * Total Submissions: 37.4K
 * Testcase Example:  '[1,3,2,2,2,3,4,3,1]\r'
 *
 * Given several boxes with different colors represented by different positive
 * numbers.
 * You may experience several rounds to remove boxes until there is no box
 * left. Each time you can choose some continuous boxes with the same color
 * (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1] 
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
 * ----> [1, 3, 3, 3, 1] (1*1=1 points) 
 * ----> [1, 1] (3*3=9 points) 
 * ----> [] (2*2=4 points)
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] mem = new int[n][n][n];
        return dfs(0, 0, n-1, boxes, mem);      
    }
    
    private int dfs(int streak, int l, int r, int[] boxes, int[][][] mem) {
        if(l>r) return 0;
        if(mem[streak][l][r]!=0) return mem[streak][l][r];
        // while(l<r && boxes[l]==boxes[l+1]) {streak++; l++; } //adjacent boxes with same color should be removed together, same as #1
        int max = (streak+1)*(streak+1) + dfs(0, l+1, r, boxes, mem);
        for(int i=l+1;i<=r;i++) 
            if(boxes[i] == boxes[l]) 
                max = Math.max(max, dfs(0, l+1, i-1, boxes, mem) + dfs(streak+1, i, r, boxes, mem));
        return mem[streak][l][r]=max;
    }
}
// @lc code=end
