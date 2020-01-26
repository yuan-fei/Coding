/*
 * @lc app=leetcode id=327 lang=java
 *
 * [327] Count of Range Sum
 *
 * https://leetcode.com/problems/count-of-range-sum/description/
 *
 * algorithms
 * Hard (34.16%)
 * Likes:    552
 * Dislikes: 72
 * Total Accepted:    37.6K
 * Total Submissions: 110.1K
 * Testcase Example:  '[-2,5,-1]\n-2\n2'
 *
 * Given an integer array nums, return the number of range sums that lie in
 * [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between
 * indices i and j (i ≤ j), inclusive.
 * 
 * Note:
 * A naive algorithm of O(n^2) is trivial. You MUST do better than that.
 * 
 * Example:
 * 
 * 
 * Input: nums = [-2,5,-1], lower = -2, upper = 2,
 * Output: 3 
 * Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective
 * sums are: -2, -1, 2.
 * 
 */

// @lc code=start
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
    	int n = nums.length;
    	long[] pSum = new long[n + 1];
    	Set<Long> s = new TreeSet<>();
    	s.add(0L);
    	for(int i = 1; i <= n; i++){
    		pSum[i] = pSum[i - 1] + nums[i - 1];
    		s.add(pSum[i]);
    	}
    	long[] distinct = new long[s.size()];
    	int j = 0;
    	for(long x : s){
    		distinct[j++] = x;
    	}
    	int cnt = 0;
    	SegmentTreeNode r = build(distinct, 0, distinct.length - 1);
    	int k = 0;
    	for (long sum : pSum) {
    		int delta = query(r, sum - upper, sum - lower);
    		cnt += delta;
    		// k++;
    		// if(k == 5){
    		// 	print(r);
    		// 	System.out.println(""+(sum - upper)+", "+(sum - lower)+", "+ delta);	
    		// }
    		
    		increase(r, sum);
    	}
    	return cnt;
    }

	class SegmentTreeNode {
		public long start, end;
		public SegmentTreeNode left, right;
		public int value;

		public SegmentTreeNode(long start, long end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "] = " + value;
		}
	}

	public void print(SegmentTreeNode root) {
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

    private SegmentTreeNode build(long[] arr, int start, int end) {
		SegmentTreeNode r = new SegmentTreeNode(start, end);
		r.start = arr[start];
		r.end = arr[end];
		if (start < end) {
			r.left = build(arr, start, start + (end - start) / 2);
			r.right = build(arr, start + (end - start) / 2 + 1, end);
		}
		return r;
	}

	private int query(SegmentTreeNode r, long start, long end) {
		if (r == null) {
			return 0;
		} else if (start > r.end || end < r.start) {
			return 0;
		} else if (start <= r.start && end >= r.end) {
			return r.value;
		} else {
			int leftSum = query(r.left, start, end);
			int rightSum = query(r.right, start, end);
			return leftSum + rightSum;
		}
	}

	private void increase(SegmentTreeNode r, long index) {
		if (r != null && index >= r.start && index <= r.end) {
			if (r.start == index && r.end == index) {
				r.value += 1;
			} else {
				increase(r.left, index);
				increase(r.right, index);
				r.value = r.left.value + r.right.value;
			}
		}
	}
}
// @lc code=end
