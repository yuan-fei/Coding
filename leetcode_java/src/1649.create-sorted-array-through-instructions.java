/*
 * @lc app=leetcode id=1649 lang=java
 *
 * [1649] Create Sorted Array through Instructions
 *
 * https://leetcode.com/problems/create-sorted-array-through-instructions/description/
 *
 * algorithms
 * Hard (41.87%)
 * Likes:    67
 * Dislikes: 8
 * Total Accepted:    2.6K
 * Total Submissions: 6.2K
 * Testcase Example:  '[1,5,6,2]'
 *
 * Given an integer array instructions, you are asked to create a sorted array
 * from the elements in instructions. You start with an empty container nums.
 * For each element from left to right in instructions, insert it into nums.
 * The cost of each insertion is the minimum of the following:
 * 
 * 
 * The number of elements currently in nums that are strictly less than
 * instructions[i].
 * The number of elements currently in nums that are strictly greater than
 * instructions[i].
 * 
 * 
 * For example, if inserting element 3 into nums = [1,2,3,5], the cost of
 * insertion is min(2, 1) (elements 1 and 2 are less than 3, element 5 is
 * greater than 3) and nums will become [1,2,3,3,5].
 * 
 * Return the total cost to insert all elements from instructions into nums.
 * Since the answer may be large, return it modulo 10^9 + 7
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: instructions = [1,5,6,2]
 * Output: 1
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 5 with cost min(1, 0) = 0, now nums = [1,5].
 * Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6].
 * Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6].
 * The total cost is 0 + 0 + 0 + 1 = 1.
 * 
 * Example 2:
 * 
 * 
 * Input: instructions = [1,2,3,6,5,4]
 * Output: 3
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 2 with cost min(1, 0) = 0, now nums = [1,2].
 * Insert 3 with cost min(2, 0) = 0, now nums = [1,2,3].
 * Insert 6 with cost min(3, 0) = 0, now nums = [1,2,3,6].
 * Insert 5 with cost min(3, 1) = 1, now nums = [1,2,3,5,6].
 * Insert 4 with cost min(3, 2) = 2, now nums = [1,2,3,4,5,6].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 2 = 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: instructions = [1,3,3,3,2,4,2,1,2]
 * Output: 4
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3,3].
 * Insert 2 with cost min(1, 3) = 1, now nums = [1,2,3,3,3].
 * Insert 4 with cost min(5, 0) = 0, now nums = [1,2,3,3,3,4].
 * ​​​​​​​Insert 2 with cost min(1, 4) = 1, now nums = [1,2,2,3,3,3,4].
 * ​​​​​​​Insert 1 with cost min(0, 6) = 0, now nums = [1,1,2,2,3,3,3,4].
 * ​​​​​​​Insert 2 with cost min(2, 4) = 2, now nums = [1,1,2,2,2,3,3,3,4].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= instructions.length <= 10^5
 * 1 <= instructions[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int createSortedArray(int[] instructions) {
        PointIncrementRangeSumQueryTree st = new PointIncrementRangeSumQueryTree(N);
        int ans = 0;
        for(int x : instructions){
        	int low = st.query(0, x - 1);
        	int high = st.query(x + 1, N - 1);
        	ans = (ans + Math.min(low, high)) % 1000000007;
        	st.increase(x, 1);
        }
        return ans;
    }
    int N = 100005;
    public class PointIncrementRangeSumQueryTree {
		class SegmentTreeNode {
			public int start, end;
			public SegmentTreeNode left, right;
			public int value;

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

		public int query(int start, int end) {
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

		private int query(SegmentTreeNode r, int start, int end) {
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
}
// @lc code=end
