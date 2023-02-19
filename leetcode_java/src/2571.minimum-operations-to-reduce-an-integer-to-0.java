/*
 * @lc app=leetcode id=2571 lang=java
 *
 * [2571] Minimum Operations to Reduce an Integer to 0
 *
 * https://leetcode.com/problems/minimum-operations-to-reduce-an-integer-to-0/description/
 *
 * algorithms
 * Easy (40.43%)
 * Likes:    52
 * Dislikes: 104
 * Total Accepted:    6.8K
 * Total Submissions: 17K
 * Testcase Example:  '39'
 *
 * You are given a positive integer n, you can do the following operation any
 * number of times:
 * 
 * 
 * Add or subtract a power of 2 from n.
 * 
 * 
 * Return the minimum number of operations to make n equal to 0.
 * 
 * A number x is power of 2 if x == 2^i where i >= 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 39
 * Output: 3
 * Explanation: We can do the following operations:
 * - Add 2^0 = 1 to n, so now n = 40.
 * - Subtract 2^3 = 8 from n, so now n = 32.
 * - Subtract 2^5 = 32 from n, so now n = 0.
 * It can be shown that 3 is the minimum number of operations we need to make n
 * equal to 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 54
 * Output: 3
 * Explanation: We can do the following operations:
 * - Add 2^1 = 2 to n, so now n = 56.
 * - Add 2^3 = 8 to n, so now n = 64.
 * - Subtract 2^6 = 64 from n, so now n = 0.
 * So the minimum number of operations is 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(int n) {
        int start = -1;
        List<int[]> l = new ArrayList<>();
        for(int i = 0; i < 18; i++){
            if(((n >> i) & 1) == 1){
                if(start == -1){
                    start = i;
                }
            }
            else{
                if(start != -1){
                    l.add(new int[]{start, i - 1});
                }   
                start = -1;
            }
        }
        int[] dp = new int[l.size() + 1];
        for(int i = 1; i <= l.size(); i++){
            dp[i] = Math.min(l.get(i - 1)[1] - l.get(i - 1)[0] + 1, 2) + dp[i - 1];
            int sum = 2;
            for(int j = i - 1; j >= 1; j--){
                sum += l.get(j)[0] - l.get(j - 1)[1] - 1;
                dp[i] = Math.min(dp[i], sum + dp[j - 1]);
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[l.size() ];
    }
}
// @lc code=end
