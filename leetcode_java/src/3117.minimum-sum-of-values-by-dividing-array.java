/*
 * @lc app=leetcode id=3117 lang=java
 *
 * [3117] Minimum Sum of Values by Dividing Array
 *
 * https://leetcode.com/problems/minimum-sum-of-values-by-dividing-array/description/
 *
 * algorithms
 * Hard (27.30%)
 * Likes:    122
 * Dislikes: 4
 * Total Accepted:    5.8K
 * Total Submissions: 21.4K
 * Testcase Example:  '[1,4,3,3,2]\n[0,3,3,2]'
 *
 * You are given two arrays nums and andValues of length n and m respectively.
 * 
 * The value of an array is equal to the last element of that array.
 * 
 * You have to divide nums into m disjoint contiguous subarrays such that for
 * the i^th subarray [li, ri], the bitwise AND of the subarray elements is
 * equal to andValues[i], in other words, nums[li] & nums[li + 1] & ... &
 * nums[ri] == andValues[i] for all 1 <= i <= m, where & represents the bitwise
 * AND operator.
 * 
 * Return the minimum possible sum of the values of the m subarrays nums is
 * divided into. If it is not possible to divide nums into m subarrays
 * satisfying these conditions, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,4,3,3,2], andValues = [0,3,3,2]
 * 
 * Output: 12
 * 
 * Explanation:
 * 
 * The only possible way to divide nums is:
 * 
 * 
 * [1,4] as 1 & 4 == 0.
 * [3] as the bitwise AND of a single element subarray is that element
 * itself.
 * [3] as the bitwise AND of a single element subarray is that element
 * itself.
 * [2] as the bitwise AND of a single element subarray is that element
 * itself.
 * 
 * 
 * The sum of the values for these subarrays is 4 + 3 + 3 + 2 = 12.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,3,5,7,7,7,5], andValues = [0,7,5]
 * 
 * Output: 17
 * 
 * Explanation:
 * 
 * There are three ways to divide nums:
 * 
 * 
 * [[2,3,5],[7,7,7],[5]] with the sum of the values 5 + 7 + 5 == 17.
 * [[2,3,5,7],[7,7],[5]] with the sum of the values 7 + 7 + 5 == 19.
 * [[2,3,5,7,7],[7],[5]] with the sum of the values 7 + 7 + 5 == 19.
 * 
 * 
 * The minimum possible sum of the values is 17.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3,4], andValues = [2]
 * 
 * Output: -1
 * 
 * Explanation:
 * 
 * The bitwise AND of the entire array nums is 0. As there is no possible way
 * to divide nums into a single subarray to have the bitwise AND of elements 2,
 * return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == nums.length <= 10^4
 * 1 <= m == andValues.length <= min(n, 10)
 * 1 <= nums[i] < 10^5
 * 0 <= andValues[j] < 10^5
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    int MAX = (int) 1e9 + 7;

    public int minimumValueSum(int[] nums, int[] andValues) {
        int n = nums.length;
        int m = andValues.length;
        RangeMinimumQueryTree[] rmqs = new RangeMinimumQueryTree[m + 1];
        for (int i = 0; i <= m; i++) {
            int[] init = new int[n + 1];
            Arrays.fill(init, MAX);
            rmqs[i] = new RangeMinimumQueryTree(init);
        }
        @SuppressWarnings("unchecked")
        TreeMap<Integer, Integer>[] possibleAndValues = new TreeMap[n];
        for (int i = 0; i < n; i++) {
            possibleAndValues[i] = new TreeMap<>();
            int v = (1 << 31) - 1;
            for (int j = i; j >= 0; j--) {
                if ((v & nums[j]) != v) {
                    v &= nums[j];
                    possibleAndValues[i].put(v, j);
                }
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        rmqs[0].update(0, 0);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int end = possibleAndValues[i - 1].getOrDefault(andValues[j - 1], -1);
                if (end != -1) {
                    int start = -1;
                    Integer lowerKey = possibleAndValues[i - 1].lowerKey(andValues[j - 1]);
                    if (lowerKey != null) {
                        start = possibleAndValues[i - 1].get(lowerKey);
                    }
                    int minPrefix = rmqs[j - 1].query(start + 1, end);
                    if (minPrefix < MAX) {
                        dp[i][j] = minPrefix + nums[i - 1];
                        rmqs[j].update(i, dp[i][j]);
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(possibleAndValues));
        // System.out.println(Arrays.deepToString(dp));
        return dp[n][m] == 0 ? -1 : dp[n][m];
    }

    public class RangeMinimumQueryTree {
        class SegmentTreeNode {
            public int start, end;
            public SegmentTreeNode left, right;
            public int value;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.left = this.right = null;
            }

            @Override
            public String toString() {
                return "[" + start + ", " + end + "] = " + value;
            }
        }

        SegmentTreeNode root;

        public RangeMinimumQueryTree(int[] nums) {
            build(nums);
        }

        public void build(int[] nums) {
            if (nums.length > 0) {
                root = build(0, nums.length - 1, nums);
            }
        }

        public Integer query(int start, int end) {
            return query(root, start, end);
        }

        public void update(int index, int value) {
            update(root, index, value);
        }

        private SegmentTreeNode build(int start, int end, int[] nums) {
            SegmentTreeNode r = new SegmentTreeNode(start, end);
            if (start == end) {
                r.value = nums[start];
            } else {
                r.left = build(start, start + (end - start) / 2, nums);
                r.right = build(start + (end - start) / 2 + 1, end, nums);
                r.value = Math.min(r.left.value, r.right.value);
            }
            return r;
        }

        private int query(SegmentTreeNode r, int start, int end) {
            if (r == null) {
                return Integer.MAX_VALUE;
            } else if (start > r.end || end < r.start) {
                return Integer.MAX_VALUE;
            } else if (start <= r.start && end >= r.end) {
                return r.value;
            } else {
                int leftSum = query(r.left, start, end);
                int rightSum = query(r.right, start, end);
                return Math.min(leftSum, rightSum);
            }
        }

        private void update(SegmentTreeNode r, int index, int value) {
            if (r != null && index >= r.start && index <= r.end) {
                if (r.start == index && r.end == index) {
                    r.value = value;
                } else {
                    update(r.left, index, value);
                    update(r.right, index, value);
                    r.value = Math.min(r.left.value, r.right.value);
                }
            }
        }
    }
}
// @lc code=end
