package dp.backpack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/** O(n2^(n/2)) */
public class LargeZeroOneBackpack {

	public static void main(String[] args) {
		System.out.println(solveByBidirectionalSearch(10, new long[] { 2, 3, 5, 7 }, new long[] { 1, 5, 2, 4 })); // 9
	}

	/** size and value are both large, but n is small (n<=40) */
	public static long solveByBidirectionalSearch(long size_limit, long[] sizes, long[] values) {
		int N = sizes.length;
		List<long[]> firstHalf = allCombs(sizes, values, 0, N / 2);
		TreeMap<Long, Long> sizeToValue = new TreeMap<>();
		for (long[] pair : firstHalf) {
			long v = sizeToValue.getOrDefault(pair[0], 0L);
			sizeToValue.put(pair[0], Math.max(pair[1], v));
		}
		List<long[]> secondHalf = allCombs(sizes, values, N / 2 + 1, N - 1);
		long max = 0;
		for (long[] pair : secondHalf) {
			Entry<Long, Long> e = sizeToValue.floorEntry(size_limit - pair[0]);
			if (e != null) {
				max = Math.max(pair[1] + e.getValue(), max);
			}
		}
		return max;
	}

	static List<long[]> allCombs(long[] sizes, long[] values, int start, int end) {
		int n = end - start + 1;
		List<long[]> ret = new ArrayList<>();
		for (int i = 0; i < (1 << n); i++) {
			long s = 0;
			long v = 0;
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					s += sizes[start + j];
					v += values[start + j];
				}
			}
			ret.add(new long[] { s, v });
		}
		return ret;
	}
}
