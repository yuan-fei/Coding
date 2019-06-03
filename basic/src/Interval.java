import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Interval {
	public static void main(String[] args) {
		List<Interval> intvs = new ArrayList<Interval>(
				Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18)));
		List<Interval> newIntvs = merge(intvs);
		System.out.println(newIntvs);
		intvs = Arrays.asList(new Interval(1, 3), new Interval(6, 9));
		newIntvs = insert(intvs, new Interval(2, 5));
		System.out.println(newIntvs);
		Interval[] a = new Interval[] { new Interval(1, 3), new Interval(8, 10) };
		Interval[] b = new Interval[] { new Interval(2, 6), new Interval(15, 18) };
		System.out.println(Arrays.toString(merge(a, b)));
		System.out.println(Arrays.toString(merge(new Interval[0], new Interval[0])));
		System.out.println(Arrays.toString(merge(new Interval[0], a)));
	}

	public static Comparator<Interval> StartComparator = new Comparator<Interval>() {
		@Override
		public int compare(Interval o1, Interval o2) {
			int r = Integer.compare(o1.start, o2.start);
			if (r == 0) {
				r = Integer.compare(o1.end, o2.end);
			}
			return r;
		}
	};

	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}

	public static boolean isOverlap(Interval intv1, Interval intv2) {
		return (intv1.start <= intv2.end && intv1.end >= intv2.start);
	}

	public static Interval merge(Interval intv1, Interval intv2) {
		return new Interval(Math.min(intv1.start, intv2.start), Math.max(intv1.end, intv2.end));
	}

	public static Interval intersect(Interval intv1, Interval intv2) {
		return new Interval(Math.max(intv1.start, intv2.start), Math.min(intv1.end, intv2.end));
	}

	/** Merge a list of intervals in O(nlogn) */
	public static List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1) {
			return intervals;
		}
		intervals.sort(Interval.StartComparator);
		List<Interval> res = new ArrayList<Interval>();
		Interval last = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval cur = intervals.get(i);
			if (!isOverlap(last, cur)) {
				res.add(last);
				last = cur;
			} else {
				last = merge(last, cur);
			}
		}
		res.add(last);
		return res;
	}

	/** Merge two array of sorted intervals */
	public static Interval[] merge(Interval[] a, Interval[] b) {
		if (a.length == 0) {
			return b;
		}
		if (b.length == 0) {
			return a;
		}
		List<Interval> ret = new ArrayList<>();
		Interval last = null;
		int i, j;
		i = j = 0;
		while (i < a.length && j < b.length) {
			Interval smaller = (a[i].start < b[j].start) ? a[i++] : b[j++];
			if (last == null) {
				last = smaller;
			} else {
				if (isOverlap(last, smaller)) {
					last = merge(last, smaller);
				} else {
					// last < smaller
					ret.add(last);
					last = smaller;
				}
			}
		}
		while (i < a.length) {
			if (isOverlap(last, a[i])) {
				last = merge(last, a[i++]);
			} else {
				// last < smaller
				ret.add(last);
				last = a[i];
			}
		}
		while (j < b.length) {
			if (isOverlap(last, b[j])) {
				last = merge(last, b[j++]);
			} else {
				// last < smaller
				ret.add(last);
				last = b[j];
			}
		}
		if (last != null) {
			ret.add(last);
		}

		return ret.toArray(new Interval[0]);
	}

	/**
	 * O(n)
	 * 
	 * Given a non-overlap list of intervals sorted by start, insert a new interval
	 * and merge if necessary.
	 */
	public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> ret = new ArrayList<>();
		for (int i = 0; i < intervals.size(); i++) {
			Interval cur = intervals.get(i);
			if (newInterval.end < cur.start) {
				ret.add(newInterval);
				ret.addAll(intervals.subList(i, intervals.size()));
				return ret;
			} else if (newInterval.start > cur.end) {
				ret.add(cur);
			} else {
				newInterval = merge(newInterval, cur);
			}
		}
		ret.add(newInterval);
		return ret;
	}
}
