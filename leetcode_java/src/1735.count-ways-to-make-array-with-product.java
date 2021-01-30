/*
 * @lc app=leetcode id=1735 lang=java
 *
 * [1735] Count Ways to Make Array With Product
 *
 * https://leetcode.com/problems/count-ways-to-make-array-with-product/description/
 *
 * algorithms
 * Hard (46.35%)
 * Likes:    56
 * Dislikes: 14
 * Total Accepted:    1.2K
 * Total Submissions: 2.6K
 * Testcase Example:  '[[2,6],[5,1],[73,660]]'
 *
 * You are given a 2D integer array, queries. For each queries[i], where
 * queries[i] = [ni, ki], find the number of different ways you can place
 * positive integers into an array of size ni such that the product of the
 * integers is ki. As the number of ways may be too large, the answer to the
 * i^th query is the number of ways modulo 10^9 + 7.
 * 
 * Return an integer array answer where answer.length == queries.length, and
 * answer[i] is the answer to the i^th query.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: queries = [[2,6],[5,1],[73,660]]
 * Output: [4,1,50734910]
 * Explanation: Each query is independent.
 * [2,6]: There are 4 ways to fill an array of size 2 that multiply to 6:
 * [1,6], [2,3], [3,2], [6,1].
 * [5,1]: There is 1 way to fill an array of size 5 that multiply to 1:
 * [1,1,1,1,1].
 * [73,660]: There are 1050734917 ways to fill an array of size 73 that
 * multiply to 660. 1050734917 modulo 10^9 + 7 = 50734910.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: queries = [[1,1],[2,2],[3,3],[4,4],[5,5]]
 * Output: [1,2,3,10,5]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= queries.length <= 10^4 
 * 1 <= ni, ki <= 10^4
 * 
 * 
 */

// @lc code=start
import java.math.*;
class Solution {
    private static long MOD = 1000000007;
    private static BigInteger MODINT = new BigInteger("1000000007");
    private static Map<ArrayList<Integer>, Integer> cache = new HashMap<>();
    private static Map<Integer, List<Integer>> factors = new HashMap<>();
    
    public int[] waysToFillArray(int[][] queries) {
        int[] result = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            result[idx++] = query(q[0], q[1]);
        }
        return result;
    }

    private int query(int numSlots, int n) {
        if (n == 1) return 1;
        List<Integer> numPrimeFactors = numPrimeFactors(n);
        // System.out.println(numSlots+ ", "+ n + "=" +numPrimeFactors);
        long result = 1;
        for (int num : numPrimeFactors) {
            result = result * choose(numSlots + num - 1, numSlots - 1) % MOD;
        }
        return (int) result;
    }
    
    private List<Integer> numPrimeFactors(int N) {
        if (factors.containsKey(N)) return factors.get(N);
        
        List<Integer> result = new LinkedList<>();
        int n = N;
        int counter = 0;
        while (n % 2 == 0) {
            ++counter;
            n = n / 2;
        }
        if (counter != 0) {
            result.add(counter);
            counter = 0;
        }
        
        for (int i = 3; i <= Math.sqrt(N); i += 2) {
            while (n % i == 0) {
                ++counter;
                n = n / i;
            }
            if (counter != 0) {
                result.add(counter);
                counter = 0;
            }
        } 
        if (n > 2) result.add(1);
        
        factors.put(N, result);
        return result;
    }
    
    private int choose(int N, int K) {
        if (K > N / 2) return choose(N, N - K);
        
        ArrayList<Integer> nk = new ArrayList<>();
        nk.add(N);
        nk.add(K);
        if (cache.get(nk) != null) return cache.get(nk);
        
        BigInteger ret = BigInteger.ONE;
        for (int k = 0; k < K; k++) {
            ret = ret.multiply(BigInteger.valueOf(N - k)).divide(BigInteger.valueOf(k + 1));
        }
        
        int retInt = ret.mod(MODINT).intValue();
        cache.put(nk, retInt);
        
        return retInt;
    }
}
// @lc code=end
