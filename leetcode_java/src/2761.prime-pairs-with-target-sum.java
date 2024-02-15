/*
 * @lc app=leetcode id=2761 lang=java
 *
 * [2761] Prime Pairs With Target Sum
 *
 * https://leetcode.com/problems/prime-pairs-with-target-sum/description/
 *
 * algorithms
 * Medium (33.04%)
 * Likes:    335
 * Dislikes: 32
 * Total Accepted:    26.2K
 * Total Submissions: 79.2K
 * Testcase Example:  '10'
 *
 * You are given an integer n. We say that two integers x and y form a prime
 * number pair if:
 * 
 * 
 * 1 <= x <= y <= n
 * x + y == n
 * x and y are prime numbers
 * 
 * 
 * Return the 2D sorted list of prime number pairs [xi, yi]. The list should be
 * sorted in increasing order of xi. If there are no prime number pairs at all,
 * return an empty array.
 * 
 * Note: A prime number is a natural number greater than 1 with only two
 * factors, itself and 1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 10
 * Output: [[3,7],[5,5]]
 * Explanation: In this example, there are two prime pairs that satisfy the
 * criteria. 
 * These pairs are [3,7] and [5,5], and we return them in the sorted order as
 * described in the problem statement.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: []
 * Explanation: We can show that there is no prime number pair that gives a sum
 * of 2, so we return an empty array. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> findPrimePairs(int n) {
        boolean[] ts = getPrimes(n);
        List<List<Integer>> ans = new ArrayList<>();
        for(int x = 0; x + x <= n; x++){
            if(ts[x] && ts[n - x]){
                ans.add(Arrays.asList(x, n - x));
            }
        }
        return ans;
    }

    boolean[] getPrimes(int n){
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i <= n; i++){
            for(int j = (int)Math.min(n, 1L * i * i); j <= n; j += i){
                isPrime[j] = false;
            }
        }
        return isPrime;
    }
}
// @lc code=end
