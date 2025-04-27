/*
 * @lc app=leetcode id=3132 lang=java
 *
 * [3132] Find the Integer Added to Array II
 *
 * https://leetcode.com/problems/find-the-integer-added-to-array-ii/description/
 *
 * algorithms
 * Medium (31.60%)
 * Likes:    166
 * Dislikes: 41
 * Total Accepted:    23.4K
 * Total Submissions: 74.2K
 * Testcase Example:  '[4,20,16,12,8]\n[14,18,10]'
 *
 * You are given two integer arrays nums1 and nums2.
 * 
 * From nums1 two elements have been removed, and all other elements have been
 * increased (or decreased in the case of negative) by an integer, represented
 * by the variable x.
 * 
 * As a result, nums1 becomes equal to nums2. Two arrays are considered equal
 * when they contain the same integers with the same frequencies.
 * 
 * Return the minimum possible integer x that achieves this equivalence.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [4,20,16,12,8], nums2 = [14,18,10]
 * 
 * Output: -2
 * 
 * Explanation:
 * 
 * After removing elements at indices [0,4] and adding -2, nums1 becomes
 * [18,14,10].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [3,5,5,3], nums2 = [7,7]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * After removing elements at indices [0,3] and adding 2, nums1 becomes
 * [7,7].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= nums1.length <= 200
 * nums2.length == nums1.length - 2
 * 0 <= nums1[i], nums2[i] <= 1000
 * The test cases are generated in a way that there is an integer x such that
 * nums1 can become equal to nums2 by removing two elements and adding x to
 * each element of nums1.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.Set;

class Solution {
    int MAX = 10000;

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int ans = MAX;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i + 1; j < nums1.length; j++) {
                int diff = checkEquity(nums1, nums2, Set.of(i, j));
                if (diff != MAX) {
                    ans = Math.min(ans, diff);
                }
            }
        }
        return ans;
    }

    int checkEquity(int[] nums1, int[] nums2, Set<Integer> excludedIndexes) {
        int j = 0;
        int diff = MAX;
        for (int i = 0; i < nums1.length; i++) {
            if (!excludedIndexes.contains(i)) {
                int d = nums2[j] - nums1[i];
                if (diff == MAX) {
                    diff = d;
                } else {
                    if (diff != d) {
                        return MAX;
                    }
                }
                j++;
            }
        }
        return diff;
    }
}
// @lc code=end
