/*
 * @lc app=leetcode id=223 lang=java
 *
 * [223] Rectangle Area
 *
 * https://leetcode.com/problems/rectangle-area/description/
 *
 * algorithms
 * Medium (36.82%)
 * Likes:    312
 * Dislikes: 595
 * Total Accepted:    97.1K
 * Total Submissions: 263.7K
 * Testcase Example:  '-3\n0\n3\n4\n0\n-1\n9\n2'
 *
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * 
 * Each rectangle is defined by its bottom left corner and top right corner as
 * shown in the figure.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 * Output: 45
 * 
 * Note:
 * 
 * Assume that the total area is never beyond the maximum possible value of
 * int.
 * 
 */

// @lc code=start
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C- A) * (D - B);
        int area2 = (G - E) * (H - F);
        int I = Math.max(A, E);
        int J = Math.max(B, F);
        int K = Math.min(C, G);
        int L = Math.min(D, H);
        int area3 = 0;
        if(I <= K && J <= L){
        	area3 = (K - I) * (L - J);
        }
        return area1 + area2 - area3;
    }
}
// @lc code=end
