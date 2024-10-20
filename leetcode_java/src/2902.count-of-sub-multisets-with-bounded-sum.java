/*
 * @lc app=leetcode id=2902 lang=java
 *
 * [2902] Count of Sub-Multisets With Bounded Sum
 *
 * https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/description/
 *
 * algorithms
 * Hard (19.53%)
 * Likes:    150
 * Dislikes: 24
 * Total Accepted:    4.4K
 * Total Submissions: 20.7K
 * Testcase Example:  '[1,2,2,3]\n6\n6'
 *
 * You are given a 0-indexed array nums of non-negative integers, and two
 * integers l and r.
 * 
 * Return the count of sub-multisets within nums where the sum of elements in
 * each subset falls within the inclusive range of [l, r].
 * 
 * Since the answer may be large, return it modulo 10^9 + 7.
 * 
 * A sub-multiset is an unordered collection of elements of the array in which
 * a given value x can occur 0, 1, ..., occ[x] times, where occ[x] is the
 * number of occurrences of x in the array.
 * 
 * Note that:
 * 
 * 
 * Two sub-multisets are the same if sorting both sub-multisets results in
 * identical multisets.
 * The sum of an empty multiset is 0.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,2,3], l = 6, r = 6
 * Output: 1
 * Explanation: The only subset of nums that has a sum of 6 is {1, 2, 3}.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,1,4,2,7], l = 1, r = 5
 * Output: 7
 * Explanation: The subsets of nums that have a sum within the range [1, 5] are
 * {1}, {2}, {4}, {2, 2}, {1, 2}, {1, 4}, and {1, 2, 2}.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,1,3,5,2], l = 3, r = 5
 * Output: 9
 * Explanation: The subsets of nums that have a sum within the range [3, 5] are
 * {3}, {5}, {1, 2}, {1, 3}, {2, 2}, {2, 3}, {1, 1, 2}, {1, 1, 3}, and {1, 2,
 * 2}.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2 * 10^4
 * Sum of nums does not exceed 2 * 10^4.
 * 0 <= l <= r <= 2 * 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    public int countSubMultisets(List<Integer> nums, int l, int r) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int x : nums){
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        int[] dp = new int[r + 1];
        dp[0] = 1;
        for(int k : freq.keySet()){
            int f = freq.get(k);
            if(k == 0){
                for(int j = 0; j <= r; j++){
                    dp[j] *= f + 1;
                    dp[j] %= MOD;
                }
            }
            else{
                // c for congruence group
                for(int c = 0; c < k; c++){
                    int[] pSum = new int[Math.max(0, (r - c + k - 1)) / k + 2];
                    for(int j = 0; j * k + c <= r; j++){
                        pSum[j + 1] = pSum[j];
                        pSum[j + 1] += dp[j * k + c];
                        pSum[j + 1] %= MOD;
                    }
                    for(int j = 0; j * k + c <= r; j++){
                        dp[j * k + c] = Math.floorMod(pSum[j + 1] - pSum[Math.max(0, j - f)], MOD);
                    }

                }    
            }
        }
        System.out.println(Arrays.toString(dp));
        int ans = 0;
        for(int i = l; i <= r; i++){
            ans += dp[i];
            ans %= MOD;
        }
        return ans;
    }
}
// @lc code=end
