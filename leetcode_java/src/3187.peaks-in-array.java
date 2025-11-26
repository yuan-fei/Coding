/*
 * @lc app=leetcode id=3187 lang=java
 *
 * [3187] Peaks in Array
 *
 * https://leetcode.com/problems/peaks-in-array/description/
 *
 * algorithms
 * Hard (26.43%)
 * Likes:    137
 * Dislikes: 10
 * Total Accepted:    11.2K
 * Total Submissions: 41.9K
 * Testcase Example:  '[3,1,4,2,5]\n[[2,3,4],[1,0,4]]'
 *
 * A peak in an array arr is an element that is greater than its previous and
 * next element in arr.
 * 
 * You are given an integer array nums and a 2D integer array queries.
 * 
 * You have to process queries of two types:
 * 
 * 
 * queries[i] = [1, li, ri], determine the count of peak elements in the
 * subarray nums[li..ri].
 * queries[i] = [2, indexi, vali], change nums[indexi] to vali.
 * 
 * 
 * Return an array answer containing the results of the queries of the first
 * type in order.
 * 
 * Notes:
 * 
 * 
 * The first and the last element of an array or a subarray cannot be a
 * peak.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,1,4,2,5], queries = [[2,3,4],[1,0,4]]
 * 
 * Output: [0]
 * 
 * Explanation:
 * 
 * First query: We change nums[3] to 4 and nums becomes [3,1,4,4,5].
 * 
 * Second query: The number of peaks in the [3,1,4,4,5] is 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,1,4,2,1,5], queries = [[2,2,4],[1,0,2],[1,0,4]]
 * 
 * Output: [0,1]
 * 
 * Explanation:
 * 
 * First query: nums[2] should become 4, but it is already set to 4.
 * 
 * Second query: The number of peaks in the [4,1,4] is 0.
 * 
 * Third query: The second 4 is a peak in the [4,1,4,2,1].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i][0] == 1 or queries[i][0] == 2
 * For all i that:
 * 
 * queries[i][0] == 1: 0 <= queries[i][1] <= queries[i][2] <= nums.length -
 * 1
 * queries[i][0] == 2: 0 <= queries[i][1] <= nums.length - 1, 1 <=
 * queries[i][2] <= 10^5
 * 
 * 
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] peakArray = new int[n];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peakArray[i] = 1;
            }
        }
        List<Integer> res = new java.util.ArrayList<>();
        PointIncrementRangeSumQueryTree s = new PointIncrementRangeSumQueryTree(n);
        for (int i = 0; i < peakArray.length; i++) {
            s.increase(i, peakArray[i]);
        }
        for (int[] q : queries) {
            if (q[0] == 2) {
                int index = q[1];
                int value = q[2];
                nums[index] = value;
                for (int i = Math.max(1, index - 1); i <= Math.min(n - 2, index + 1); i++) {
                    int newPeak = (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) ? 1 : 0;
                    int diff = newPeak - peakArray[i];
                    if (diff != 0) {
                        s.increase(i, diff);
                        peakArray[i] = newPeak;
                    }
                }
            } else if (q[0] == 1) {
                int left = q[1] + 1;
                int right = q[2] - 1;
                if (left > right) {
                    res.add(0);
                    continue;
                }
                int count = (int) s.query(left, right);
                res.add(count);
            }
            // System.out.println(Arrays.toString(peakArray));
        }
        return res;
    }
}

class PointIncrementRangeSumQueryTree {
    class SegmentTreeNode {
        public int start, end;
        public SegmentTreeNode left, right;
        public long value;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "] = " + value;
        }
    }

    SegmentTreeNode root;
    int n;

    public PointIncrementRangeSumQueryTree(int n) {
        build(n);
    }

    public void build(int n) {
        this.n = n;
        root = build(0, n - 1);
    }

    public long query(int start, int end) {
        return query(root, start, end);
    }

    public void increase(int index, int value) {
        increase(root, index, value);
    }

    private SegmentTreeNode build(int start, int end) {
        SegmentTreeNode r = new SegmentTreeNode(start, end);
        if (start < end) {
            r.left = build(start, start + (end - start) / 2);
            r.right = build(start + (end - start) / 2 + 1, end);
            r.value = r.left.value + r.right.value;
        }
        return r;
    }

    private long query(SegmentTreeNode r, int start, int end) {
        if (r == null) {
            return 0;
        } else if (start > r.end || end < r.start) {
            return 0;
        } else if (start <= r.start && end >= r.end) {
            return r.value;
        } else {
            long leftSum = query(r.left, start, end);
            long rightSum = query(r.right, start, end);
            return leftSum + rightSum;
        }
    }

    private void increase(SegmentTreeNode r, int index, int value) {
        if (r != null && index >= r.start && index <= r.end) {
            if (r.start == index && r.end == index) {
                r.value += value;
            } else {
                increase(r.left, index, value);
                increase(r.right, index, value);
                r.value = r.left.value + r.right.value;
            }
        }
    }

}

// @lc code=end
