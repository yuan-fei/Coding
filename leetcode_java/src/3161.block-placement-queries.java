/*
 * @lc app=leetcode id=3161 lang=java
 *
 * [3161] Block Placement Queries
 *
 * https://leetcode.com/problems/block-placement-queries/description/
 *
 * algorithms
 * Hard (15.85%)
 * Likes:    131
 * Dislikes: 24
 * Total Accepted:    9.1K
 * Total Submissions: 55K
 * Testcase Example:  '[[1,2],[2,3,3],[2,3,1],[2,2,2]]'
 *
 * There exists an infinite number line, with its origin at 0 and extending
 * towards the positive x-axis.
 * 
 * You are given a 2D array queries, which contains two types of queries:
 * 
 * 
 * For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x
 * from the origin. It is guaranteed that there is no obstacle at distance x
 * when the query is asked.
 * For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to
 * place a block of size sz anywhere in the range [0, x] on the line, such that
 * the block entirely lies in the range [0, x]. A block cannot be placed if it
 * intersects with any obstacle, but it may touch it. Note that you do not
 * actually place the block. Queries are separate.
 * 
 * 
 * Return a boolean array results, where results[i] is true if you can place
 * the block specified in the i^th query of type 2, and false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]
 * 
 * Output: [false,true,true]
 * 
 * Explanation:
 * 
 * 
 * 
 * For query 0, place an obstacle at x = 2. A block of size at most 2 can be
 * placed before x = 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]
 * 
 * Output: [true,true,false]
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * Place an obstacle at x = 7 for query 0. A block of size at most 7 can be
 * placed before x = 7.
 * Place an obstacle at x = 2 for query 2. Now, a block of size at most 5 can
 * be placed before x = 7, and a block of size at most 2 before x = 2.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= queries.length <= 15 * 10^4
 * 2 <= queries[i].length <= 3
 * 1 <= queries[i][0] <= 2
 * 1 <= x, sz <= min(5 * 10^4, 3 * queries.length)
 * The input is generated such that for queries of type 1, no obstacle exists
 * at distance x when the query is asked.
 * The input is generated such that there is at least one query of type 2.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

class Solution {
    static abstract public class LazyPropSegmentTree {

        public static void main(String[] args) {
            System.out.println("RangeIncRangeMax");
            LazyPropSegmentTree s = new RangeIncRangeMax(4);
            s.increase(0, 6, -1);
            s.print();
            s.increase(0, 2, 3);
            s.print();
            System.out.println(s.query(0, 2));
            System.out.println(s.query(2, 6));
            System.out.println(s.query(4, 6));
            s.increase(0, 0, -9);
            s.print();
            System.out.println(s.query(0, 0));
        }

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

    static class RangeIncRangeMax extends LazyPropSegmentTree {
        public RangeIncRangeMax(int[] nums) {
            build(nums);
        }

        public RangeIncRangeMax(int n) {
            build(new int[n]);
        }

        long zeroAnswer() {
            return Long.MIN_VALUE;
        }

        long combineAnswer(long a, long b) {
            return Math.max(a, b);
        }

        void applyInc(SegmentTreeNode r, long inc) {
            r.answer += inc;
            r.pendingPushDownInc += inc;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int MAX = Math.min(50005, 3 * queries.length + 5);
        TreeSet<Integer> blocked = new TreeSet<>();
        blocked.add(0);
        blocked.add(MAX);
        LazyPropSegmentTree s = new RangeIncRangeMax(MAX);
        int[] init = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            init[i] = i;
        }
        s.build(init);
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int prevBlock = blocked.floor(q[1]);
                int nextBlock = blocked.ceiling(q[1]);
                int delta = q[1] - prevBlock;
                // update
                s.increase(q[1] + 1, nextBlock, -delta);
                blocked.add(q[1]);

            } else {
                // query
                // System.out.println(s.query(0, q[1]));
                ans.add(s.query(0, q[1]) >= q[2]);
            }
        }
        return ans;
    }
}
// @lc code=end
