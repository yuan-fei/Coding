package greedy;

import java.util.Arrays;

/**
 * Given a sorted positive integer array nums and an integer n, add/patch
 * elements to the array such that any number in range [1, n] inclusive can be
 * formed by the sum of some elements in the array. Return the minimum number of
 * patches required.
 * 
 * see
 * https://leetcode.com/problems/patching-array/discuss/78495/Share-my-thinking-process
 */
public class MinPatches {

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 3 };
		int n = 6;
		System.out.println(minPatches(nums, n)); // 1
		nums = new int[] { 1, 5, 10 };
		n = 20;
		System.out.println(minPatches(nums, n)); // 2
		nums = new int[] { 1, 2, 2 };
		n = 5;
		System.out.println(minPatches(nums, n)); // 0
	}

	public static int minPatches(int[] nums, int n) {
		long maxNumberReachable = 0;
		Arrays.sort(nums);
		int cnt = 0;
		for (int x : nums) {
			while (maxNumberReachable < x - 1) {
				maxNumberReachable += maxNumberReachable + 1;
				cnt++;
				if (maxNumberReachable >= n) {
					return cnt;
				}
			}
			maxNumberReachable += x;
			if (maxNumberReachable >= n) {
				return cnt;
			}
		}
		while (maxNumberReachable < n) {
			maxNumberReachable += maxNumberReachable + 1;
			cnt++;
		}
		return cnt;
	}
}
