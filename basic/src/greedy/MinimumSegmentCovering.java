package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** Given n segments, find minimum # of segments that can cover all segments */
public class MinimumSegmentCovering {

	public static void main(String[] args) {
		int[] start = { 1, 2, 7, 6, 8 };
		int[] end = { 3, 5, 7, 9, 10 };
		System.out.println(Arrays.toString(select(start, end)));
	}

	static int[] select(int[] start, int[] end) {
		Segment[] s = new Segment[start.length];
		for (int i = 0; i < start.length; i++) {
			s[i] = new Segment(start[i], end[i], i);
		}
		Arrays.sort(s, Comparator.comparing(e -> e.start));
		List<Integer> selected = new ArrayList<Integer>();

		int candidate = -1;
		int maxRight = Integer.MIN_VALUE;
		int curRight = s[0].start;
		int i = 0;
		while (i < s.length) {
			if (s[i].start <= curRight) {
				if (s[i].end >= curRight && s[i].end >= maxRight) {
					candidate = s[i].index;
					maxRight = s[i].end;
				}
				i++;
			} else {
				if (maxRight >= curRight) {
					selected.add(candidate);
					curRight = maxRight + 1;
					candidate = -1;
				} else {
					return new int[0];
				}
			}
		}
		if (maxRight >= curRight) {
			selected.add(candidate);
			curRight = maxRight + 1;
			candidate = -1;
		}
		return selected.stream().mapToInt(e -> e.intValue()).toArray();
	}

	static class Segment {
		int start;
		int end;
		int index;

		public Segment(int start, int end, int index) {
			this.start = start;
			this.end = end;
			this.index = index;
		}

	}
}
