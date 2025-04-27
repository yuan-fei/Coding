/*
 * @lc app=leetcode id=3134 lang=java
 *
 * [3134] Find the Median of the Uniqueness Array
 *
 * https://leetcode.com/problems/find-the-median-of-the-uniqueness-array/description/
 *
 * algorithms
 * Hard (28.61%)
 * Likes:    158
 * Dislikes: 12
 * Total Accepted:    7.1K
 * Total Submissions: 25.6K
 * Testcase Example:  '[1,2,3]'
 *
 * You are given an integer array nums. The uniqueness array of nums is the
 * sorted array that contains the number of distinct elements of all the
 * subarrays of nums. In other words, it is a sorted array consisting of
 * distinct(nums[i..j]), for all 0 <= i <= j < nums.length.
 * 
 * Here, distinct(nums[i..j]) denotes the number of distinct elements in the
 * subarray that starts at index i and ends at index j.
 * 
 * Return the median of the uniqueness array of nums.
 * 
 * Note that the median of an array is defined as the middle element of the
 * array when it is sorted in non-decreasing order. If there are two choices
 * for a median, the smaller of the two values is taken.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3]
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The uniqueness array of nums is [distinct(nums[0..0]), distinct(nums[1..1]),
 * distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]),
 * distinct(nums[0..2])] which is equal to [1, 1, 1, 2, 2, 3]. The uniqueness
 * array has a median of 1. Therefore, the answer is 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,4,3,4,5]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The uniqueness array of nums is [1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3,
 * 3]. The uniqueness array has a median of 2. Therefore, the answer is 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [4,3,5,4]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The uniqueness array of nums is [1, 1, 1, 1, 2, 2, 2, 3, 3, 3]. The
 * uniqueness array has a median of 2. Therefore, the answer is 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n;
        long totalSubArrayCount = 1L * n * (n + 1) / 2;
        long medianCount = (totalSubArrayCount + 1) / 2;
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            long cnt = countDistinctNumberLessOrEqualTo(nums, mid);
            if (cnt < medianCount) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (countDistinctNumberLessOrEqualTo(nums, low) == medianCount) {
            return low;
        }
        return high;
    }

    long countDistinctNumberLessOrEqualTo(int[] nums, int maxDistinct) {
        long ans = 0;
        int l = 0;
        FreqCounter f = new FreqCounter();
        for (int r = 0; r < nums.length; r++) {
            f.inc(nums[r]);
            while (f.getCount() > maxDistinct) {
                f.dec(nums[l++]);
            }
            ans += r - l + 1;
        }
        return ans;
    }

    class FreqCounter {
        Map<Integer, Integer> valueToCnt = new HashMap<>();

        void inc(int v) {
            valueToCnt.put(v, valueToCnt.getOrDefault(v, 0) + 1);
        }

        void dec(int v) {
            int f = valueToCnt.getOrDefault(v, 0) - 1;
            if (f <= 0) {
                valueToCnt.remove(v);
            } else {
                valueToCnt.put(v, f);
            }
        }

        int getCount() {
            return valueToCnt.size();
        }
    }
}
// @lc code=end
