/*
 * @lc app=leetcode id=3224 lang=java
 *
 * [3224] Minimum Array Changes to Make Differences Equal
 *
 * https://leetcode.com/problems/minimum-array-changes-to-make-differences-equal/description/
 *
 * algorithms
 * Medium (23.34%)
 * Likes:    253
 * Dislikes: 28
 * Total Accepted:    15.3K
 * Total Submissions: 62.9K
 * Testcase Example:  '[1,0,1,2,4,3]\n4'
 *
 * You are given an integer array nums of size n where n is even, and an
 * integer k.
 * 
 * You can perform some changes on the array, where in one change you can
 * replace any element in the array with any integer in the range from 0 to k.
 * 
 * You need to perform some changes (possibly none) such that the final array
 * satisfies the following condition:
 * 
 * 
 * There exists an integer X such that abs(a[i] - a[n - i - 1]) = X for all (0
 * <= i < n).
 * 
 * 
 * Return the minimum number of changes required to satisfy the above
 * condition.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,0,1,2,4,3], k = 4
 * 
 * Output: 2
 * 
 * Explanation:
 * We can perform the following changes:
 * 
 * 
 * Replace nums[1] by 2. The resulting array is nums = [1,2,1,2,4,3].
 * Replace nums[3] by 3. The resulting array is nums = [1,2,1,3,4,3].
 * 
 * 
 * The integer X will be 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,1,2,3,3,6,5,4], k = 6
 * 
 * Output: 2
 * 
 * Explanation:
 * We can perform the following operations:
 * 
 * 
 * Replace nums[3] by 0. The resulting array is nums = [0,1,2,0,3,6,5,4].
 * Replace nums[4] by 4. The resulting array is nums = [0,1,2,0,4,6,5,4].
 * 
 * 
 * The integer X will be 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n == nums.length <= 10^5
 * n is even.
 * 0 <= nums[i] <= k <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    // x == |a - b| -> 0
    // x <= max(a, b) -> 1
    // x <= k - min(a, b) -> 1
    // x > max(max(a, b), k - min(a, b)) -> 2

    // cap(i)
    // for x : n/2 - count(diff == x) + count(cap < x)
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> diffFreq = new HashMap<>();
        Map<Integer, Integer> capFreq = new HashMap<>();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int a = nums[i];
            int b = nums[j];
            int diff = Math.abs(a - b);
            int cap = Math.max(Math.max(a, b), k - Math.min(a, b));
            diffFreq.put(diff, diffFreq.getOrDefault(diff, 0) + 1);
            capFreq.put(cap, capFreq.getOrDefault(cap, 0) + 1);
        }
        int ans = n;
        int capCount = 0;
        for (int x = 0; x <= k; x++) {
            int ops = n / 2 + capCount - diffFreq.getOrDefault(x, 0);
            ans = Math.min(ans, ops);
            capCount += capFreq.getOrDefault(x, 0);
        }
        return ans;
    }
}
// @lc code=end
