/*
 * @lc app=leetcode id=3162 lang=java
 *
 * [3162] Find the Number of Good Pairs I
 *
 * https://leetcode.com/problems/find-the-number-of-good-pairs-i/description/
 *
 * algorithms
 * Easy (86.05%)
 * Likes:    138
 * Dislikes: 14
 * Total Accepted:    80.4K
 * Total Submissions: 94K
 * Testcase Example:  '[1,3,4]\n[1,3,4]\n1'
 *
 * You are given 2 integer arrays nums1 and nums2 of lengths n and m
 * respectively. You are also given a positive integer k.
 * 
 * A pair (i, j) is called good if nums1[i] is divisible by nums2[j] * k (0 <=
 * i <= n - 1, 0 <= j <= m - 1).
 * 
 * Return the total number of good pairs.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,3,4], nums2 = [1,3,4], k = 1
 * 
 * Output: 5
 * 
 * Explanation:
 * The 5 good pairs are (0, 0), (1, 0), (1, 1), (2, 0), and (2, 2).
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [1,2,4,12], nums2 = [2,4], k = 3
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The 2 good pairs are (3, 0) and (3, 1).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, m <= 50
 * 1 <= nums1[i], nums2[j] <= 50
 * 1 <= k <= 50
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {

    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> freq = Arrays.stream(nums2).mapToObj(x -> x)
                .collect(Collectors.toMap(Function.identity(), _ -> 1, (a, b) -> a + b));
        return Arrays.stream(nums1).filter(x -> x % k == 0).map(x -> x / k)
                .map(x -> getFactors(x).stream().mapToInt(f -> freq.getOrDefault(f, 0)).sum()).sum();
    }

    Set<Integer> getFactors(int n) {
        Set<Integer> ret = new HashSet<>();
        for (int f = 1; f * f <= n; f++) {
            if (n % f == 0) {
                ret.add(f);
                ret.add(n / f);
            }
        }
        return ret;
    }
}
// @lc code=end
