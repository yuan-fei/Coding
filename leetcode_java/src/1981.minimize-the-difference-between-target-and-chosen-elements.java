/*
 * @lc app=leetcode id=1981 lang=java
 *
 * [1981] Minimize the Difference Between Target and Chosen Elements
 *
 * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/description/
 *
 * algorithms
 * Medium (28.96%)
 * Likes:    185
 * Dislikes: 62
 * Total Accepted:    7.1K
 * Total Submissions: 24.5K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]\n13'
 *
 * You are given an m x n integer matrix mat and an integer target.
 * 
 * Choose one integer from each row in the matrix such that the absolute
 * difference between target and the sum of the chosen elements is minimized.
 * 
 * Return the minimum absolute difference.
 * 
 * The absolute difference between two numbers a and b is the absolute value of
 * a - b.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 * Output: 0
 * Explanation: One possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 5 from the second row.
 * - Choose 7 from the third row.
 * The sum of the chosen elements is 13, which equals the target, so the
 * absolute difference is 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[1],[2],[3]], target = 100
 * Output: 94
 * Explanation: The best possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 2 from the second row.
 * - Choose 3 from the third row.
 * The sum of the chosen elements is 6, and the absolute difference is 94.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: mat = [[1,2,9,8,7]], target = 6
 * Output: 1
 * Explanation: The best choice is to choose 7 from the first row.
 * The absolute difference is 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 70
 * 1 <= mat[i][j] <= 70
 * 1 <= target <= 800
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;
        int MAX = (int)1e7;
        Set<Integer> s = new HashSet<>();
        s.add(0);
        for(int i = 0; i < n; i++){
            Set<Integer> newS = new HashSet<>();
            for(int j = 0; j < m; j++){
                int minAbove = MAX;
                for(int x : s){
                    if(x + mat[i][j] > target){
                        minAbove = Math.min(x + mat[i][j], minAbove);
                    }
                    else{
                        newS.add(x + mat[i][j]);
                    }
                }
                if(minAbove != MAX){
                    newS.add(minAbove);
                }
            }
            s = newS;
        }
        TreeSet<Integer> ts = new TreeSet<>(s);
        Integer ceiling = ts.ceiling(target);
        Integer floor = ts.floor(target);
        int ret = 1000000;
        if(ceiling != null){
            ret = Math.min(ceiling - target, ret);
        }
        if(floor != null){
            ret = Math.min(target - floor, ret);
        }
        return ret;
    }
}
// @lc code=end
