/*
 * @lc app=leetcode id=3165 lang=java
 *
 * [3165] Maximum Sum of Subsequence With Non-adjacent Elements
 *
 * https://leetcode.com/problems/maximum-sum-of-subsequence-with-non-adjacent-elements/description/
 *
 * algorithms
 * Hard (16.41%)
 * Likes:    133
 * Dislikes: 28
 * Total Accepted:    6.9K
 * Total Submissions: 43.5K
 * Testcase Example:  '[3,5,9]\n[[1,-2],[0,-3]]'
 *
 * You are given an array nums consisting of integers. You are also given a 2D
 * array queries, where queries[i] = [posi, xi].
 * 
 * For query i, we first set nums[posi] equal to xi, then we calculate the
 * answer to query i which is the maximum sum of a subsequence of nums where no
 * two adjacent elements are selected.
 * 
 * Return the sum of the answers to all queries.
 * 
 * Since the final answer may be very large, return it modulo 10^9 + 7.
 * 
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,5,9], queries = [[1,-2],[0,-3]]
 * 
 * Output: 21
 * 
 * Explanation:
 * After the 1^st query, nums = [3,-2,9] and the maximum sum of a subsequence
 * with non-adjacent elements is 3 + 9 = 12.
 * After the 2^nd query, nums = [-3,-2,9] and the maximum sum of a subsequence
 * with non-adjacent elements is 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,-1], queries = [[0,-5]]
 * 
 * Output: 0
 * 
 * Explanation:
 * After the 1^st query, nums = [-5,-1] and the maximum sum of a subsequence
 * with non-adjacent elements is 0 (choosing an empty subsequence).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 5 * 10^4
 * -10^5 <= nums[i] <= 10^5
 * 1 <= queries.length <= 5 * 10^4
 * queries[i] == [posi, xi]
 * 0 <= posi <= nums.length - 1
 * -10^5 <= xi <= 10^5
 * 
 * 
 */

// @lc code=start

class Solution {
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        // e.g: [1000, 1, -1, 2, 1000]
        // dp[i]: max sub array sum in [0, i]
        // dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
        // seg tree node:
        // maxSubArrSum(i + 1, j - 1), maxSubArrSum(i, j - 1), maxSubArrSum(i + 1, j),
        // maxSubArrSum(i, j)
        // maxSubArrSums[include_left][include_right]
        RangeMaxSubArrSumQueryTree segTree = new RangeMaxSubArrSumQueryTree(nums);
        long ans = 0;
        for (int[] query : queries) {
            segTree.update(query[0], query[1]);
            ans += segTree.query();
        }
        return (int) (ans % 1_000_000_007);
    }

    public class RangeMaxSubArrSumQueryTree {
        class SegmentTreeNode {
            public int start, end;
            public SegmentTreeNode left, right;
            public long[][] maxSubArrSums;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.left = this.right = null;
                maxSubArrSums = new long[2][2];
            }

            void mergeFromChildren() {
                for (int i = 0; i <= 1; i++) {
                    for (int j = 0; j <= 1; j++) {
                        maxSubArrSums[i][j] = 0;
                        maxSubArrSums[i][j] = Math.max(maxSubArrSums[i][j],
                                left.maxSubArrSums[i][0] + right.maxSubArrSums[0][j]);
                        maxSubArrSums[i][j] = Math.max(maxSubArrSums[i][j],
                                left.maxSubArrSums[i][0] + right.maxSubArrSums[1][j]);
                        maxSubArrSums[i][j] = Math.max(maxSubArrSums[i][j],
                                left.maxSubArrSums[i][1] + right.maxSubArrSums[0][j]);
                    }
                }
            }

            long maxValue() {
                return maxSubArrSums[1][1];
            }

            @Override
            public String toString() {
                return "[" + start + ", " + end + "] = " + maxSubArrSums;
            }
        }

        SegmentTreeNode root;

        public RangeMaxSubArrSumQueryTree(int[] nums) {
            build(nums);
        }

        public void build(int[] nums) {
            if (nums.length > 0) {
                root = build(0, nums.length - 1, nums);
            }
        }

        public long query() {
            return root.maxValue();
        }

        public void update(int index, int value) {
            update(root, index, value);
        }

        private SegmentTreeNode build(int start, int end, int[] nums) {
            SegmentTreeNode r = new SegmentTreeNode(start, end);
            if (start == end) {
                r.maxSubArrSums[1][1] = Math.max(0, nums[start]);
            } else {
                r.left = build(start, start + (end - start) / 2, nums);
                r.right = build(start + (end - start) / 2 + 1, end, nums);
                r.mergeFromChildren();
            }
            return r;
        }

        private void update(SegmentTreeNode r, int index, int value) {
            if (r != null && index >= r.start && index <= r.end) {
                if (r.start == index && r.end == index) {
                    r.maxSubArrSums[1][1] = Math.max(0, value);
                } else {
                    update(r.left, index, value);
                    update(r.right, index, value);
                    r.mergeFromChildren();
                }
            }
        }
    }

}
// @lc code=end
