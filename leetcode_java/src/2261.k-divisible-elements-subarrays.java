/*
 * @lc app=leetcode id=2261 lang=java
 *
 * [2261] K Divisible Elements Subarrays
 *
 * https://leetcode.com/problems/k-divisible-elements-subarrays/description/
 *
 * algorithms
 * Medium (42.95%)
 * Likes:    99
 * Dislikes: 82
 * Total Accepted:    7.4K
 * Total Submissions: 17.4K
 * Testcase Example:  '[2,3,3,2,2]\n2\n2'
 *
 * Given an integer array nums and two integers k and p, return the number of
 * distinct subarrays which have at most k elements divisible by p.
 * 
 * Two arrays nums1 and nums2 are said to be distinct if:
 * 
 * 
 * They are of different lengths, or
 * There exists at least one index i where nums1[i] != nums2[i].
 * 
 * 
 * A subarray is defined as a non-empty contiguous sequence of elements in an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,3,3,2,2], k = 2, p = 2
 * Output: 11
 * Explanation:
 * The elements at indices 0, 3, and 4 are divisible by p = 2.
 * The 11 distinct subarrays which have at most k = 2 elements divisible by 2
 * are:
 * [2], [2,3], [2,3,3], [2,3,3,2], [3], [3,3], [3,3,2], [3,3,2,2], [3,2],
 * [3,2,2], and [2,2].
 * Note that the subarrays [2] and [3] occur more than once in nums, but they
 * should each be counted only once.
 * The subarray [2,3,3,2,2] should not be counted because it has 3 elements
 * that are divisible by 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4], k = 4, p = 1
 * Output: 10
 * Explanation:
 * All element of nums are divisible by p = 1.
 * Also, every subarray of nums will have at most 4 elements that are divisible
 * by 1.
 * Since all subarrays are distinct, the total number of subarrays satisfying
 * all the constraints is 10.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 200
 * 1 <= nums[i], p <= 200
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length;
        // we are storing hashcode for all the substrings so that we can compare them faster.
        // main goal is to avoid entire sub array comparision using hashcode.
        Set<Long> ways = new HashSet<>(); 
        for(int i = 0; i < n; i++) {
            int cnt = 0;
            long hc = 1; // this is the running hashcode for sub array [i...j]
            for(int j = i; j < n; j++) {
                hc = 199L * hc + nums[j]; // updating running hashcode, since we nums are <=200, we shall consider a prime near 200 to avoid collision
                if(nums[j] % p == 0)
                    cnt++;
                if(cnt <= k) { // if current subarray [i...j] is valid, add its hashcode in our storage.
                    ways.add(hc);
                }
            }
        }
        return ways.size();
    }
}
// @lc code=end
