/*
 * @lc app=leetcode id=2569 lang=java
 *
 * [2569] Handling Sum Queries After Update
 *
 * https://leetcode.com/problems/handling-sum-queries-after-update/description/
 *
 * algorithms
 * Hard (15.85%)
 * Likes:    37
 * Dislikes: 5
 * Total Accepted:    1K
 * Total Submissions: 6.8K
 * Testcase Example:  '[1,0,1]\n[0,0,0]\n[[1,1,1],[2,1,0],[3,0,0]]'
 *
 * You are given two 0-indexed arrays nums1 and nums2 and a 2D array queries of
 * queries. There are three types of queries:
 * 
 * 
 * For a query of type 1, queries[i] = [1, l, r]. Flip the values from 0 to 1
 * and from 1 to 0 in nums1 from index l to index r. Both l and r are
 * 0-indexed.
 * For a query of type 2, queries[i] = [2, p, 0]. For every index 0 <= i < n,
 * set nums2[i] = nums2[i] + nums1[i] * p.
 * For a query of type 3, queries[i] = [3, 0, 0]. Find the sum of the elements
 * in nums2.
 * 
 * 
 * Return an array containing all the answers to the third type queries.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,0,1], nums2 = [0,0,0], queries = [[1,1,1],[2,1,0],[3,0,0]]
 * Output: [3]
 * Explanation: After the first query nums1 becomes [1,1,1]. After the second
 * query, nums2 becomes [1,1,1], so the answer to the third query is 3. Thus,
 * [3] is returned.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [1], nums2 = [5], queries = [[2,0,0],[3,0,0]]
 * Output: [5]
 * Explanation: After the first query, nums2 remains [5], so the answer to the
 * second query is 5. Thus, [5] is returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length,nums2.length <= 10^5
 * nums1.length = nums2.length
 * 1 <= queries.length <= 10^5
 * queries[i].length = 3
 * 0 <= l <= r <= nums1.length - 1
 * 0 <= p <= 10^6
 * 0 <= nums1[i] <= 1
 * 0 <= nums2[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        LazyPropSegmentTree s = new RangeIncRangeSum(nums1);
        List<Long> l = new ArrayList<>();
        long sum = 0;
        for(int x : nums2){
            sum += x;
        }
        for(int[] q : queries){
            switch(q[0]){
            case 1:
                s.increase(q[1], q[2], 1);
                break;
            case 2:
                sum += s.query(0, nums1.length) * q[1];
                break;
            default:
                l.add(sum);
            }
        }
        long[] ans = new long[l.size()];
        for(int i = 0; i < l.size(); i++){
            ans[i] = l.get(i);
        }
        return ans;
    }

    static abstract class LazyPropSegmentTree {

        class SegmentTreeNode {
            public int start, end;
            public SegmentTreeNode left, right;
            public boolean pendingPushDown;
            public long answer;
            public long pendingPushDownInc;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.left = this.right = null;
            }

            @Override
            public String toString() {
                return "[" + start + ", " + end + "] = " + answer + ", " + pendingPushDownInc + ", " + pendingPushDown;
            }
        }

        SegmentTreeNode root;

        public void build(int[] nums) {
            if (nums.length > 0) {
                root = build(0, nums.length - 1, nums);
            }
        }

        public long query(int start, int end) {
            return query(root, start, end);
        }

        public void increase(int start, int end, long value) {
            increase(root, start, end, value);
        }

        private SegmentTreeNode build(int start, int end, int[] nums) {
            SegmentTreeNode r = new SegmentTreeNode(start, end);
            if (start == end) {
                r.answer = nums[start];
            } else {
                r.left = build(start, start + (end - start) / 2, nums);
                r.right = build(start + (end - start) / 2 + 1, end, nums);
                build(r);
            }
            return r;
        }

        /** combine(zero, x) = x */
        abstract long zeroAnswer();

        /** combine child answers to current node answer */
        abstract long combineAnswer(long a, long b);

        /**
         * if an inc update is applied for the whole interval of node r, then
         * 
         * 1. calculate and update the new answer to node r
         * 
         * 2. accumulate the pendingPushdownInc
         */
        abstract void applyInc(SegmentTreeNode r, long inc);

        void apply(SegmentTreeNode r, long inc) {
            if (r != null) {
                r.pendingPushDown = true;
                applyInc(r, inc);
            }
        }

        void build(SegmentTreeNode r) {
            r.answer = combineAnswer(r.left.answer, r.right.answer);
        }

        void pushDown(SegmentTreeNode r) {
            if (r.pendingPushDown) {
                apply(r.left, r.pendingPushDownInc);
                apply(r.right, r.pendingPushDownInc);
            }
            r.pendingPushDown = false;
            r.pendingPushDownInc = 0;
        }

        private long query(SegmentTreeNode r, int start, int end) {
            if (r == null) {
                return zeroAnswer();
            } else if (start > r.end || end < r.start) {
                return zeroAnswer();
            } else if (start <= r.start && r.end <= end) {
                return r.answer;
            } else {
                pushDown(r);
                long leftSum = query(r.left, start, end);
                long rightSum = query(r.right, start, end);
                return combineAnswer(leftSum, rightSum);
            }
        }

        private void increase(SegmentTreeNode r, int start, int end, long value) {
            if (r != null && start <= r.end && r.start <= end) {
                if (start <= r.start && r.end <= end) {
                    apply(r, value);
                } else {
                    pushDown(r);
                    increase(r.left, start, end, value);
                    increase(r.right, start, end, value);
                    build(r);
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

    static class RangeIncRangeSum extends LazyPropSegmentTree {

        public RangeIncRangeSum(int[] nums) {
            build(nums);
        }

        public RangeIncRangeSum(int n) {
            build(new int[n]);
        }

        @Override
        long zeroAnswer() {
            return 0;
        }

        @Override
        long combineAnswer(long a, long b) {
            return a + b;
        }

        @Override
        void applyInc(SegmentTreeNode r, long inc) {
            if(inc == 1){
                r.answer = (r.end - r.start + 1) - r.answer;
                r.pendingPushDownInc = 1 - r.pendingPushDownInc;
            }
        }
    }
}
// @lc code=end
