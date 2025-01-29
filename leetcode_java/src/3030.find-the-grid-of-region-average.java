/*
 * @lc app=leetcode id=3030 lang=java
 *
 * [3030] Find the Grid of Region Average
 *
 * https://leetcode.com/problems/find-the-grid-of-region-average/description/
 *
 * algorithms
 * Medium (43.57%)
 * Likes:    79
 * Dislikes: 127
 * Total Accepted:    10.5K
 * Total Submissions: 24.1K
 * Testcase Example:  '[[5,6,7,10],[8,9,10,10],[11,12,13,10]]\n3'
 *
 * You are given m x n grid image which represents a grayscale image, where
 * image[i][j] represents a pixel with intensity in the range [0..255]. You are
 * also given a non-negative integer threshold.
 * 
 * Two pixels are adjacent if they share an edge.
 * 
 * A region is a 3 x 3 subgrid where the absolute difference in intensity
 * between any two adjacent pixels is less than or equal to threshold.
 * 
 * All pixels in a region belong to that region, note that a pixel can belong
 * to multiple regions.
 * 
 * You need to calculate a m x n grid result, where result[i][j] is the average
 * intensity of the regions to which image[i][j] belongs, rounded down to the
 * nearest integer. If image[i][j] belongs to multiple regions, result[i][j] is
 * the average of the rounded-down average intensities of these regions,
 * rounded down to the nearest integer. If image[i][j] does not belong to any
 * region, result[i][j] is equal to image[i][j].
 * 
 * Return the grid result.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: image = [[5,6,7,10],[8,9,10,10],[11,12,13,10]], threshold = 3
 * 
 * Output: [[9,9,9,9],[9,9,9,9],[9,9,9,9]]
 * 
 * Explanation:
 * 
 * 
 * 
 * There are two regions as illustrated above. The average intensity of the
 * first region is 9, while the average intensity of the second region is 9.67
 * which is rounded down to 9. The average intensity of both of the regions is
 * (9 + 9) / 2 = 9. As all the pixels belong to either region 1, region 2, or
 * both of them, the intensity of every pixel in the result is 9.
 * 
 * Please note that the rounded-down values are used when calculating the
 * average of multiple regions, hence the calculation is done using 9 as the
 * average intensity of region 2, not 9.67.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: image = [[10,20,30],[15,25,35],[20,30,40],[25,35,45]], threshold =
 * 12
 * 
 * Output: [[25,25,25],[27,27,27],[27,27,27],[30,30,30]]
 * 
 * Explanation:
 * 
 * 
 * 
 * There are two regions as illustrated above. The average intensity of the
 * first region is 25, while the average intensity of the second region is 30.
 * The average intensity of both of the regions is (25 + 30) / 2 = 27.5 which
 * is rounded down to 27.
 * 
 * All the pixels in row 0 of the image belong to region 1, hence all the
 * pixels in row 0 in the result are 25. Similarly, all the pixels in row 3 in
 * the result are 30. The pixels in rows 1 and 2 of the image belong to region
 * 1 and region 2, hence their assigned value is 27 in the result.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: image = [[5,6,7],[8,9,10],[11,12,13]], threshold = 1
 * 
 * Output: [[5,6,7],[8,9,10],[11,12,13]]
 * 
 * Explanation:
 * 
 * There is only one 3 x 3 subgrid, while it does not have the condition on
 * difference of adjacent pixels, for example, the difference between
 * image[0][0] and image[1][0] is |5 - 8| = 3 > threshold = 1. None of them
 * belong to any valid regions, so the result should be the same as image.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= n, m <= 500
 * 0 <= image[i][j] <= 255
 * 0 <= threshold <= 255
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] resultGrid(int[][] image, int threshold) {
        int n = image.length;
        int m = image[0].length;
        int[][] ret = new int[n][m];
        int[][] cnt = new int[n][m];
        int[][] directions = {{0, 0},{0, 1},{0, -1}, {1, 0},{-1, 0},{1, 1},{-1, -1},{-1, 1},{1, -1}};
        for (int i = 1; i < n - 1; i++) {
            for(int j = 1; j < m - 1; j++){
                boolean isValid = true;
                int total = 0;
                if(isRegion(image, threshold, i, j)){
                    for(int[] dir : directions){
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        total += image[ni][nj];
                    }
                    for(int[] dir : directions){
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        ret[ni][nj] += total / 9;
                        cnt[ni][nj]++;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++){
                if(cnt[i][j] == 0){
                    ret[i][j] = image[i][j];
                }
                else{
                    ret[i][j] = ret[i][j] / cnt[i][j];
                }
            }
        }
        // System.out.println(Arrays.deepToString(cnt));
        return ret;
    }

    boolean isRegion(int[][] image, int threshold, int i, int j){
        for(int di = -1; di <= 1; di++){
            for(int dj = -1; dj <= 1; dj++){
                int ni = i + di;
                int nj = j + dj;
                if((nj < j + 1 && Math.abs(image[ni][nj] - image[ni][nj + 1]) > threshold) || (ni < i + 1  && Math.abs(image[ni][nj] - image[ni + 1][nj]) > threshold)){
                    return false;
                }
            }
        }
        return true;
    }
}
// @lc code=end
