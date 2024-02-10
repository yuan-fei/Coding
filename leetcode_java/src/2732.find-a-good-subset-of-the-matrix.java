/*
 * @lc app=leetcode id=2732 lang=java
 *
 * [2732] Find a Good Subset of the Matrix
 *
 * https://leetcode.com/problems/find-a-good-subset-of-the-matrix/description/
 *
 * algorithms
 * Hard (44.55%)
 * Likes:    203
 * Dislikes: 22
 * Total Accepted:    6.2K
 * Total Submissions: 13.9K
 * Testcase Example:  '[[0,1,1,0],[0,0,0,1],[1,1,1,1]]'
 *
 * You are given a 0-indexed m x n binary matrix grid.
 * 
 * Let us call a non-empty subset of rows good if the sum of each column of the
 * subset is at most half of the length of the subset.
 * 
 * More formally, if the length of the chosen subset of rows is k, then the sum
 * of each column should be at most floor(k / 2).
 * 
 * Return an integer array that contains row indices of a good subset sorted in
 * ascending order.
 * 
 * If there are multiple good subsets, you can return any of them. If there are
 * no good subsets, return an empty array.
 * 
 * A subset of rows of the matrix grid is any matrix that can be obtained by
 * deleting some (possibly none or all) rows from grid.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,1,1,0],[0,0,0,1],[1,1,1,1]]
 * Output: [0,1]
 * Explanation: We can choose the 0^th and 1^st rows to create a good subset of
 * rows.
 * The length of the chosen subset is 2.
 * - The sum of the 0^th column is 0 + 0 = 0, which is at most half of the
 * length of the subset.
 * - The sum of the 1^st column is 1 + 0 = 1, which is at most half of the
 * length of the subset.
 * - The sum of the 2^nd column is 1 + 0 = 1, which is at most half of the
 * length of the subset.
 * - The sum of the 3^rd column is 0 + 1 = 1, which is at most half of the
 * length of the subset.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0]]
 * Output: [0]
 * Explanation: We can choose the 0^th row to create a good subset of rows.
 * The length of the chosen subset is 1.
 * - The sum of the 0^th column is 0, which is at most half of the length of
 * the subset.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,1,1],[1,1,1]]
 * Output: []
 * Explanation: It is impossible to choose any subset of rows to create a good
 * subset.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 10^4
 * 1 <= n <= 5
 * grid[i][j] is either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] bitmaskToRow = new int[1 << n];
        Arrays.fill(bitmaskToRow, -1);
        for(int i = 0; i < m; i++){
            int bitmask = 0;
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    bitmask |= 1 << j;
                }
            }
            bitmaskToRow[bitmask] = i;
        }
        // System.out.println(Arrays.toString(bitmaskToRow));
        if(bitmaskToRow[0] != -1){
            return Arrays.asList(bitmaskToRow[0]);
        }
        for(int i = 0; i < bitmaskToRow.length; i++){
            if(bitmaskToRow[i] != -1){
                for(int j = i + 1; j < bitmaskToRow.length; j++){
                    if(bitmaskToRow[j] != -1 && (i & j) == 0){
                        return IntStream.of(bitmaskToRow[i], bitmaskToRow[j]).sorted().boxed().toList();
                    }
                }    
            }
        }
        
        List<Integer> bitmasks = IntStream.range(0, 1 << n).filter(b -> bitmaskToRow[b] != -1).boxed().collect(Collectors.toList());
        // System.out.println(bitmasks);
        int goodBitMask = getGoodBitmask(bitmasks, n);
        if(goodBitMask == -1){
            return Arrays.asList();
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < bitmasks.size(); i++){
            if(((goodBitMask >> i) & 1) == 1){
                ans.add(bitmaskToRow[bitmasks.get(i)]);
            }
        }
        Collections.sort(ans);
        return ans;

    }

    int getGoodBitmask(List<Integer> bitmasks, int columns){
        int[][] bitmaskToColumnSums = new int[1 << bitmasks.size()][columns];
        for(int bm = 0 ; bm < (1 << bitmasks.size()); bm++){
            for(int i = 0 ; i < bitmasks.size(); i++){
                if(((bm >> i) & 1) == 1){
                    boolean good = true;
                    for(int j = 0; j < columns; j++){
                        bitmaskToColumnSums[bm][j] = bitmaskToColumnSums[bm ^ (1 << i)][j] + ((bitmasks.get(i) >> j) & 1);
                        if(bitmaskToColumnSums[bm][j] > Integer.bitCount(bm) / 2){
                            good = false;
                        }
                    }
                    if(good){
                        return bm;
                    }
                    break;
                }
            }
        }
        return -1;
    }
}
// @lc code=end
