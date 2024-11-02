/*
 * @lc app=leetcode id=2916 lang=java
 *
 * [2916] Subarrays Distinct Element Sum of Squares II
 *
 * https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/description/
 *
 * algorithms
 * Hard (17.77%)
 * Likes:    145
 * Dislikes: 8
 * Total Accepted:    3.3K
 * Total Submissions: 15.5K
 * Testcase Example:  '[1,2,1]'
 *
 * You are given a 0-indexed integer array nums.
 * 
 * The distinct count of a subarray of nums is defined as:
 * 
 * 
 * Let nums[i..j] be a subarray of nums consisting of all the indices from i to
 * j such that 0 <= i <= j < nums.length. Then the number of distinct values in
 * nums[i..j] is called the distinct count of nums[i..j].
 * 
 * 
 * Return the sum of the squares of distinct counts of all subarrays of nums.
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,1]
 * Output: 15
 * Explanation: Six possible subarrays are:
 * [1]: 1 distinct value
 * [2]: 1 distinct value
 * [1]: 1 distinct value
 * [1,2]: 2 distinct values
 * [2,1]: 2 distinct values
 * [1,2,1]: 2 distinct values
 * The sum of the squares of the distinct counts in all subarrays is equal to
 * 1^2 + 1^2 + 1^2 + 2^2 + 2^2 + 2^2 = 15.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2]
 * Output: 3
 * Explanation: Three possible subarrays are:
 * [2]: 1 distinct value
 * [2]: 1 distinct value
 * [2,2]: 1 distinct value
 * The sum of the squares of the distinct counts in all subarrays is equal to
 * 1^2 + 1^2 + 1^2 = 3.
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
class Solution {
    int MAX = 100000;
    int MOD = (int)1e9 + 7;
    public int sumCounts(int[] nums) {
        int n = nums.length;
        RangeUpdateRangeQueryTree st = new RangeUpdateRangeQueryTree(n);
        int[] lastIndex = new int[MAX + 1];
        Arrays.fill(lastIndex, -1);
        int ans = 0;
        int cur = 0;
        for(int i = 0; i < nums.length; i++){
            st.increase(i, i, 1);
            cur += 1;
            cur %= MOD;
            if(lastIndex[nums[i]] != i - 1){
                long sum = st.query(lastIndex[nums[i]] + 1, i - 1);
                cur += Math.floorMod(sum * 2, MOD);
                cur %= MOD;
                cur += i - lastIndex[nums[i]] - 1;
                st.increase(lastIndex[nums[i]] + 1, i - 1, 1);
            }
            ans += cur;
            ans %= MOD;
            // System.out.println(Arrays.asList(cur, ans));
            lastIndex[nums[i]] = i;
        }
        return ans;
    }

    static class RangeUpdateRangeQueryTree {
        class SegmentTreeNode {
            public int start, end;
            public SegmentTreeNode left, right;
            public long base;
            public long increment;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
            }

            public void extend() {
                if (left == null) {
                    int mid = (start + end) / 2;
                    left = new SegmentTreeNode(start, mid);
                    right = new SegmentTreeNode(mid + 1, end);
                }
            }

            @Override
            public String toString() {
                return "[" + start + ", " + end + "] = " + base + ", " + increment;
            }
        }

        public RangeUpdateRangeQueryTree(int n) {
            root = new SegmentTreeNode(0, n - 1);
        }

        SegmentTreeNode root;

        public Long query(int start, int end) {
            return query(root, start, end);
        }

        public void increase(int start, int end, int value) {
            increase(root, start, end, value);
        }

        private long query(SegmentTreeNode r, int start, int end) {
            if (r == null) {
                return 0;
            } else if (start > r.end || end < r.start) {
                return 0;
            } else if (start <= r.start && r.end <= end) {
                return r.base + (r.end - r.start + 1) * r.increment;
            } else {
                r.extend();
                long leftSum = query(r.left, start, end);
                long rightSum = query(r.right, start, end);
                return leftSum + rightSum + (Math.min(end, r.end) - Math.max(start, r.start) + 1) * r.increment;
            }
        }

        private void increase(SegmentTreeNode r, int start, int end, int value) {
            if (r != null && start <= r.end && r.start <= end) {
                if (start <= r.start && r.end <= end) {
                    r.increment += value;
                } else {
                    r.extend();
                    r.base += (Math.min(end, r.end) - Math.max(start, r.start) + 1) * value;
                    increase(r.left, start, end, value);
                    increase(r.right, start, end, value);
                }
            }
        }

        public void print() {
            Queue<SegmentTreeNode> q = new LinkedList<SegmentTreeNode>();
            q.offer(root);
            while (!q.isEmpty()) {
                int cnt = q.size();
                String s = "";
                for (int i = 0; i < cnt; i++) {
                    SegmentTreeNode n = q.poll();
                    s += (n + ",");
                    if (n != null) {
                        q.offer(n.left);
                        q.offer(n.right);
                    }
                }
                System.out.println(s);
            }
            System.out.println();
        }
    }
}
// @lc code=end
