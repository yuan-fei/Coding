/*
 * @lc app=leetcode id=808 lang=java
 *
 * [808] Soup Servings
 *
 * https://leetcode.com/problems/soup-servings/description/
 *
 * algorithms
 * Medium (43.42%)
 * Likes:    308
 * Dislikes: 841
 * Total Accepted:    17.1K
 * Total Submissions: 39.4K
 * Testcase Example:  '50'
 *
 * There are two types of soup: type A and type B. Initially, we have n ml of
 * each type of soup. There are four kinds of operations:
 * 
 * 
 * Serve 100 ml of soup A and 0 ml of soup B,
 * Serve 75 ml of soup A and 25 ml of soup B,
 * Serve 50 ml of soup A and 50 ml of soup B, and
 * Serve 25 ml of soup A and 75 ml of soup B.
 * 
 * 
 * When we serve some soup, we give it to someone, and we no longer have it.
 * Each turn, we will choose from the four operations with an equal probability
 * 0.25. If the remaining volume of soup is not enough to complete the
 * operation, we will serve as much as possible. We stop once we no longer have
 * some quantity of both types of soup.
 * 
 * Note that we do not have an operation where all 100 ml's of soup B are used
 * first.
 * 
 * Return the probability that soup A will be empty first, plus half the
 * probability that A and B become empty at the same time. Answers within 10^-5
 * of the actual answer will be accepted.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 50
 * Output: 0.62500
 * Explanation: If we choose the first two operations, A will become empty
 * first.
 * For the third operation, A and B will become empty at the same time.
 * For the fourth operation, B will become empty first.
 * So the total probability of A becoming empty first plus half the probability
 * that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) =
 * 0.625.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 100
 * Output: 0.71875
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {

    int MAX = 5000;
    int[][] probs = {{4, 0}, {3, 1}, {2, 2}, {1, 3}};
    double[][] cache;

    public double soupServings(int n) {
        if(n >= MAX){
            return 1;
        }
        n = (n + 24) / 25;
        cache = new double[n + 1][n + 1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(cache[i], -1);
        }
        return solve(n, n);
    }
    
    double solve(int a, int b){
        if(a <= 0 && b <= 0){
            return 0.5;
        }
        if(a <= 0){
            return 1;
        }
        if(b <= 0){
            return 0;
        }
        if(cache[a][b] == -1){
            cache[a][b] = 0;
            for(int[] p : probs){
                cache[a][b] += 0.25 * solve(a - p[0], b - p[1]);
            }
        }
        return cache[a][b];
    }
}
// @lc code=end
