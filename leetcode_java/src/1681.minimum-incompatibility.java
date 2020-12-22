/*
 * @lc app=leetcode id=1681 lang=java
 *
 * [1681] Minimum Incompatibility
 *
 * https://leetcode.com/problems/minimum-incompatibility/description/
 *
 * algorithms
 * Hard (32.81%)
 * Likes:    77
 * Dislikes: 72
 * Total Accepted:    3K
 * Total Submissions: 9.2K
 * Testcase Example:  '[1,2,1,4]\n2'
 *
 * You are given an integer array nums​​​ and an integer k. You are asked to
 * distribute this array into k subsets of equal size such that there are no
 * two equal elements in the same subset.
 * 
 * A subset's incompatibility is the difference between the maximum and minimum
 * elements in that array.
 * 
 * Return the minimum possible sum of incompatibilities of the k subsets after
 * distributing the array optimally, or return -1 if it is not possible.
 * 
 * A subset is a group integers that appear in the array with no particular
 * order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,1,4], k = 2
 * Output: 4
 * Explanation: The optimal distribution of subsets is [1,2] and [1,4].
 * The incompatibility is (2-1) + (4-1) = 4.
 * Note that [1,1] and [2,4] would result in a smaller sum, but the first
 * subset contains 2 equal elements.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [6,3,8,1,3,1,2,2], k = 4
 * Output: 6
 * Explanation: The optimal distribution of subsets is [1,2], [2,3], [6,8], and
 * [1,3].
 * The incompatibility is (2-1) + (3-2) + (8-6) + (3-1) = 6.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [5,3,3,6,3,3], k = 3
 * Output: -1
 * Explanation: It is impossible to distribute nums into 3 subsets where no two
 * elements are equal in the same subset.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= nums.length <= 16
 * nums.length is divisible by k
 * 1 <= nums[i] <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int m = n / k;
        int maxBitmap = 1 << n;
        Map<Integer, Integer> pool = getPool(nums, m);
        System.out.println(pool.size());
        int[] dp = new int[maxBitmap]; // dp[b] is min incompatibility when we limit nums to a subset of bitmap_of_size_b
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < maxBitmap; i++) {
            if (Integer.bitCount(i) % m != 0) continue; // increment by m each time, assign each group with a new member
            for (int key : pool.keySet()) {
                int submap = key; // a bitmap(of valid size m) from the subset of current bitmap(of valid size i)
                int incomp = pool.get(key); // incompatibility of this submap
                if (submap > i) break;
                if ((submap & i) != submap) continue; // guarantee submap is strictly a subset of b
                if (dp[i - submap] == -1) continue; // previous dp base does not exist, invalid
                int cand = dp[i - submap] + incomp;
                dp[i] = (dp[i] == -1) ? cand : Math.min(dp[i], cand);
            }
        }
        return dp[maxBitmap-1];
    }
    
    private Map<Integer, Integer> getPool(int[] nums, int m) { // choose m unique elements from nums
        int n = nums.length;
        int maxBitmap = 1 << n;
        Map<Integer, Integer> pool = new LinkedHashMap<>(); // ordered map will lead to TLE
        for (int b = 0; b < maxBitmap; b++) {
            if (Integer.bitCount(b) != m) continue;
            boolean duplicate = false;
            Set<Integer> seen = new HashSet<>();
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if ((b & (1 << i)) == 0) continue; // must from one inside the current bitmap
                if (seen.contains(nums[i])) { // evict duplicate
                    duplicate = true;
                    break;
                }
                seen.add(nums[i]);
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);               
            }
            if (duplicate) continue;
            pool.put(b, max - min);
        }
        return pool;
    }

}

// @lc code=end
