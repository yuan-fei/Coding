/*
 * @lc app=leetcode id=2407 lang=java
 *
 * [2407] Longest Increasing Subsequence II
 *
 * https://leetcode.com/problems/longest-increasing-subsequence-ii/description/
 *
 * algorithms
 * Hard (13.24%)
 * Likes:    163
 * Dislikes: 9
 * Total Accepted:    2.3K
 * Total Submissions: 17.6K
 * Testcase Example:  '[4,2,1,4,3,4,5,8,15]\n3'
 *
 * You are given an integer array nums and an integer k.
 * 
 * Find the longest subsequence of nums that meets the following
 * requirements:
 * 
 * 
 * The subsequence is strictly increasing and
 * The difference between adjacent elements in the subsequence is at most k.
 * 
 * 
 * Return the length of the longest subsequence that meets the requirements.
 * 
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
 * Output: 5
 * Explanation:
 * The longest subsequence that meets the requirements is [1,3,4,5,8].
 * The subsequence has a length of 5, so we return 5.
 * Note that the subsequence [1,3,4,5,8,15] does not meet the requirements
 * because 15 - 8 = 7 is larger than 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [7,4,5,1,8,12,4,7], k = 5
 * Output: 4
 * Explanation:
 * The longest subsequence that meets the requirements is [4,5,8,12].
 * The subsequence has a length of 4, so we return 4.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,5], k = 1
 * Output: 1
 * Explanation:
 * The longest subsequence that meets the requirements is [1].
 * The subsequence has a length of 1, so we return 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        int[] cnt = new int[100005];
        RangeMaximumQueryTree s = new RangeMaximumQueryTree(cnt);
        int ans = 0;
        for(int x : nums){
            int l = s.query(Math.max(0, x - k), x - 1);
            s.update(x, l + 1);
            ans = Math.max(ans, l + 1);
        }
        return ans;
    }

    public class RangeMaximumQueryTree{
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

        public RangeMaximumQueryTree(int[] nums) {
            build(nums);
        }

        public void build(int[] nums) {
            if (nums.length > 0) {
                root = build(0, nums.length - 1, nums);
            }
        }

        public Integer query(int start, int end) {
            // System.out.println("query: " + start + ", " + end);
            return query(root, start, end);
        }

        public void update(int index, int value) {
            // System.out.println("update:" + index + ", " + value);
            update(root, index, value);
        }

        private SegmentTreeNode build(int start, int end, int[] nums) {
            SegmentTreeNode r = new SegmentTreeNode(start, end);
            if (start == end) {
                r.value = nums[start];
            } else {
                r.left = build(start, start + (end - start) / 2, nums);
                r.right = build(start + (end - start) / 2 + 1, end, nums);
                r.value = Math.max(r.left.value, r.right.value);
            }
            return r;
        }

        private int query(SegmentTreeNode r, int start, int end) {
            if (r == null) {
                return Integer.MIN_VALUE;
            } else if (start > r.end || end < r.start) {
                return Integer.MIN_VALUE;
            } else if (start <= r.start && end >= r.end) {
                return r.value;
            } else {
                int leftSum = query(r.left, start, end);
                int rightSum = query(r.right, start, end);
                return Math.max(leftSum, rightSum);
            }
        }

        private void update(SegmentTreeNode r, int index, int value) {
            if (r != null && index >= r.start && index <= r.end) {
                if (r.start == index && r.end == index) {
                    r.value = value;
                } else {
                    update(r.left, index, value);
                    update(r.right, index, value);
                    r.value = Math.max(r.left.value, r.right.value);
                }
            }
        }
    }
}
// @lc code=end
