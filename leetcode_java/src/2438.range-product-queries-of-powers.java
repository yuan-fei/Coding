/*
 * @lc app=leetcode id=2438 lang=java
 *
 * [2438] Range Product Queries of Powers
 *
 * https://leetcode.com/problems/range-product-queries-of-powers/description/
 *
 * algorithms
 * Medium (34.83%)
 * Likes:    122
 * Dislikes: 21
 * Total Accepted:    8.1K
 * Total Submissions: 23.3K
 * Testcase Example:  '15\n[[0,1],[2,2],[0,3]]'
 *
 * Given a positive integer n, there exists a 0-indexed array called powers,
 * composed of the minimum number of powers of 2 that sum to n. The array is
 * sorted in non-decreasing order, and there is only one way to form the
 * array.
 * 
 * You are also given a 0-indexed 2D integer array queries, where queries[i] =
 * [lefti, righti]. Each queries[i] represents a query where you have to find
 * the product of all powers[j] with lefti <= j <= righti.
 * 
 * Return an array answers, equal in length to queries, where answers[i] is the
 * answer to the i^th query. Since the answer to the i^th query may be too
 * large, each answers[i] should be returned modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 15, queries = [[0,1],[2,2],[0,3]]
 * Output: [2,4,64]
 * Explanation:
 * For n = 15, powers = [1,2,4,8]. It can be shown that powers cannot be a
 * smaller size.
 * Answer to 1st query: powers[0] * powers[1] = 1 * 2 = 2.
 * Answer to 2nd query: powers[2] = 4.
 * Answer to 3rd query: powers[0] * powers[1] * powers[2] * powers[3] = 1 * 2 *
 * 4 * 8 = 64.
 * Each answer modulo 10^9 + 7 yields the same answer, so [2,4,64] is
 * returned.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, queries = [[0,0]]
 * Output: [2]
 * Explanation:
 * For n = 2, powers = [2].
 * The answer to the only query is powers[0] = 2. The answer modulo 10^9 + 7 is
 * the same, so [2] is returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * 1 <= queries.length <= 10^5
 * 0 <= starti <= endi < powers.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] productQueries(int n, int[][] queries) {
        List<Integer> l = getExpList(n);
        int[] ans = new int[queries.length];
        int[] pSum = new int[l.size() + 1];
        for(int i = 1; i < pSum.length; i++){
            pSum[i] = pSum[i - 1] + l.get(i - 1);
        }
        // System.out.println(Arrays.toString(pSum));
        long mod = (long)1e9 + 7;
        for(int i = 0; i < queries.length; i++){
            int[] q = queries[i];
            ans[i] = (int)pow(2, pSum[q[1] + 1] - pSum[q[0]], mod);
        }
        return ans;
    }

    private List<Integer> getExpList(int n){
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < 32; i++){
            if(((n >> i) & 1) == 1){
                ret.add(i);
            }
        }
        return ret;
    }

    long pow(int b, int p, long m){
        if(p == 0){
            return 1;
        }
        else if(p % 2 == 1){
            long t = pow(b, p - 1, m);
            return (t * b) % m;
        }
        else{
            long t = pow(b, p / 2, m);
            t *= t;
            t %= m;
            return t;
        }
    }
}
// @lc code=end
