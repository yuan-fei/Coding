/*
 * @lc app=leetcode id=835 lang=java
 *
 * [835] Image Overlap
 *
 * https://leetcode.com/problems/image-overlap/description/
 *
 * algorithms
 * Medium (63.91%)
 * Likes:    1217
 * Dislikes: 441
 * Total Accepted:    87.5K
 * Total Submissions: 136.9K
 * Testcase Example:  '[[1,1,0],[0,1,0],[0,1,0]]\n[[0,0,0],[0,1,1],[0,0,1]]'
 *
 * You are given two images, img1 and img2, represented as binary, square
 * matrices of size n x n. A binary matrix has only 0s and 1s as values.
 * 
 * We translate one image however we choose by sliding all the 1 bits left,
 * right, up, and/or down any number of units. We then place it on top of the
 * other image. We can then calculate the overlap by counting the number of
 * positions that have a 1 in both images.
 * 
 * Note also that a translation does not include any kind of rotation. Any 1
 * bits that are translated outside of the matrix borders are erased.
 * 
 * Return the largest possible overlap.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
 * Output: 3
 * Explanation: We translate img1 to right by 1 unit and down by 1 unit.
 * 
 * The number of positions that have a 1 in both images is 3 (shown in red).
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: img1 = [[1]], img2 = [[1]]
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: img1 = [[0]], img2 = [[0]]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == img1.length == img1[i].length
 * n == img2.length == img2[i].length
 * 1 <= n <= 30
 * img1[i][j] is either 0 or 1.
 * img2[i][j] is either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        return Math.max(overlap(img1, img2), overlap(img2, img1));

    }
    public int overlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int ret = 0;
        for(int di = 0; di < n; di++){
            for(int dj = 0; dj < n; dj++){
                int cnt = 0;
                for(int i = 0; i < n - di; i++){
                    for(int j = 0; j < n - dj; j++){
                        if(img1[i][j] == img2[di + i][dj + j]){
                            cnt += img1[i][j];
                        }
                    }
                }
                ret = Math.max(ret, cnt);
                cnt = 0;
                for(int i = 0; i < n - di; i++){
                    for(int j = 0; j < n - dj; j++){
                        if(img1[i][j + dj] == img2[di + i][j]){
                            cnt += img1[i][j + dj];
                        }
                    }
                }
                ret = Math.max(ret, cnt);
            }
        }
        return ret;
    }
}
// @lc code=end
