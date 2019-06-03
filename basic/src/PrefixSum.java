import java.util.Arrays;
import java.util.List;

/** 1-based */
public class PrefixSum {

	public static void main(String[] args) {
		long[] a = new long[] { 1, 1, 2, 3, 5 };
		PrefixSum p = new PrefixSum(a);
		System.out.println("getGap");
		for (int i = 1; i <= a.length; i++) {
			for (int j = i; j <= a.length; j++) {
				System.out.println(i + ", " + j + "=" + p.getGapToMax(i, j));
			}
		}
		System.out.println("getSum");
		List<int[]> increments = Arrays.asList(new int[] { 1, 5, 1 }, new int[] { 3, 5, 1 }, new int[] { 4, 4, 1 },
				new int[] { 5, 5, 2 });
		p = new PrefixSum(5, increments);
		for (int i = 1; i <= a.length; i++) {
			for (int j = i; j <= a.length; j++) {
				System.out.println(i + ", " + j + "=" + p.getSum(i, j));
			}
		}
	}

	long[] prefSum;

	public PrefixSum(long[] a) {
		prefSum = new long[a.length + 1];
		for (int i = 1; i <= a.length; i++) {
			prefSum[i] += prefSum[i - 1] + a[i - 1];
		}
	}

	/* increments is 1-based */
	public PrefixSum(int n, List<int[]> increments) {
		prefSum = new long[n + 1];
		for (int i = 0; i < increments.size(); i++) {
			int start = increments.get(i)[0];
			int end = increments.get(i)[1];
			int inc = increments.get(i)[2];
			prefSum[start] = inc;
			if (end < n) {
				prefSum[end + 1] -= inc;
			}
		}

		long cur = 0;
		for (int i = 1; i <= n; i++) {
			cur += prefSum[i];
			prefSum[i] = prefSum[i - 1] + cur;
		}
	}

	/** 1-based */
	public long getSum(int start, int endInclusive) {
		return prefSum[endInclusive] - prefSum[start - 1];
	}

	/** assert: array is sorted */
	public long getGapToMax(int start, int endInclusive) {
		long vEnd = prefSum[endInclusive] - prefSum[endInclusive - 1];
		long rangeSum = (prefSum[endInclusive] - prefSum[start - 1]);
		return vEnd * (endInclusive - start + 1) - rangeSum;
	}

}
