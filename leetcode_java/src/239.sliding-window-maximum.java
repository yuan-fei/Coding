/*
 * [239] Sliding Window Maximum
 *
 * https://leetcode.com/problems/sliding-window-maximum/description/
 *
 * algorithms
 * Hard (34.45%)
 * Total Accepted:    156K
 * Total Submissions: 410.2K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Return the max sliding window.
 * 
 * Example:
 * 
 * 
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7] 
 * Explanation: 
 * 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * ⁠1 [3  -1  -3] 5  3  6  7       3
 * ⁠1  3 [-1  -3  5] 3  6  7       5
 * ⁠1  3  -1 [-3  5  3] 6  7       5
 * ⁠1  3  -1  -3 [5  3  6] 7       6
 * ⁠1  3  -1  -3  5 [3  6  7]      7
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty
 * array.
 * 
 * Follow up:
 * Could you solve it in linear time?
 */
class Solution {
    public int[] maxSlidingWindow(int[] a, int k) {
        if (k > a.length||a.length == 0) {
			return new int[0];
		}
		int n = a.length;
		ArrayDeque<Integer> window = new ArrayDeque<>();
		int[] ret = new int[n - k + 1];
		int i = 0;
		for (; i < k; i++) {
			while (!window.isEmpty() && a[window.peekLast()] < a[i]) {
				window.pollLast();
			}
			window.offer(i);
		}
		ret[0] = a[window.peekFirst()];
		for (; i < n; i++) {
			if (window.peekFirst() <= i - k) {
				window.pollFirst();
			}
			while (!window.isEmpty() && a[window.peekLast()] < a[i]) {
				window.pollLast();
			}
			window.offer(i);
			ret[i - k + 1] = a[window.peekFirst()];
		}
		return ret;
    }
}
