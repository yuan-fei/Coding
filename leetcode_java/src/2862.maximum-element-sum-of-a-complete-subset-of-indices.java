/*
 * @lc app=leetcode id=2862 lang=java
 *
 * [2862] Maximum Element-Sum of a Complete Subset of Indices
 *
 * https://leetcode.com/problems/maximum-element-sum-of-a-complete-subset-of-indices/description/
 *
 * algorithms
 * Hard (43.06%)
 * Likes:    212
 * Dislikes: 55
 * Total Accepted:    7.8K
 * Total Submissions: 17.7K
 * Testcase Example:  '[8,7,3,5,7,2,4,9]'
 *
 * You are given a 1-indexed array nums. Your task is to select a complete
 * subset from nums where every pair of selected indices multiplied is a
 * perfect square,. i. e. if you select ai and aj, i * j must be a perfect
 * square.
 * 
 * Return the sum of the complete subset with the maximum sum.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [8,7,3,5,7,2,4,9]
 * 
 * Output: 16
 * 
 * Explanation:
 * 
 * We select elements at indices 2 and 8 and 2 * 8 is a perfect square.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [8,10,3,8,1,13,7,9,4]
 * 
 * Output: 20
 * 
 * Explanation:
 * 
 * We select elements at indices 1, 4, and 9. 1 * 4, 1 * 9, 4 * 9 are perfect
 * squares.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == nums.length <= 10^4
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long maximumSum(List<Integer> nums) {
        int n = nums.size();
        long ans = 0;
        Map<Integer, Long> dp = new HashMap<>();
        for(int i = 1; i <= n; i++){
            int reduced = removeSquareFactors(i);
            dp.put(reduced, dp.getOrDefault(reduced, 0L) + nums.get(i - 1));
            ans = Math.max(ans, dp.get(reduced));
        }
        return ans;
    }

    int removeSquareFactors(int x){
        int ret = 1;
        for(int f = 2; f * f <= x; f++){
            int cnt = 0;
            while(x % f == 0){
                x /= f;
                cnt++;
            }
            if(cnt % 2 == 1){
                ret *= f;
            }
        }
        if(x > 1){
            ret *= x;
        }
        return ret;
    }
}
// @lc code=end
