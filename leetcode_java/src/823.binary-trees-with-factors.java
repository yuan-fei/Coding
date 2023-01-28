/*
 * @lc app=leetcode id=823 lang=java
 *
 * [823] Binary Trees With Factors
 *
 * https://leetcode.com/problems/binary-trees-with-factors/description/
 *
 * algorithms
 * Medium (49.79%)
 * Likes:    2460
 * Dislikes: 179
 * Total Accepted:    88.8K
 * Total Submissions: 178.4K
 * Testcase Example:  '[2,4]'
 *
 * Given an array of unique integers, arr, where each integer arr[i] is
 * strictly greater than 1.
 * 
 * We make a binary tree using these integers, and each number may be used for
 * any number of times. Each non-leaf node's value should be equal to the
 * product of the values of its children.
 * 
 * Return the number of binary trees we can make. The answer may be too large
 * so return the answer modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10,
 * 2, 5], [10, 5, 2].
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 10^9
 * All the values of arr are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    public int numFactoredBinaryTrees(int[] arr) {
        Map<Integer, Long> dp = new HashMap<>();
        Arrays.sort(arr);
        long ans = 0;
        for(int x : arr){
            dp.put(x, 1L);
            for(int f : arr){
                if(f < x && x % f == 0 && dp.containsKey(x / f)){
                    long cnt = (dp.get(f) * dp.get(x / f)) % MOD;
                    dp.put(x, (dp.get(x) + cnt) % MOD);
                }
            }
            ans += dp.get(x);
            ans %= MOD;
        }
        return (int)ans;
    }
}
// @lc code=end
