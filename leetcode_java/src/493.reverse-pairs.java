/*
 * @lc app=leetcode id=493 lang=java
 *
 * [493] Reverse Pairs
 *
 * https://leetcode.com/problems/reverse-pairs/description/
 *
 * algorithms
 * Hard (25.17%)
 * Likes:    874
 * Dislikes: 119
 * Total Accepted:    38.8K
 * Total Submissions: 154.2K
 * Testcase Example:  '[1,3,2,3,1]'
 *
 * Given an array nums, we call (i, j) an important reverse pair if i < j and
 * nums[i] > 2*nums[j].
 * 
 * You need to return the number of important reverse pairs in the given
 * array.
 * 
 * Example1:
 * 
 * Input: [1,3,2,3,1]
 * Output: 2
 * 
 * 
 * Example2:
 * 
 * Input: [2,4,3,5,1]
 * Output: 3
 * 
 * 
 * Note:
 * 
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 32-bit integer.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int reversePairs(int[] nums) {
		int[] aux = new int[nums.length];
		return mergeSort(nums, aux, 0, nums.length);
    }

	/** sort arr[start..end] with help of aux array */
	private int mergeSort(int[] arr, int[] aux, int start, int end) {
		int sum = 0;
		if (start + 1 < end) {
			int mid = (start + end) / 2;
			sum += mergeSort(arr, aux, start, mid);
			sum += mergeSort(arr, aux, mid, end);
			sum += count(arr, start, mid, end);
			merge(arr, aux, start, mid, end);
			System.arraycopy(aux, start, arr, start, end - start);
		}
		return sum;
	}

	/** merge from[start..mid] and from[mid, end] to to[start..end] */
	private void merge(int[] from, int[] to, int start, int mid, int end) {
		int i = start;
		int j = mid;
		int k = start;
		while (i < mid && j < end) {
			if (from[i] <= from[j]) {
				to[k] = from[i];
				i++;
			} else {
				to[k] = from[j];
				j++;
			}
			k++;
		}
		while (i < mid) {
			to[k] = from[i];
			i++;
			k++;
		}
		while (j < end) {
			to[k] = from[j];
			j++;
			k++;
		}
	}

	/** count inversions between from[start..mid] and from[mid, end] */
	private int count(int[] from, int start, int mid, int end) {
		int totalInversion = 0;
		int j = mid;
		for (int i = start; i < mid; i++) {
			while (j < end && from[i] > 2L * from[j]) {
				j++;
			}
			totalInversion += j - mid;
		}
		return totalInversion;
	}
}
// @lc code=end
