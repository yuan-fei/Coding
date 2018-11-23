import java.util.Arrays;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class OrderStatisticsWithRangeIncrement {

	public static void main(String[] args) {
		int[] start = new int[] { 7, -4 };
		int[] end = new int[] { 9, 5 };
		int[] increment = new int[] { 1, 1 };
		System.out.println(kthElement(start, end, increment, 1));
		System.out.println(kthElement(start, end, increment, 2));
		System.out.println(kthElement(start, end, increment, 3));
		System.out.println(kthElement(start, end, increment, 4));
		System.out.println(kthElement(start, end, increment, 5));
		System.out.println(kthElement(start, end, increment, 6));
		System.out.println(kthElement(start, end, increment, 7));
		System.out.println(kthElement(start, end, increment, 8));
		System.out.println(kthElement(start, end, increment, 9));
		System.out.println(kthElement(start, end, increment, 10));
		System.out.println(kthElement(start, end, increment, 11));

	}

	private static int kthElement(int[] start, int[] end, int[] increment, int k) {
		int[][] allIntervals = new int[start.length * 2][2];
		for (int i = 0; i < start.length; i++) {
			allIntervals[i][0] = start[i];
			allIntervals[i][1] = increment[i];
			allIntervals[start.length + i][0] = end[i] + 1;
			allIntervals[start.length + i][1] = -increment[i];
		}
		Arrays.sort(allIntervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}
				return Integer.compare(o1[0], o2[0]);
			}
		});

		TreeMap<Integer, int[]> prefixSum = new TreeMap<>();
		int begin = allIntervals[0][0];
		int cnt = allIntervals[0][1];
		int prefix = 0;
		prefixSum.put(prefix, new int[] { begin, cnt });
		for (int i = 1; i < allIntervals.length; i++) {
			prefix += (allIntervals[i][0] - begin) * cnt;
			cnt += allIntervals[i][1];
			begin = allIntervals[i][0];
			prefixSum.put(prefix, new int[] { begin, cnt });
		}

		k -= 1;
		Entry<Integer, int[]> e = prefixSum.floorEntry(k);
		prefix = e.getKey();
		int[] intv = e.getValue();
		return intv[0] + (k - prefix) / intv[1];
	}

}
