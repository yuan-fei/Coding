import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of size n containing 0’s and 1’s only. The problem is to find
 * the length of the longest subarray having count of 1’s one more than count of
 * 0’s.
 * 
 * (https://www.geeksforgeeks.org/longest-subarray-count-1s-one-count-0s/)
 */
public class LongestSubArrayWithOneMoreThanZero {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 0, 1, 1, 0, 0, 1 }));
		System.out.println(solve(new int[] { 1, 0, 0, 1, 0 }));
	}

	public static int solve(int[] a) {
		int diff = 0;
		int maxLen = 0;
		Map<Integer, Integer> firstSeen = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			diff += a[i] == 0 ? -1 : 1;
			if (diff == 1) {
				maxLen = i + 1;
			} else {
				if (firstSeen.containsKey(diff - 1)) {
					maxLen = Math.max(maxLen, i - firstSeen.get(diff - 1));
				}
				if (!firstSeen.containsKey(diff)) {
					firstSeen.put(diff, i);
				}
			}
		}
		return maxLen;
	}
}
