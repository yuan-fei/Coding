/*
 * @lc app=leetcode id=3044 lang=java
 *
 * [3044] Most Frequent Prime
 *
 * https://leetcode.com/problems/most-frequent-prime/description/
 *
 * algorithms
 * Medium (46.31%)
 * Likes:    92
 * Dislikes: 67
 * Total Accepted:    16.6K
 * Total Submissions: 35.7K
 * Testcase Example:  '[[1,1],[9,9],[1,1]]'
 *
 * You are given a m x n 0-indexed 2D matrix mat. From every cell, you can
 * create numbers in the following way:
 * 
 * 
 * There could be at most 8 paths from the cells namely: east, south-east,
 * south, south-west, west, north-west, north, and north-east.
 * Select a path from them and append digits in this path to the number being
 * formed by traveling in this direction.
 * Note that numbers are generated at every step, for example, if the digits
 * along the path are 1, 9, 1, then there will be three numbers generated along
 * the way: 1, 19, 191.
 * 
 * 
 * Return the most frequent prime number greater than 10 out of all the numbers
 * created by traversing the matrix or -1 if no such prime number exists. If
 * there are multiple prime numbers with the highest frequency, then return the
 * largest among them.
 * 
 * Note: It is invalid to change the direction during the move.
 * 
 * 
 * Example 1:
 * ⁠
 * 
 * 
 * 
 * Input: mat = [[1,1],[9,9],[1,1]]
 * Output: 19
 * Explanation: 
 * From cell (0,0) there are 3 possible directions and the numbers greater than
 * 10 which can be created in those directions are:
 * East: [11], South-East: [19], South: [19,191].
 * Numbers greater than 10 created from the cell (0,1) in all possible
 * directions are: [19,191,19,11].
 * Numbers greater than 10 created from the cell (1,0) in all possible
 * directions are: [99,91,91,91,91].
 * Numbers greater than 10 created from the cell (1,1) in all possible
 * directions are: [91,91,99,91,91].
 * Numbers greater than 10 created from the cell (2,0) in all possible
 * directions are: [11,19,191,19].
 * Numbers greater than 10 created from the cell (2,1) in all possible
 * directions are: [11,19,19,191].
 * The most frequent prime number among all the created numbers is 19.
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[7]]
 * Output: -1
 * Explanation: The only number which can be formed is 7. It is a prime number
 * however it is not greater than 10, so return -1.
 * 
 * Example 3:
 * 
 * 
 * Input: mat = [[9,7,8],[4,6,5],[2,8,6]]
 * Output: 97
 * Explanation: 
 * Numbers greater than 10 created from the cell (0,0) in all possible
 * directions are: [97,978,96,966,94,942].
 * Numbers greater than 10 created from the cell (0,1) in all possible
 * directions are: [78,75,76,768,74,79].
 * Numbers greater than 10 created from the cell (0,2) in all possible
 * directions are: [85,856,86,862,87,879].
 * Numbers greater than 10 created from the cell (1,0) in all possible
 * directions are: [46,465,48,42,49,47].
 * Numbers greater than 10 created from the cell (1,1) in all possible
 * directions are: [65,66,68,62,64,69,67,68].
 * Numbers greater than 10 created from the cell (1,2) in all possible
 * directions are: [56,58,56,564,57,58].
 * Numbers greater than 10 created from the cell (2,0) in all possible
 * directions are: [28,286,24,249,26,268].
 * Numbers greater than 10 created from the cell (2,1) in all possible
 * directions are: [86,82,84,86,867,85].
 * Numbers greater than 10 created from the cell (2,2) in all possible
 * directions are: [68,682,66,669,65,658].
 * The most frequent prime number among all the created numbers is 97.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 6
 * 1 <= mat[i][j] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int mostFrequentPrime(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        Map<Integer, Integer> valueToCount = new HashMap<>();
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0,-1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                for(int[] dir : directions){
                    List<Integer> numbers = getPrimesGreaterThen10(mat, i, j, dir[0], dir[1]);
                    for (int x : numbers) {
                        valueToCount.put(x, valueToCount.getOrDefault(x, 0) + 1);
                    }
                }
            }
        }
        return valueToCount.keySet().stream().max(Comparator.<Integer, Integer>comparing(valueToCount::get).thenComparing(Function.identity())).orElse(-1);
    }

    List<Integer> getPrimesGreaterThen10(int[][] mat, int r, int c, int deltaR, int deltaC){
        int n = mat.length;
        int m = mat[0].length;
        List<Integer> ret = new ArrayList<>();
        int cur = 0;
        for(int newR = r, newC = c; 0 <= newR && newR < n && 0 <= newC && newC < m; newR += deltaR, newC += deltaC){
            cur *= 10;
            cur += mat[newR][newC];
            if(cur > 10 && isPrime(cur)){
                ret.add(cur);
            }
        }
        return ret;
    }

    boolean isPrime(int n){
        if(n <= 1){
            return false;
        }
        for(int f = 2; f * f <= n; f++){
            if(n % f == 0){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
